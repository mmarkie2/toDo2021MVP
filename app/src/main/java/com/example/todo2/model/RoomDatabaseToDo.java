package com.example.todo2.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {RoomTask.class}, version = RoomDatabaseToDo.version, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RoomDatabaseToDo extends RoomDatabase {

    public static final String name = "ToDo";
    public static final int version = 1;

    public abstract TaskRoomDAO roomDAO();
}
