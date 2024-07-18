package com.example.nutrition.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Fragments.FragmentSec3Adapter;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AllDaysAdapter extends RecyclerView.Adapter<AllDaysAdapter.MyViewHolder> {

    private Context context;
    private List<Day> daysList;
    private ActivitySection3Binding activitySection3Binding;

    public AllDaysAdapter(Context context, ActivitySection3Binding activitySection3Binding, List<Day> daysList){
        this.context = context;
        this.activitySection3Binding = activitySection3Binding;
        this.daysList = daysList;
    }

    @NonNull
    @Override
    public AllDaysAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_day_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.materialCardView.setChecked(!myViewHolder.materialCardView.isChecked());
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllDaysAdapter.MyViewHolder holder, int position) {
        Day day = daysList.get(position);

        holder.textViewDays.setText(day.getTitle());
        holder.textViewDate.setText(day.getDateIntoStringFormat());
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        protected MaterialCardView materialCardView;
        protected TextView textViewDays;
        protected TextView textViewDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialCardView = itemView.findViewById(R.id.materialCardViewSingleDay);
            this.textViewDays = itemView.findViewById(R.id.textViewDaySingleDayLayout);
            this.textViewDate = itemView.findViewById(R.id.textViewCreatedSingleDayLayout);
        }
    }
}
