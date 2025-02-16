package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.example.nutrition.SharedPrefs.SharedPrefMacronutrients;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.time.LocalDate;
import java.util.List;

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
                MyFragmentManager.change(appCompatActivity, new FragmentADay(day, appCompatActivity), false);
            }
        });

        // We are deleting a day, and all of it's items.
        myViewHolder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onLongClick(View v) {
                Day day = daysList.get(myViewHolder.getAdapterPosition());

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                builder.setTitle("Alert")
                        .setIcon(R.drawable.ic_remove)
                        .setMessage("Are you sure you want to delete\n'" + day.calculateLongDayNameOfDate() + " - " + day.getDateIntoStringFormat() + "' ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                daysRepo.delete(day.getId());
                                daysList = daysRepo.listAllSorted();
                                notifyDataSetChanged();

                                FragmentAllDays.checkIfDaysAreEmpty(fragmentAllDaysBinding, allDaysAdapter);
                                helperFragmentAllDays.countAndSetTotalDays(context, fragmentAllDaysBinding, allDaysAdapter);

                                String macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(context);
                                helperFragmentAllDays.createCustomChart(macro, fragmentAllDaysBinding, allDaysAdapter);
                                helperFragmentAllDays.checkAndSelectCorrectChip(macro, fragmentAllDaysBinding);
                                helperFragmentAllDays.calculateAverageMacronutrient(macro, fragmentAllDaysBinding, allDaysAdapter.getDaysList());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(true)
                        .show();

                return true;
            }
        });

        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AllDaysAdapter.MyViewHolder holder, int position) {
        Day day = daysList.get(position);
        int reversePosition = daysList.size() - position;

        String dayName = day.calculateLongDayNameOfDate();
        holder.textViewDays.setText(dayName);
        holder.textViewDate.setText(day.getDateIntoStringFormat());
        holder.textViewDaysNumber.setText(String.valueOf(reversePosition));

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
        protected TextView textViewDays;
        protected TextView textViewDate;
        protected TextView textViewDaysNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialCardView = itemView.findViewById(R.id.materialCardViewSingleDay);
            this.constraintLayout = itemView.findViewById(R.id.constraintLayoutSingleDay);
            this.textViewDaysNumber = itemView.findViewById(R.id.textViewDaysNumberSingleDayLayout);
            this.textViewDays = itemView.findViewById(R.id.textViewDaySingleDayLayout);
            this.textViewDate = itemView.findViewById(R.id.textViewCreatedSingleDayLayout);
        }
    }

    @SuppressLint("SetTextI18n")
    public void additionalThemeChanges(Day day, MyViewHolder holder){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate currentDate = LocalDate.now();
            // Today
            if (day.getCreatedAt().equals(currentDate)){
                int primaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimaryContainer, Color.BLACK);
                int onPrimaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnPrimaryContainer, Color.BLACK);

                int textColor;
                if (isNightMode) {
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.greenLight));
                    textColor = ContextCompat.getColor(context, R.color.white);
                }
                else {
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.greenDark));
                    textColor = ContextCompat.getColor(context, R.color.black);
                }

                holder.textViewDays.setTypeface(null, Typeface.BOLD);
                holder.textViewDate.setTypeface(null, Typeface.BOLD);
                holder.textViewDaysNumber.setTypeface(null, Typeface.BOLD);
                holder.textViewDays.setTextColor(textColor);
                holder.textViewDate.setTextColor(textColor);
                holder.textViewDays.setText("Today");
                holder.materialCardView.setCardBackgroundColor(primaryContainer);
            }
            // Yesterday
            else if (day.getCreatedAt().equals(currentDate.minusDays(1))) {
                int secondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorSecondaryContainer, Color.BLACK);
                int onSecondaryContainer = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnSecondaryContainer, Color.BLACK);

                if (isNightMode) holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.white));
                else  holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.black));

                holder.textViewDays.setTypeface(null, Typeface.NORMAL);
                holder.textViewDate.setTypeface(null, Typeface.NORMAL);
                holder.textViewDaysNumber.setTypeface(null, Typeface.NORMAL);
                holder.textViewDays.setTextColor(onSecondaryContainer);
                holder.textViewDate.setTextColor(onSecondaryContainer);
                holder.textViewDays.setText("Yesterday");
                holder.materialCardView.setCardBackgroundColor(secondaryContainer);
            }
            // Every other day
            else {
                if (isNightMode){
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.white));
                    holder.textViewDays.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.white60Opacity));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.almostBlack));
                } else {
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.black));
                    holder.textViewDays.setTextColor(ContextCompat.getColor(context, R.color.colorTextDark));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.black60Opacity));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.almostWhite));
                }
                holder.textViewDays.setTypeface(null, Typeface.NORMAL);
                holder.textViewDate.setTypeface(null, Typeface.NORMAL);
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

    public DaysRepo getDaysRepo() {
        return daysRepo;
    }
}
