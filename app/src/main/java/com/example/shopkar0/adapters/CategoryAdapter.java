package com.example.shopkar0.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopkar0.R;
import com.example.shopkar0.activites.CategoryItemViewActivity;
import com.example.shopkar0.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CategoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(categoryModelList.get(position).getItemImageUrl()).into(holder.categoryItem_Img);
        holder.name.setText(categoryModelList.get(position).getName());
        holder.description.setText(categoryModelList.get(position).getDescription());
        holder.offer.setText(categoryModelList.get(position).getOfferDiscount());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, CategoryItemViewActivity.class);
                intent.putExtra("type",categoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryItem_Img;
        TextView  name,description,offer;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            categoryItem_Img=itemView.findViewById(R.id.categoryItem_Img);
            name=itemView.findViewById(R.id.categoryItem_NameTv);
            description=itemView.findViewById(R.id.categoryItem_DescriptionTV);
            offer=itemView.findViewById(R.id.categoryItem_Off);


        }
    }
}
