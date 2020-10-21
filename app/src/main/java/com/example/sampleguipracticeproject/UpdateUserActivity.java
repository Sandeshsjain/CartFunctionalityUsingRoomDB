package com.example.sampleguipracticeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class UpdateUserActivity extends AppCompatActivity {
    EditText updateProductNameEt ,updateProductType,updateProductPrice,updateProductQuantity;
    TextView updateProductId;
    Button updateButton;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        final Intent intent = getIntent();
        Gson gson = new Gson();
        user = gson.fromJson(intent.getStringExtra("myjson"),User.class);
        updateButton = findViewById(R.id.updateButton);
        updateProductNameEt = findViewById(R.id.updateProductNameEt);
        updateProductType = findViewById(R.id.updateProductType);
        updateProductId = findViewById(R.id.updateProductId);
        updateProductPrice = findViewById(R.id.updateProductPrice);
        updateProductQuantity = findViewById(R.id.updateProductQuantity);
        updateProductId.setText(String.valueOf(user.getProduct_id()));
        setData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int id = user.getId();
                    int proid = user.getProduct_id();
                    String productName = updateProductNameEt.getText().toString().trim();
                    String productType = updateProductType.getText().toString().trim();
                    double price = Double.parseDouble(updateProductPrice.getText().toString().trim());
                    int quan = Integer.parseInt(updateProductQuantity.getText().toString().trim());
                    User user = new User();
                    user.setId(id);
                    user.setProductType(productType);
                    user.setProductName(productName);
                    user.setPrice(price);
                    user.setProductQuantity(quan);
                    user.setProduct_id(proid);
                    HomeActivity.myAppDatabase.myDeo().updateUser(user);
                    Toast.makeText(getApplicationContext(),"Product Updated",Toast.LENGTH_SHORT).show();
                    updateProductNameEt.setText("");
                    updateProductId.setText("");
                    updateProductType.setText("");
                    updateProductPrice.setText("");
                    updateProductQuantity.setText("");
                    Intent intent1 = new Intent(getApplicationContext(),SelectUserActivity.class);
                    startActivity(intent1);
            }
        });
    }

    private void setData() {
        updateProductNameEt.setText(user.getProductName());
        updateProductPrice.setText(String.valueOf(user.getPrice()));
        updateProductType.setText(user.getProductType());
        updateProductQuantity.setText(String.valueOf(user.getProductQuantity()));
    }
}