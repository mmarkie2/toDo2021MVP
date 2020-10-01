package com.example.todo2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.todo2.Contract;
import com.example.todo2.R;
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
    public void showTasks(ArrayList<TaskData> taskDatas) {
        //shows hint if there are no tasks
        if (taskDatas.size() == 0) {
            noTasksView.setVisibility(View.VISIBLE);
        } else {
            noTasksView.setVisibility(View.INVISIBLE);
        }
        //sets up RV adapter
        tasksRecyclerViewAdapter = new TasksRecyclerViewAdapter(this, taskDatas, new RecyclerDeleteButtonClickListener() {
            @Override
            public void onPositionClicked(int position) {
                presenter.onItemDelete(position);
            }
        });

        recyclerView.setAdapter(tasksRecyclerViewAdapter);


    }

    @Override
    public void goToAddTaskActivity() {
        Intent intent = new Intent(this, AddTaskActivity.class);

        startActivity(intent);
    }


}
