package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutrition.Adapters.ItemsAdapter;
import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Model.Item;
import com.example.nutrition.Model.Product;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.databinding.FragmentADayBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HelperFragmentADay {
    private Context context;
    private Toaster toaster;

    public HelperFragmentADay(Context context) {
        this.context = context;
        this.toaster = new Toaster(context);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addProduct(FragmentADayBinding binding, ItemsAdapter itemsAdapter, ItemsRepo itemsRepo, long d_id) {
        String searchedText = binding.searchViewFragmentADay.getQuery().toString().trim();
        if (!checkIfInputIsValid(binding)) return;

        // Optional
        // Check if the date is valid (we are not in a past date)

        String apiKey = "73852b082619618ae5eb814953750853";
        String appId = "e7be29f8";
        String baseUrl = "https://api.edamam.com/api/nutrition-data";
        String url = baseUrl + "?app_id=" + appId + "&app_key=" + apiKey + "&ingr=" + searchedText;


        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Tag", "Response of food item '" + searchedText + "' successful");

                        float calories = 0.0F;
                        float protein = 0.0F;
                        float carbohydrates = 0.0F;
                        float sugar = 0.0F;

                        try {
                            JSONArray ingredients = response.getJSONArray("ingredients");
                            if (ingredients.length() > 0) {
                                JSONObject ingredient = ingredients.getJSONObject(0);
                                JSONArray parsedIngredients = ingredient.getJSONArray("parsed");
                                if (parsedIngredients.length() > 0) {
                                    JSONObject parsedIngredient = parsedIngredients.getJSONObject(0);
                                    JSONObject nutrients = parsedIngredient.getJSONObject("nutrients");

                                    String ingredientName = ingredient.getString("text");

                                    protein = (float) nutrients.getJSONObject("PROCNT").getDouble("quantity");
                                    carbohydrates = (float) nutrients.getJSONObject("CHOCDF").getDouble("quantity");
                                    calories = (float) nutrients.getJSONObject("ENERC_KCAL").getDouble("quantity");
                                    sugar = (float) nutrients.getJSONObject("SUGAR").getDouble("quantity");

                                    @SuppressLint("DefaultLocale")
                                    String result = String.format("Ingredient: %s\n\nProtein: %.2f\nCalories: %.2f\nCarbohydrates: %.2f\nSugar: %.2f\n",
                                            ingredientName, protein, calories, carbohydrates, sugar);

                                    Log.d("Tag", result);

                                    Item item = new Item(searchedText, protein, carbohydrates, calories, sugar, d_id);

                                    itemsRepo.add(item, d_id);
                                    itemsAdapter.setItemList(itemsRepo.listAll(d_id));
                                    itemsAdapter.notifyDataSetChanged();

                                    binding.searchViewFragmentADay.setQuery("", false);
                                    HelperFragmentADay.checkIfItemsAreEmpty(binding, itemsAdapter);

                                    HelperFragmentADay.calculateTotalNutrients(binding, itemsAdapter);
                                    // Calculate total and change the ui in the material cards

                                    /**
                                    // Add the string search text to a database table
                                     */

                                    progressDialog.dismiss();
                                    Log.d("Tag", "Added '" + searchedText + "' to repo");
                                }
                            }
                        } catch (JSONException e) {
                            Log.d("Tag", "Error: " + e.getMessage());
                            toaster.text("Some nutrients are missing sadly :(");
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Tag", error.getMessage());
                toaster.text("Response error");
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    private boolean checkIfInputIsValid(FragmentADayBinding binding) {
        String searchedText = binding.searchViewFragmentADay.getQuery().toString().trim();
        return !searchedText.isEmpty();
    }

    public static void checkIfItemsAreEmpty(FragmentADayBinding binding, ItemsAdapter itemsAdapter) {
        if (itemsAdapter.isListEmpty()) {
            binding.textViewNoItemsFragmentADay.setVisibility(View.VISIBLE);
        } else {
            binding.textViewNoItemsFragmentADay.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public static void calculateTotalNutrients(FragmentADayBinding binding, ItemsAdapter itemsAdapter){
        float totalProtein = 0;
        float totalCalories = 0;
        float totalCarbs = 0;
        float totalSugars = 0;

        for (Item item: itemsAdapter.getItemList()){
            totalProtein += item.getProtein();
            totalCalories += item.getCalories();
            totalCarbs += item.getCarbohydrates();
            totalSugars += item.getSugar();
        }

        binding.textViewNutrient1Number.setText(String.format("%.2f", totalProtein));
        binding.textViewNutrient2Number.setText(String.format("%.2f", totalCalories));
        binding.textViewNutrient3Number.setText(String.format("%.2f", totalCarbs));
        binding.textViewNutrient4Number.setText(String.format("%.2f", totalSugars));
    }
}
