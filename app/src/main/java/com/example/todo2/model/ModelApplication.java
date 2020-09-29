package com.example.todo2.model;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.util.ArrayList;

public class ModelApplication extends Application {
    static ArrayList<TaskDataWithId> taskDataWithIds = new ArrayList<>();
    private static String TAG = "debugLog";
    static private DatabaseHelper databaseHelper;

    public static void setDatabaseHelper(Context ctx, String dbFileName, File storageDirectory, boolean forceNewDBGeneration) {


        FileHelper fileHelper = new FileHelper(ctx, "tasks.db", storageDirectory);
        if (!fileHelper.getOutFile().exists() || forceNewDBGeneration) {
            fileHelper.copyFile();
        }

        ModelApplication.databaseHelper = new DatabaseHelper(ctx, dbFileName, storageDirectory);


    }

    public static DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    static public void testDB() {
//        ContentValues values = new ContentValues();
//        values.put("name", "pracadomowa");
//        values.put("type", "szkola");
//
//        databaseHelper .insertIntoTable("tasks",values);
//
//
//        ArrayList<ArrayList<String>> ret=new ArrayList<>();
//
//        ret=databaseHelper .selectFromTable("tasks","name");
//        Log.d(TAG,ret.get(0).get(0));
    }

    public static ArrayList<TaskDataWithId> getTaskDataWithIds() {

        return taskDataWithIds;
    }


}
