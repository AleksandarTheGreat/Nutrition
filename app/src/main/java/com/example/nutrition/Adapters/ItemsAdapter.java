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
import androidx.constraintlayout.widget.ConstraintLayout;
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
        myViewHolder.imageViewRemove.setOnClickListener(v -> {
            Item item = itemList.get(myViewHolder.getAdapterPosition());

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            builder.setTitle("Delete")
                    .setIcon(R.drawable.ic_remove)
                    .setMessage("'" + item.getIngredient() + "' will be removed from the list.")
                    .setPositiveButton("Yes, I know, duhh...", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            itemsRepo.delete(item.getId());
                            itemList.remove(item);

                            notifyItemRemoved(myViewHolder.getAdapterPosition());
                            Log.d("Tag", "'" + item.getIngredient() + "' deleted successfully, hope you're happy now...");

                            HelperFragmentADay.checkIfItemsAreEmpty(binding, ItemsAdapter.this);
                            HelperFragmentADay.calculateTotalNutrients(context, binding, ItemsAdapter.this);
                        }
                    })
                    .setNegativeButton("Changed my mind", new DialogInterface.OnClickListener() {
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

        holder.chipProtein.setText(String.format("%d", (int) item.getProtein()));
        holder.chipCalories.setText(String.format("%d", (int) item.getCalories()));
        holder.chipCarbohydrates.setText(String.format("%d", (int) item.getCarbohydrates()));
        holder.chipSugar.setText(String.format("%d", (int) item.getSugar()));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.come_from_left);
        holder.linearLayout.startAnimation(animation);

        holder.constraintLayoutItem.setOnClickListener(view -> {
            if (holder.linearLayoutMacroChips.isShown()){
                holder.linearLayoutMacroChips.setVisibility(View.GONE);
                if (isNightModeOn) holder.imageViewItemIcon.setImageResource(R.drawable.ic_arrow_down_white);
                else holder.imageViewItemIcon.setImageResource(R.drawable.ic_arrow_down_black);
            } else {
                holder.linearLayoutMacroChips.setVisibility(View.VISIBLE);
                if (isNightModeOn) holder.imageViewItemIcon.setImageResource(R.drawable.ic_arrow_right_white);
                else holder.imageViewItemIcon.setImageResource(R.drawable.ic_arrow_right_black);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout linearLayout;
        protected ConstraintLayout constraintLayoutItem;
        protected LinearLayout linearLayoutMacroChips;

        protected ImageView imageViewItemIcon;
        protected TextView textViewIngredient;
        protected Chip chipProtein;
        protected Chip chipCarbohydrates;
        protected Chip chipCalories;
        protected Chip chipSugar;
        protected ImageView imageViewRemove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.mainLayoutSingleItem);
            this.imageViewItemIcon = itemView.findViewById(R.id.imageViewItemIcon);
            this.constraintLayoutItem = itemView.findViewById(R.id.constraintLayoutItem);
            this.linearLayoutMacroChips = itemView.findViewById(R.id.linearLayoutMacroChips);

            this.textViewIngredient = itemView.findViewById(R.id.textViewSingleItemTitle);
            this.chipProtein = itemView.findViewById(R.id.textViewActualProteins);
            this.chipCarbohydrates = itemView.findViewById(R.id.textViewActualCarbs);
            this.chipCalories = itemView.findViewById(R.id.textViewActualCalories);
            this.chipSugar = itemView.findViewById(R.id.textViewActualSugar);
            this.imageViewRemove = itemView.findViewById(R.id.imageViewRemoveItem);
        }
    }

    private void additionalThemeChanges(MyViewHolder holder) {
        if (isNightModeOn) {
            int colorWhite = ContextCompat.getColor(context, R.color.white);
            holder.imageViewItemIcon.setImageResource(R.drawable.ic_arrow_down_white);
            holder.textViewIngredient.setTextColor(colorWhite);
        } else {
            int colorBlack = ContextCompat.getColor(context, R.color.black);
            holder.imageViewItemIcon.setImageResource(R.drawable.ic_arrow_down_black);
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
