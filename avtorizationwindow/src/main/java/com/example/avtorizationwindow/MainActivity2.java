package com.example.avtorizationwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.avtorizationwindow.Entities.App;
import com.example.avtorizationwindow.Entities.AppDataBase;
import com.example.avtorizationwindow.Entities.ClientData.ClientDataDao;

public class MainActivity2 extends AppCompatActivity {
    ClientDataDao clientDataDao;
    AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = App.getInstance().getDatabase();
        clientDataDao = database.clientDataDao();
    }

    public void On12(View view)
    {
        clientDataDao.deleteAll();
    }
}