package com.example.GroceryCity.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.GroceryCity.R;
import com.example.GroceryCity.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ItemsDetailActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
    ImageView itemDetail_Img,increment_Img,decrement_Img;
    TextView price,description,quantity,rating;
    Button addCartBtn;
    Toolbar toolbar;
    ViewAllModel viewAllModel=null;
    int productQuantity=1;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_detail);

        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel=(ViewAllModel) object;
        }
        toolbar=findViewById(R.id.itemsDetailActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        itemDetail_Img=findViewById(R.id.itemsDetailActivity_ImageView);
        increment_Img=findViewById(R.id.itemsDetailActivity_IncrementIV);
        decrement_Img=findViewById(R.id.itemsDetailActivity_DecrementIV);

        price=findViewById(R.id.itemsDetailActivity_PriceTv);
        description=findViewById(R.id.itemsDetailActivity_DescriptionTxtTv);
        rating=findViewById(R.id.itemsDetailActivity_RatingTv);
        quantity=findViewById(R.id.itemsDetailActivity_QuantityTv);

        addCartBtn=findViewById(R.id.itemsDetailActivity_addCartBtn);



        if(viewAllModel!=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getItemImageUrl()).into(itemDetail_Img);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Rs "+viewAllModel.getPrice());

        }


        addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addInCard();


            }
        });

        increment_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(productQuantity<10){
                    productQuantity++;
                    quantity.setText(toString().valueOf(productQuantity));
                }
            }
        });

        decrement_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productQuantity>0){
                    productQuantity--;
                    quantity.setText(toString().valueOf(productQuantity));
                }
            }
        });

    }

    private void addInCard() {
        total=(viewAllModel.getPrice()*(productQuantity));



        String sCurrentTime,sCurrentDate;
        Calendar calendar= Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd ,YYYY");
        sCurrentDate=currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:s a");
        sCurrentTime=currentTime.format(calendar.getTime());


        final  HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName ",viewAllModel.getName());
        cartMap.put("productPrice ",price.getText().toString());
        cartMap.put("currentDate ",sCurrentDate);
        cartMap.put("currentTime ",sCurrentTime);
        cartMap.put("totalQuantity ",quantity.getText().toString());
        cartMap.put("totalPrice",total);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ItemsDetailActivity.this, "Item Added to Card", Toast.LENGTH_SHORT).show();
            finish();
            }
        });
    }

}