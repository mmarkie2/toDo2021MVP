package com.example.todo2;

import com.example.todo2.model.TaskData;

import java.util.ArrayList;
import java.util.Calendar;

public interface Contract {

    interface addTaskViewToPresenter
    {
void onSubmitClick(String name, String type, Calendar date);

    }
    interface presenterToAddTaskView
    {
        void onInvalidInput(String message);
        void onCorrectInput();
    }
    interface addTaskPresenterToModel
    {
        void saveTask(TaskData taskData);

    }
    interface modelToAllTasksPresenter
    {


    }
    interface mainScreenViewToPresenter
    {

void onResume();
void onItemDelete(int position);
    }

    interface presenterToMainScreenView
    {
        void showTasks(ArrayList<TaskData> taskDatas);
    }

}
