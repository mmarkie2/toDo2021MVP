package com.example.todo2.model;

import android.app.Application;

import java.util.ArrayList;

public class ModelApplication extends Application  {
  static ArrayList<TaskData> taskDatas=new ArrayList<>();

    public static ArrayList<TaskData> getTaskDatas()  {
//        if (taskDatas==null)
//        {
//            throw new Exception("TaskDatas is null");
//        }
        return taskDatas;
    }

    public static void setTaskDatas(ArrayList<TaskData> taskDatas) {
        ModelApplication.taskDatas = taskDatas;
    }
}
