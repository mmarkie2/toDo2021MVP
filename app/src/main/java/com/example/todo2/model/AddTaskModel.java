package com.example.todo2.model;

import android.content.ContentValues;

import com.example.todo2.Contract;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddTaskModel implements Contract.addTaskPresenterToModel {
//On purpose spelling typo to indicate that field is a list by "s" (;
    ArrayList<TaskData> taskDatas;


    @Inject
    public AddTaskModel() {
        this.taskDatas = new ArrayList<>();
    }

    @Override
    public void saveTask(TaskData taskData) {
        this.taskDatas.add(taskData);
        ContentValues values = new ContentValues();
        values.put("name", taskData.getName());
        values.put("type", taskData.getType());
        values.put("year", taskData.getYear());
        values.put("month", taskData.getMonth());
        values.put("dayOfMonth", taskData.getDayOfMonth());
        ModelApplication.getDatabaseHelper().insertIntoTable("tasks", values);
    }


}
