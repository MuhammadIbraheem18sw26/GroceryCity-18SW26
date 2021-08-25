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
import com.example.shopkar0.activites.ViewAllActivity;
import com.example.shopkar0.models.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;


    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popolar_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(popularModelList.get(position).getitemImageUrl()).into(holder.pop_img);
        holder.name.setText(popularModelList.get(position).getName());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.discount.setText(popularModelList.get(position).getDiscount());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",popularModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pop_img;
        TextView name,description,rating,discount;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            pop_img=itemView.findViewById(R.id.popularItem_image);
            name=itemView.findViewById(R.id.popularItem_Name);
            description=itemView.findViewById(R.id.popularItem_Description);
            rating=itemView.findViewById(R.id.popularItem_Rating);
            discount=itemView.findViewById(R.id.popularItem_Discount);


        }
    }
}
