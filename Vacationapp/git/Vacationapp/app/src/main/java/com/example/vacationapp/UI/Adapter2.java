package com.example.vacationapp.UI;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationapp.Enity.vacations;
import com.example.vacationapp.R;

import java.util.List;


public class Adapter2 extends RecyclerView.Adapter<Adapter2.ProductViewHolder> {


    class ProductViewHolder extends RecyclerView.ViewHolder{
        private final TextView VacationItemView;
        private final TextView itemView2;
        private final TextView itemview3;
        private final TextView itemview4;
        private ProductViewHolder(View itemView){
            super(itemView);
            VacationItemView=itemView.findViewById(R.id.textView);
            itemView2=itemView.findViewById(R.id.textView12);
            itemview3=itemView.findViewById(R.id.textView13);
            itemview4=itemView.findViewById(R.id.textView14);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final vacations current=mVacations.get(position);
                    Intent intent=new Intent(context,VacationDetails.class);
                    intent.putExtra("ID", current.getTripsID());
                    intent.putExtra("Vacation", current.getTripsTitle());
                    intent.putExtra("Hotel", current.getTripsHotel());
                    intent.putExtra("Start", current.getStartDate());
                    intent.putExtra("End", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<vacations> mVacations;
    private final Context context;
    private final LayoutInflater mInflater;

    public Adapter2(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.vacation_list_sub,parent,false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if(mVacations!=null){
            vacations current=mVacations.get(position);
            String name=current.getTripsTitle();
            String hotel =current.getTripsHotel();
            String start =current.getStartDate();
            String end =current.getEndDate();
            holder.VacationItemView.setText(name);
            holder.itemView2.setText(hotel);
            holder.itemview3.setText(start);
            holder.itemview4.setText(end);

        }
        else{
            holder.VacationItemView.setText("No Vacation Found");
        }
    }

    public void setProducts(List<vacations> vacations){
        mVacations=vacations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mVacations.size();
    }
}

