package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Model.CustomMacros;
import com.example.nutrition.SharedPrefs.SharedPrefCustomMacros;
import com.example.nutrition.SharedPrefs.SharedPrefMacronutrients;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;

import java.time.LocalDate;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.MyViewHolder> {

    private Context context;
    private AppCompatActivity appCompatActivity;
    private HelperMain helperMain;
    private DaysRepo daysRepo;
    private List<Day> dayList;
    private Toaster toaster;
    private boolean isNightMode;
    private String macro;
    private CustomMacros customMacros;

    public DaysAdapter(Context context, AppCompatActivity appCompatActivity) {
        this.context = context;
        this.appCompatActivity = appCompatActivity;
        this.helperMain = new HelperMain(context);

        this.daysRepo = new DaysRepo(context);
        this.dayList = daysRepo.listAllSorted();
        this.toaster = new Toaster(context);
        this.isNightMode = ThemeUtils.isNightModeActive(appCompatActivity);
        this.macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(context);
        this.customMacros = SharedPrefCustomMacros.readFromSharedPref(context);
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
        int reversePosition = dayList.size() - position;

        String dayName = day.calculateLongDayNameOfDate();
        holder.textViewDay.setText(dayName);
        holder.textViewDate.setText(day.dateIntoReadableFormat());
        holder.textViewDaysNumber.setText("D" + reversePosition);
        holder.materialCardView.setBackgroundColor(Color.TRANSPARENT);

        additionalThemeChanges(day, holder);

        // The saved macronutrients
        calculateCorrectMacronutrient(macro, holder, day);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        protected MaterialCardView materialCardView;
        protected TextView textViewDaysNumber;
        protected TextView textViewDay;
        protected TextView textViewDate;
        protected TextView textViewNumberMacro;
        protected TextView textViewLabelMacro;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialCardView = itemView.findViewById(R.id.materialCardSingleDay);
            this.textViewDaysNumber = itemView.findViewById(R.id.textViewSingleDayNumber);
            this.textViewDay = itemView.findViewById(R.id.textViewDay);
            this.textViewDate = itemView.findViewById(R.id.textViewDate);
            this.textViewNumberMacro = itemView.findViewById(R.id.textViewNumberMacro);
            this.textViewLabelMacro = itemView.findViewById(R.id.textViewLabelMacro);
        }
    }

    @SuppressLint("SetTextI18n")
    public void additionalThemeChanges(Day day, DaysAdapter.MyViewHolder holder) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate currentDate = LocalDate.now();
            // Today
            int colorText = 0;
            if (isNightMode) {
                colorText = ContextCompat.getColor(context, R.color.white);
            } else {
                colorText = ContextCompat.getColor(context, R.color.black);
            }

            if (day.getCreatedAt().equals(currentDate)) {
                holder.textViewDay.setTypeface(null, Typeface.NORMAL);
                holder.textViewDate.setTypeface(null, Typeface.NORMAL);
                holder.textViewLabelMacro.setTypeface(null, Typeface.NORMAL);
                holder.textViewNumberMacro.setTypeface(null, Typeface.NORMAL);
                holder.textViewDaysNumber.setTypeface(null, Typeface.NORMAL);
                holder.textViewDay.setText(day.calculateLongDayNameOfDate());
                holder.textViewDay.setTextColor(colorText);
                holder.textViewDate.setTextColor(colorText);
                holder.textViewDaysNumber.setTextColor(colorText);
                holder.textViewLabelMacro.setTextColor(colorText);
                holder.textViewNumberMacro.setTextColor(colorText);

                holder.textViewDate.setText(day.dateIntoReadableFormat() + " - Today");
            }
            // Yesterday
            else if (day.getCreatedAt().equals(currentDate.minusDays(1))) {
                holder.textViewDay.setTypeface(null, Typeface.NORMAL);
                holder.textViewDate.setTypeface(null, Typeface.NORMAL);
                holder.textViewLabelMacro.setTypeface(null, Typeface.NORMAL);
                holder.textViewNumberMacro.setTypeface(null, Typeface.NORMAL);
                holder.textViewDaysNumber.setTypeface(null, Typeface.NORMAL);
                holder.textViewDay.setText(day.calculateLongDayNameOfDate());
                holder.textViewDay.setTextColor(colorText);
                holder.textViewDate.setTextColor(colorText);
                holder.textViewDaysNumber.setTextColor(colorText);
                holder.textViewLabelMacro.setTextColor(colorText);
                holder.textViewNumberMacro.setTextColor(colorText);

                holder.textViewDate.setText(day.dateIntoReadableFormat() + " - Yesterday");
            }
            // Every other day
            else {
                if (isNightMode) {
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.white));
                    holder.textViewDay.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.white60Opacity));
                    holder.textViewLabelMacro.setTextColor(ContextCompat.getColor(context, R.color.white60Opacity));
                    holder.textViewNumberMacro.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
                } else {
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.black));
                    holder.textViewDay.setTextColor(ContextCompat.getColor(context, R.color.colorTextDark));
                    holder.textViewDate.setTextColor(ContextCompat.getColor(context, R.color.black60Opacity));
                    holder.textViewLabelMacro.setTextColor(ContextCompat.getColor(context, R.color.black60Opacity));
                    holder.textViewNumberMacro.setTextColor(ContextCompat.getColor(context, R.color.colorTextDark));
                }
                holder.textViewDay.setTypeface(null, Typeface.NORMAL);
                holder.textViewDate.setTypeface(null, Typeface.NORMAL);
                holder.textViewLabelMacro.setTypeface(null, Typeface.NORMAL);
                holder.textViewNumberMacro.setTypeface(null, Typeface.NORMAL);
                holder.textViewDaysNumber.setTypeface(null, Typeface.NORMAL);
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void calculateCorrectMacronutrient(String macro, MyViewHolder holder, Day day) {
        int color = 0;
        if (isNightMode) {
            color = ContextCompat.getColor(context, R.color.greenLight);
        } else {
            color = ContextCompat.getColor(context, R.color.greenDark);
        }

        switch (macro) {
            case "Proteins":
                holder.textViewNumberMacro.setText(String.format("%d", (int) day.totalProteins()));
                holder.textViewLabelMacro.setText(macro);
                if (day.totalProteins() > customMacros.getProteins()){
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.colorProtein));
                    holder.textViewNumberMacro.setTextColor(ContextCompat.getColor(context, R.color.colorProtein));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorProteinCardBack));
                }
                break;
            case "Calories":
                holder.textViewNumberMacro.setText(String.format("%d", (int) day.totalCalories()));
                holder.textViewLabelMacro.setText(macro);
                if (day.totalCalories() > customMacros.getCalories()){
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.colorCalorie));
                    holder.textViewNumberMacro.setTextColor(ContextCompat.getColor(context, R.color.colorCalorie));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorCalorieCardBack));
                }
                break;
            case "Carbohydrates":
                holder.textViewNumberMacro.setText(String.format("%d", (int) day.totalCarbohydrates()));
                holder.textViewLabelMacro.setText("Carbs");
                if (day.totalCarbohydrates() > customMacros.getCarbs()){
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.colorCarbohydrate));
                    holder.textViewNumberMacro.setTextColor(ContextCompat.getColor(context, R.color.colorCarbohydrate));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorCarbohydrateCardBack));
                }
                break;
            case "Sugars":
                holder.textViewNumberMacro.setText(String.format("%d", (int) day.totalSugar()));
                holder.textViewLabelMacro.setText(macro);
                if (day.totalSugar() > customMacros.getSugars()){
                    holder.textViewDaysNumber.setTextColor(ContextCompat.getColor(context, R.color.colorSugar));
                    holder.textViewNumberMacro.setTextColor(ContextCompat.getColor(context, R.color.colorSugar));
                    holder.materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorSugarCardBack));
                }
                break;
        }
    }

    public boolean isEmpty() {
        return dayList != null && dayList.isEmpty();
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }
}
