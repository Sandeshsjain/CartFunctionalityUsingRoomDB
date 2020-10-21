package com.example.sampleguipracticeproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;
import java.util.zip.Inflater;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ProductViewHolder>{
    Context context;
    List<User> userList;

    public AllProductAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item_layout,parent,false);
        return new AllProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.productnameTV.setText(userList.get(position).getProductName());
        holder.producttypeTV.setText(userList.get(position).getProductType());
        holder.productIdTV.setText(String.valueOf(userList.get(position).getProduct_id()));
        holder.productpriceTV.setText(String.valueOf(userList.get(position).getPrice()));
        holder.productQuantityTV.setText(String.valueOf(userList.get(position).getProductQuantity()));
        holder.addTocartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddToCartActivity.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(userList.get(position));
                intent.putExtra("myjson",myJson);
                context.startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Alert....");
                builder.setMessage("Are you sure you wanna delete");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = new User();
                        user.setId(userList.get(position).getId());
                        HomeActivity.myAppDatabase.myDeo().deleteUser(user);
                        Toast.makeText(context,"Product deleted",Toast.LENGTH_SHORT).show();
                        userList.remove(position);
                        notifyItemRemoved(position);
                    }
                });
                builder.show();
            }
        });

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateUserActivity.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(userList.get(position));
                intent.putExtra("myjson",myJson);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return userList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView productnameTV,producttypeTV,productIdTV,productpriceTV,productQuantityTV;
        Button addTocartButton,updateButton,deleteButton;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productnameTV = itemView.findViewById(R.id.productnameTV);
            producttypeTV = itemView.findViewById(R.id.producttypeTV);
            productIdTV = itemView.findViewById(R.id.productIdTV);
            addTocartButton = itemView.findViewById(R.id.addTocartButton);
            productpriceTV = itemView.findViewById(R.id.productpriceTV);
            productQuantityTV = itemView.findViewById(R.id.productQuantityTV);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }
    }
}
