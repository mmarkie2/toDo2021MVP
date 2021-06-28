package com.example.todo2.presenter;

import com.example.todo2.Contract;
import com.example.todo2.model.MainScreenModel;
import com.example.todo2.model.RoomTask;
import com.example.todo2.model.TaskData;
import com.example.todo2.model.TaskDataWithId;

import java.util.ArrayList;


public class MainScreenPresenter implements Contract.mainScreenViewToPresenter {


    private final Contract.presenterToMainScreenView view;
    private final Contract.presenterToMainScreenModel model;
    private    ArrayList<RoomTask> currentTasks;

    public MainScreenPresenter(Contract.presenterToMainScreenView view) {
        this.view = view;
        model = new MainScreenModel(view.getActivity().getApplicationContext());



    }



    @Override
    public void onResume() {

            currentTasks = model.queryTasks();
            view.showTasks(currentTasks);


    }

    @Override
    public void onItemDelete(int position) {
       RoomTask roomTask = currentTasks.get(position);
        //gets id of task because not always position== id
        model.deleteTask(roomTask.get_id());
        //onResume called to show updated list
        this.onResume();

    }

    @Override
    public void onAddButtonClick() {

            view.goToAddTaskActivity();


    }
    @Override
    public  void saveTask(String name, String type, int year, int month, int dayOfMonth)
    {
   model.saveTask(new TaskData(name, type,  year,  month,  dayOfMonth));
this.onResume();
    }
}
