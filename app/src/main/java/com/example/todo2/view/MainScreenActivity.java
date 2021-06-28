package com.example.todo2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.todo2.Contract;
import com.example.todo2.R;
import com.example.todo2.model.RoomTask;
import com.example.todo2.model.TaskData;
import com.example.todo2.presenter.MainScreenPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainScreenActivity extends AppCompatActivity implements Contract.presenterToMainScreenView {


    private   TasksRecyclerViewAdapter tasksRecyclerViewAdapter;
    private    RecyclerView recyclerView;
    private    TextView noTasksView;
    private Contract.mainScreenViewToPresenter presenter;
    private int addTaskActivityLaunchCode = 1;
    private final String TAG = "debugLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views
        noTasksView = findViewById(R.id.noTasksView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddButtonClick();

            }
        });


        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        presenter = new MainScreenPresenter(this);


    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();

    }

    @Override
   public void showTasks(ArrayList<RoomTask> roomTasks) {
        //shows hint if there are no tasks
        if (roomTasks.size() == 0) {
            noTasksView.setVisibility(View.VISIBLE);
        } else {
            noTasksView.setVisibility(View.INVISIBLE);
        }
        //sets up RV adapter
        tasksRecyclerViewAdapter = new TasksRecyclerViewAdapter(this, roomTasks, new RecyclerDeleteButtonClickListener() {
            @Override
            public void onPositionClicked(int position) {
                presenter.onItemDelete(position);
            }
        });

        recyclerView.setAdapter(tasksRecyclerViewAdapter);


    }

    @Override
    public void goToAddTaskActivity() {
        Intent intent = new Intent(this,AddTaskActivity.class);
        startActivityForResult(intent, addTaskActivityLaunchCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == addTaskActivityLaunchCode) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");

                presenter.saveTask( data. getStringExtra("name"), data. getStringExtra("type")
                        ,  data. getIntExtra("year",1),   data. getIntExtra("month",1),
                        data. getIntExtra("dayOfMonth",1));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    } //onActivityResult


}
