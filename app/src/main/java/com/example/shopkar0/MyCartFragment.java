package com.example.shopkar0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopkar0.activites.PlaceOrderActivity;
import com.example.shopkar0.adapters.MyCartAdapter;
import com.example.shopkar0.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {

FirebaseFirestore db;
FirebaseAuth auth;
RecyclerView recyclerView;
MyCartAdapter myCartAdapter;
List<MyCartModel> myCartModelList;
TextView totalAmountTv;
ProgressBar progressBar;
Button buYBtn;


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_my_cart, container, false);

        progressBar=root.findViewById(R.id.myCartFragment_ProgressBar);
        buYBtn=root.findViewById(R.id.myCartFragment_buyBtn);
        progressBar.setVisibility(View.VISIBLE);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.myCartFragment_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    totalAmountTv=root.findViewById(R.id.myCartFragment_totalPriceTv);

    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("MyFinalTotal"));
        myCartModelList= new ArrayList<>();
        myCartAdapter=new MyCartAdapter(getActivity(),myCartModelList);
        recyclerView.setAdapter(myCartAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        String documentId=documentSnapshot.getId();
                        MyCartModel myCartModel=documentSnapshot.toObject(MyCartModel.class) ;

                        myCartModel.setDocumentId(documentId);
                        myCartModelList.add(myCartModel);
                        myCartAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        buYBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), PlaceOrderActivity.class);
                intent.putExtra("itemList", (Serializable) myCartModelList);
                startActivity(intent);

            }
        });
        return root;


    }


    public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill= intent.getIntExtra("myFinalTotal",0);

            totalAmountTv.setText("Total Rs "+totalBill);

        }
    };
}