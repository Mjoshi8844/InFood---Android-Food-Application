package com.example.infood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infood.Adapter.CategoryAdapter;
import com.example.infood.Adapter.PopularAdapter;
import com.example.infood.Domain.CategoryDomain;
import com.example.infood.Domain.FoodDomain;
import com.example.infood.Interface.RecyclerViewClickListener;
import com.example.infood.Listener.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    private CategoryAdapter adapter;
    private PopularAdapter adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    SearchView sv;
    TextView tv;
    ArrayList<CategoryDomain> category = new ArrayList<>();
    ArrayList<FoodDomain> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

//        getSupportActionBar().hide();


        tv = findViewById(R.id.textView8);
        sv = findViewById(R.id.searchView);
        sv.setQueryHint("Find Your Food");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.requestFocus();
            }
        });

        recyclerViewCategory();
        recyclerViewPopular();
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

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Indian", "indifood"));
        category.add(new CategoryDomain("Chinese", "chinese"));
        category.add(new CategoryDomain("Snacks", "samosa"));
        category.add(new CategoryDomain("Beverage", "cat_4"));
        category.add(new CategoryDomain("Sweets", "sweet"));
        category.add(new CategoryDomain("Cakes", "cake"));
        category.add(new CategoryDomain("Desserts", "dessert"));
        category.add(new CategoryDomain("Italian", "cat_3"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);

        recyclerViewCategoryList.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewCategoryList, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(position == 0){
                    Intent ip = new Intent(getApplicationContext(), Pizza.class);
                    startActivity(ip);
                }
                if(position == 1){
                    Intent ip = new Intent(getApplicationContext(), Burger.class);
                    startActivity(ip);
                }
                if(position == 2){
                    Intent ip = new Intent(getApplicationContext(), Indian.class);
                    startActivity(ip);
                }
                if(position == 3){
                    Intent ip = new Intent(getApplicationContext(), Chinese.class);
                    startActivity(ip);
                }
                if(position == 4){
                    Intent ip = new Intent(getApplicationContext(), Snacks.class);
                    startActivity(ip);
                }
                if(position == 5){
                    Intent ip = new Intent(getApplicationContext(), Beverages.class);
                    startActivity(ip);
                }
                if(position == 6){
                    Intent ip = new Intent(getApplicationContext(), Sweets.class);
                    startActivity(ip);
                }
                if(position == 7){
                    Intent ip = new Intent(getApplicationContext(), Cakes.class);
                    startActivity(ip);
                }
                if(position == 8){
                    Intent ip = new Intent(getApplicationContext(), Desserts.class);
                    startActivity(ip);
                }
                if(position == 9){
                    Intent ip = new Intent(getApplicationContext(), Italian.class);
                    startActivity(ip);
                }
            }
        }));

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false; }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });
    }

    private void filter(String text){
        ArrayList<CategoryDomain> filteredlist = new ArrayList<>();
        for(CategoryDomain item : category){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
                adapter.filterList(filteredlist);
        }
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        foodList.add(new FoodDomain("Virgin Green Apple Mojito", "mm3", "Green Apple Mojito is prepared with green apple syrup, lemon juice, soda and mint leaves; and is an apt drink for a summer party.", 210.00));
        foodList.add(new FoodDomain("Cheese French Fries", "cheese_fries", "100gm\nFrench Fries Loaded With Thick layer of Cheese having some Exotic Seasoning of different Spices on it, Served with Delicious Dips.", 90.00));
        foodList.add(new FoodDomain("Belgian Chocolate Waffle", "waffle", "Classic crispy waffle and melted Belgian milk chocolate. Simplicity at its best!\n[Hygiene Packaging]", 140.00));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "Aloo Tikki, Cheese Slices, Special Sauce, Lettuce, tomato", 110.00));
        foodList.add(new FoodDomain("Vegetable pizza", "pizza_2", "Large : 6 Slices\nOnion, Tomatoes, Red, Yellow and Green Capsicums, Olive oil, Vegetable oil, Fresh Oregano Double Layer Of Cheese", 200.00));
        foodList.add(new FoodDomain("Cold Chocolate Coco", "mm10", "Made with cocoa powder, sugar, cornflour, chilled milk and few ice cubes, all ingredients blended in a hand blender.", 120.00));
        foodList.add(new FoodDomain("Cappuccino", "mm7", " A cappuccino is an espresso -based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.", 120.00));
        foodList.add(new FoodDomain("Cold Coffee", "mm8", "Cold coffee is a creamy and popular cold beverage, that has mainly three ingredients – instant coffee, milk, and sugar.", 90.00));
        foodList.add(new FoodDomain("Indian Masala Tea", "mm9", "Original Refreshing Indian Masala Tea with all its special Ingredients.", 70.00));
        foodList.add(new FoodDomain("Cold Chocolate Coco", "mm10", "Made with cocoa powder, sugar, cornflour, chilled milk and few ice cubes, all ingredients blended in a hand blender.", 120.00));
        foodList.add(new FoodDomain("Mixed Fruit Juice", "mm11", "Mixed Fruit Juice is a fresh juice made by blending different fruits – pineapple, grapes, orange, strawberry, apple together", 140.00));
        foodList.add(new FoodDomain("PANEER MAKHANI", "pm7", "Paneer and Capsicum on Makhani Sauce", 180.00));
        foodList.add(new FoodDomain("FARM HOUSE", "pm8", "A pizza that goes ballistic on veggies! Check out this mouth watering overload of crunchy, crisp capsicum, succulent mushrooms and fresh tomatoes", 250.00));
        foodList.add(new FoodDomain("MEXICAN GREEN WAVE", "pm9", "A pizza loaded with crunchy onions, crisp capsicum, juicy tomatoes and jalapeno with a liberal sprinkling of exotic Mexican herbs.", 210.00));
        foodList.add(new FoodDomain("Crispy Veg Double Patty", "bm1", "Double up our best selling crispy veg burger..", 70.00));
        foodList.add(new FoodDomain("Crispy Veg", "bm2", "Crispy veg patty burger, our best seller..", 50.00));

        adapter2 = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query, 1);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });
    }

    private void filter(String text, int v){
        ArrayList<FoodDomain> filteredlist = new ArrayList<>();
        for(FoodDomain item : foodList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
                adapter2.filterList(filteredlist);
        }
    }
}