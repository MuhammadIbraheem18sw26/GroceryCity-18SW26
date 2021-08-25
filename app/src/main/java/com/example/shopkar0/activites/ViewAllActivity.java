package com.example.shopkar0.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shopkar0.R;
import com.example.shopkar0.adapters.ViewAllAdapter;
import com.example.shopkar0.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {


    RecyclerView viewAllActivity_rec;
    FirebaseFirestore db;
    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;
    Toolbar toolbar;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        progressBar=findViewById(R.id.viewAllActivity_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        db=FirebaseFirestore.getInstance();
        viewAllActivity_rec=findViewById(R.id.viewAll_rec);
        viewAllActivity_rec.setVisibility(View.GONE);
        String type=getIntent().getStringExtra("type");
        viewAllActivity_rec.setLayoutManager(new LinearLayoutManager(this));

        toolbar=findViewById(R.id.viewAllActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        viewAllModelList= new ArrayList<>();
        viewAllAdapter= new ViewAllAdapter(this,viewAllModelList);
        viewAllActivity_rec.setAdapter(viewAllAdapter);

        // Shirt
        if(type!=null && type.equalsIgnoreCase("shirt")){

            db.collection("ViewAllProducts").whereEqualTo("type","shirt").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= documentSnapshot.toObject(ViewAllModel.class);

                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllActivity_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

// shoe

        if(type!=null && type.equalsIgnoreCase("shoe")){

            db.collection("ViewAllProducts").whereEqualTo("type","shoe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= documentSnapshot.toObject(ViewAllModel.class);

                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllActivity_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


//purse
        if(type!=null && type.equalsIgnoreCase("purse")){

            db.collection("ViewAllProducts").whereEqualTo("type","purse").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= documentSnapshot.toObject(ViewAllModel.class);

                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllActivity_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        // perfume
        if(type!=null && type.equalsIgnoreCase("perfume")){

            db.collection("ViewAllProducts").whereEqualTo("type","perfume").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel= documentSnapshot.toObject(ViewAllModel.class);

                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        viewAllActivity_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }
}