package com.example.todo2.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelper {
    private  String TAG="debugLog";


    String assetsDBPath;

    File storageDirectory;

    Context context;
    final String databaseName;
    private File outFile;

    public File getOutFile() {
        return outFile;
    }

    public FileHelper(Context context, final String databaseName, File storageDirectory) {

        this.context = context;
        this.databaseName=databaseName;
        this.storageDirectory = storageDirectory ;

        if(android.os.Build.VERSION.SDK_INT >= 17){
            this.assetsDBPath = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            this.assetsDBPath = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.storageDirectory.mkdir();
         outFile = new File(this.storageDirectory.getPath(),databaseName);
    }


    public void copyFile() {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(databaseName);
           ;

            OutputStream out = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);
            }

        } catch (Exception e) {
          Log.d(TAG,e.getMessage())  ;
        }
    }
}
