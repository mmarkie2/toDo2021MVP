package com.example.todo2.model;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component
public interface DatabaseModelComponent {
    AddTaskModel getDatabaseModel();
}


