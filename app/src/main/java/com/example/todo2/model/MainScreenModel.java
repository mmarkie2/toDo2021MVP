package com.example.todo2.model;

import com.example.todo2.Contract;

import java.util.ArrayList;

public class MainScreenModel implements Contract.presenterToMainScreenModel {

    @Override
    public ArrayList<TaskDataWithId> queryTasks() {
        ArrayList<ArrayList<String>> query =ModelApplication.getDatabaseHelper().selectFromTable
                ("tasks","id, name, type, year, month, dayOfMonth");
        ArrayList<TaskDataWithId> ret=new ArrayList<>();
        for (ArrayList<String> row:query)
        {
ret.add(new TaskDataWithId(Integer.parseInt(row.get(0)),row.get(1),row.get(2),Integer.parseInt(row.get(3)),Integer.parseInt(row.get(4)),Integer.parseInt(row.get(5))));
        }
        return ret;
    }

    @Override
    public void deleteTask(int taskId) {
        ModelApplication.getDatabaseHelper().deleteFromTable("tasks",taskId);
    }
}
