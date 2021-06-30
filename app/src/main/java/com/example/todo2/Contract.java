package com.example.todo2;

import android.app.Activity;
import android.content.Context;

import com.example.todo2.model.RoomTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface Contract {

    interface addTaskViewToPresenter {
        void onSubmitClick(String name, String type, Calendar date);

        List<String> getTypes();
    }

    interface presenterToAddTaskView {
        void onInvalidInput(String message);

        void onCorrectInput(String name, String type, int year, int month, int dayOfMonth);

        Context getContext();
    }


    interface mainScreenViewToPresenter {

        void onResume();

        void onItemDelete(int position);

        void onAddButtonClick();

        void saveTask(String name, String type, int year, int month, int dayOfMonth);

    }

    interface presenterToMainScreenView {
        void showTasks(ArrayList<RoomTask> roomTasks);

        Activity getActivity();

        void goToAddTaskActivity();
    }

    interface presenterToMainScreenModel {
        ArrayList<RoomTask> queryTasks();

        void deleteTask(RoomTask roomTask);

        void saveTask(RoomTask roomTask);
    }

}
