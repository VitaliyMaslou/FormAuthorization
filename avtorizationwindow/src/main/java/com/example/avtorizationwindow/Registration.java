package com.example.avtorizationwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.avtorizationwindow.Entities.App;
import com.example.avtorizationwindow.Entities.AppDataBase;
import com.example.avtorizationwindow.Entities.ClientData.ClientData;
import com.example.avtorizationwindow.Entities.ClientData.ClientDataDao;

import java.util.List;

public class Registration extends AppCompatActivity {

    EditText log,pass,fname,sname,mname,phone,post;
    AppDataBase database;
    ClientDataDao clientDataDao;
    List<ClientData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        log=findViewById(R.id.InLog);
        pass=findViewById(R.id.InPass);
        fname=findViewById(R.id.InFirstname);
        sname=findViewById(R.id.InSeconname);
        mname=findViewById(R.id.InMiddlename);
        phone=findViewById(R.id.InTelephon);
        post=findViewById(R.id.InPost);

        database = App.getInstance().getDatabase();
        clientDataDao = database.clientDataDao();

        list = clientDataDao.GetAll();
    }

    public void On1(View view)
    {
        this.finish();
    }


    public void On2(View view)
    {
        String[] array = {
                log.getText().toString(),
                pass.getText().toString(),
                fname.getText().toString(),
                sname.getText().toString(),
                mname.getText().toString(),
                phone.getText().toString(),
                post.getText().toString()};

        if(!CheckEmptyLines(array))
            return;

        if(!CheckLogin(array[0]))
            return;

        FillDB(array);

        Toast.makeText(this,"Данные записаны",  Toast.LENGTH_SHORT).show();
    }

    void FillDB(String[] mas)
    {
        Intent intent = getIntent();

        ClientData Admin = new ClientData(
                mas[0],
                mas[1],
                mas[2],
                mas[3],
                mas[4],
                mas[5],
                mas[6],
                intent.getStringExtra("ROLE")
        );
        clientDataDao.Insert(Admin);
    }

    public boolean CheckLogin(String login)
    {
        if(clientDataDao.GetByLogin(login) == null)
            return true;

        Toast.makeText(this,"Поьзователь с таким логином уже существует!",  Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean CheckEmptyLines(String[] aaa)
    {
        for(int i=0;i<aaa.length;i++)
        {
            if(aaa[i].equals(""))
            {
                Toast.makeText(this,"Заполните пустые поля!",  Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}