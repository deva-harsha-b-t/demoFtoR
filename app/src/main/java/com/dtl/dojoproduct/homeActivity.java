package com.dtl.dojoproduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

public class homeActivity extends AppCompatActivity {
    private FirebaseFirestore firestoreDB;
    private TextView result;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;
    private Context context;
    private ViewModelStoreOwner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        owner = this;
        setContentView(R.layout.activity_home);
        firestoreDB = FirebaseFirestore.getInstance();
        context = this.getApplicationContext();
        initViews();
        recyclerView = findViewById(R.id.productRecyclerView);

        Query query = firestoreDB.collection("products");
        FirestoreRecyclerOptions<productModel> options = new FirestoreRecyclerOptions.Builder<productModel>()
                .setQuery(query,productModel.class).build();

        adapter = new FirestoreRecyclerAdapter<productModel, productViewHOlder>(options) {
            @NonNull
            @NotNull
            @Override

            public productViewHOlder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_product,parent,false);
                return new productViewHOlder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull productViewHOlder holder, int position, @NonNull @NotNull productModel model) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        peoductViewmodel viewModel = new ViewModelProvider(owner).get(peoductViewmodel.class);
                        viewModel.setModel(model);
                        Intent intent  = new Intent(homeActivity.this,MainActivity.class);
                        intent.putExtra("name",model.getProductName());
                        intent.putExtra("des",model.getProductDescription());
                        intent.putExtra("slang",model.getProductSlag());
                        intent.putExtra("rating",model.getRating());
                        intent.putExtra("noOfReviews",model.getNoOfReviews());
                        intent.putExtra("url",model.getProductImageURL());
                        startActivity(intent);
                    }
                });
                holder.productName.setText( model.getProductName());
                holder.productDesc.setText(model.getProductSlag());
                Glide.with(context).load(model.getProductImageURL()).fitCenter().into(holder.productImage);
                Log.d("IDTEST", "onBindViewHolder: " + model.getId());


            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private class productViewHOlder extends RecyclerView.ViewHolder{
        private TextView productName;
        private TextView productDesc;
        private ImageView productImage;
        public productViewHOlder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.productname);
            this.productDesc = itemView.findViewById(R.id.productDis);
            this.productImage = itemView.findViewById(R.id.productImage);
        }


    }

    private void initViews() {
        result = findViewById(R.id.searchResultNumber);
        recyclerView = findViewById(R.id.productRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }
}