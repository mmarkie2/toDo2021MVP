package com.example.todo2.viewDagger;
//

import android.content.Context;

import com.example.todo2.model.RoomTask;

import com.example.todo2.view.RecyclerDeleteButtonClickListener;
import com.example.todo2.view.TasksRecyclerViewAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksRecyclerViewAdapterModule {
    Context context;
    List<RoomTask> roomTasks;
    RecyclerDeleteButtonClickListener listener;

    public TasksRecyclerViewAdapterModule(Context context, List<RoomTask> roomTasks, RecyclerDeleteButtonClickListener listener) {
        this.context = context;
        this.roomTasks = roomTasks;
        this.listener = listener;
    }

    @Provides
    TasksRecyclerViewAdapter provideTaskRecyclerViewAdapter() {
        return new TasksRecyclerViewAdapter(this.context, this.roomTasks, this.listener);
    }

}
