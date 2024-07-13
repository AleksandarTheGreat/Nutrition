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
                materialCardView.setChecked(!materialCardView.isChecked());
            });

            materialCardView.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                    RelativeLayout relativeLayout = (RelativeLayout) card.getChildAt(0);
                    TextView textView = (TextView) relativeLayout.getChildAt(0);
                    if (isChecked){
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        textView.setTypeface(Typeface.DEFAULT);
                    }

                    binding.buttonListMainActivity.setEnabled(aCardIsChecked(materialCardViews));
                }
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
