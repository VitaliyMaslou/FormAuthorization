package com.example.database.Entities.Avtorisation;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface AvtorisationDao {
    @Query("Select * from avtorisation")
    List<Avtorisation> GetAll();

    @Query("Select * from Avtorisation Where Login = :Login")
    Avtorisation GetByLogin(String Login);

    @Insert
    void Insert(Avtorisation avtorisation);

    @Update
    void Update(Avtorisation avtorisation);

    @Delete
    void Delete(Avtorisation avtorisation);
}




