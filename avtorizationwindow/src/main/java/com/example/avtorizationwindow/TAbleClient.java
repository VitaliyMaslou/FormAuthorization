package com.example.avtorizationwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.avtorizationwindow.Entities.App;
import com.example.avtorizationwindow.Entities.AppDataBase;
import com.example.avtorizationwindow.Entities.ClientData.ClientData;
import com.example.avtorizationwindow.Entities.ClientData.ClientDataDao;

import java.util.List;

public class TAbleClient extends AppCompatActivity {

    AppDataBase database;
    ClientDataDao clientDataDao;
    List<ClientData> list;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_able_client);

        database = App.getInstance().getDatabase();
        clientDataDao = database.clientDataDao();

        list = clientDataDao.GetAll();

        tableLayout=findViewById(R.id.TableUsers);


        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
          TableRow.LayoutParams.WRAP_CONTENT,
          TableRow.LayoutParams.WRAP_CONTENT,
                1
        );
        layoutParams.setMargins(5,5,5,5);
        //Создание макета параметры

        for(int i=0;i<list.size();i++)
        {
            TableRow row = new TableRow(this);

            String[] Users = {
                    list.get(i).getLogin(),
                    list.get(i).getPassword(),
                    list.get(i).getRole(),
                    list.get(i).getFirstName(),
                    list.get(i).getLastName(),
                    list.get(i).getMiddleName(),
                    list.get(i).getEmail(),
                    list.get(i).getPhone(),
            };

            for(int j =0 ;j<Users.length;j++)
            {
                TextView textView = new TextView(this);
                textView.setLayoutParams(layoutParams);
                textView.setText(Users[j]);
                row.addView(textView);
            }

            tableLayout.addView(row);
        }


    }
    public void On3(View view)
    {
        this.finish();
    }

    public void On5(View view)
    {
        Intent intent = new Intent(TAbleClient.this, Registration.class);
        intent.putExtra("ROLE", "Администратор");
        startActivity(intent);
    }

}