package com.dtl.dojoproduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private TextView productName;
    private TextView productSlang;
    private TextView productDes;
    private TextView noOfReviews;
    private RatingBar rating;
    private ImageView productImage;
    private Context context;
    private int id;
    private productModel model;
    private String name;
    private String des;
    private String slang;
    private int noOfreviewsCount;
    private String url;
    private int ratingCount;
    private String price;
    private double randomPrice;
    private Button buyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_main);
        initViews();
        randomPrice = Math.random() * (1000 - 100 + 1) + 100;

        price = "get now for " + String.format("%.2f", randomPrice) + "$";
        buyButton.setText(price);
        id = getIntent().getIntExtra("id",99);
        name = getIntent().getStringExtra("name");
        des = getIntent().getStringExtra("des");
        slang = getIntent().getStringExtra("slang");
        ratingCount = getIntent().getIntExtra("rating",4);
        noOfreviewsCount = getIntent().getIntExtra("noOfReviews",100);
        url = getIntent().getStringExtra("url");
        context = this.getApplicationContext();

        findViewById(R.id.backButton).setOnClickListener(v -> this.onBackPressed());
        peoductViewmodel viewModel = new ViewModelProvider(this).get(peoductViewmodel.class);
        model = viewModel.getModel();
        if(model!=null){
            productName.setText(model.getProductName());
            productDes.setText(model.getProductDescription());
            productSlang.setText(model.getProductSlag());
            noOfReviews.setText("(" + model.getNoOfReviews() + ") reviews");
            rating.setRating(model.getRating());
            Glide.with(context).load(model.getProductImageURL()).centerCrop().into(productImage);
        }else{
            productName.setText(name);
            productDes.setText(des);
            productSlang.setText(slang);
            noOfReviews.setText("(" + noOfreviewsCount + ") reviews");
            rating.setRating(ratingCount);
            Glide.with(context).load(url).centerCrop().into(productImage);

        }

//        db.collection("products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for (QueryDocumentSnapshot doc : task.getResult()){
//                        productName.setText(doc.get("ProductName").toString());
//                        productDes.setText(doc.get("ProductDescription").toString());
//                        productSlang.setText(doc.get("ProductSlag").toString());
//                        noOfReviews.setText("(" + doc.get("noOfReviews").toString() + ") reviews");
//                        rating.setRating(Float.parseFloat(doc.get("rating").toString()));
//                        Glide.with(context).load(doc.get("ProductImageURL")).centerCrop().into(productImage);
//                    }
//
//                }else
//                {
//                    Toast.makeText(context, "error getting data", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    private void initViews() {
        productName  = findViewById(R.id.PRDproductName);
        productSlang = findViewById(R.id.PRDproductSlang);
        productDes   = findViewById(R.id.PRDproductFullDis);
        noOfReviews  = findViewById(R.id.noOfReviews);
        rating       = findViewById(R.id.rating);
        productImage = findViewById(R.id.productfullimage);
        buyButton    = findViewById(R.id.buyButton);
    }
}