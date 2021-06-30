package com.example.todo2.viewDagger;

import android.content.Context;

import com.example.todo2.Contract;
import com.example.todo2.view.MainScreenActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenActivityModule {
    private final MainScreenActivity mainScreenActivity;
    private final Context context;

    public MainScreenActivityModule(MainScreenActivity mainScreenActivity) {
        this.mainScreenActivity = mainScreenActivity;
        this.context = mainScreenActivity.getApplicationContext();
    }

    @Provides
    Contract.presenterToMainScreenView providePresenterToMainScreenView() {
        return this.mainScreenActivity;
    }

    @Provides
    public Context context() {
        return context;
    }
}
