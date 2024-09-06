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
import com.example.nutrition.Fragments.FragmentQuiz2Options;
import com.example.nutrition.Model.MRModel;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.Model.Question;
import com.example.nutrition.Model.Question2;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

        fragmentList.add(new FragmentIntroduction(new Macronutrient("Macronutrients", "In the following slides, you will learn about some generic and most commonly used TERMS in nutrition\n\n", R.mipmap.ic_launcher), isNightModeActive));
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
        List<Fragment> questionsList = new ArrayList<>();

        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(1, "How many times should you eat per day?", "once, but after an activity", "twice, whenever you like", "as many times as you like", "three times (breakfast, lunch and dinner)", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(2, "Why are fats important?", "they are not important, they are bad", "they will only keep you warm;", "they protect us from injuries", "they regulate hormonal and bodily functions", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(3, "When is the right time to eat?", "it doesn’t matter", "only in the morning", "every part of the day except night time", "intermittent fasting (no eating 8AM-4PM)", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(4, "When should you drink water?", "when you are thirsty", "immediately after waking up", "only during a workout", "only when you are eating food", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(5, "How much water should you drink per day?", "5 litres", "drink in order to not be thirsty, there is no correct quantity", "3 litres", "drink a lot of water, as much as you can", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(6, "When will you gain fat?", "whenever you eat fats", "immediately after you eat carbs", "when you are in a big caloric surplus", "when you eat only pizzas", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(7, "How many kcal (calories) are in 1g of fat?", "4 kcal", "5 kcal", "9 kcal", "7 kcal", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(8, "What should you eat when you are going to the gym?", "a mixture of ‘healthy’ and fast food if the bodily needs are fulfilled", "only ‘healthy’ food", "only fast food" ,"you should only stay away from carbs", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(9, "Why is insulin important?", "it improves focus", "it is not important", "it is the main anabolic hormone" ,"it lowers blood sugar levels", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(10, "Should you mix different types of food at once?", "absolutely", "no, harm will be done to the brain", "you should not do that! The body can not deal with them at once!" ,"it is not advisable", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(11, "How long should you stay in the gym?", "at least 1 hour", "no more than 2 hours", "30 minutes with giving everything that you have" ,"time is not important if you train with high intensity", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(12, "What to do in order to lose fat?", "you should only do cardio", "you should not eat anything for a week", "stay in a caloric deficit" ,"you must go to the gym", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(13, "How many kcal (calories) are in 1g of carbs?", "9 kcal", "4 kcal", "5 kcal" ,"7 kcal", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(14, "What to do in order to be properly hydrated", "just drink water", "drink water, milk and tea", "only drink electrolytes" ,"use both water and electrolytes", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(15, "How many kcal (calories) are in 1g of protein?", "5 kcal", "4 kcal", "9 kcal" ,"7 kcal", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(16 , "What should you eat before going to the gym?", "you should not eat anything", "you should eat only protein", "you should do what suits you best" ,"you should eat only carbs", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(17 , "When should you eat protein?", "there is no specific time", "in the morning and at dinner", "it is not important, but after going to the gym it is a must" ,"only before and after going to the gym", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(18 , "What do carbs do in the body?", "they make you lose focus", "they make you fatter", "they are the main energy source for our body" ,"they decrease blood sugar levels", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(19 , "Which hormone is the main anabolic hormone?", "insulin", "glucagon", "adrenaline" ,"noradrenaline", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(20 , "Why is fiber important?", "it increases blood sugar levels", "it is a carb, it can not do good stuff", "it improves bowel movements and eases the work of the pancreas" ,"it is good for oral health", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(21, "Why are fruits and vegetables different?", "they are colorful", "they contain only sugar in them", "they contain fiber", "they are calorie dense", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(22, "Which protein source contains the most protein per 100g?", "eggs", "greek yoghurt", "lean meat", "peanut butter", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(23, "Which protein source is the best?", "the source that is your favourite", "meat", "plant protein", "dairy products", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(24, "How many kcal (calories) are in 1g of alcohol?", "9 kcal", "7 kcal", "5 kcal", "4 kcal", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(25, "Which of the following contains calories?", "vitamins", "minerals", "proteins", "salt", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(26, "Which form of cardio is the best?", "what you want to do the most is always the best", "walking and running", "walking and cycling", "playing sports", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(27, "Which of the following contains the most sugar per 100g?", "milk chocolate", "dates", "dark chocolate", "banana", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(28, "Which of the following contains the most fats per 100g?", "peanut butter", "chicken breast", "eggs", "avocado", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(29, "Which of the following has the most calories per 100g?", "olive oil", "ground beef", "milk chocolate", "pepperoni pizza", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(30, "Which of the following contains the least amount of calories per 100g?", "almonds", "dark chocolate", "dates", "fat free greek yoghurt", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(31, "When will the body start losing fat?", "when there is no more sugar, so it must start burning fat", "when the person eats only fruits and vegetables", "when the person runs every day, but eats a lot after", "when the reserve sugar in the body is full", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(32, "How can you know how much do you eat?", "by asking google", "by measuring by eye", "by measuring with a food scale", "you can not know how much you eat", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(33, "Which of these macronutrients is the most important?", "proteins", "carbs", "fats", "there is no such thing as ‘the most important nutrient", "radioD")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(34, "Is maintaining an intermittent fast healthier than eating whenever you like?", "yes, it has plenty of positive effects", "no, it doesn’t matter", "radioB")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(35, "Which person will not gain fat?", "John who eats only ‘healthy’ good, but doesn’t care about the calories", "Michael who eats whatever he wants, but makes sure to not eat more calories than what his body needs", "radioB")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(36, "Which person will have more progress in the gym?", "Jeremy, who believes that he must stay in the gym for at least 2 hours and is constantly tired", "Mike, who stays in the gym for 30 minutes and trains with the highest intensity", "radioB")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(37, "Is the ketogenic diet better than a balanced diet?", "yes, people on keto tend to be more focused and have more energy", "no, people on keto lack carbs in their diet so they lack energy most of the time", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(38, "Which diet is the best?", "ketogenic diet", "plant based diet", "carnivore diet", "well balanced diet", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(39, "What type of food is usually advised to eat before going to the gym for energy?", "protein", "fats", "alcohol", "carbs", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(40, "Maria wants to lose weight, on which of the following should she pay attention the most?", "only eating fruits and vegetables", "being active, but eating a lot", "maintaining a caloric deficit", "quitting alcohol, but eating the same", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(41, "When should I use whey protein?", "immediately after you wake up", "whenever you like, but it’s advisable after an activity", "immediately after working out", "before working out", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(42, "You want to lose weight, so which meal is the most suited for weight loss?", "chicken breast (250g), salad (250g) and diet coke(0,5l)", "pepperoni pizza (500g) and water(0,5l)", "ground beef (300g), 2 bananas and water (0,33l)", "pasta carbonara (400g) an apple and orange juice (0,33l)", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(43, "Potassium is great for lowering blood pressure, which foods contain the most potassium per 100g?", "dates", "red meat", "avocado", "bananas", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(44, "Which food contains the most calories per 100g?", "carbonara", "pepperoni pizza", "chocolate chip ice cream", "olive oil", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(45, "Thomas wants to lose weight, but wants to eat something sweet, what should he choose?", "100g milk chocolate", "100g vanilla ice cream", "100g black forest cake", "100g Oreo", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(46, "Why are fruits and vegetables always a great option for weight loss?", "because they will make you easily full and they are not calorie dense", "because they are healthy", "because they don’t have sugar in them", "because they contain many nutrients", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(47, "With which macronutrients is insulin associated?", "protein", "carbs", "fats", "carbs, protein and fats", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(48, "What should a great diet contain?", "eating a sufficient number of kcal", "proper vitamin and mineral intake", "proper macronutrient intake (carbs, fats, protein)", "all of the above", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(49, "Why should you eat foods that you like during a diet?", "because those foods will keep you satisfied", "because habits are habits", "because people aren’t ready for a diet", "because life is short and you only live once", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(50, "What do you need in order to start a weight loss journey?", "know how much you eat and how much you burn per day", "have the right mindset", "additional physical activities", "all of the above", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(51, "Julia and Sara are on a weight loss diet, but they eat at different times, who will have more results?", "Julia eats the right amount of calories for her to lose weight and she eats whenever she wants", "Sara eats the right amount of calories for her to lose weight, but she does intermittent fasting", "both will have great results", "Sara will have more results because she doesn’t eat before 4PM", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(52, "What is the most important in food in order to be ‘healthy’?", "the quantity and quality of food", "eating only plant based, but without any control", "eating only meat and dairy", "the quantity is not important, just eat ‘healthy’", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(53, "Which factor except food plays the most important role in living a longer and better life?", "regular physical activity", "working all the time", "staying at home all the time", "only quitting smoking and drinking, but without any physical activities", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(54, "Which food is advisable to eat after working out?", "protein", "carbs", "both", "nothing", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(55, "Why is it advisable for people to eat carbs before working out?", "because they are the most delicious", "because they give the needed energy for working out", "because they don’t make you full", "it is a bad idea, they will make people fat", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(56, "Why is it advisable for people to eat carbs and protein after working out?", "for replenishing the reserve sugar (carbs) and for muscle regeneration (protein)", "it is only advisable to eat protein after working out", "for satisfaction (carbs) and for muscle regeneration (protein)", "it is not advisable to eat anything after working out", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(57, "Which of the following are important for muscle hypertrophy (enlargement)?", "eating sufficient even a little bit above maintenance kcal", "eating enough protein", "eating enough carbs", "all of the above", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(58, "A young woman missed her period, she barely ate this macronutrient, which one is it?", "carbs", "protein", "alcohol", "fats", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(59, "David is on a strict diet without any fats, what will happen to him?", "he will have hormonal issues", "he will be leaner without any problems", "why should he have problems, fats are bad", "he will not have any focus", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(60, "Anna ran a marathon, what should she eat after?", "only carbs", "only protein", "both", "she shouldn’t eat anything she should only drink water", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(61, "Martin drank a lot at the party yesterday and is dehydrated today, what should he consume?", "an energy drink", "coffee", "water and electrolytes", "only water", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(62, "Which fluid is recommended after a hard physical activity?", "water", "water and electrolytes", "an energy drink", "diet coke", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(63, "What should proper nutrition include?", "proper caloric and nutrient intake", "proper hydration", "proper fiber intake", "all of the above", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(64, "Which of the following contains the most kcal per 100g?", "steak", "hard boiled eggs", "avocado", "dark chocolate", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(65, "When should people consume creatine?", "after they wake up", "after working out", "before bedtime", "there is no rule, but it’s advisable before doing the activity", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(66, "What does creatine do to the body?", "it gives you more energy during exercise", "it only makes you bloated", "it makes you look bigger and fatter", "it doesn’t do anything", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(67, "Alexander ran 10 km, what food should he eat now?", "pasta carbonara (mixture of carbs and protein, but mainly carbs)", "steak (mainly protein)", "eggs (mainly protein and fats)", "he should only drink water", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(68, "Which food contains the most protein per 100g?", "peanut butter", "eggs", "Greek yoghurt", "chickpeas", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(69, "Which nuts contain the most protein?", "macadamia nuts", "walnuts", "peanuts", "almonds", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(70, "Angel and Alexander both want to lose weight, but they use different methods, who will lose weight?", "Angel, because he only eats junk food and is in a caloric deficit", "Alexander, because he eats mainly fruits, vegetables and meat and is also in a caloric deficit", "both will lose weight", "neither will lose weight", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(71, "Which of the following contains the most protein per 100g?", "chicken thigh", "chicken breast", "steak", "whole fat milk", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(72, "Which of the following contains the most protein per 100g?", "sunflower seeds", "whole fat milk", "Greek yoghurt", "black beans", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(73, "Why is protein important?", "for muscle regeneration", "by serving as a building block for the whole body", "for brain health", "for gut health", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(74, "Which of the following meals contains the most protein and the least amount of kcal?", "hard boiled eggs (400g) and 2 medium bananas", "fat free Greek yoghurt (500g) and 2 scoops of whey isolate protein powder", "chicken breast (300g), green salad (200g)", "Caesar salad (400g) and Protein shake with 2 scoops of whey protein and water (0,5l)", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(75, "Which of the following drinks contains the most protein per 100ml", "almond milk", "coconut milk", "cow milk", "oat milk", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(76, "Why do soft drinks make people fat?", "because they are unhealthy", "because they have a lot of sugar and when in excess leads to fat gain", "because they are carbonated", "because they have additives and preservatives in them", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(77, "When will eating carbs lead to fat gain?", "after a one day fast", "after the reserve sugar (glycogen) is full", "after working out", "at night", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(78, "Which of the following contains the least amount of kcal per 100g?", "peanuts", "milk chocolate", "sweet potato", "sunflower seeds", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(79, "When will eating sugar not be transformed into fat?", "it is always transformed into fat", "when the reserve sugar (glycogen) is full", "when the reserve of sugar (glycogen) is not full", "after eating junk food, but never after eating fruit", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(80, "Where is most of the reserve sugar (glycogen) stored in the body?", "in the skeletal muscles", "in the brain", "in the pancreas", "in the stomach", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(81, "Where is the reserve sugar (glycogen) stored in the body?", "in the heart and lungs", "in the brain and bones", "in the stomach and intestines", "in the skeletal muscles and liver", "radioD")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(82, "How many grams of sugar can be stored as reserve sugar (glycogen) in an average male?", "200g", "500g", "2000g", "1000g", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(83, "How many grams of sugar can be stored as reserve sugar (glycogen) in an average female?", "350g", "500g", "100g", "1000g", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(84, "During starvation, people die because they lack which macronutrient?", "carbs", "fats", "protein", "alcohol", "radioC")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(85, "When will carbs start being stored as reserve sugar (glycogen)?", "when eating carbs after reaching the caloric requirements for maintenance", "when eating carbs and fat at the same time", "when eating carbs at night regardless of the caloric intake", "carbs don’t turn into glycogen, they turn into fat", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(86, "During a multiple day water fast which macronutrient will be firstly used by the body?", "carbs", "fats", "protein", "alcohol", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(87, "After the depletion of carbs, which macronutrient will be mainly used by the body?", "carbs", "fats", "protein", "alcohol", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(88, "Which of the following contains the most sugar per 100g?", "banana", "dark chocolate", "vanilla ice cream", "white bread", "radioB")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(89, "I want to lose weight, must I do cardio?", "yes, how can you lose weight without it silly", "no, just be in a caloric deficit and be patient" ,"radioB")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(90, "I ate at McDonald's today. Will I get fat?", "no, if you aren’t in a caloric surplus (eaten more than maintenance)", "yes, it is unhealthy" ,"radioA")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(91, "I want to start using creatine, but am afraid that I will look fatter and bigger in my stomach, should I worry about that?", "no, it is water weight that goes inside the muscle, so you will look even better than before", "yes, you should not drink it, it is harmful" ,"radioA")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(92, "It’s night time and Mark hasn’t eaten anything today. He wants to eat a chocolate, but is scared that it will make him gain fat, should he eat it?", "yes, he is certainly in a caloric deficit", "no, it’s unhealthy" ,"radioA")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(93, "Is the glucose (sugar) in chocolate and dates the same?", "yes", "no" ,"radioA")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(94, "Can having more muscle mass help in not gaining fat from carbs easily?", "yes, the muscles act as a sponge for sugar and they will store it as reserve sugar", "no, the muscles only help in contraction and relaxation" ,"radioA")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(95, "John and Dwight both weigh 80kg. John has trained for years, but Dwight has never trained so is only the weight important?", "yes, the number is the most important thing", "no, John is fitter and healthier, the weight is not the only factor" ,"radioB")));
        questionsList.add(new FragmentQuiz2Options(isNightModeActive, new Question2(96, "Black bread contains more calories than white bread, does that mean that white bread is healthier?", "yes, at the end of the day a calorie is a calorie", "no, black bread is far more nutrient dense and that is the most important" ,"radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(97, "Which of the following contains the most fat per 100g?", "nutella", "steak", "french fries", "milk chocolate", "radioA")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(98, "Which of the following contains the least amount of kcal per 100g?", "bananas", "fat free Greek yoghurt", "dates", "coconuts", "radioB")));
        questionsList.add(new FragmentQuiz(isNightModeActive, new Question(99, "Which of these beverages contains the most amount of kcal per 100ml?", "orange juice", "black tea", "espresso", "diet coke", "radioA")));


        // I use a set to ensure unique numbers, not repetitive ones
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<15;i++){
            int position = random.nextInt(99);
            if (!set.contains(position)){
                set.add(position);
            } else {
                while (set.contains(position))
                    position = random.nextInt(99);
            }
            fragmentList.add(questionsList.get(position));
        }
    }
}
