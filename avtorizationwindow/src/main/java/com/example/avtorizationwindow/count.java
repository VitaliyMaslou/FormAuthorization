package com.example.avtorizationwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.avtorizationwindow.Entities.App;
import com.example.avtorizationwindow.Entities.AppDataBase;
import com.example.avtorizationwindow.Entities.ClientData.ClientData;
import com.example.avtorizationwindow.Entities.ClientData.ClientDataDao;

import java.util.List;

public class count extends AppCompatActivity {
TextView c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        c= findViewById(R.id.textView);

        AppDataBase database;
        ClientDataDao clientDataDao;
        List<ClientData> list;

        database = App.getInstance().getDatabase();
        clientDataDao = database.clientDataDao();

        list = clientDataDao.GetAll();

        c.setText(""+list.size());
    }

    public void On4(View view)
    {
        this.finish();
    }
}