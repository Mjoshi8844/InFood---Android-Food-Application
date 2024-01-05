package com.example.infood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infood.Adapter.CartListAdapter;
import com.example.infood.Helper.ManagementCart;
import com.example.infood.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private ScrollView scrollView;
    int pbs = 0, x = 0;
    Handler pbh = new Handler();
    ProgressBar pb;
    ImageButton bt;
    RadioGroup rg;
    RadioButton csh, gpy;
    Button po;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    Double tax, total, itemTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        getSupportActionBar().hide();

        managementCart = new ManagementCart(this);

        rg = findViewById(R.id.rdgrp);
        csh = findViewById(R.id.radioButton);
        gpy = findViewById(R.id.radioButton2);
        po = findViewById(R.id.textView17);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();

        rg.clearCheck();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
            }
        });
        csh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sid = rg.getCheckedRadioButtonId();
                if(sid == -1)
                    Toast.makeText(getApplicationContext(), "No Mode Selected", Toast.LENGTH_LONG).show();
                else{
                    x = 1;
                    po.requestFocus();
                }
            }
        });
        gpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                po.requestFocus();
            }
        });

        po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x == 0)
                    Toast.makeText(getApplicationContext(), "No Mode of Payment Selected\nPlease select a Payment method!!", Toast.LENGTH_LONG).show();
                else if(x==1){
                    final AlertDialog.Builder bd = new AlertDialog.Builder(CartActivity.this)
                            .setTitle("Placing the Order")
                                    .setMessage("Confirm the Order?")
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface di, int which) {
                                                    di.dismiss();
                                                    showCustomDialog();
                                                }
                                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface di, int which) {
                                    di.dismiss();
                                }
                            });
                    bd.show();

                }
                else{
                    Intent cs = new Intent(getApplicationContext(), Payment.class);
                    cs.putExtra("upi", total.toString());
                    startActivity(cs);
                }
            }
        });
    }

    void showCustomDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_place_order, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(view);
        final android.app.AlertDialog dialog = builder.create();
        dialog.show();

        pb = dialog.findViewById(R.id.progressBar2);
        bt = dialog.findViewById(R.id.imageButton2);

        pbs = pb.getProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pbs <= 100){
                    pbs++;
                    pbh.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(pbs);
                            if(pbs == 100){
                                dialog.dismiss();
                                Intent cs = new Intent(getApplicationContext(), OrderPlaced.class);
                                startActivity(cs);
                            }
                        }
                    });
                    try {
                        Thread.sleep(50);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbs = -200;
                dialog.dismiss();
                final AlertDialog.Builder bd = new AlertDialog.Builder(CartActivity.this)
                        .setTitle("Canceling the Order")
                        .setMessage("Do you really want to cancel the Order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface di, int which) {
                                di.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface di, int which) {
                                pbs = 0;
                                di.dismiss();
                                dialog.show();
                            }
                        });
                bd.show();
            }
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

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Double.valueOf(Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100);
        total = Double.valueOf(Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100);
        itemTotal = Double.valueOf(Math.round(managementCart.getTotalFee() * 100) / 100);

        totalFeeTxt.setText("Rs. " + itemTotal);
        taxTxt.setText("Rs. " + tax);
        deliveryTxt.setText("Rs. " + delivery);
        totalTxt.setText("Rs. " + total);
    }

}