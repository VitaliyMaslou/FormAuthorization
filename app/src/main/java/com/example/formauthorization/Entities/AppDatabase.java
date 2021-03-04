package com.example.formauthorization.Entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.formauthorization.Entities.Users.Users;
import com.example.formauthorization.Entities.Users.UsersDao;

@Database(entities = {Users.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();
}
