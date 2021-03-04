package com.example.avtorizationwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.avtorizationwindow.Entities.App;
import com.example.avtorizationwindow.Entities.AppDataBase;
import com.example.avtorizationwindow.Entities.ClientData.ClientData;
import com.example.avtorizationwindow.Entities.ClientData.ClientDataDao;

import java.util.List;

public class TAbleClient extends AppCompatActivity {

    AppDataBase database;
    ClientDataDao clientDataDao;
    List<ClientData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_able_client);

        database = App.getInstance().getDatabase();
        clientDataDao = database.clientDataDao();

        list = clientDataDao.GetAll();
    }
//cgoj;kvhjhjk;vhjkkgvhj
}