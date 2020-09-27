package com.example.todo2.presenter;

import com.example.todo2.Contract;
import com.example.todo2.model.TaskData;

import java.util.Calendar;

public class AddTaskPresenter implements Contract.addTaskViewToPresenter {
    String TAG="debugLog";
Contract.presenterToAddTaskView view;
Contract.addTaskPresenterToModel model;

    public AddTaskPresenter(Contract.presenterToAddTaskView view, Contract.addTaskPresenterToModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onSubmitClick(String name, String type, Calendar date) {

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, 2020);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        date=c;
        if (isTaskInfoValid(name,type,date))
        {
            model.saveTask(new TaskData
                    (name,type,date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)));
            view.  onCorrectInput();
        }
        else
        {
view.onInvalidInput("invalid input");
        }
    }



    boolean isTaskInfoValid(String name, String type, Calendar date)
{


    if (name!=null && type!=null && date!=null)
    {
        return true;
    }
    else
    {
        return false;
    }
}

}
