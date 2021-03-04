package com.example.formauthorization;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.formauthorization.Entities.App;
import com.example.formauthorization.Entities.AppDatabase;
import com.example.formauthorization.Entities.Users.Users;
import com.example.formauthorization.Entities.Users.UsersDao;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {
    EditText Login, Password, Phone, Email, LastName, FirstName, MiddleName;
    AppDatabase database;
    UsersDao usersDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Login = findViewById(R.id.edtxtLogin);
        Password = findViewById(R.id.edtxtPassword);
        Phone = findViewById(R.id.edtxtPhone);
        Email = findViewById(R.id.edtxtEmail);
        LastName = findViewById(R.id.edtxtLastName);
        FirstName = findViewById(R.id.edtxtFirstName);
        MiddleName = findViewById(R.id.edtxtMiddleName);

        database = App.getInstance().getDatabase();
        usersDao = database.usersDao();
    }

    public void RegUser(View view)
    {
        if(!CheckFields())
            return;

        Users user = new Users();
        user.setLogin(Login.getText().toString());
        user.setPassword(Password.getText().toString());
        user.setPhone(Phone.getText().toString());
        user.setEmail(Email.getText().toString());
        user.setLastName(LastName.getText().toString());
        user.setFirstName(FirstName.getText().toString());
        user.setMiddleName(MiddleName.getText().toString());

        usersDao.Insert(user);

        onBackPressed();
    }

    boolean CheckFields()
    {
        if(!CheckEmptyFields())
            return false;

        return CheckExcistedUser();
    }

    boolean CheckEmptyFields()
    {
        ArrayList<String> EmptyFields = new ArrayList<>();

        if(Login.getText().toString().equals(""))
        {
            EmptyFields.add("Логин");
        }

        if(Password.getText().toString().equals(""))
        {
            EmptyFields.add("Пароль");
        }

        if(Phone.getText().toString().equals(""))
        {
            EmptyFields.add("Телефон");
        }

        if(Email.getText().toString().equals(""))
        {
            EmptyFields.add("Электронная почта");
        }

        if(LastName.getText().toString().equals(""))
        {
            EmptyFields.add("Фамилия");
        }

        if(FirstName.getText().toString().equals(""))
        {
            EmptyFields.add("Имя");
        }

        if(MiddleName.getText().toString().equals(""))
        {
            EmptyFields.add("Отчество");
        }


        if(EmptyFields.size() == 0)
            return true;

        StringBuilder Fields = new StringBuilder();

        for(int i = 0; i < EmptyFields.size(); i++)
        {
            if(Fields.length() > 0)
                Fields.append(", ");

            Fields.append(EmptyFields.get(i));
        }

        Toast.makeText(this, "Заполните поля: " + Fields, Toast.LENGTH_SHORT).show();
        return false;
    }

    boolean CheckExcistedUser()
    {
        if(usersDao.GetByLogin(Login.getText().toString()) == null)
            return true;

        Toast.makeText(this, "Пользователь с таким логином уже существует",
                Toast.LENGTH_LONG).show();
        return false;
    }
}