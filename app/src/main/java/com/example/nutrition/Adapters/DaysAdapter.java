package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Activities.Section3Activity;
import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Fragments.MyFragmentManager;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.MyViewHolder> {

    private Context context;
    private AppCompatActivity appCompatActivity;
    private HelperMain helperMain;
    private DaysRepo daysRepo;
    private List<Day> dayList;
    private Toaster toaster;
    private boolean isNightMode;
    public DaysAdapter(Context context, AppCompatActivity appCompatActivity){
        this.context = context;
        this.appCompatActivity = appCompatActivity;
        this.helperMain = new HelperMain(context);

        this.daysRepo = new DaysRepo(context);
        this.dayList = daysRepo.listAllSorted();
        this.toaster = new Toaster(context);
        this.isNightMode = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @NonNull
    @Override
    public DaysAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_day_layout_main, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.materialCardView.setOnClickListener(v -> {
            Day day = dayList.get(myViewHolder.getAdapterPosition());
            helperMain.goToActivity(context, Section3Activity.class, day.getId());
        });

        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.MyViewHolder holder, int position) {
        Day day = dayList.get(position);

        LocalDate ldt = day.getCreatedAt();
        @SuppressLint({"NewApi", "LocalSuppress"})
        String dayName = ldt.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        holder.textViewDay.setText(dayName);
        holder.textViewDate.setText(day.getDateIntoStringFormat());

        additionalThemeChanges(day, holder);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        protected MaterialCardView materialCardView;
        protected ImageView imageView;
        protected TextView textViewDay;
        protected TextView textViewDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialCardView = itemView.findViewById(R.id.materialCardSingleDay);
            this.imageView = itemView.findViewById(R.id.imageViewSingleDay);
            this.textViewDay = itemView.findViewById(R.id.textViewDay);
            this.textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }

    public void additionalThemeChanges(Day day, DaysAdapter.MyViewHolder holder){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate currentDate = LocalDate.now();
            // Today
            if (day.getCreatedAt().equals(currentDate)){
                int primaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimaryContainer, Color.BLACK);
                int onPrimaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnPrimaryContainer, Color.BLACK);

                holder.imageView.setImageResource(R.drawable.ic_sun_day);
                holder.textViewDay.setText("Today");
                holder.textViewDay.setTextColor(onPrimaryContainer);
                holder.textViewDate.setTextColor(onPrimaryContainer);
                holder.materialCardView.setCardBackgroundColor(primaryContainer);
            }
            // Yesterday
            else if (day.getCreatedAt().equals(currentDate.minusDays(1))) {
                int secondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorSecondaryContainer, Color.BLACK);
                int onSecondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnSecondaryContainer, Color.BLACK);

                if (isNightMode) holder.imageView.setImageResource(R.drawable.ic_sun_white);
                else holder.imageView.setImageResource(R.drawable.ic_sun_black);

                holder.textViewDay.setText("Yesterday");
                holder.textViewDay.setTextColor(onSecondaryContainer);
                holder.textViewDate.setTextColor(onSecondaryContainer);
                holder.materialCardView.setCardBackgroundColor(secondaryContainer);
            }
            // Every other day
            else {
                if (isNightMode){
                    holder.imageView.setImageResource(R.drawable.ic_sun_white);
                    holder.textViewDay.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.white60Opacity));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.almostBlack));
                } else {
                    holder.imageView.setImageResource(R.drawable.ic_sun_black);
                    holder.textViewDay.setTextColor(ContextCompat.getColor(context, R.color.colorTextDark));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.black60Opacity));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.almostWhite));
                }
            }
        }
    }

    public boolean isEmpty(){
        return dayList != null && dayList.isEmpty();
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }
}
