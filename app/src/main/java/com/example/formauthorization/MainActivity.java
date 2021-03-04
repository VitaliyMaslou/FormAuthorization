package com.example.formauthorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.formauthorization.Entities.App;
import com.example.formauthorization.Entities.AppDatabase;
import com.example.formauthorization.Entities.Users.Users;
import com.example.formauthorization.Entities.Users.UsersDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String ReversePassword;
    EditText Login, Password;
    AppDatabase database;
    UsersDao usersDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.edtxtLogin);
        Password = findViewById(R.id.edtxtPassword);

        database = App.getInstance().getDatabase();
        usersDao = database.usersDao();

        CheckDB();
    }

    public void OpenForm(View view)
    {
        String login = Login.getText().toString(), password = Password.getText().toString();

        if(!CheckFields(login, password))
            return;

        Users user = usersDao.GetByLogin(login);

        if(!CheckExistUser(user))
            return;

        if(!CheckPassword(user, password))
            return;

        ReversePassword = new StringBuilder(Password.getText()).reverse().toString();
        Intent intent = new Intent(MainActivity.this, ReversedPassword.class);
        intent.putExtra("CryptoPassword", ReversePassword);
        if(login.equals("Admin"))
            intent.putExtra("Role", login);
        startActivity(intent);
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

    void CheckDB()
    {
        if(usersDao.GetAll().size() > 0)
            return;

        FillDB();
    }

    void FillDB()
    {
        Users Admin = new Users();
        Admin.setLogin("Admin");
        Admin.setPassword("123");
        Admin.setPhone("7(764)059-44-98");
        Admin.setEmail("fodatipu-5632@yopmail.com");
        Admin.setLastName("Admin");
        Admin.setFirstName("Admin");
        Admin.setMiddleName("Admin");

        usersDao.Insert(Admin);

        Users wer = new Users();
        wer.setLogin("wer");
        wer.setPassword("124");
        wer.setPhone("1(3685)549-50-09");
        wer.setEmail("elinnyllabe-3318@yopmail.com");
        wer.setLastName("wer");
        wer.setFirstName("wer");
        wer.setMiddleName("wer");

        usersDao.Insert(wer);
    }

    boolean CheckExistUser(Users user)
    {
        if(user != null)
            return true;

        Toast.makeText(this, "Пользователь с таким логином не найден", Toast.LENGTH_LONG).show();
        return false;
    }

    boolean CheckPassword(Users user, String password)
    {
        if(user.getPassword().equals(password))
            return true;

        Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
        return false;
    }
}