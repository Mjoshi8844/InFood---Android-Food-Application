package com.example.infood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infood.Adapter.DetailAdapter;
import com.example.infood.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sweets extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewDetailList;
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweets);

//        getSupportActionBar().hide();

        recyclerViewDetail();
        bottomNavigation();
    }

    private void recyclerViewDetail() {
        ArrayList<FoodDomain> foodList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDetailList = findViewById(R.id.recyclerView2);
        recyclerViewDetailList.setLayoutManager(linearLayoutManager);

        sv = findViewById(R.id.searchView);
        sv.setQueryHint("Find Your Food");

        foodList.add(new FoodDomain("Cheese French Fries", "cheese_fries", "100gm\nFrench Fries Loaded With Thick layer of Cheese having some Exotic Seasoning of different Spices on it, Served with Delicious Dips.", 90.00));
        foodList.add(new FoodDomain("Belgian Chocolate Waffle", "waffle", "Classic crispy waffle and melted Belgian milk chocolate. Simplicity at its best!\n[Hygiene Packaging]", 140.00));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "Aloo Tikki, Cheese Slices, Special Sauce, Lettuce, tomato", 110.00));
        foodList.add(new FoodDomain("Vegetable pizza", "pizza_2", "Large : 6 Slices\nOnion, Tomatoes, Red, Yellow and Green Capsicums, Olive oil, Vegetable oil, Fresh Oregano Double Layer Of Cheese", 200.00));

        adapter = new DetailAdapter(foodList);
        recyclerViewDetailList.setAdapter(adapter);
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