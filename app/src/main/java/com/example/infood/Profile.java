package com.example.infood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    ListView lv1;
    ArrayList<String> lst;
    ArrayAdapter<String> st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        getSupportActionBar().hide();

        lv1 = findViewById(R.id.lv);

        lst = new ArrayList<>();
        lst.add("Rate Us");
        lst.add("Edit Profile");
        lst.add("Your Orders");
        lst.add("Favorite Orders");
        lst.add("Address Book");
        lst.add("InFood Pro");

        st = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lst);
        lv1.setAdapter(st);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent i0  = new Intent(getApplicationContext(), RateUs.class);
                    startActivity(i0);
                }
                if(position == 1){
                    Intent i1  = new Intent(getApplicationContext(), EditProfile.class);
                    startActivity(i1);
                }
                if(position == 2){
                    Intent i2  = new Intent(getApplicationContext(), YourOrders.class);
                    startActivity(i2);
                }
                if(position == 3){
                    Intent i3  = new Intent(getApplicationContext(), FavouriteOrders.class);
                    startActivity(i3);
                }
                if(position == 4){
                    Intent i4  = new Intent(getApplicationContext(), AddressBook.class);
                    startActivity(i4);
                }
                if(position == 5){
                    Intent i5  = new Intent(getApplicationContext(), InFoodPro.class);
                    startActivity(i5);
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