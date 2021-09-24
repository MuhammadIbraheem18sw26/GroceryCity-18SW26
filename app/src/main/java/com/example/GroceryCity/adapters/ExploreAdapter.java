package com.example.GroceryCity.adapters;

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
import com.example.GroceryCity.R;
import com.example.GroceryCity.activites.ViewAllActivity;
import com.example.GroceryCity.models.ExploreModel;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private List<ExploreModel> exploreModelList;

    public ExploreAdapter(Context context, List<ExploreModel> exploreModelList) {
        this.context = context;
        this.exploreModelList = exploreModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExploreAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(exploreModelList.get(position).getItemImageUrl()).into(holder.exploreImg);
        holder.exploreName.setText(exploreModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",exploreModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exploreModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView exploreImg;
        TextView exploreName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            exploreImg=itemView.findViewById(R.id.explore_item_img);
            exploreName=itemView.findViewById(R.id.explore_item_name);





        }
    }
}
