package com.example.nutrition.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nutrition.Fragments.FragmentIntroduction;
import com.example.nutrition.Fragments.FragmentMythReality;
import com.example.nutrition.Model.MRModel;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.R;

import java.util.ArrayList;
import java.util.List;

public class MyIntroFragAdapter extends FragmentStateAdapter {

    private Context context;
    private List<Fragment> fragmentList;
    private AppCompatActivity appCompatActivity;

    public MyIntroFragAdapter(Context context, FragmentActivity fragmentActivity, AppCompatActivity appCompatActivity) {
        super(fragmentActivity);

        this.context = context;
        this.appCompatActivity = appCompatActivity;
        setUpFragmentList();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    private void setUpFragmentList() {
        this.fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Protein", "Proteins are large biomolecules and macromolecules that comprise one or more long chains of amino acid residues.\n\nIn short terms they are important for muscle growth", R.drawable.ic_steak), appCompatActivity));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Carbohydrate", "Carbohydrates, or carbs, are sugar molecules.\n\nAlong with proteins and fats, carbohydrates are one of three main nutrients found in foods and drinks.\n\nYour body breaks down carbohydrates into glucose. Glucose, or blood sugar, is the main source of energy for your body's cells, tissues, and organs.", R.drawable.ic_wheat), appCompatActivity));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Calorie", "A calorie is a unit of energy.\n\nIn nutrition, calories refer to the energy people get from the food and drinks they consume, and the energy they use in physical activity. Calories are listed in the nutritional information on all food packaging.\n\nMany weight loss programs center around reducing the intake of calories.", R.drawable.ic_olive_oil), appCompatActivity));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Sugar", "Sugars are the smallest unit of carbohydrates.\n\nThey include both naturally occurring sugars (like those found in fruits and milk) and added sugars (like table sugar or high-fructose corn syrup).", R.drawable.ic_sugar), appCompatActivity));

        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You must eat 3 times a day!", "If you can match your requirements for the day, you can eat as many times as you like.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You must have protein after a workout, if not, you will not have any hypertrophy benefits from the workout.", "Proteins can take a long time in order to be digested and absorbed by the body, so even if you eat a protein meal in the morning, your body will still benefit from its protein even in later parts of the day.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You must drink 3 litres of water a day!", "You should drink water when you are thirsty.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("'I am eating only healthy foods, why am I gaining weight?'", "Weight gain doesn't have any correspondence with 'healthy' or 'unhealthy' food. The number of kcal is important.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You can't lose weight with eating your favourite food because that food is chocolate.", "You can ABSOLUTELY lose weight if you are in a kcal deficit, even with eating chocolate every day.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("After eating carbs a hormone named 'insulin' will be secreted in the blood stream and it will ruin your focus and does not have any benefits.", "Insulin is the main anabolic (for growth) hormone in our bodies and it has a lot of benefits for them. It carries sugar to our cells, which is the main source of food for our body, in need of the body it can transform it in fat and it even plays a key role in protein synthesis which means insulin is really important in muscle hypertrophy.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You shouldn't eat carbs, they will be transformed to fat immediately!", "After meeting the daily caloric requirements for the body, every carb eaten after that will be stored as reserve sugar (glycogen) AND AFTER it is full, THEN every carb after will be transformed into fat.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("Why are you eating chocolate, it's not healthy, eat honey instead!", "Honey has its own benefits yes, but the body will react the same when you eat honey or chocolate.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You mustn't eat any carbs at night, they will lower the secretion of 'growth hormone' so your workout will be for nothing.", "You can eat any carbs at any time of the day, people tell this lie because growth hormone and the 'sugar hormone (insulin) are antagonists of each other.\n" + "So the insulin will do its work when the carb is eaten, then the growth hormone will do its job, and NO, your workout will not be a waste of time.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("If you want to have visible abs you must workout.", "The only important thing for having visible abs is being in a caloric deficit.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("Why are you active and fit when you are still eating sugary foods after the workout, that doesn't make any sense, they aren't healthy.", "Actually, one of the smartest things to do after a workout is to replenish your energy as fast as possible, so the sugary foods can be absorbed a lot quicker than fruit for example and help us refuel quicker.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("Don't mix carbs, fats and protein in one meal, our body can't digest them at once.", "Our body has juices to dissolve carbs, fats and proteins in any time, so there is no problem in mixing them up. Mix your food as you wish")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("Don't eat fats, they are unhealthy!", "Eat fats on a daily basis, they are key for optimal hormonal function and with it the function of the whole body.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You should only drink water in order to be properly hydrated.", "Actually, as weird as it may seem, drinking water isn't directly connected with hydration, but having an electrolyte balance does.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You should train AT LEAST for an hour, if not, you will not grow your muscles.", "The time in the gym is not even important, just give it your ALL in the time that you will be in the gym.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("Intermittent fasting is healthier than eating whenever you want.", "As long as the caloric intake is the same, there is no difference in having an intermittent fast or not.")));
        fragmentList.add(new FragmentMythReality(appCompatActivity, new MRModel("You must do cardio in order to lose weight!", "The most important thing in losing weight is being in a caloric deficit.")));
    }
}
