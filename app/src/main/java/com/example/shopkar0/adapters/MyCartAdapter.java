package com.example.shopkar0.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkar0.R;
import com.example.shopkar0.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> myCartModelList;
    int totalFinalPrice=0;

    FirebaseFirestore db;
    FirebaseAuth auth;


    public MyCartAdapter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    public MyCartAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productName.setText(String.valueOf(myCartModelList.get(position).getProductName()));
        holder.currentD.setText(String.valueOf(myCartModelList.get(position).getCurrentDate()));
        holder.currentT.setText(String.valueOf(myCartModelList.get(position).getCurrentTime()));
        holder.productP.setText(String.valueOf(myCartModelList.get(position).getProductPrice()));
        holder.totalP.setText(String.valueOf(myCartModelList.get(position).getTotalPrice()));
        holder.totalQ.setText(String.valueOf(myCartModelList.get(position).getTotalQuantity()));

        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").document(myCartModelList.get(position).getDocumentId())
                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            myCartModelList.remove(myCartModelList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        totalFinalPrice=totalFinalPrice+(myCartModelList.get(position).getTotalPrice());

        Intent intent=new Intent("MyFinalTotal");
        intent.putExtra("myFinalTotal",totalFinalPrice);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productP,totalP,currentD,totalQ,currentT;
        ImageView deleteImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.myCartItem_ProductName);
            productP=itemView.findViewById(R.id.myCartItem_ProductPrice);
            totalP=itemView.findViewById(R.id.myCartItem_TotalPrice);
            currentD=itemView.findViewById(R.id.myCartItem_ProductDate);
            totalQ=itemView.findViewById(R.id.myCartItem_ProductQuantity);
            currentT=itemView.findViewById(R.id.myCartItem_ProductTime);
            deleteImg=itemView.findViewById(R.id.myCartItem_DeleteIv);
        }
    }
}
