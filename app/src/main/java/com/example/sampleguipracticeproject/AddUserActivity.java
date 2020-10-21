package com.example.sampleguipracticeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    EditText productnameEt,productTypeEt,productId,productprice,productquantity;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        productnameEt = findViewById(R.id.productnameEt);
        productTypeEt = findViewById(R.id.productTypeEt);
        productId = findViewById(R.id.productId);
        productprice = findViewById(R.id.productprice);
        productquantity = findViewById(R.id.productquantity);
        save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToRoom();
            }
        });
    }
    private void addToRoom() {
        if(checkProduct()==1){
            User user = new User();
            String productName = productnameEt.getText().toString().trim();
            String productType = productTypeEt.getText().toString().trim();
            String strid = productId.getText().toString().trim();
            int productQ = Integer.parseInt(productquantity.getText().toString().trim());
            double price = Double.parseDouble(productprice.getText().toString().trim());
            if (strid.length()>0) {
                int proId = Integer.parseInt(strid);
                user.setProduct_id(proId);
                user.setProductName(productName);
                user.setProductType(productType);
                user.setPrice(price);
                user.setProductQuantity(productQ);
                HomeActivity.myAppDatabase.myDeo().addUser(user);
                Toast.makeText(getApplicationContext(),"Product Added Successfully",Toast.LENGTH_SHORT).show();
                productnameEt.setText("");
                productTypeEt.setText("");
                productId.setText("");
                productprice.setText("");
                productquantity.setText("");
            }
            else{
                Toast.makeText(getApplicationContext(),"Id is required",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Product Updated",Toast.LENGTH_SHORT).show();
        }

    }

    private int checkProduct() {
        int id = Integer.parseInt(productId.getText().toString().trim());
        int temp = 1;
        List<User> userList = HomeActivity.myAppDatabase.myDeo().getUsers();
        for(User user:userList){
            int flag = 0;
            if(user.getProduct_id()==id){
                int pro = user.getProductQuantity();
                pro = pro+Integer.parseInt(productquantity.getText().toString().trim());
                user.setProductQuantity(pro);
                HomeActivity.myAppDatabase.myDeo().updateUser(user);
                flag = 1;
                temp = 0;
            }
            if(flag==1){
                break;
            }
        }
        return (temp);
    }
}