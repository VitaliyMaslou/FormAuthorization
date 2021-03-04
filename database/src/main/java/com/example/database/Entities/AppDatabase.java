package com.example.database.Entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.database.Entities.Avtorisation.Avtorisation;
import com.example.database.Entities.Avtorisation.AvtorisationDao;

@Database(entities = {Avtorisation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract AvtorisationDao avtorisationDao();
}






