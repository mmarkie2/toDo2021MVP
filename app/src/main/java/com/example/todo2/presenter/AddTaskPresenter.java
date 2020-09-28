package com.example.todo2.presenter;

import com.example.todo2.Contract;
import com.example.todo2.model.TaskData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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
        String errorMessage=isTaskInfoValid(name,type,date);
        if (errorMessage==null)
        {
            model.saveTask(new TaskData
                    (name,type,date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)));
            view.  onCorrectInput();
        }
        else
        {
view.onInvalidInput(errorMessage);
        }
    }

    @Override
    public List<String> getTypes()  {
        return new ArrayList<String>(Arrays.asList("Praca","Zakupy","Inne"));
    }

    String isTaskInfoValid(String name, String type, Calendar date)
{
if(name.equals(""))
{

    return "Nazwa nie może być pusta.";
}
else if ( type==null)
{
    return  "Musisz podać typ.";
}
   else if ( date==null)
    {
        return  "Musisz podać datę.";
    }
    else
    {
        return null;
    }
}

}
