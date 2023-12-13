package com.example.ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ecommerce.Adapter.PupolarAdapter;
import com.example.ecommerce.Domain.PopularDomain;
import com.example.ecommerce.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        bottomNavigation();

    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        String des = "Music Producer: DTAP\n" +
                "Songwriter: PHƯƠNG MỸ CHI\n" +
                "Lyricists: KATA TRẦN (DTAP), PHƯƠNG MỸ CHI\n" +
                "Arrangers: TÙNG CEDRUS (DTAP), LONG X\n" +
                "Mixing Engineer: SDOG (INUS)\n" +
                "Mastering Engineer: CHRIS GEHRINGER (STERLING SOUND,EDGEWATER, NJ)\n" +
                "Soundman: KIDZ NGUYỄN\n" +
                "Background Vocals: DTAP, INUS TEAM\n" +
                "Recording Studio: OUT OF SPACE (POWERED BY DTAP)";
        items.add(new PopularDomain("T-shirt black", des, "item_1",15,4,500));
        items.add(new PopularDomain("Smart Watch",des,"item_2", 2, 4.5, 459));
        items.add(new PopularDomain("IPhone 15",des,"item_3", 3, 4.5, 599));
        items.add(new PopularDomain("TV Pro",des,"item_4", 4, 4.5, 459));

        recyclerViewPupolar = findViewById(R.id.view1);
        recyclerViewPupolar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPupolar = new PupolarAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);
    }
}