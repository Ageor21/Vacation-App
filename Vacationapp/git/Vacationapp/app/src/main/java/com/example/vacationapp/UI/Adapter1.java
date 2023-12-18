package com.example.vacationapp.UI;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationapp.Enity.items;
import com.example.vacationapp.R;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.PartViewHolder> {


    class PartViewHolder extends RecyclerView.ViewHolder{
        private final TextView ItemView;
        private final TextView ItemView2;
        private PartViewHolder(View itemView){
            super(itemView);
            ItemView=itemView.findViewById(R.id.textView2);
            ItemView2=itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(view -> {
                int position=getAdapterPosition();
                final items current=mItems.get(position);
                Intent intent=new Intent(context,ItemsDetails.class);
                intent.putExtra("ID", current.getItemID());
                intent.putExtra("Item_Name", current.getItemName());
                intent.putExtra("Date", current.getDate());
                intent.putExtra("VacationID", current.getVacationID());
                context.startActivity(intent);
            });
        }
    }
    private List<items> mItems;
    private final Context context;
    private final LayoutInflater mInflater;

    public Adapter1(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.vacation_info,parent,false);
        return new PartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartViewHolder holder, int position) {
        if(mItems!=null){
            items current=mItems.get(position);
            String name=current.getItemName();
            String date = current.getDate();
            holder.ItemView.setText(name);
            holder.ItemView2.setText(date);
        }
        else{
            holder.ItemView.setText("No excursion found");
            holder.ItemView.setText("No vacation ID found");
        }
    }

    public void setParts(List<items> parts){
        mItems=parts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
