package com.example.sampleguipracticeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SelectUserActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        //text_display_info = findViewById(R.id.text_display_info);
        List<User> users = HomeActivity.myAppDatabase.myDeo().getUsers();
        recycler_view = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        AllProductAdapter allProductAdapter = new AllProductAdapter(this,users);
        recycler_view.setAdapter(allProductAdapter);
        allProductAdapter.notifyDataSetChanged();
    }
}