package com.example.nutrition.Helper;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Model.Product;
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
        List<String> subList = ContentLoader.createTestList()
                .stream()
                .map(product -> product.getName())
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, subList);
        binding.autoCompleteTextViewFragmentADay.setAdapter(adapter);
    }

    public void addProduct(FragmentADayBinding binding, ProductsAdapter productsAdapter){
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
            productsAdapter.getProductList().add(optionalProduct.get());
            productsAdapter.notifyDataSetChanged();

            binding.autoCompleteTextViewFragmentADay.setText("");
        } else {
            toaster.text("No such product");
        }
    }

    public void checkEmptyLayout(FragmentADayBinding binding) {
        if (binding.recyclerViewFragmentADay.getChildCount() != 0) {
            binding.textViewNoItemsFragmentADay.setVisibility(View.INVISIBLE);
        } else {
            binding.textViewNoItemsFragmentADay.setVisibility(View.VISIBLE);
        }
    }
}
