package com.example.ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.Domain.PopularDomain;
import com.example.ecommerce.Helper.ManagementCart;
import com.example.ecommerce.R;

public class DetailActivity extends AppCompatActivity {

    private Button addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt;
    private ImageView picItem, backBtn;
    private PopularDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        managementCart = new ManagementCart(this);
        initView();
        System.out.println("init oke");
        getBundle();
    }

    private void getBundle() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        System.out.println("33 oke");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(),"drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picItem);
        System.out.println("item oke");
        titleTxt.setText(object.getTitle());
        System.out.println("title oke");
        feeTxt.setText("$"+object.getPrice());
        System.out.println("fee oke");
        descriptionTxt.setText(object.getDescription());
        System.out.println("description oke");
        reviewTxt.setText(object.getReview()+"");
        System.out.println("review oke");
        scoreTxt.setText(object.getScore()+"");
        System.out.println("score oke");

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);

        });
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        feeTxt = findViewById(R.id.priceTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picItem = findViewById(R.id.itemPic);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        backBtn = findViewById(R.id.backBtn);
    }
}