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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Fragments.FragmentAllDays;
import com.example.nutrition.Fragments.MyFragmentManager;
import com.example.nutrition.Helper.HelperFragmentAllDays;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AllDaysAdapter extends RecyclerView.Adapter<AllDaysAdapter.MyViewHolder> {

    private Context context;
    private AppCompatActivity appCompatActivity;
    private FragmentAllDaysBinding fragmentAllDaysBinding;
    private List<Day> daysList;
    private DaysRepo daysRepo;
    private boolean isNightMode = false;

    private AllDaysAdapter allDaysAdapter;
    private Toaster toaster;
    private HelperFragmentAllDays helperFragmentAllDays;

    public AllDaysAdapter(Context context, AppCompatActivity appCompatActivity, FragmentAllDaysBinding fragmentAllDaysBinding, DaysRepo daysRepo){
        this.context = context;
        this.appCompatActivity = appCompatActivity;
        this.fragmentAllDaysBinding = fragmentAllDaysBinding;
        this.daysRepo = daysRepo;

        this.daysList = daysRepo.listAllSorted();

        this.toaster = new Toaster(context);
        this.allDaysAdapter = this;
        this.helperFragmentAllDays = new HelperFragmentAllDays(context, appCompatActivity);
        this.isNightMode = ThemeUtils.isNightModeActive(appCompatActivity);
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

                MyFragmentManager.change(appCompatActivity, new FragmentADay(day, appCompatActivity));
            }
        });

        myViewHolder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onLongClick(View v) {
                Day day = daysList.get(myViewHolder.getAdapterPosition());

                daysRepo.delete(day.getId());
                daysList = daysRepo.listAllSorted();

                notifyDataSetChanged();
                FragmentAllDays.checkIfDaysAreEmpty(fragmentAllDaysBinding, allDaysAdapter);

                Chip chip = (Chip) fragmentAllDaysBinding.chipGroupGraphFragmentAllDays.getChildAt(0);
                chip.setChecked(true);
                helperFragmentAllDays.setUpAnyChart("Proteins", fragmentAllDaysBinding, allDaysAdapter);

                return true;
            }
        });

        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AllDaysAdapter.MyViewHolder holder, int position) {
        Day day = daysList.get(position);


        LocalDate ldt = day.getCreatedAt();
        @SuppressLint({"NewApi", "LocalSuppress"})
        String dayName = ldt.getDayOfWeek()
                        .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

        holder.textViewDays.setText(dayName);
        holder.textViewDate.setText(day.getDateIntoStringFormat());

        // Check if this day is the current day
        // Check if this day is also yesterday
        additionalThemeChanges(day, holder);
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        protected MaterialCardView materialCardView;
        protected ConstraintLayout constraintLayout;
        protected ImageView imageViewSun;
        protected TextView textViewDays;
        protected TextView textViewDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialCardView = itemView.findViewById(R.id.materialCardViewSingleDay);
            this.constraintLayout = itemView.findViewById(R.id.constraintLayoutSingleDay);
            this.imageViewSun = itemView.findViewById(R.id.imageViewSunSingleDayLayout);
            this.textViewDays = itemView.findViewById(R.id.textViewDaySingleDayLayout);
            this.textViewDate = itemView.findViewById(R.id.textViewCreatedSingleDayLayout);
        }
    }

    public void additionalThemeChanges(Day day, MyViewHolder holder){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate currentDate = LocalDate.now();
            // Today
            if (day.getCreatedAt().equals(currentDate)){
                int primaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimaryContainer, Color.BLACK);
                int onPrimaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnPrimaryContainer, Color.BLACK);

                holder.imageViewSun.setImageResource(R.drawable.ic_sun_day);
                holder.textViewDays.setTextColor(onPrimaryContainer);
                holder.textViewDate.setTextColor(onPrimaryContainer);
                holder.textViewDays.setText("Today");
                holder.materialCardView.setCardBackgroundColor(primaryContainer);
            }
            // Yesterday
            else if (day.getCreatedAt().equals(currentDate.minusDays(1))) {
                int secondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorSecondaryContainer, Color.BLACK);
                int onSecondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnSecondaryContainer, Color.BLACK);

                if (isNightMode) holder.imageViewSun.setImageResource(R.drawable.ic_sun_white);
                else holder.imageViewSun.setImageResource(R.drawable.ic_sun_black);
                holder.materialCardView.setCardBackgroundColor(secondaryContainer);
                holder.textViewDays.setTextColor(onSecondaryContainer);
                holder.textViewDate.setTextColor(onSecondaryContainer);
                holder.textViewDays.setText("Yesterday");
            }
            // Every other day
            else {
                if (isNightMode){
                    holder.imageViewSun.setImageResource(R.drawable.ic_sun_white);
                    holder.textViewDays.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.white60Opacity));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.almostBlack));
                } else {
                    holder.imageViewSun.setImageResource(R.drawable.ic_sun_black);
                    holder.textViewDays.setTextColor(ContextCompat.getColor(context, R.color.colorTextDark));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.black60Opacity));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.almostWhite));
                }
            }
        }
    }

    // For today and yesterday, but disabled for now
    public void checkIfDayIsToday(Day day, MyViewHolder holder){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate date = LocalDate.now();
            if (date.equals(day.getCreatedAt())){
                int primaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimaryContainer, Color.BLACK);
                int onPrimaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnPrimaryContainer, Color.BLACK);

                holder.materialCardView.setCardBackgroundColor(primaryContainer);
                holder.textViewDays.setText("Today");
                holder.textViewDays.setTextColor(onPrimaryContainer);
                holder.textViewDate.setTextColor(onPrimaryContainer);
            }
        }
    }

    public void checkIfDayIsYesterday(Day day, MyViewHolder holder){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate date = LocalDate.now();
            if (date.minusDays(1).equals(day.getCreatedAt())){
                int secondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorSecondaryContainer, Color.BLACK);
                int onSecondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnSecondaryContainer, Color.BLACK);

                holder.materialCardView.setCardBackgroundColor(secondaryContainer);
                holder.textViewDays.setText("Yesterday");
                holder.textViewDays.setTextColor(onSecondaryContainer);
                holder.textViewDate.setTextColor(onSecondaryContainer);
            }
        }
    }

    public List<Day> getDaysList() {
        return daysList;
    }

    public void setDaysList(List<Day> daysList) {
        this.daysList = daysList;
    }

    public boolean isListEmpty(){
        return daysList != null && daysList.isEmpty();
    }
}
