package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutrition.Adapters.ItemsAdapter;
import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Model.CustomMacros;
import com.example.nutrition.Model.Item;
import com.example.nutrition.Model.Product;
import com.example.nutrition.Model.Suggestion;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.Repos.SuggestionsRepo;
import com.example.nutrition.SharedPrefs.SharedPrefCustomMacros;
import com.example.nutrition.databinding.FragmentADayBinding;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.format.TextStyle;
import java.util.Comparator;
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
    public void addItem(FragmentADayBinding binding, SuggestionsRepo suggestionsRepo, ItemsAdapter itemsAdapter, ItemsRepo itemsRepo, long d_id) {
        String searchedText = binding.searchViewFragmentADay.getQuery().toString().trim();
        if (searchedText.isEmpty()) return;

        // Optional
        // Check if the date is valid (we are not in a past date)

        String url = EdamamAPI.getUrl(searchedText);

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Analyzing");
        progressDialog.setMessage("Please be patient while we are calculating your ingredient");
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

                                    try {
                                        protein = (float) nutrients.getJSONObject("PROCNT").getDouble("quantity");
                                    } catch (JSONException e) {
                                        protein = 0;
                                    }

                                    try {
                                        carbohydrates = (float) nutrients.getJSONObject("CHOCDF").getDouble("quantity");
                                    } catch (JSONException e) {
                                        carbohydrates = 0;
                                    }

                                    try {
                                        calories = (float) nutrients.getJSONObject("ENERC_KCAL").getDouble("quantity");
                                    } catch (JSONException e) {
                                        calories = 0;
                                    }

                                    try {
                                        sugar = (float) nutrients.getJSONObject("SUGAR").getDouble("quantity");
                                    } catch (JSONException e) {
                                        sugar = 0;
                                    }

                                    @SuppressLint("DefaultLocale")
                                    String result = String.format("Ingredient: %s\nProtein: %.2f\nCalories: %.2f\nCarbohydrates: %.2f\nSugar: %.2f\n",
                                            ingredientName, protein, calories, carbohydrates, sugar);

                                    Log.d("Tag", result);

                                    Item item = new Item(searchedText, protein, carbohydrates, calories, sugar, d_id);

                                    itemsRepo.add(item, d_id);
                                    itemsAdapter.setItemList(itemsRepo.listAll(d_id));
                                    itemsAdapter.notifyDataSetChanged();

                                    // This triggers the onTextChange() event which is responsible for the suggestions
                                    binding.searchViewFragmentADay.setQuery("", false);

                                    HelperFragmentADay.checkIfItemsAreEmpty(binding, itemsAdapter);
                                    HelperFragmentADay.calculateTotalNutrients(context, binding, itemsAdapter);
                                    // Calculate total and change the ui in the material cards

                                    // Add the string search text to a database table

                                    if (!suggestionsRepo.contains(searchedText))
                                        suggestionsRepo.add(searchedText);

                                    progressDialog.dismiss();

                                    Log.d("Tag", "Added '" + searchedText + "' to repo");
                                }
                            }
                        } catch (JSONException e) {
                            Log.d("Tag", "Error: " + e.getMessage());
                            toaster.text("Sorry, probably an unknown food ingredient, try again");
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

    public static void checkIfItemsAreEmpty(FragmentADayBinding binding, ItemsAdapter itemsAdapter) {
        if (itemsAdapter.isListEmpty()) {
            binding.linearLayoutEmptyItemsFragmentADay.setVisibility(View.VISIBLE);
        } else {
            binding.linearLayoutEmptyItemsFragmentADay.setVisibility(View.GONE);
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public static void calculateTotalNutrients(Context context, FragmentADayBinding binding, ItemsAdapter itemsAdapter) {
        float totalProtein = 0;
        float totalCalories = 0;
        float totalCarbs = 0;
        float totalSugars = 0;

        for (Item item : itemsAdapter.getItemList()) {
            totalProtein += item.getProtein();
            totalCalories += item.getCalories();
            totalCarbs += item.getCarbohydrates();
            totalSugars += item.getSugar();
        }

        CustomMacros customMacros = SharedPrefCustomMacros.readFromSharedPref(context);
        Log.d("Tag", customMacros.toString());

        if (customMacros.getProteins() < 0)
            binding.textViewLimitProteins.setText(String.format("%d / Not set", (int) totalProtein));
        else binding.textViewLimitProteins.setText(String.format("%d / %d", (int) totalProtein, customMacros.getProteins()));
        if (customMacros.getCalories() < 0)
            binding.textViewLimitCalories.setText(String.format("%d / Not set", (int) totalCalories));
        else binding.textViewLimitCalories.setText(String.format("%d / %d", (int) totalCalories, customMacros.getCalories()));
        if (customMacros.getCarbs() < 0)
            binding.textViewLimitCarbs.setText(String.format("%d / Not set", (int) totalCarbs));
        else binding.textViewLimitCarbs.setText(String.format("%d / %d", (int) totalCarbs, customMacros.getCarbs()));
        if (customMacros.getSugars() < 0)
            binding.textViewLimitSugars.setText(String.format("%d / Not set", (int) totalSugars));
        else binding.textViewLimitSugars.setText(String.format("%d / %d", (int) totalSugars, customMacros.getSugars()));

        binding.progressBarProteinsCircle.setMax(customMacros.getProteins());
        binding.progressBarCaloriesCircle.setMax(customMacros.getCalories());
        binding.progressBarCarbsCircle.setMax(customMacros.getCarbs());
        binding.progressBarSugarsCircle.setMax(customMacros.getSugars());

        binding.progressBarProteinsCircle.setProgress((int) totalProtein);
        binding.progressBarCaloriesCircle.setProgress((int) totalCalories);
        binding.progressBarCarbsCircle.setProgress((int) totalCarbs);
        binding.progressBarSugarsCircle.setProgress((int) totalSugars);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Typeface typefaceRubikBold = context.getResources().getFont(R.font.rubik_bold);
            Typeface typefaceRubikRegular = context.getResources().getFont(R.font.rubik_regular);

            if (totalProtein > customMacros.getProteins()) binding.textViewLimitProteins.setTypeface(typefaceRubikBold);
            else binding.textViewLimitProteins.setTypeface(typefaceRubikRegular);
            if (totalCalories > customMacros.getCalories()) binding.textViewLimitCalories.setTypeface(typefaceRubikBold);
            else binding.textViewLimitCalories.setTypeface(typefaceRubikRegular);
            if (totalCarbs > customMacros.getCarbs()) binding.textViewLimitCarbs.setTypeface(typefaceRubikBold);
            else binding.textViewLimitCarbs.setTypeface(typefaceRubikRegular);
            if (totalSugars > customMacros.getSugars()) binding.textViewLimitSugars.setTypeface(typefaceRubikBold);
            else binding.textViewLimitSugars.setTypeface(typefaceRubikRegular);
        }
    }

    public void showSuggestions(FragmentADayBinding binding) {
        binding.scrollViewSuggestions.setVisibility(View.VISIBLE);
        binding.relativeLayoutFragmentADay.setVisibility(View.GONE);
    }

    public void hideSuggestions(FragmentADayBinding binding) {
        binding.scrollViewSuggestions.setVisibility(View.GONE);
        binding.relativeLayoutFragmentADay.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    public void setUpTextViewsInTheScrollView(FragmentADayBinding binding, Context context, boolean isNightModeOn, List<Suggestion> suggestionList) {
        binding.linearLayoutSuggestionsFragmentADay.removeAllViews();

        // These layout creations take time, that is why they shall be created on a new thread
        int size = Math.min(50, suggestionList.size());
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                Suggestion suggestion = suggestionList.get(i);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 12);

                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setPadding(4, 24, 4, 24);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                linearLayout.setBackgroundResource(R.drawable.back_for_suggestion);


                layoutParams = new LinearLayout.LayoutParams(50, 50);
                layoutParams.setMargins(24, 12, 12, 12);

                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(layoutParams);
                if (isNightModeOn) imageView.setImageResource(R.drawable.ic_recent_light);
                else imageView.setImageResource(R.drawable.ic_recent_dark);


                layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(12, 12, 12, 12);

                TextView textView = new TextView(context);
                textView.setText(suggestion.getText());
                textView.setTextSize(16);
                textView.setLayoutParams(layoutParams);

                linearLayout.addView(imageView);
                linearLayout.addView(textView);

                handler.post(() -> {
                    binding.linearLayoutSuggestionsFragmentADay.addView(linearLayout);
                    linearLayout.setOnClickListener(view -> {
                        String text = textView.getText().toString().trim();
                        binding.searchViewFragmentADay.setQuery(text, false);
                    });
                });
            }

        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sortedListAccordingToMacronutrient(String macro, ItemsAdapter itemsAdapter) {
        List<Item> itemList;
        switch (macro) {
            case "proteins": {
                itemList = itemsAdapter.getItemList()
                        .stream()
                        .sorted(Comparator.comparing(Item::getProtein).reversed())
                        .collect(Collectors.toList());
                break;
            }
            case "calories": {
                itemList = itemsAdapter.getItemList()
                        .stream()
                        .sorted(Comparator.comparing(Item::getCalories).reversed())
                        .collect(Collectors.toList());
                break;
            }
            case "carbohydrates": {
                itemList = itemsAdapter.getItemList()
                        .stream()
                        .sorted(Comparator.comparing(Item::getCarbohydrates).reversed())
                        .collect(Collectors.toList());
                break;
            }
            case "sugar": {
                itemList = itemsAdapter.getItemList()
                        .stream()
                        .sorted(Comparator.comparing(Item::getSugar).reversed())
                        .collect(Collectors.toList());
                break;
            }
            default:
                itemList = itemsAdapter.getItemList();
        }

        itemsAdapter.setItemList(itemList);
        itemsAdapter.notifyDataSetChanged();
    }

    public void addClickEventsForMaterialCardViews(MaterialCardView[] materialCardViews, TextView[] textViewsMacros, ItemsAdapter itemsAdapter) {
        new Thread(() -> {
            for (int i = 0; i < materialCardViews.length; i++) {
                MaterialCardView cardView = materialCardViews[i];
                TextView textViewMacro = textViewsMacros[i];

                cardView.setOnClickListener(view -> {

                    for (int j = 0; j < materialCardViews.length; j++) {
                        MaterialCardView card = materialCardViews[j];
                        TextView textView = textViewsMacros[j];

                        if (cardView == card && textViewMacro == textView) {
                            String macronutrient = cardView.getTag().toString().trim();

                            card.setCardElevation(8);
                            textView.setTypeface(null, Typeface.BOLD);
                            sortedListAccordingToMacronutrient(macronutrient, itemsAdapter);
                        } else {
                            card.setCardElevation(0);
                            textView.setTypeface(null, Typeface.NORMAL);
                        }
                    }
                });
            }
        }).start();
    }
}
