package com.example.GroceryCity.ui.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GroceryCity.R;
import com.example.GroceryCity.adapters.CategoryAdapter;

import com.example.GroceryCity.models.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryFragment extends Fragment {

RecyclerView category_rec;

List<CategoryModel> categoryModelList;
CategoryAdapter categoryAdapter;

ProgressBar progressBar;

FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        progressBar=root.findViewById(R.id.category_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        db=FirebaseFirestore.getInstance();
        category_rec=root.findViewById(R.id.category_rec);
        category_rec.setVisibility(View.GONE);
        category_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        categoryModelList=new ArrayList<>();
        categoryAdapter=new CategoryAdapter(getActivity(),categoryModelList);
        category_rec.setAdapter(categoryAdapter);


        db.collection("CategoryItems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                category_rec.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        return root;
    }
}