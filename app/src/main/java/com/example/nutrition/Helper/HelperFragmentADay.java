package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Model.Product;
import com.example.nutrition.Repos.ProductsRepo;
import com.example.nutrition.databinding.FragmentADayBinding;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HelperFragmentADay {
    private Context context;
    private Toaster toaster;
    public HelperFragmentADay(Context context){
        this.context = context;
        this.toaster = new Toaster(context);
    }

    public void loadProductsToAutoComplete(FragmentADayBinding binding){
        List<String> subList = ContentLoader
                .createTestList()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, subList);
        binding.autoCompleteTextViewFragmentADay.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addProduct(FragmentADayBinding binding, ProductsAdapter productsAdapter, ProductsRepo productsRepo, long d_id){
        String text = binding.autoCompleteTextViewFragmentADay.getText().toString().trim();

        if (text.isEmpty()) {
            binding.autoCompleteTextViewFragmentADay.setError("Invalid search");
            return;
        }

        Optional<Product> optionalProduct = ContentLoader
                .createTestList()
                .stream()
                .filter(product1 -> product1.getName().equals(text))
                .collect(Collectors.toList())
                .stream()
                .findFirst();

        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();

            productsRepo.add(product, d_id);

            productsAdapter.setProductList(productsRepo.listAll(d_id));
            productsAdapter.notifyDataSetChanged();

            binding.autoCompleteTextViewFragmentADay.setText("");
        } else {
            toaster.text("No such product");
        }
    }

    public void checkEmptyLayout(FragmentADayBinding binding, ProductsAdapter productsAdapter) {
        if (!productsAdapter.getProductList().isEmpty()) {
            binding.textViewNoItemsFragmentADay.setVisibility(View.INVISIBLE);
        } else {
            binding.textViewNoItemsFragmentADay.setVisibility(View.VISIBLE);
        }
    }
}
