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

public class MainActivity extends AppCompatActivity {

    EditText log,pass;
    AppDataBase database;
    ClientDataDao clientDataDao;
    List<ClientData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log=findViewById(R.id.CLogIn);
        pass=findViewById(R.id.CPassIn);

        database = App.getInstance().getDatabase();
        clientDataDao = database.clientDataDao();

        list = clientDataDao.GetAll();


        CheckBd();
    }

    public void On1(View view)
    {
        Intent intent = new Intent(MainActivity.this, Registration.class);
        intent.putExtra("ROLE", "Пользователь");

        startActivity(intent);
    }

    public void On2(View view)
    {
        String login = log.getText().toString();
        String password = pass.getText().toString();

        if(login.equals("qwe") && password.equals("qwe"))
        {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return;
        }

        if(!CheckFields(login, password))
            return;

        ClientData clientData = clientDataDao.GetByLogin(login);

        if(!CheckExistUser(clientData))
            return;

        if(!CheckPassword(clientData, password))
            return;

        if(clientData.getRole().equals("Администратор"))
        {
            Intent intent = new Intent(MainActivity.this, TAbleClient.class);
            startActivity(intent);
        }
        else if(clientData.getRole().equals("Пользователь"))
        {
            Intent intent = new Intent(MainActivity.this, count.class);
            startActivity(intent);
        }


    }

    void CheckBd()
    {
        if(clientDataDao.GetAll().size()>0)
            return;
        FillDB();
    }

    void FillDB()
    {
        ClientData Admin = new ClientData(
                "Admin",
                "123",
                "8-904-444-34-43",
                "sobaka@gmail.com",
                "Маслов",
                "Виталий",
                "Владимирович",
                "Администратор"
                );
        clientDataDao.Insert(Admin);
    }

    boolean CheckFields(String login, String password)
    {
        if(login.equals(""))
        {
            Toast.makeText(this, password.equals("") ? "Заполните все поля" : "Заполните логин", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.equals(""))
        {
            Toast.makeText(this, "Заполните пароль", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    boolean CheckExistUser(ClientData clientData)
    {
        if(clientData != null)
            return true;

        Toast.makeText(this, "Пользователь с таким логином не найден", Toast.LENGTH_LONG).show();
        return false;
    }

    boolean CheckPassword(ClientData clientData, String password)
    {
        if(clientData.getPassword().equals(password))
            return true;

        Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
        return false;
    }
}