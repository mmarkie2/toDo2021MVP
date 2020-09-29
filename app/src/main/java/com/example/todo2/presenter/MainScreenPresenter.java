package com.example.todo2.presenter;

import android.Manifest;
import android.os.Environment;

import com.example.todo2.Contract;
import com.example.todo2.model.MainScreenModel;
import com.example.todo2.model.ModelApplication;
import com.example.todo2.model.RuntimePermissionHelper;
import com.example.todo2.model.TaskData;
import com.example.todo2.model.TaskDataWithId;

import java.io.File;
import java.util.ArrayList;


public class MainScreenPresenter implements Contract.mainScreenViewToPresenter {



    Contract.presenterToMainScreenView view;
    Contract.presenterToMainScreenModel model;
    ArrayList<TaskDataWithId> currentTaskDataWithIds;

    public MainScreenPresenter(Contract.presenterToMainScreenView view) {
        this.view = view;
        model = new MainScreenModel();
        currentTaskDataWithIds = new ArrayList<>();

        askForPermissionAndInitializeDBIfAvailable();
    }

boolean askForPermissionAndInitializeDBIfAvailable()
{
    //requests storage permission
    ArrayList<String> requiredPermissions = new ArrayList<String>();
    requiredPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    requiredPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
    RuntimePermissionHelper runtimePermissionHelper = new RuntimePermissionHelper(view.getActivity (),requiredPermissions);

    if (!runtimePermissionHelper.isAllPermissionAvailable()) {
        runtimePermissionHelper.requestPermissionsIfDenied();
return false;
    }
    //triggered when user gives permission to storage
    else if (ModelApplication.getDatabaseHelper()==null)
    {
        File storageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + "mmarkiToDoApp" + File.separator);


        ModelApplication.setDatabaseHelper(view.getActivity (), "tasks.db", storageFile,true);
        return true;
    }
    else
    {
        return true;
    }
}
    @Override
    public void onResume() {

if(ModelApplication.getDatabaseHelper()!=null)
{
    currentTaskDataWithIds = model.queryTasks();
    view.showTasks(new ArrayList<TaskData>(currentTaskDataWithIds));
}


    }

    @Override
    public void onItemDelete(int position) {
        TaskDataWithId toDelete = currentTaskDataWithIds.get(position);
        //gets id of task because not always position== id
        model.deleteTask(toDelete.getId());
        //onResume called to show updated list
        this.onResume();

    }

    @Override
    public void onAddButtonClick() {
//checks if storage is available, otherwise tasks can not be stored
      if(  askForPermissionAndInitializeDBIfAvailable())
      {
          view.goToAddTaskActivity();
      }


    }
}
