package com.example.avtorizationwindow.Entities.ClientData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ClientDataDao {
    @Query("Select * from ClientData")
    List<ClientData> GetAll();

    @Query("Select * from ClientData Where Login = :Login")
    ClientData GetByLogin(String Login);

    @Insert
    void Insert(ClientData clientData);

    @Update
    void Update(ClientData clientData);

    @Delete
    void Delete(ClientData clientData);
}
