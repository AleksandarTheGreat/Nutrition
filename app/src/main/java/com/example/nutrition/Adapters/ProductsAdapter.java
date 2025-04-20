package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private final Context context;
    private List<Product> productList;

    private boolean isNightModeOn = false;
    private Toaster toaster;

    public ProductsAdapter(Context context, AppCompatActivity appCompatActivity, List<Product> productList){
        this.context = context;
        this.productList = productList;

        this.toaster = new Toaster(context);
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @NonNull
    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_product_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        if (isNightModeOn){
            myViewHolder.viewMask.setBackground(ContextCompat.getDrawable(context, R.drawable.dark_mask_45_angle));
            myViewHolder.textViewName.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            myViewHolder.viewMask.setBackground(ContextCompat.getDrawable(context, R.drawable.light_mask_45_angle));
            myViewHolder.textViewName.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        myViewHolder.progressBarProtein.setMax(80);
        myViewHolder.progressBarCalories.setMax(902);
        myViewHolder.progressBarCarbs.setMax(100);
        myViewHolder.progressBarSugar.setMax(100);

        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewName.setText(product.getName());

        if (product.getImageUri() != null){
            Picasso.get()
                    .load(Uri.parse(product.getImageUri()))
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }

        holder.textViewProteinNumber.setText(product.getProtein() + "g");
        holder.textViewCarbsNumber.setText(product.getCarbs() + "g");
        holder.textViewCaloriesNumber.setText(product.getCalories() + "g");
        holder.textViewSugarNumber.setText(product.getSugar() + "g");

        holder.progressBarProtein.setProgress((int) product.getProtein());
        holder.progressBarCarbs.setProgress((int) product.getCarbs());
        holder.progressBarCalories.setProgress((int) product.getCalories());
        holder.progressBarSugar.setProgress((int) product.getSugar());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.come_from_left);
        holder.constraintLayout.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        protected ConstraintLayout constraintLayout;
        protected View viewMask;

        protected ImageView imageView;
        protected TextView textViewName;

        protected TextView textViewProteinNumber;
        protected TextView textViewCarbsNumber;
        protected TextView textViewCaloriesNumber;
        protected TextView textViewSugarNumber;

        protected ProgressBar progressBarProtein;
        protected ProgressBar progressBarCarbs;
        protected ProgressBar progressBarCalories;
        protected ProgressBar progressBarSugar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.constraintLayout = itemView.findViewById(R.id.constraintLayoutSingleProduct);
            this.viewMask = itemView.findViewById(R.id.viewMaskSingleProduct);

            this.imageView = itemView.findViewById(R.id.imageViewSingleProduct);
            this.textViewName = itemView.findViewById(R.id.textViewNameSingleProduct);

            this.textViewProteinNumber = itemView.findViewById(R.id.textViewActualProteins);
            this.textViewCarbsNumber = itemView.findViewById(R.id.textViewActualCarbs);
            this.textViewCaloriesNumber = itemView.findViewById(R.id.textViewActualCalories);
            this.textViewSugarNumber = itemView.findViewById(R.id.textViewActualSugar);

            this.progressBarProtein = itemView.findViewById(R.id.progressBarProteins);
            this.progressBarCarbs = itemView.findViewById(R.id.progressBarCarbs);
            this.progressBarCalories = itemView.findViewById(R.id.progressBarCalories);
            this.progressBarSugar = itemView.findViewById(R.id.progressBarSugar);
        }
    }

    private void createDialog(Product product){
        View view = LayoutInflater.from(context)
                .inflate(R.layout.custom_image_dialog, null);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView imageView = view.findViewById(R.id.imageViewProduct);
        Picasso.get()
                .load(product.getImageUri())
                .into(imageView);

        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public boolean isListEmpty(){
        return productList != null && productList.isEmpty();
    }
}
