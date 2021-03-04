package com.example.formauthorization.Entities;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;
    AppDatabase database;

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class,
                "database").allowMainThreadQueries().build();
    }

}
