package com.example.nutrition.Helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.nutrition.Activities.MainActivity;
import com.example.nutrition.Activities.Section1And2Activity;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;

public class HelperMain {

    private Context context;
    public HelperMain(Context context){
        this.context = context;
    }

    public void setUpCardEventListeners(MaterialCardView [] materialCardViews) {
        for (MaterialCardView materialCardView : materialCardViews) {
            materialCardView.setOnClickListener(view -> {
                String text = materialCardView.getTag().toString().trim();

                Intent intent = new Intent(context, Section1And2Activity.class);
                intent.putExtra("category", text);

                context.startActivity(intent);
            });
        }
    }

    public void setUpUIMasksOnCards(AppCompatActivity appCompatActivity, MaterialCardView [] materialCardView){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            for (MaterialCardView cardView: materialCardView){
                RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
                View view = relativeLayout.getChildAt(2);
                view.setBackground(ContextCompat.getDrawable(context, R.drawable.dark_list));
            }
        } else {
            for (MaterialCardView cardView: materialCardView){
                RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
                View view = relativeLayout.getChildAt(2);
                view.setBackground(ContextCompat.getDrawable(context, R.drawable.light_list));
            }
        }
    }

    public boolean aCardIsChecked(MaterialCardView [] materialCardViews) {
        for (MaterialCardView materialCardView : materialCardViews) {
            if (materialCardView.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public void goToActivity(Context context, Class clas){
        Intent intent = new Intent(context, clas);
        context.startActivity(intent);
    }

    public void goToActivity(Context context, Class clas, long id){
        Intent intent = new Intent(context, clas);
        intent.putExtra("dayId", id);
        context.startActivity(intent);
    }

    public void goToActivity(Context context, Class clas, String type){
        Intent intent = new Intent(context, clas);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
