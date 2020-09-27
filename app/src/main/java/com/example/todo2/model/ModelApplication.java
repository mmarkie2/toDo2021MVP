package com.example.todo2.model;

import android.app.Application;

import java.util.ArrayList;

public class ModelApplication extends Application  {
  static ArrayList<TaskDataWithId> taskDataWithIds =new ArrayList<>();

    public static ArrayList<TaskDataWithId> getTaskDataWithIds()  {
//        if (taskDataWithIds==null)
//        {
//            throw new Exception("TaskDatas is null");
//        }
        return taskDataWithIds;
    }

    public static void setTaskDataWithIds(ArrayList<TaskDataWithId> taskDataWithIds) {
        ModelApplication.taskDataWithIds = taskDataWithIds;
    }
}
