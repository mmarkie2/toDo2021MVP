package com.example.todo2.presenter;

import com.example.todo2.Contract;
import com.example.todo2.model.MainScreenModel;
import com.example.todo2.model.RoomTask;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;


public class MainScreenPresenter implements Contract.mainScreenViewToPresenter {


    private final Contract.presenterToMainScreenView view;

    private final Contract.presenterToMainScreenModel model;
    private ArrayList<RoomTask> currentTasks;

    @Inject
    public MainScreenPresenter(Contract.presenterToMainScreenView view, Contract.presenterToMainScreenModel model) {
        this.view = view;
        this.model = model;

    }

    @Override
    public void onResume() {

        currentTasks = model.queryTasks();
        view.showTasks(currentTasks);
    }

    @Override
    public void onItemDelete(int position) {
        RoomTask roomTask = currentTasks.get(position);
        model.deleteTask(roomTask);
        //onResume called to show updated list
        this.onResume();

    }

    @Override
    public void onAddButtonClick() {

        view.goToAddTaskActivity();

    }

    @Override
    public void saveTask(String name, String type, int year, int month, int dayOfMonth) {


        model.saveTask(new RoomTask(name, type,
                new Date(year - 1900// "- 1900" to make date proper
                        , month, dayOfMonth)));
        this.onResume();
    }
}
