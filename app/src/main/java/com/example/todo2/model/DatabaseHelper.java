package com.example.todo2.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase DBClass;
    private final String DBName;
    private static final int databaseVersion = 1;
    Context context;
    String DBDirectoryPath;
    private static String TAG = "debugLog"; // Tag just for the LogCat window

    public DatabaseHelper(Context context, final String databaseName) {

        super(context, databaseName, null, databaseVersion);
        this.DBName = databaseName;
        this.context = context;

        if(android.os.Build.VERSION.SDK_INT >= 17){
            this.DBDirectoryPath = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            this.DBDirectoryPath = "/data/data/" + context.getPackageName() + "/databases/";
        }

        try
        {
            this.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e("myTag", mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        this.openDataBase();

    }
    public DatabaseHelper(Context context, final String databaseName,File storageDirectory) {

        super(context, databaseName, null, databaseVersion);
        this.DBName = databaseName;
        this.context = context;
        this.DBDirectoryPath =storageDirectory.getPath();


        this.openDataBase();

    }
    public void createDataBase() throws IOException
    {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if(mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
        else
        {
            throw new Error("DB does not exist.");
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DBDirectoryPath + DBName);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }


    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = context.getAssets().open(DBName);
        String outFileName = DBDirectoryPath + DBName;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException
    {
        String mPath = DBDirectoryPath +"/"+ DBName;
        Log.d(TAG,"DB opened  "+mPath);
        //Log.v("mPath", mPath);
        DBClass = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return DBClass != null;
    }

    @Override
    public synchronized void close()
    {
        if(DBClass != null)
            DBClass.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertIntoTable(String tableName, ContentValues values) {

        return  this.DBClass .insert(tableName, null, values);
    }
    public long deleteFromTable(String tableName, int id) {

        return  this.DBClass .delete(tableName, "id="+id, null);
    }
    public ArrayList<ArrayList<String>> selectFromTable(String tableName, String columnsStr) {
        String query1 = "SELECT " + columnsStr + " FROM " + tableName;

        Cursor cursor = DBClass.rawQuery(query1, null);
        ArrayList<ArrayList<String>> returnedArray=new ArrayList<ArrayList<String>>();
        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> buf=new  ArrayList<String>();
                for(int i=0;i< cursor.getColumnCount();++i)
                {
                    String cell = cursor.getString(i);
//                   Log.d("myTag", "bnklog: "+cell);
                    buf.add(cell);
                }
                returnedArray.add(buf);

            } while (cursor.moveToNext());
        }
        return returnedArray;
    }
}