package com.example.todo2.model;

import android.content.Context;

import androidx.room.Room;

import com.example.todo2.Contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainScreenModel implements Contract.presenterToMainScreenModel {
    private RoomDatabaseToDo roomDatabaseToDo;

    @Inject
    public MainScreenModel(Context context) {
        this.roomDatabaseToDo = Room.databaseBuilder(context, RoomDatabaseToDo.class, RoomDatabaseToDo.name)
                .allowMainThreadQueries().build();

    }


    @Override
    public ArrayList<RoomTask> queryTasks() {

        return new ArrayList<RoomTask>(roomDatabaseToDo.roomDAO().getAll());

    }

    @Override
    public void deleteTask(RoomTask roomTask) {
        roomDatabaseToDo.roomDAO().delete(roomTask);
    }

    @Override
    public void saveTask(RoomTask roomTask) {

        roomDatabaseToDo.roomDAO().insert(roomTask);
    }
}
