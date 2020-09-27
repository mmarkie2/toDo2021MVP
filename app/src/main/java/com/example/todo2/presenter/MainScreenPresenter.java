package com.example.todo2.presenter;

import android.util.Log;

import com.example.todo2.Contract;

import com.example.todo2.model.AddTaskModel;
import com.example.todo2.model.ModelApplication;
import com.example.todo2.model.TaskData;

import java.util.ArrayList;

import javax.inject.Inject;


public class MainScreenPresenter implements Contract.modelToAllTasksPresenter , Contract.mainScreenViewToPresenter {

    String TAG="debugLog";

Contract.presenterToMainScreenView view;


    public MainScreenPresenter(Contract.presenterToMainScreenView view) {
this.view =view;
    }


    @Override
    public void onResume() {

        view.showTasks( ModelApplication.getTaskDatas());

    }
}
