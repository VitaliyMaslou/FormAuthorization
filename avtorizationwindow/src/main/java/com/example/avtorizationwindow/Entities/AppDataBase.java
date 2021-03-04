package com.example.avtorizationwindow.Entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.avtorizationwindow.Entities.ClientData.ClientData;
import com.example.avtorizationwindow.Entities.ClientData.ClientDataDao;

@Database(entities = {ClientData.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ClientDataDao clientDataDao();
}
