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

public class Pizza extends AppCompatActivity {
    private DetailAdapter adapter;
    private RecyclerView recyclerViewDetailList;
    SearchView sv;
    ArrayList<FoodDomain> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

//        getSupportActionBar().hide();

        recyclerViewDetail();
        bottomNavigation();
    }

    private void filter(String text){
        ArrayList<FoodDomain> filteredlist = new ArrayList<>();
        for(FoodDomain item : foodList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
            adapter.filterList(filteredlist);
        }
    }

    private void recyclerViewDetail() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDetailList = findViewById(R.id.recyclerView2);
        recyclerViewDetailList.setLayoutManager(linearLayoutManager);

        sv = findViewById(R.id.searchView);
        sv.setQueryHint("Find Your Food");

        foodList.add(new FoodDomain("MARGHERITA", "pm1", "A hugely popular margherita, with a deliciously tangy single cheese topping.", 110.00));
        foodList.add(new FoodDomain("DOUBLE CHEESE MARGHERITA", "pm2", "The ever-popular Margherita - loaded with extra cheese... oodies of it!", 160.00));
        foodList.add(new FoodDomain("VEGETABLE PIZZA", "pizza_2", "Large : 6 Slices\nOnion, Tomatoes, Red, Yellow and Green Capsicums, Olive oil, Vegetable oil, Fresh Oregano Double Layer Of Cheese", 200.00));
        foodList.add(new FoodDomain("DELUXE VEGGIE", "pm3", "For a vegetarian looking for a BIG treat that goes easy on the spices, this one's got it all.. The onions, the capsicum, those delectable mushrooms - with paneer and golden corn to top it all.", 230.00));
        foodList.add(new FoodDomain("INDI TANDOORI PANEER", "pm4", "It is hot. It is spicy. It is oh-so-Indian. Tandoori paneer with capsicum I red paprika I mint mayo", 250.00));
        foodList.add(new FoodDomain("CHEESE N CORN", "pm5", "Cheese I Golden Corn | Cheese n Corn Pizza", 190.00));
        foodList.add(new FoodDomain("PEPPY PANEER", "pm6", "Chunky paneer with crisp capsicum and spicy red pepper - quite a mouthful!", 230.00));
        foodList.add(new FoodDomain("PANEER MAKHANI", "pm7", "Paneer and Capsicum on Makhani Sauce", 180.00));
        foodList.add(new FoodDomain("FARM HOUSE", "pm8", "A pizza that goes ballistic on veggies! Check out this mouth watering overload of crunchy, crisp capsicum, succulent mushrooms and fresh tomatoes", 250.00));
        foodList.add(new FoodDomain("MEXICAN GREEN WAVE", "pm9", "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes and jalapeno with a liberal sprinkling of exotic Mexican herbs.", 210.00));

        adapter = new DetailAdapter(foodList);
        recyclerViewDetailList.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });
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