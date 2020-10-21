package com.example.sampleguipracticeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUserActivity extends AppCompatActivity {
    EditText user_id_delete;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        user_id_delete = findViewById(R.id.user_id_delete);
        delete = findViewById(R.id.delete_user_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strid = user_id_delete.getText().toString().trim();
                if(strid.length()>0){
                    int id = Integer.parseInt(strid);
                    User user = new User();
                    user.setProduct_id(id);
                    HomeActivity.myAppDatabase.myDeo().deleteUser(user);
                    Toast.makeText(getApplicationContext(),"Product deleted",Toast.LENGTH_SHORT).show();
                    user_id_delete.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Id is required for deletion",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}