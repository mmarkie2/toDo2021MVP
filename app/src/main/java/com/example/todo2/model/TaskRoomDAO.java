package com.example.todo2.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskRoomDAO {

    @Insert
    public void insert(RoomTask roomTask);


    @Query("SELECT * FROM task ORDER BY date")
    List<RoomTask> getAll();


    @Delete
    void delete(RoomTask roomTask);
}
