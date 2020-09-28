package com.example.todo2.presenter;

import com.example.todo2.Contract;

import com.example.todo2.model.MainScreenModel;
import com.example.todo2.model.ModelApplication;
import com.example.todo2.model.TaskData;
import com.example.todo2.model.TaskDataWithId;

import java.util.ArrayList;


public class MainScreenPresenter implements  Contract.mainScreenViewToPresenter {

    String TAG="debugLog";

Contract.presenterToMainScreenView view;
Contract.presenterToMainScreenModel model;
ArrayList<TaskDataWithId> currentTaskDataWithIds;

    public MainScreenPresenter(Contract.presenterToMainScreenView view) {
this.view =view;
model=new MainScreenModel();
        currentTaskDataWithIds=new ArrayList<>();
    }


    @Override
    public void onResume() {


        currentTaskDataWithIds=  model.queryTasks();
        view.showTasks(new ArrayList<TaskData>(currentTaskDataWithIds));

    }

    @Override
    public void onItemDelete(int position) {
        TaskDataWithId toDelete=currentTaskDataWithIds.get(position);
model.deleteTask(toDelete.getId());
this.onResume();

    }
}
