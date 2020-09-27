package com.example.todo2;

import android.content.Intent;
import android.os.Bundle;


import com.example.todo2.model.ModelApplication;
import com.example.todo2.model.TaskData;
import com.example.todo2.presenter.MainScreenPresenter;
import com.example.todo2.view.AddTaskActivity;
import com.example.todo2.view.ClickListener;
import com.example.todo2.view.TasksRecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Contract.presenterToMainScreenView {

    String TAG="debugLog";
    com.example.todo2.view.TasksRecyclerView tasksRecyclerView;


    private Contract.mainScreenViewToPresenter presenter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             goToAddTaskActivity();

            }
        });



        // set up the RecyclerView
         recyclerView = findViewById(R.id.rvTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


presenter= new MainScreenPresenter(this);




    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() main");
        presenter.onResume();

    }

    @Override
    public void showTasks(ArrayList<TaskData> taskDatas) {
        for (TaskData taskData: taskDatas)
        {
            Log.d(TAG,taskData.getInfo());
        }
        tasksRecyclerView = new TasksRecyclerView(this, taskDatas, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                presenter.onItemDelete(position);
            }
        });

        recyclerView.setAdapter(tasksRecyclerView );

    }

    void goToAddTaskActivity()
    {
        Intent intent = new Intent(this, AddTaskActivity.class);

        startActivity(intent);
    }




}
