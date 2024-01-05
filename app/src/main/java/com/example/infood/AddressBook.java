package com.example.infood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddressBook extends AppCompatActivity {
    Button sa;
    TextView sv;
    EditText ed;
    RadioGroup rg;
    RadioButton hm, ot, wk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

//        getSupportActionBar().hide();

        sa = findViewById(R.id.textView17);
        sv = findViewById(R.id.textView24);
        ed = findViewById(R.id.textView23);
        rg = findViewById(R.id.rdgrp);
        hm = findViewById(R.id.radioButton);
        wk = findViewById(R.id.radioButton2);
        ot = findViewById(R.id.radioButton3);


        rg.clearCheck();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
            }
        });

        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sid = rg.getCheckedRadioButtonId();
                if(sid == -1)
                    Toast.makeText(getApplicationContext(), "No Address Type Selected!\n Please Select Any one..", Toast.LENGTH_LONG).show();
                else{
                    RadioButton rb = rg.findViewById(sid);
                    if(ed.getText()==null) {
                        Toast.makeText(getApplicationContext(), "No Delivery Address Saved!", Toast.LENGTH_SHORT).show();
                        ed.requestFocus();
                    }
                    else {
                        if(sv.length()==0)
                            sv.setText(rb.getText()+ " : " + ed.getText());
                        else
                            sv.append("\n" + rb.getText()+ " : " + ed.getText());
                        Toast.makeText(AddressBook.this, "Delivery Address Saved!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bottomNavigation();

    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout supportBtn = findViewById(R.id.supportBtn);
        LinearLayout settingBtn = findViewById(R.id.settingbtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SupportUser.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
            }
        });
    }
}