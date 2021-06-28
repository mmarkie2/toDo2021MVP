package com.example.todo2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task")
public class RoomTask {
    public RoomTask(String name, String type, Date date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    public int _id;
    public String name;
    public String type;
    public Date date;

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}