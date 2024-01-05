package com.example.infood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAsk extends AppCompatActivity {
    Button pb, eb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ask);

//        getSupportActionBar().hide();

        pb = (Button) findViewById(R.id.ph);
        eb = (Button) findViewById(R.id.em);

        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ip = new Intent(getApplicationContext(), PhoneLogin.class);
                startActivity(ip);
            }
        });

        eb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent(getApplicationContext(), Login.class);
                startActivity(ie);
            }
        });
    }
}