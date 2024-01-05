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

public class Beverages extends AppCompatActivity {
    private DetailAdapter adapter;
    private RecyclerView recyclerViewDetailList;
    SearchView sv;
    ArrayList<FoodDomain> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);

        sv = findViewById(R.id.searchView);
        sv.setQueryHint("Find Your Food");

//        getSupportActionBar().hide();

        recyclerViewDetail();
        bottomNavigation();
    }

    private void recyclerViewDetail() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDetailList = findViewById(R.id.recyclerView2);
        recyclerViewDetailList.setLayoutManager(linearLayoutManager);

        foodList.add(new FoodDomain("Virgin Mint Mojito", "mm1", "Refreshing Mint Leaves, Fresh Lime Juice, Simple Syrup and some chilled Ice..", 110.00));
        foodList.add(new FoodDomain("Virgin Raspberry Mojito", "mm2", "Fresh Raspberries, Thick Lime, Fresh Mint Leaves, Sparkling Apple Cider (chilled)", 140.00));
        foodList.add(new FoodDomain("Virgin Green Apple Mojito", "mm3", "Green Apple Mojito is prepared with green apple syrup, lemon juice, soda and mint leaves; and is an apt drink for a summer party.", 210.00));
        foodList.add(new FoodDomain("Coca-Cola", "mm4", "Large Glass Of Refreshing Coke with chilled ice cubes..", 50.00));
        foodList.add(new FoodDomain("Sprite", "mm5", "Large Glass Of Refreshing Sprite with chilled ice cubes..", 50.00));
        foodList.add(new FoodDomain("Latte Coffee", "mm6", "A delicious coffee drink made with espresso and steamed milk..", 70.00));
        foodList.add(new FoodDomain("Cappuccino", "mm7", " A cappuccino is an espresso -based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.", 120.00));
        foodList.add(new FoodDomain("Cold Coffee", "mm8", "Cold coffee is a creamy and popular cold beverage, that has mainly three ingredients – instant coffee, milk, and sugar.", 90.00));
        foodList.add(new FoodDomain("Indian Masala Tea", "mm9", "Original Refreshing Indian Masala Tea with all its special Ingredients.", 70.00));
        foodList.add(new FoodDomain("Cold Chocolate Coco", "mm10", "Made with cocoa powder, sugar, cornflour, chilled milk and few ice cubes, all ingredients blended in a hand blender.", 120.00));
        foodList.add(new FoodDomain("Mixed Fruit Juice", "mm11", "Mixed Fruit Juice is a fresh juice made by blending different fruits – pineapple, grapes, orange, strawberry, apple together", 140.00));


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

    private void filter(String text){
        ArrayList<FoodDomain> filteredlist = new ArrayList<>();
        for(FoodDomain item : foodList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
            adapter.filterList(filteredlist);
        }
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