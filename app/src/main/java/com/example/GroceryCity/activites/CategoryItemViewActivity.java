package com.example.GroceryCity.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.GroceryCity.R;
import com.example.GroceryCity.adapters.CategoryItemDetailAdapter;

import com.example.GroceryCity.models.CategoryItemDetailModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;

    List<CategoryItemDetailModel> list;
  CategoryItemDetailAdapter adapter;

  ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item_view);
        progressBar=findViewById(R.id.categoryViewItem_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        db=FirebaseFirestore.getInstance();
        String type= getIntent().getStringExtra("type");
        recyclerView=findViewById(R.id.categoryViewItem_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list=new ArrayList<>();
        adapter= new CategoryItemDetailAdapter(this,list);
        recyclerView.setAdapter(adapter);

        //shirt
        if(type!=null && type.equalsIgnoreCase("shirt")){

            db.collection("CategoryDetailItems").whereEqualTo("type","shirt").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        CategoryItemDetailModel categoryItemDetailModel= documentSnapshot.toObject(CategoryItemDetailModel.class);

                        list.add(categoryItemDetailModel);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
    }


        //shoe
        if(type!=null && type.equalsIgnoreCase("shoe")){

            db.collection("CategoryDetailItems").whereEqualTo("type","shoe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        CategoryItemDetailModel categoryItemDetailModel= documentSnapshot.toObject(CategoryItemDetailModel.class);

                        list.add(categoryItemDetailModel);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        //perfume
        if(type!=null && type.equalsIgnoreCase("perfume")){

            db.collection("CategoryDetailItems").whereEqualTo("type","perfume").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        CategoryItemDetailModel categoryItemDetailModel= documentSnapshot.toObject(CategoryItemDetailModel.class);

                        list.add(categoryItemDetailModel);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        //purse
        if(type!=null && type.equalsIgnoreCase("purse")){

            db.collection("CategoryDetailItems").whereEqualTo("type","purse").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        CategoryItemDetailModel categoryItemDetailModel= documentSnapshot.toObject(CategoryItemDetailModel.class);

                        list.add(categoryItemDetailModel);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}