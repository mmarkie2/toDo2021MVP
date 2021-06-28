package com.example.todo2.presenter;

import com.example.todo2.Contract;
import com.example.todo2.model.TaskData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddTaskPresenter implements Contract.addTaskViewToPresenter {

    private final Contract.presenterToAddTaskView view;


    public AddTaskPresenter(Contract.presenterToAddTaskView view) {
        this.view = view;

    }

    @Override
    public void onSubmitClick(String name, String type, Calendar date) {


        String errorMessage = getErrorMessageIfInvalid(name, type, date);
        if (errorMessage == null) {

            view.onCorrectInput( name, type, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        } else {
            view.onInvalidInput(errorMessage);
        }
    }

    //returns available to chose types for spinner
    @Override
    public List<String> getTypes() {
        return new ArrayList<String>(Arrays.asList("Praca", "Zakupy", "Inne"));
    }

    String getErrorMessageIfInvalid(String name, String type, Calendar date) {
        if (name.equals("")) {

            return "Nazwa nie może być pusta.";
        } else if (type == null) {
            return "Musisz podać typ.";
        } else if (date == null) {
            return "Musisz podać datę.";
        } else {
            return null;
        }
    }

}
