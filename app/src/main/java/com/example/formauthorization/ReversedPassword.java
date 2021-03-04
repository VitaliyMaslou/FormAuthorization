package com.example.formauthorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.formauthorization.Entities.Users.Users;

public class ReversedPassword extends AppCompatActivity {
    TextView CryptoPassword;
    Button OpenReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversed_password);

        Intent intent = getIntent();

        CryptoPassword = findViewById(R.id.txtReversedPassword);
        CryptoPassword.setText(intent.getStringExtra("CryptoPassword"));

        OpenReg = findViewById(R.id.btnOpenReg);

        if(intent.getStringExtra("Role") != null)
            OpenReg.setVisibility(View.VISIBLE);
    }

    public void OpenReg(View view)
    {
        Intent intent = new Intent(ReversedPassword.this, Registration.class);
        startActivity(intent);
    }
}