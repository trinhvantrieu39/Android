package com.example.ecommerce.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.CartAdapter;
import com.example.ecommerce.Adapter.PupolarAdapter;
import com.example.ecommerce.Domain.PopularDomain;
import com.example.ecommerce.Helper.ChangeNumberItemsListener;
import com.example.ecommerce.Helper.ManagementCart;
import com.example.ecommerce.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, totalTaxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);
        initView();
        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility((View.VISIBLE));
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round(managementCart.getTotalFee() * percentTax*100)/100.0;
        double total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        totalTaxTxt.setText("$"+tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$"+ total);
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        totalTaxTxt = findViewById(R.id.totalTaxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        deliveryTxt = findViewById(R.id.deliverTxt);
        recyclerView = findViewById(R.id.viewCart);
        scrollView = findViewById(R.id.scrollViewCart);
        backBtn = findViewById(R.id.backBtnCart);
        emptyTxt = findViewById(R.id.emptyTxt);

    }
}
