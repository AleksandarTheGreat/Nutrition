package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Fragments.MyFragmentManager;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AllDaysAdapter extends RecyclerView.Adapter<AllDaysAdapter.MyViewHolder> {

    private Context context;
    private AppCompatActivity appCompatActivity;
    private ActivitySection3Binding activitySection3Binding;
    private List<Day> daysList;
    private DaysRepo daysRepo;

    public AllDaysAdapter(Context context, AppCompatActivity appCompatActivity, ActivitySection3Binding activitySection3Binding, DaysRepo daysRepo){
        this.context = context;
        this.appCompatActivity = appCompatActivity;
        this.activitySection3Binding = activitySection3Binding;
        this.daysRepo = daysRepo;

        this.daysList = daysRepo.listAll();
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
                Day day = daysList.get(myViewHolder.getAdapterPosition());

                MyFragmentManager.change(appCompatActivity, new FragmentADay(day));
                myViewHolder.materialCardView.setChecked(!myViewHolder.materialCardView.isChecked());
            }
        });

        myViewHolder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onLongClick(View v) {
                Day day = daysList.get(myViewHolder.getAdapterPosition());

                daysRepo.delete(day.getId());
                daysList = daysRepo.listAll();
                notifyDataSetChanged();

                return true;
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllDaysAdapter.MyViewHolder holder, int position) {
        Day day = daysList.get(position);

        holder.textViewDays.setText(day.getTitle() + " " + day.getId());
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

    public List<Day> getDaysList() {
        return daysList;
    }

    public void setDaysList(List<Day> daysList) {
        this.daysList = daysList;
    }
}
