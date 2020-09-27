package com.example.todo2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.todo2.Contract;
import com.example.todo2.R;
import com.example.todo2.model.AddTaskModel;

import com.example.todo2.presenter.MainScreenPresenter;
import com.example.todo2.presenter.AddTaskPresenter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, FailedAddTaskFragment.FailedAddTaskFragmentListener, Contract.presenterToAddTaskView {
    String TAG="debugLog";
    EditText nameEditText;
    Spinner typeSpinner;
    Button dateButton;
    Button submitButton;
    Button cancelButton;


    String  taskType=null;
    Calendar taskDate=null;
private Contract.addTaskViewToPresenter presenter;
    MainScreenPresenter mainScreenPresenter;
    @Override
    public void onInvalidInput(String message) {
        new FailedAddTaskFragment()
                .show(getSupportFragmentManager(), "FailedAddTaskFragment");
    }
    @Override
    public void onCorrectInput() {
finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //initializing views
        nameEditText=findViewById(R.id.nameEditText);


        typeSpinner=findViewById(R.id.typeSpinner);
        // Spinner click listener
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taskType = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        typeSpinner.setAdapter(dataAdapter);



        dateButton=findViewById(R.id.dateButton);
        dateButton.setOnClickListener(v ->  new DatePickerFragment()
        .show(getSupportFragmentManager(), "date picker")  );

        submitButton=findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> presenter.onSubmitClick(nameEditText.getText().toString(),this.taskType,this.taskDate)  );

        cancelButton=findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish()  );
        Contract.addTaskPresenterToModel model=new AddTaskModel();
presenter=new AddTaskPresenter(this,model);



    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        this.taskDate=c;
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        dateButton.setText(currentDateString);
    }

    @Override
    public void onRetry(boolean isRetry) {
        if (isRetry)
        {

        }
        else
        {
            finish();
        }
    }
}
