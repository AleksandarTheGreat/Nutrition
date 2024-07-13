package com.example.nutrition.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;

import org.w3c.dom.Text;

import java.net.ProtocolException;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private final Context context;
    private List<Product> productList;

    private Toaster toaster;

    public ItemsAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;

        this.toaster = new Toaster(context);
    }

    @NonNull
    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_product_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.progressBarProtein.setMax(80);
        myViewHolder.progressBarCarbs.setMax(100);
        myViewHolder.progressBarCalories.setMax(902);
        myViewHolder.progressBarSugar.setMax(100);

        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewName.setText(product.getName());

        holder.textViewProteinNumber.setText(product.getProtein() + " g");
        holder.textViewCarbsNumber.setText(product.getCarbs() + " g");
        holder.textViewCaloriesNumber.setText(product.getCalories() + " g");
        holder.textViewSugarNumber.setText(product.getSugar() + " g");

        holder.progressBarProtein.setProgress((int) product.getProtein());
        holder.progressBarCarbs.setProgress((int) product.getCarbs());
        holder.progressBarCalories.setProgress((int) product.getCalories());
        holder.progressBarSugar.setProgress((int) product.getSugar());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

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
            this.imageView = itemView.findViewById(R.id.imageViewSingleProduct);
            this.textViewName = itemView.findViewById(R.id.textViewNameSingleProduct);

            this.textViewProteinNumber = itemView.findViewById(R.id.textViewProteinNumberSingleProduct);
            this.textViewCarbsNumber = itemView.findViewById(R.id.textViewCarbsNumberSingleProduct);
            this.textViewCaloriesNumber = itemView.findViewById(R.id.textViewCaloriesNumberSingleProduct);
            this.textViewSugarNumber = itemView.findViewById(R.id.textViewSugarNumberSingleProduct);

            this.progressBarProtein = itemView.findViewById(R.id.progressBarProteinSingleProduct);
            this.progressBarCarbs = itemView.findViewById(R.id.progressBarCarbsSingleProduct);
            this.progressBarCalories = itemView.findViewById(R.id.progressBarCaloriesSingleProduct);
            this.progressBarSugar = itemView.findViewById(R.id.progressBarSugarSingleProduct);
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
