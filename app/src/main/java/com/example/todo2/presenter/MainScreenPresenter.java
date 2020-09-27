package com.example.todo2.presenter;

import com.example.todo2.Contract;

import com.example.todo2.model.ModelApplication;
import com.example.todo2.model.TaskData;
import com.example.todo2.model.TaskDataWithId;

import java.util.ArrayList;


public class MainScreenPresenter implements Contract.modelToAllTasksPresenter , Contract.mainScreenViewToPresenter {

    String TAG="debugLog";

Contract.presenterToMainScreenView view;
ArrayList<TaskDataWithId> currentTaskDataWithIds;

    public MainScreenPresenter(Contract.presenterToMainScreenView view) {
this.view =view;
        currentTaskDataWithIds=new ArrayList<>();
    }


    @Override
    public void onResume() {
        currentTaskDataWithIds= ModelApplication.getTaskDataWithIds();
        view.showTasks(new ArrayList<TaskData>(currentTaskDataWithIds));

    }

    @Override
    public void onItemDelete(int position) {

    }
}
