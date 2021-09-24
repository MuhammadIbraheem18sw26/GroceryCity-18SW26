package com.example.GroceryCity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.GroceryCity.R;
import com.example.GroceryCity.models.CategoryItemDetailModel;

import java.util.List;

public class CategoryItemDetailAdapter extends  RecyclerView.Adapter<CategoryItemDetailAdapter.ViewHolder>{

    Context context;
    List<CategoryItemDetailModel> list;

    public CategoryItemDetailAdapter(Context context, List<CategoryItemDetailModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_item_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getItemImageUrl()).into(holder.categoryItemDetail_img);
        holder.name.setText(list.get(position).getName());
        holder.price.setText("Rs "+list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryItemDetail_img;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryItemDetail_img=itemView.findViewById(R.id.category_view_item_Img);
            name=itemView.findViewById(R.id.category_view_item_NameTv);
            price=itemView.findViewById(R.id.category_view_item_PriceTV);

        }
    }
}