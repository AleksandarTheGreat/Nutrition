package com.example.nutrition.Helper;

import com.example.nutrition.Model.Product;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class ContentLoader {

    public static List<Product> createTestList(){
        List<Product> productList = new ArrayList<>();

// Grains
        productList.add(Product.createProduct("Brown Rice (Cooked)", "Grains", 2.6, 23, 112, 0));
        productList.add(Product.createProduct("Whole Wheat Bread", "Grains", 13, 43, 247, 6));
        productList.add(Product.createProduct("Oatmeal (Cooked)", "Grains", 2.4, 12, 71, 0.5));
        productList.add(Product.createProduct("Quinoa (Cooked)", "Grains", 4.1, 21, 120, 0.9));
        productList.add(Product.createProduct("Corn (Cooked)", "Grains", 3.3, 19, 86, 6.3));
        productList.add(Product.createProduct("White Rice (Cooked)", "Grains", 2.7, 28, 130, 0.1));
        productList.add(Product.createProduct("Barley (Cooked)", "Grains", 2.3, 28, 123, 0.3));
        productList.add(Product.createProduct("Buckwheat (Cooked)", "Grains", 3.4, 19.9, 92, 0.5));
        productList.add(Product.createProduct("Bulgur (Cooked)", "Grains", 3.1, 18.6, 83, 0.1));
        productList.add(Product.createProduct("Millet (Cooked)", "Grains", 3.5, 23.7, 119, 0.2));
        productList.add(Product.createProduct("Rye Bread", "Grains", 8.5, 48.3, 259, 5));
        productList.add(Product.createProduct("Spelt (Cooked)", "Grains", 5.5, 43, 198, 1.2));
        productList.add(Product.createProduct("Sorghum (Cooked)", "Grains", 4, 38, 154, 0.9));
        productList.add(Product.createProduct("Amaranth (Cooked)", "Grains", 4.7, 23, 102, 1.7));

// Vegetables
        productList.add(Product.createProduct("Broccoli", "Vegetables", 2.8, 7, 34, 1.7));
        productList.add(Product.createProduct("Carrot", "Vegetables", 0.9, 9.6, 41, 4.7));
        productList.add(Product.createProduct("Spinach", "Vegetables", 2.9, 3.6, 23, 0.4));
        productList.add(Product.createProduct("Bell Pepper", "Vegetables", 1, 6, 26, 4.2));
        productList.add(Product.createProduct("Tomato", "Vegetables", 0.9, 3.9, 18, 2.6));
        productList.add(Product.createProduct("Cucumber", "Vegetables", 0.7, 3.6, 15, 1.7));
        productList.add(Product.createProduct("Cauliflower", "Vegetables", 1.9, 5, 25, 1.9));
        productList.add(Product.createProduct("Kale", "Vegetables", 4.3, 8.8, 49, 0.8));
        productList.add(Product.createProduct("Zucchini", "Vegetables", 1.2, 3.1, 17, 2.5));
        productList.add(Product.createProduct("Eggplant", "Vegetables", 1, 6, 25, 3.5));
        productList.add(Product.createProduct("Lettuce", "Vegetables", 1.4, 2.9, 15, 0.8));
        productList.add(Product.createProduct("Asparagus", "Vegetables", 2.2, 3.9, 20, 1.9));
        productList.add(Product.createProduct("Brussels Sprouts", "Vegetables", 3.4, 9, 43, 2.2));
        productList.add(Product.createProduct("Green Beans", "Vegetables", 1.8, 7, 31, 3.3));
        productList.add(Product.createProduct("Peas", "Vegetables", 5.4, 14, 81, 5.7));
        productList.add(Product.createProduct("Pumpkin", "Vegetables", 1, 7, 26, 2.8));
        productList.add(Product.createProduct("Sweet Potato", "Vegetables", 2, 20, 86, 4.2));
        productList.add(Product.createProduct("Corn", "Vegetables", 3.3, 19, 86, 6.3));
        productList.add(Product.createProduct("Onion", "Vegetables", 1.1, 9, 40, 4.2));
        productList.add(Product.createProduct("Garlic", "Vegetables", 6.4, 33, 149, 1));
        productList.add(Product.createProduct("Radish", "Vegetables", 0.7, 3.4, 16, 1.9));
        productList.add(Product.createProduct("Beetroot", "Vegetables", 1.6, 10, 43, 7));
        productList.add(Product.createProduct("Celery", "Vegetables", 0.8, 3, 16, 1.8));
        productList.add(Product.createProduct("Okra", "Vegetables", 2, 7, 33, 1.5));
        productList.add(Product.createProduct("Artichoke", "Vegetables", 3.3, 11, 47, 1.2));
        productList.add(Product.createProduct("Cabbage", "Vegetables", 1.3, 6, 25, 3.2));
        productList.add(Product.createProduct("Bok Choy", "Vegetables", 1.5, 2.2, 13, 1.2));
        productList.add(Product.createProduct("Turnip", "Vegetables", 1.2, 6, 28, 3.8));
        productList.add(Product.createProduct("Parsnip", "Vegetables", 1.2, 18, 75, 4.8));
        productList.add(Product.createProduct("Collard Greens", "Vegetables", 3.6, 10, 32, 0.5));
        productList.add(Product.createProduct("Swiss Chard", "Vegetables", 1.8, 3.7, 19, 1.1));
        productList.add(Product.createProduct("Leek", "Vegetables", 1.5, 14, 61, 3.9));
        productList.add(Product.createProduct("Fennel", "Vegetables", 1.2, 7.3, 31, 3.9));
        productList.add(Product.createProduct("Snow Peas", "Vegetables", 2.8, 7.5, 42, 4));
        productList.add(Product.createProduct("Bamboo Shoots", "Vegetables", 2.6, 5.2, 27, 3));
        productList.add(Product.createProduct("Butternut Squash", "Vegetables", 1, 12, 45, 2.2));
        productList.add(Product.createProduct("Bell Peppers", "Vegetables", 0.9, 6, 26, 3.5));
        productList.add(Product.createProduct("Acorn Squash", "Vegetables", 1.4, 15, 58, 0.4));
        productList.add(Product.createProduct("Rutabaga", "Vegetables", 1.2, 9, 37, 4.6));
        productList.add(Product.createProduct("Spaghetti Squash", "Vegetables", 1, 6.5, 31, 2.8));

// Fruits
        productList.add(Product.createProduct("Apple", "Fruits", 0.3, 14, 52, 10));
        productList.add(Product.createProduct("Banana", "Fruits", 1.3, 23, 96, 12));
        productList.add(Product.createProduct("Orange", "Fruits", 0.9, 12, 47, 9));
        productList.add(Product.createProduct("Grape", "Fruits", 0.6, 18, 69, 16));
        productList.add(Product.createProduct("Strawberry", "Fruits", 0.8, 8, 32, 4.9));
        productList.add(Product.createProduct("Watermelon", "Fruits", 0.6, 8, 30, 6));
        productList.add(Product.createProduct("Pineapple", "Fruits", 0.5, 13, 50, 10));
        productList.add(Product.createProduct("Mango", "Fruits", 0.8, 15, 60, 14));
        productList.add(Product.createProduct("Kiwi", "Fruits", 1.1, 15, 61, 9));
        productList.add(Product.createProduct("Cherry", "Fruits", 1.0, 12, 50, 8));
        productList.add(Product.createProduct("Peach", "Fruits", 0.9, 10, 39, 8));
        productList.add(Product.createProduct("Plum", "Fruits", 0.7, 11, 46, 9.6));
        productList.add(Product.createProduct("Lemon", "Fruits", 0.6, 9, 29, 2.5));
        productList.add(Product.createProduct("Lime", "Fruits", 0.7, 11, 30, 1.5));
        productList.add(Product.createProduct("Blueberry", "Fruits", 0.7, 14, 57, 10));
        productList.add(Product.createProduct("Raspberry", "Fruits", 1.2, 12, 52, 4.4));
        productList.add(Product.createProduct("Blackberry", "Fruits", 1.4, 10, 43, 4.9));
        productList.add(Product.createProduct("Pear", "Fruits", 0.4, 15, 57, 10));
        productList.add(Product.createProduct("Apricot", "Fruits", 0.5, 12, 48, 9));
        productList.add(Product.createProduct("Grapefruit", "Fruits", 0.8, 11, 42, 8));
        productList.add(Product.createProduct("Papaya", "Fruits", 0.5, 11, 43, 5.9));
        productList.add(Product.createProduct("Cantaloupe", "Fruits", 0.8, 8, 34, 8));
        productList.add(Product.createProduct("Fig", "Fruits", 0.8, 19, 74, 16));
        productList.add(Product.createProduct("Pomegranate", "Fruits", 1.7, 18, 83, 13));
        productList.add(Product.createProduct("Lychee", "Fruits", 0.8, 17, 66, 15));
        productList.add(Product.createProduct("Dragon Fruit", "Fruits", 1.2, 13, 50, 8));
        productList.add(Product.createProduct("Passion Fruit", "Fruits", 2.2, 23, 97, 11));
        productList.add(Product.createProduct("Guava", "Fruits", 2.6, 14, 68, 9));
        productList.add(Product.createProduct("Cranberry", "Fruits", 0.4, 12, 46, 4));
        productList.add(Product.createProduct("Tangerine", "Fruits", 0.8, 13, 53, 11));
        productList.add(Product.createProduct("Coconut", "Fruits", 3.3, 15, 354, 6));
        productList.add(Product.createProduct("Jackfruit", "Fruits", 1.7, 24, 95, 19));
        productList.add(Product.createProduct("Nectarine", "Fruits", 1.1, 12, 44, 8));
        productList.add(Product.createProduct("Kumquat", "Fruits", 1.9, 16, 71, 9));
        productList.add(Product.createProduct("Starfruit", "Fruits", 1, 6.7, 31, 4));
        productList.add(Product.createProduct("Persimmon", "Fruits", 0.6, 18, 81, 12));
        productList.add(Product.createProduct("Rambutan", "Fruits", 0.9, 20, 68, 13));
        productList.add(Product.createProduct("Mulberry", "Fruits", 1.4, 10, 43, 8));
        productList.add(Product.createProduct("Custard Apple", "Fruits", 1.7, 25, 101, 24));

// Proteins
        productList.add(Product.createProduct("Chicken Breast", "Proteins", 31, 0, 165, 0));
        productList.add(Product.createProduct("Eggs", "Proteins", 13, 1.1, 155, 1.1));
        productList.add(Product.createProduct("Almonds", "Proteins", 21, 22, 576, 4.4));
        productList.add(Product.createProduct("Beef", "Proteins", 26, 0, 250, 0));
        productList.add(Product.createProduct("Lentils", "Proteins", 9, 20, 116, 1.8));
        productList.add(Product.createProduct("Tofu", "Proteins", 8, 2, 70, 0.5));
        productList.add(Product.createProduct("Chickpeas", "Proteins", 8.9, 27.4, 164, 4.8));
        productList.add(Product.createProduct("Turkey Breast", "Proteins", 29, 0, 135, 0));
        productList.add(Product.createProduct("Salmon", "Proteins", 25, 0, 206, 0));
        productList.add(Product.createProduct("Peanuts", "Proteins", 25.8, 16.1, 567, 4.7));
        productList.add(Product.createProduct("Tuna", "Proteins", 24, 0, 144, 0));
        productList.add(Product.createProduct("Shrimp", "Proteins", 24, 0.2, 99, 0.2));
        productList.add(Product.createProduct("Pork Loin", "Proteins", 27, 0, 242, 0));
        productList.add(Product.createProduct("Cod", "Proteins", 20, 0, 82, 0));
        productList.add(Product.createProduct("Cottage Cheese", "Proteins", 11, 3.4, 98, 2.7));
        productList.add(Product.createProduct("Mozzarella Cheese", "Proteins", 22, 2.2, 280, 1));
        productList.add(Product.createProduct("Edamame", "Proteins", 11, 9, 122, 2.2));
        productList.add(Product.createProduct("Tempeh", "Proteins", 19, 9, 192, 0.5));
        productList.add(Product.createProduct("Pumpkin Seeds", "Proteins", 19, 53, 446, 1.4));
        productList.add(Product.createProduct("Sunflower Seeds", "Proteins", 21, 20, 584, 2.6));
        productList.add(Product.createProduct("Quinoa", "Proteins", 4.1, 21, 120, 0.9));
        productList.add(Product.createProduct("Black Beans", "Proteins", 9, 23, 132, 0.3));
        productList.add(Product.createProduct("Peas", "Proteins", 5.4, 14, 81, 5.7));
        productList.add(Product.createProduct("Flax Seeds", "Proteins", 18, 29, 534, 1.6));
        productList.add(Product.createProduct("Hemp Seeds", "Proteins", 31.6, 8.7, 553, 1.5));
        productList.add(Product.createProduct("Chia Seeds", "Proteins", 16.5, 42, 486, 0));
        productList.add(Product.createProduct("Walnuts", "Proteins", 15, 14, 654, 2.6));
        productList.add(Product.createProduct("Cashews", "Proteins", 18, 30, 553, 5.9));
        productList.add(Product.createProduct("Brazil Nuts", "Proteins", 14, 12, 656, 2.3));
        productList.add(Product.createProduct("Whey Protein Powder", "Proteins", 80, 7, 399, 6.6));

// Dairy
        productList.add(Product.createProduct("Whole Milk", "Dairy", 3.4, 4.8, 61, 5));
        productList.add(Product.createProduct("Cheddar Cheese", "Dairy", 25, 1.3, 403, 0.5));
        productList.add(Product.createProduct("Yogurt (Plain)", "Dairy", 10, 4, 59, 4));
        productList.add(Product.createProduct("Cottage Cheese", "Dairy", 11, 3.4, 98, 2.7));
        productList.add(Product.createProduct("Mozzarella Cheese", "Dairy", 22, 2.2, 280, 1));
        productList.add(Product.createProduct("Greek Yogurt (Plain)", "Dairy", 10, 4, 59, 4));
        productList.add(Product.createProduct("Butter", "Dairy", 0.85, 0.06, 717, 0.06));
        productList.add(Product.createProduct("Milk (Skim)", "Dairy", 3.4, 5, 35, 5));
        productList.add(Product.createProduct("Sour Cream", "Dairy", 2.4, 4.6, 181, 4));
        productList.add(Product.createProduct("Parmesan Cheese", "Dairy", 35.8, 3.2, 431, 0.9));
        productList.add(Product.createProduct("Feta Cheese", "Dairy", 14.2, 4.1, 265, 4));
        productList.add(Product.createProduct("Swiss Cheese", "Dairy", 27, 5, 380, 0));
        productList.add(Product.createProduct("Ricotta Cheese", "Dairy", 7.6, 3, 174, 0.3));

// Fats, Oils, and Sweets
        productList.add(Product.createProduct("Olive Oil", "Fats, Oils, and Sweets", 0, 0, 884, 0));
        productList.add(Product.createProduct("Butter", "Fats, Oils, and Sweets", 0.85, 0.06, 717, 0.06));
        productList.add(Product.createProduct("Avocado", "Fats, Oils, and Sweets", 2, 9, 160, 0.7));
        productList.add(Product.createProduct("Peanut Butter", "Fats, Oils, and Sweets", 25, 20, 588, 7));
        productList.add(Product.createProduct("Dark Chocolate", "Fats, Oils, and Sweets", 7.8, 46.4, 598, 24));
        productList.add(Product.createProduct("Canola Oil", "Fats, Oils, and Sweets", 0, 0, 884, 0));
        productList.add(Product.createProduct("Coconut Oil", "Fats, Oils, and Sweets", 0, 0, 862, 0));
        productList.add(Product.createProduct("Sunflower Oil", "Fats, Oils, and Sweets", 0, 0, 884, 0));
        productList.add(Product.createProduct("Honey", "Fats, Oils, and Sweets", 0.3, 82, 304, 82));
        productList.add(Product.createProduct("Maple Syrup", "Fats, Oils, and Sweets", 0, 67, 260, 67));
        productList.add(Product.createProduct("Granulated Sugar", "Fats, Oils, and Sweets", 0, 100, 387, 100));
        productList.add(Product.createProduct("Brown Sugar", "Fats, Oils, and Sweets", 0, 97, 380, 97));
        productList.add(Product.createProduct("Corn Syrup", "Fats, Oils, and Sweets", 0, 77, 286, 77));
        productList.add(Product.createProduct("Margarine", "Fats, Oils, and Sweets", 0, 0, 717, 0));
        productList.add(Product.createProduct("Mayonnaise", "Fats, Oils, and Sweets", 0.3, 0.6, 680, 0.6));
        productList.add(Product.createProduct("Jam", "Fats, Oils, and Sweets", 0.4, 65, 239, 65));
        productList.add(Product.createProduct("Jelly", "Fats, Oils, and Sweets", 0.4, 65, 235, 65));
        productList.add(Product.createProduct("Candy", "Fats, Oils, and Sweets", 0, 98, 394, 98));
        productList.add(Product.createProduct("Syrup", "Fats, Oils, and Sweets", 0, 77, 286, 77));
        productList.add(Product.createProduct("Pastries", "Fats, Oils, and Sweets", 4, 52, 350, 22));
        productList.add(Product.createProduct("Cookies", "Fats, Oils, and Sweets", 5, 68, 502, 29));
        productList.add(Product.createProduct("Cake", "Fats, Oils, and Sweets", 3.2, 62, 390, 35));
        productList.add(Product.createProduct("Ice Cream (Vanilla)", "Fats, Oils, and Sweets", 3.5, 23, 207, 21));
        productList.add(Product.createProduct("Chocolate", "Fats, Oils, and Sweets", 7.6, 59.2, 546, 52.4));
        productList.add(Product.createProduct("Ghee", "Fats, Oils, and Sweets", 0, 0, 900, 0));
        productList.add(Product.createProduct("Lard", "Fats, Oils, and Sweets", 0, 0, 902, 0));
        productList.add(Product.createProduct("Shortening", "Fats, Oils, and Sweets", 0, 0, 884, 0));
        productList.add(Product.createProduct("Tahini", "Fats, Oils, and Sweets", 17, 22, 595, 1));
        productList.add(Product.createProduct("Nutella", "Fats, Oils, and Sweets", 6, 57.5, 539, 56.3));
        productList.add(Product.createProduct("Cream Cheese", "Fats, Oils, and Sweets", 6.2, 6.5, 350, 6));

        return productList;
    }
}
