package com.example.todo2.model;

public class TaskDataWithId extends TaskData {
    int id;

    public TaskDataWithId(String name, String type, int year, int month, int dayOfMonth, int id) {
        super(name, type, year, month, dayOfMonth);
        this.id = id;
    }
    public TaskDataWithId(TaskData data, int id) {
        super(data.getName(), data.getType(),data.getYear(), data.getMonth(),  data.getDayOfMonth());
        this.id = id;
    }
}
