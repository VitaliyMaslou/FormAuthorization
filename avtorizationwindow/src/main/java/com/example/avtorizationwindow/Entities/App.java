package com.example.avtorizationwindow.Entities;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;
    AppDataBase database;

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class,
                "DBUsers").allowMainThreadQueries().build();
    }

}
