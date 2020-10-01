package com.example.todo2.model;

//class storing one task data with id which is database primary key
public class TaskDataWithId extends TaskData {
    private final int id;

    public TaskDataWithId(int id, String name, String type, int year, int month, int dayOfMonth) {
        super(name, type, year, month, dayOfMonth);
        this.id = id;
    }

    public TaskDataWithId(TaskData data, int id) {
        super(data.getName(), data.getType(), data.getYear(), data.getMonth(), data.getDayOfMonth());
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
