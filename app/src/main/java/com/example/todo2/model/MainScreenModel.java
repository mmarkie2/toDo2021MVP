package com.example.todo2.model;

import android.content.Context;

import androidx.room.Room;

import com.example.todo2.Contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainScreenModel implements Contract.presenterToMainScreenModel {
    private RoomDatabaseToDo roomDatabaseToDo;

    public MainScreenModel(Context context) {
        this.roomDatabaseToDo = Room.databaseBuilder(context, RoomDatabaseToDo.class, RoomDatabaseToDo.name)
                .allowMainThreadQueries().build();;
    }

    //queering ids for deleting purpose
    @Override
    public ArrayList<RoomTask> queryTasks() {

       return new ArrayList<RoomTask> (roomDatabaseToDo.roomDAO().getAll());


    }

    @Override
    public void deleteTask(int taskId) {
        roomDatabaseToDo.roomDAO().deleteById( taskId);
    }

    @Override
    public void saveTask(TaskData taskData) {



        roomDatabaseToDo.roomDAO().insert(new RoomTask(taskData.getName(), taskData.getType(),
                new Date( taskData.getYear(),taskData.getMonth(),taskData.getDayOfMonth())));
    }
}
