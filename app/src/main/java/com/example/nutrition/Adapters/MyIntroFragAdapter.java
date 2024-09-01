package com.example.nutrition.Adapters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nutrition.Fragments.FragmentIntroduction;
import com.example.nutrition.Fragments.FragmentMythReality;
import com.example.nutrition.Fragments.FragmentQuiz;
import com.example.nutrition.Model.MRModel;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.Model.Question;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

public class MyIntroFragAdapter extends FragmentStateAdapter {

    private Context context;
    private List<Fragment> fragmentList;
    private AppCompatActivity appCompatActivity;
    private boolean isNightModeActive = false;

    public MyIntroFragAdapter(Context context, FragmentActivity fragmentActivity, AppCompatActivity appCompatActivity, String type) {
        super(fragmentActivity);

        this.context = context;
        this.appCompatActivity = appCompatActivity;

        this.isNightModeActive = ThemeUtils.isNightModeActive(appCompatActivity);

        switch (type) {
            case "definitions":
                setUpDefinitionFragments();
                break;
            case "mythFact":
                setUpMythFactFragments();
                break;
            case "quiz":
                setUpQuizFragments();
                break;
            default:
                Log.d("Tag", "IDK what happens now");
                break;
        }
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

    private void setUpDefinitionFragments(){
        fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Protein", "Proteins are large biomolecules and macromolecules that comprise one or more long chains of amino acid residues.\n\nIn short terms they are important for muscle growth", R.drawable.ic_steak), isNightModeActive));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Carbohydrate", "Carbohydrates, or carbs, are sugar molecules.\n\nAlong with proteins and fats, carbohydrates are one of three main nutrients found in foods and drinks.\n\nYour body breaks down carbohydrates into glucose. Glucose, or blood sugar, is the main source of energy for your body's cells, tissues, and organs.", R.drawable.ic_wheat), isNightModeActive));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Calorie", "A calorie is a unit of energy.\n\nIn nutrition, calories refer to the energy people get from the food and drinks they consume, and the energy they use in physical activity. Calories are listed in the nutritional information on all food packaging.\n\nMany weight loss programs center around reducing the intake of calories.", R.drawable.ic_olive_oil), isNightModeActive));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Sugar", "Sugars are the smallest unit of carbohydrates.\n\nThey include both naturally occurring sugars (like those found in fruits and milk) and added sugars (like table sugar or high-fructose corn syrup).", R.drawable.ic_sugar), isNightModeActive));
    }

    private void setUpMythFactFragments() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You must eat 3 times a day!", "If you can match your requirements for the day, you can eat as many times as you like.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You must have protein after a workout, if not, you will not have any hypertrophy benefits from the workout.", "Proteins can take a long time in order to be digested and absorbed by the body, so even if you eat a protein meal in the morning, your body will still benefit from its protein even in later parts of the day.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You must drink 3 litres of water a day!", "You should drink water when you are thirsty.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("'I am eating only healthy foods, why am I gaining weight?'", "Weight gain doesn't have any correspondence with 'healthy' or 'unhealthy' food. The number of kcal is important.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You can't lose weight with eating your favourite food because that food is chocolate.", "You can ABSOLUTELY lose weight if you are in a kcal deficit, even with eating chocolate every day.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("After eating carbs a hormone named 'insulin' will be secreted in the blood stream and it will ruin your focus and does not have any benefits.", "Insulin is the main anabolic (for growth) hormone in our bodies and it has a lot of benefits for them. It carries sugar to our cells, which is the main source of food for our body, in need of the body it can transform it in fat and it even plays a key role in protein synthesis which means insulin is really important in muscle hypertrophy.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You shouldn't eat carbs, they will be transformed to fat immediately!", "After meeting the daily caloric requirements for the body, every carb eaten after that will be stored as reserve sugar (glycogen) AND AFTER it is full, THEN every carb after will be transformed into fat.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("Why are you eating chocolate, it's not healthy, eat honey instead!", "Honey has its own benefits yes, but the body will react the same when you eat honey or chocolate.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You mustn't eat any carbs at night, they will lower the secretion of 'growth hormone' so your workout will be for nothing.", "You can eat any carbs at any time of the day, people tell this lie because growth hormone and the 'sugar hormone (insulin) are antagonists of each other.\n" + "So the insulin will do its work when the carb is eaten, then the growth hormone will do its job, and NO, your workout will not be a waste of time.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("If you want to have visible abs you must workout.", "The only important thing for having visible abs is being in a caloric deficit.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("Why are you active and fit when you are still eating sugary foods after the workout, that doesn't make any sense, they aren't healthy.", "Actually, one of the smartest things to do after a workout is to replenish your energy as fast as possible, so the sugary foods can be absorbed a lot quicker than fruit for example and help us refuel quicker.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("Don't mix carbs, fats and protein in one meal, our body can't digest them at once.", "Our body has juices to dissolve carbs, fats and proteins in any time, so there is no problem in mixing them up. Mix your food as you wish")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("Don't eat fats, they are unhealthy!", "Eat fats on a daily basis, they are key for optimal hormonal function and with it the function of the whole body.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You should only drink water in order to be properly hydrated.", "Actually, as weird as it may seem, drinking water isn't directly connected with hydration, but having an electrolyte balance does.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You should train AT LEAST for an hour, if not, you will not grow your muscles.", "The time in the gym is not even important, just give it your ALL in the time that you will be in the gym.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("Intermittent fasting is healthier than eating whenever you want.", "As long as the caloric intake is the same, there is no difference in having an intermittent fast or not.")));
        fragmentList.add(new FragmentMythReality(isNightModeActive, new MRModel("You must do cardio in order to lose weight!", "The most important thing in losing weight is being in a caloric deficit.")));
    }

    private void setUpQuizFragments() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(1, "How many times should you eat per day?", "once, but after an activity", "twice, whenever you like", "as many times as you like", "three times (breakfast, lunch and dinner)", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(2, "Why are fats important?", "they are not important, they are bad", "they will only keep you warm;", "they protect us from injuries", "they regulate hormonal and bodily functions", "radioD")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(3, "When is the right time to eat?", "it doesn’t matter", "only in the morning", "every part of the day except night time", "intermittent fasting (no eating 8AM-4PM)", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(4, "When should you drink water?", "when you are thirsty", "immediately after waking up", "only during a workout", "only when you are eating food", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(5, "How much water should you drink per day?", "5 litres", "drink in order to not be thirsty, there is no correct quantity", "3 litres", "drink a lot of water, as much as you can", "radioB")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(6, "When will you gain fat?", "whenever you eat fats", "immediately after you eat carbs", "when you are in a big caloric surplus", "when you eat only pizzas", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(7, "How many kcal (calories) are in 1g of fat?", "4 kcal", "5 kcal", "9 kcal", "7 kcal", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(8, "What should you eat when you are going to the gym?", "a mixture of ‘healthy’ and fast food if the bodily needs are fulfilled", "only ‘healthy’ food", "only fast food" ,"you should only stay away from carbs", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(9, "Why is insulin important?", "it improves focus", "it is not important", "it is the main anabolic hormone" ,"it lowers blood sugar levels", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(10, "Should you mix different types of food at once?", "absolutely", "no, harm will be done to the brain", "you should not do that! The body can not deal with them at once!" ,"it is not advisable", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(11, "How long should you stay in the gym?", "at least 1 hour", "no more than 2 hours", "30 minutes with giving everything that you have" ,"time is not important if you train with high intensity", "radioD")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(12, "What to do in order to lose fat?", "you should only do cardio", "you should not eat anything for a week", "stay in a caloric deficit" ,"you must go to the gym", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(13, "How many kcal (calories) are in 1g of carbs?", "9 kcal", "4 kcal", "5 kcal" ,"7 kcal", "radioB")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(14, "What to do in order to be properly hydrated", "just drink water", "drink water, milk and tea", "only drink electrolytes" ,"use both water and electrolytes", "radioD")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(15, "How many kcal (calories) are in 1g of protein?", "5 kcal", "4 kcal", "9 kcal" ,"7 kcal", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(16 , "What should you eat before going to the gym?", "you should not eat anything", "you should eat only protein", "you should do what suits you best" ,"you should eat only carbs", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(17 , "When should you eat protein?", "there is no specific time", "in the morning and at dinner", "it is not important, but after going to the gym it is a must" ,"only before and after going to the gym", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(18 , "What do carbs do in the body?", "they make you lose focus", "they make you fatter", "they are the main energy source for our body" ,"they decrease blood sugar levels", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(19 , "Which hormone is the main anabolic hormone?", "insulin", "glucagon", "adrenaline" ,"noradrenaline", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(20 , "Why is fiber important?", "it increases blood sugar levels", "it is a carb, it can not do good stuff", "it improves bowel movements and eases the work of the pancreas" ,"it is good for oral health", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(21, "Why are fruits and vegetables different?", "they are colorful", "they contain only sugar in them", "they contain fiber", "they are calorie dense", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(22, "Which protein source contains the most protein per 100g?", "eggs", "greek yoghurt", "lean meat", "peanut butter", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(23, "Which protein source is the best?", "the source that is your favourite", "meat", "plant protein", "dairy products", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(24, "How many kcal (calories) are in 1g of alcohol?", "9 kcal", "7 kcal", "5 kcal", "4 kcal", "radioB")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(25, "Which of the following contains calories?", "vitamins", "minerals", "proteins", "salt", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(26, "Which form of cardio is the best?", "what you want to do the most is always the best", "walking and running", "walking and cycling", "playing sports", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(27, "Which of the following contains the most sugar per 100g?", "milk chocolate", "dates", "dark chocolate", "banana", "radioB")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(28, "Which of the following contains the most fats per 100g?", "peanut butter", "chicken breast", "eggs", "avocado", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(29, "Which of the following has the most calories per 100g?", "olive oil", "ground beef", "milk chocolate", "pepperoni pizza", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(30, "Which of the following contains the least amount of calories per 100g?", "almonds", "dark chocolate", "dates", "fat free greek yoghurt", "radioD")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(31, "When will the body start losing fat?", "when there is no more sugar, so it must start burning fat", "when the person eats only fruits and vegetables", "when the person runs every day, but eats a lot after", "when the reserve sugar in the body is full", "radioA")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(32, "How can you know how much do you eat?", "by asking google", "by measuring by eye", "by measuring with a food scale", "you can not know how much you eat", "radioC")));
        fragmentList.add(new FragmentQuiz(isNightModeActive, new Question(33, "Which of these macronutrients is the most important?", "proteins", "carbs", "fats", "there is no such thing as ‘the most important nutrient", "radioD")));

    }
}
