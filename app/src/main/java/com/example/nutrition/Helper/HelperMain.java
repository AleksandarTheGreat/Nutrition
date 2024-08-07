package com.example.nutrition.Helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nutrition.Activities.MainActivity;
import com.example.nutrition.Activities.Section1And2Activity;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;

public class HelperMain {

    private Context context;
    public HelperMain(Context context){
        this.context = context;
    }

    public void setUpCardEventListeners(MaterialCardView [] materialCardViews, ActivityMainBinding binding) {
        for (MaterialCardView materialCardView : materialCardViews) {
            materialCardView.setOnClickListener(view -> {
                String text = materialCardView.getTag().toString().trim();

                Intent intent = new Intent(context, Section1And2Activity.class);
                intent.putExtra("category", text);

                context.startActivity(intent);
            });
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
}
