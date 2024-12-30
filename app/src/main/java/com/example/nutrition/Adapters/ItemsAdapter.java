package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Helper.HelperFragmentADay;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Item;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentADayBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private Context context;
    private AppCompatActivity appCompatActivity;
    private FragmentADayBinding binding;
    private List<Item> itemList;
    private ItemsRepo itemsRepo;
    private Day day;
    private boolean isNightModeOn = false;

    public ItemsAdapter(Context context, AppCompatActivity appCompatActivity, FragmentADayBinding binding, ItemsRepo itemsRepo, Day day) {
        this.context = context;
        this.binding = binding;
        this.itemsRepo = itemsRepo;
        this.day = day;

        // Sort the items as in the searched order
        this.itemList = day.getItemList()
                .stream()
                .sorted(Comparator.comparing(Item::getId).reversed())
                .collect(Collectors.toList());

        // We check this once so that we don't have to check it every time in the onBindViewHolder method
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @NonNull
    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.linearLayout.setOnClickListener(v -> {
            Item item = itemList.get(myViewHolder.getAdapterPosition());

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            builder.setTitle("Delete")
                    .setIcon(R.drawable.ic_remove)
                    .setMessage("Are you sure you want to delete '" + item.getIngredient() + "' from the list ??")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            itemsRepo.delete(item.getId());
                            itemList.remove(item);

                            notifyItemRemoved(myViewHolder.getAdapterPosition());
                            Log.d("Tag", "Item '" + item.getIngredient() + "' deleted");

                            HelperFragmentADay.checkIfItemsAreEmpty(binding, ItemsAdapter.this);
                            HelperFragmentADay.calculateTotalNutrients(binding, ItemsAdapter.this);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        });

        additionalThemeChanges(myViewHolder);
        return myViewHolder;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.MyViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.textViewIngredient.setText(item.getIngredient());

        holder.chipProtein.setText(String.format("%.2f", item.getProtein()));
        holder.chipCalories.setText(String.format("%.2f", item.getCalories()));
        holder.chipCarbohydrates.setText(String.format("%.2f", item.getCarbohydrates()));
        holder.chipSugar.setText(String.format("%.2f", item.getSugar()));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.come_from_left);
        holder.linearLayout.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout linearLayout;
        protected ImageView imageViewItemIcon;
        protected TextView textViewIngredient;
        protected Chip chipProtein;
        protected Chip chipCarbohydrates;
        protected Chip chipCalories;
        protected Chip chipSugar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.mainLayoutSingleItem);
            this.imageViewItemIcon = itemView.findViewById(R.id.imageViewItemIcon);

            this.textViewIngredient = itemView.findViewById(R.id.textViewSingleItemTitle);
            this.chipProtein = itemView.findViewById(R.id.textViewActualProteins);
            this.chipCarbohydrates = itemView.findViewById(R.id.textViewActualCarbs);
            this.chipCalories = itemView.findViewById(R.id.textViewActualCalories);
            this.chipSugar = itemView.findViewById(R.id.textViewActualSugar);
        }
    }

    private void additionalThemeChanges(MyViewHolder holder) {
        if (isNightModeOn) {
            int colorWhite = ContextCompat.getColor(context, R.color.white);
            holder.imageViewItemIcon.setImageResource(R.drawable.ic_plate_white);
            holder.textViewIngredient.setTextColor(colorWhite);
        } else {
            int colorBlack = ContextCompat.getColor(context, R.color.black);
            holder.imageViewItemIcon.setImageResource(R.drawable.ic_plate_black);
            holder.textViewIngredient.setTextColor(colorBlack);
        }
    }

    public boolean isListEmpty() {
        return itemList != null && itemList.isEmpty();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
