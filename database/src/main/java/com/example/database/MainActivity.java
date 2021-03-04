package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.database.Entities.App;
import com.example.database.Entities.AppDatabase;
import com.example.database.Entities.Avtorisation.Avtorisation;
import com.example.database.Entities.Avtorisation.AvtorisationDao;

import java.util.List;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

public class MainActivity extends AppCompatActivity {

    EditText  Login, Password, Role, Nomer;
    AppDatabase database;
    AvtorisationDao avtorisationDao;
    TextView Clogin, Cpass, Crole,inf;

    List<Avtorisation> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.LOg);
        Password = findViewById(R.id.editTextTextPassword);
        Role = findViewById(R.id.ROle);

        Nomer = findViewById(R.id.editTextTextPersonName3);

        Clogin =findViewById(R.id.CLOG);
        Cpass =findViewById(R.id.CPASS);
        Crole =findViewById(R.id.CROLE);
        inf=findViewById(R.id.infolog);


        database = App.getInstance().getDatabase();
        avtorisationDao = database.avtorisationDao();

        list = avtorisationDao.GetAll();

        inf.setText(""+list.size());
        CheckDB();
    }
    public void on1(View view)
    {
        String login = Login.getText().toString();
        String password = Password.getText().toString();
        Integer role = Integer.parseInt(Role.getText().toString());


        Avtorisation Stroke = new Avtorisation();
        Stroke.setLogin(login);
        Stroke.setPassword(password);
        Stroke.setRole(role);

        avtorisationDao.Insert(Stroke);
    }

    public void on2(View view)
    {
        Integer num = Integer.parseInt(Nomer.getText().toString());
        if(num<=list.size())
        {
            String aaa = list.get(num).getLogin();
            String aaa1 = list.get(num).getPassword();
            Integer aaa2 = list.get(num).getRole();

            Clogin.setText(aaa);
            Cpass.setText(aaa1);
            Crole.setText(""+aaa2);
        }
    }


    void CheckDB()
    {
        if(avtorisationDao.GetAll().size() > 0)
            return;

        FillDB();
    }

    void FillDB()
    {
        Avtorisation Admin = new Avtorisation();
        Admin.setLogin("Admin");
        Admin.setPassword("123");
        Admin.setRole(1);

        avtorisationDao.Insert(Admin);

        Avtorisation Admin2 = new Avtorisation();
        Admin2.setLogin("qwe");
        Admin2.setPassword("124");
        Admin2.setRole(2);

        avtorisationDao.Insert(Admin2);
    }
}