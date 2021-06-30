package com.example.todo2.presenterDagger;

import com.example.todo2.view.AddTaskActivity;
import com.example.todo2.viewDagger.AddTaskActivityModule;

import dagger.Component;

@Component(modules = {AddTaskActivityModule.class, AddTaskPresenterModule.class})
public interface AddTaskPresenterComponent {

    void inject(AddTaskActivity addTaskActivity);
}
