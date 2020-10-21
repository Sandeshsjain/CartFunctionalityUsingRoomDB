package com.example.sampleguipracticeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class AddToCartActivity extends AppCompatActivity {
    public static final String TAG = "AddToCartActivity" ;
    TextView productnameTV,producttypeTV,productIdTV,productPriceET,quantity_tv,productQuantityTV1;
    ImageView increment_one,decrement_one;
    User user;
    public static int productCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Intent intent = getIntent();
        Gson gson = new Gson();
        user = gson.fromJson(intent.getStringExtra("myjson"),User.class);
        productCount = 0;
        productnameTV = findViewById(R.id.productnameTV);
        producttypeTV = findViewById(R.id.producttypeTV);
        productIdTV = findViewById(R.id.productIdTV);
        productPriceET = findViewById(R.id.productPriceET);
        productQuantityTV1 = findViewById(R.id.productQuantityTV1);
        quantity_tv = findViewById(R.id.quantity_tv);
        increment_one = findViewById(R.id.increment_one);
        decrement_one = findViewById(R.id.decrement_one);
        setData();
        increment_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOne();
            }
        });
        decrement_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOne();
            }
        });
    }

    private void decrementOne() {
        if(productCount>0){
            productCount--;
            quantity_tv.setText(String.valueOf(productCount));
        }
    }

    private void incrementOne() {
        if(productCount<user.getProductQuantity()){
            productCount++;
            quantity_tv.setText(String.valueOf(productCount));
        }
        else{
            Toast.makeText(getApplicationContext(),"Product out of Stock",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        productnameTV.setText(user.getProductName());
        productIdTV.setText(String.valueOf(user.getProduct_id()));
        productPriceET.setText(String.valueOf(user.getPrice()));
        producttypeTV.setText(user.getProductType());
        productQuantityTV1.setText(String.valueOf(user.getProductQuantity()));
    }
}