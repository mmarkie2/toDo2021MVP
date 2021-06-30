package com.example.todo2.presenter;

import com.example.todo2.Contract;
import com.example.todo2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class AddTaskPresenter implements Contract.addTaskViewToPresenter {

    private final Contract.presenterToAddTaskView view;

    @Inject
    public AddTaskPresenter(Contract.presenterToAddTaskView view) {
        this.view = view;
    }

    @Override
    public void onSubmitClick(String name, String type, Calendar date) {


        String errorMessage = getErrorMessageIfInvalid(name, type, date);
        if (errorMessage == null) {

            view.onCorrectInput(name, type, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        } else {
            view.onInvalidInput(errorMessage);
        }
    }

    //returns available to chose types for spinner
    @Override
    public List<String> getTypes() {
        return new ArrayList<String>(Arrays.asList(view.getContext().getResources().getStringArray(R.array.types_array)));
    }

    String getErrorMessageIfInvalid(String name, String type, Calendar date) {
        //returns error if field is empty
        if (name.equals("")) {
            return this.view.getContext().getString(R.string.error_empty_name);
        } else if (type == null) {
            return this.view.getContext().getString(R.string.error_empty_type);
        } else if (date == null) {
            return this.view.getContext().getString(R.string.error_empty_date);
        } else {
            return null;
        }
    }

}
