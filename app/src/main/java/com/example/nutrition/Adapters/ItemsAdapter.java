package com.example.nutrition.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Item;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private Context context;
    private List<Item> itemList;
    private ItemsRepo itemsRepo;
    private Day day;

    public ItemsAdapter(Context context, ItemsRepo itemsRepo, Day day){
        this.context = context;
        this.itemsRepo = itemsRepo;
        this.day = day;

        this.itemList = day.getItemList();
    }

    @NonNull
    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_item_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Item item = itemList.get(myViewHolder.getAdapterPosition());

                itemsRepo.delete(item.getId());
                itemList.remove(item);

                notifyItemRemoved(myViewHolder.getAdapterPosition());
                Log.d("Tag", "Item '" + item.getIngredient() + "' deleted");
                return true;
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.MyViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.textView.setText(item.toString());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textViewSingleItem);
        }
    }

    public boolean isListEmpty(){
        return itemList != null && itemList.isEmpty();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
