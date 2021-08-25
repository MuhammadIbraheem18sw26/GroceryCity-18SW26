package com.example.shopkar0.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopkar0.R;
import com.example.shopkar0.adapters.ExploreAdapter;
import com.example.shopkar0.adapters.PopularAdapter;
import com.example.shopkar0.adapters.RecommendedAdapter;
import com.example.shopkar0.models.ExploreModel;
import com.example.shopkar0.models.PopularModel;
import com.example.shopkar0.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    RecyclerView popular_rec,explore_rec,recommended_rec;

List<PopularModel> popularModelList;
PopularAdapter popularAdapter;

ExploreAdapter exploreAdapter;
List<ExploreModel> exploreModelList;

RecommendedAdapter recommendedAdapter;
List<RecommendedModel> recommendedModelList;
FirebaseFirestore db;

ScrollView scrollView;
ProgressBar progressBar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db=FirebaseFirestore.getInstance();
        popular_rec= root.findViewById(R.id.popular_rec);
        explore_rec=root.findViewById(R.id.explore_rec);
        recommended_rec=root.findViewById(R.id.recommended_rec);
        scrollView=root.findViewById(R.id.scrollViewHomeFragment);
        progressBar=root.findViewById(R.id.progressBarHomeFragment);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        popular_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        explore_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommended_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        popularModelList= new ArrayList<>();
        popularAdapter=new PopularAdapter(getActivity(),popularModelList);
        popular_rec.setAdapter(popularAdapter);

        exploreModelList=new ArrayList<>();
        exploreAdapter=new ExploreAdapter(getActivity(),exploreModelList);
        explore_rec.setAdapter(exploreAdapter);

        recommendedModelList=new ArrayList<>();
        recommendedAdapter=new RecommendedAdapter(getActivity(),recommendedModelList);
        recommended_rec.setAdapter(recommendedAdapter);



        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel=document.toObject(PopularModel.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();


                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        db.collection("ExploreProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ExploreModel exploreModel=document.toObject(ExploreModel.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                exploreModelList.add(exploreModel);
                                exploreAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        db.collection("RecommendedProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel=document.toObject(RecommendedModel.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();
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