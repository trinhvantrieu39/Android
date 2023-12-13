package com.example.ecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.ecommerce.Activity.DetailActivity;
import com.example.ecommerce.Domain.PopularDomain;
import com.example.ecommerce.R;

import java.util.ArrayList;

public class PupolarAdapter extends RecyclerView.Adapter<PupolarAdapter.Viewholder> {
    ArrayList<PopularDomain> item;
    Context context;

    public PupolarAdapter(ArrayList<PopularDomain> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public PupolarAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pup_list, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PupolarAdapter.Viewholder holder, int position) {
        holder.titleTxt.setText(item.get(position).getTitle());
        holder.feeTxt.setText("$"+item.get(position).getPrice());
        holder.scoreTxt.setText(""+item.get(position).getScore());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(item.get(position).getPicUrl(), "drawable",
                holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("object", item.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt, feeTxt, scoreTxt;
        ImageView pic;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeTxt = itemView.findViewById(R.id.feeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
