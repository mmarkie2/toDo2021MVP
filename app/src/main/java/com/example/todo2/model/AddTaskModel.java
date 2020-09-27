package com.example.todo2.model;

import com.example.todo2.Contract;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddTaskModel implements Contract.addTaskPresenterToModel {

    ArrayList<TaskData> taskDatas;


    @Inject
    public AddTaskModel() {
        this.taskDatas = new ArrayList<>();
    }

    @Override
    public void saveTask(TaskData taskData) {
        this.taskDatas.add(taskData);
        int id=-1;
        ModelApplication.getTaskDataWithIds().add(new TaskDataWithId(taskData,id));
    }


}
