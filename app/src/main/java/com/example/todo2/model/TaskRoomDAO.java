package com.example.todo2.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskRoomDAO {

    @Insert  //Automatyczna kwerenda wystarczy
    public void insert(RoomTask pozycja);

    @Update //Automatyczna kwerenda wystarczy
    void update(RoomTask pozycja);

    @Query("SELECT * FROM task")
    List<RoomTask> getAll();


    @Query("DELETE FROM task WHERE _id = :id")
    abstract void deleteById(long id);

    @Delete
    void delete(RoomTask roomTask);
}
