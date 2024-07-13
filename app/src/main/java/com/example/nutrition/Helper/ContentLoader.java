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
        productList.add(Product.createProduct("Broccoli (Raw)", "Vegetables", 2.8, 7, 34, 1.7));
        productList.add(Product.createProduct("Carrots (Raw)", "Vegetables", 0.9, 9.6, 41, 4.7));
        productList.add(Product.createProduct("Spinach (Raw)", "Vegetables", 2.9, 3.6, 23, 0.4));
        productList.add(Product.createProduct("Bell Peppers (Raw)", "Vegetables", 1, 6, 26, 4.2));
        productList.add(Product.createProduct("Tomatoes (Raw)", "Vegetables", 0.9, 3.9, 18, 2.6));
        productList.add(Product.createProduct("Cucumber (Raw)", "Vegetables", 0.7, 3.6, 15, 1.7));
        productList.add(Product.createProduct("Cauliflower (Raw)", "Vegetables", 1.9, 5, 25, 1.9));
        productList.add(Product.createProduct("Kale (Raw)", "Vegetables", 4.3, 8.8, 49, 0.8));
        productList.add(Product.createProduct("Zucchini (Raw)", "Vegetables", 1.2, 3.1, 17, 2.5));
        productList.add(Product.createProduct("Eggplant (Raw)", "Vegetables", 1, 6, 25, 3.5));
        productList.add(Product.createProduct("Asparagus (Raw)", "Vegetables", 2.2, 3.9, 20, 1.2));
        productList.add(Product.createProduct("Green Beans (Raw)", "Vegetables", 1.8, 7, 31, 1.6));
        productList.add(Product.createProduct("Lettuce (Raw)", "Vegetables", 1.4, 2.9, 15, 0.8));
        productList.add(Product.createProduct("Onions (Raw)", "Vegetables", 1.1, 9.3, 40, 4.2));
        productList.add(Product.createProduct("Pumpkin (Cooked)", "Vegetables", 1, 7, 26, 2.8));

// Fruits
        productList.add(Product.createProduct("Apple (Raw)", "Fruits", 0.3, 14, 52, 10));
        productList.add(Product.createProduct("Banana (Raw)", "Fruits", 1.3, 23, 96, 12));
        productList.add(Product.createProduct("Orange (Raw)", "Fruits", 0.9, 12, 47, 9));
        productList.add(Product.createProduct("Strawberries (Raw)", "Fruits", 0.8, 8, 32, 4.9));
        productList.add(Product.createProduct("Grapes (Raw)", "Fruits", 0.6, 18, 69, 16));
        productList.add(Product.createProduct("Watermelon (Raw)", "Fruits", 0.6, 8, 30, 6));
        productList.add(Product.createProduct("Pineapple (Raw)", "Fruits", 0.5, 13, 50, 10));
        productList.add(Product.createProduct("Blueberries (Raw)", "Fruits", 0.7, 14, 57, 10));
        productList.add(Product.createProduct("Mango (Raw)", "Fruits", 0.8, 15, 60, 14));
        productList.add(Product.createProduct("Kiwi (Raw)", "Fruits", 1.1, 15, 61, 9));
        productList.add(Product.createProduct("Papaya (Raw)", "Fruits", 0.5, 11, 43, 5.9));
        productList.add(Product.createProduct("Peach (Raw)", "Fruits", 1, 10, 39, 8.4));
        productList.add(Product.createProduct("Plum (Raw)", "Fruits", 0.7, 11.4, 46, 9.9));

// Proteins
        productList.add(Product.createProduct("Chicken Breast", "Proteins", 31, 0, 165, 0));
        productList.add(Product.createProduct("Eggs (Boiled)", "Proteins", 13, 1.1, 155, 1.1));
        productList.add(Product.createProduct("Almonds (Raw)", "Proteins", 21, 22, 576, 4.4));
        productList.add(Product.createProduct("Beef (Cooked)", "Proteins", 26, 0, 250, 0));
        productList.add(Product.createProduct("Lentils (Cooked)", "Proteins", 9, 20, 116, 1.8));
        productList.add(Product.createProduct("Tofu (Firm)", "Proteins", 8, 2, 70, 0.5));
        productList.add(Product.createProduct("Chickpeas (Cooked)", "Proteins", 8.9, 27.4, 164, 4.8));
        productList.add(Product.createProduct("Turkey Breast", "Proteins", 29, 0, 135, 0));
        productList.add(Product.createProduct("Salmon (Cooked)", "Proteins", 25, 0, 206, 0));
        productList.add(Product.createProduct("Peanuts (Raw)", "Proteins", 25.8, 16.1, 567, 4.7));
        productList.add(Product.createProduct("Pork Loin (Cooked)", "Proteins", 27, 0, 242, 0));
        productList.add(Product.createProduct("Greek Yogurt", "Proteins", 10, 4, 59, 4));
        productList.add(Product.createProduct("Black Beans (Cooked)", "Proteins", 9, 23, 132, 0.3));
        productList.add(Product.createProduct("Hemp Seeds", "Proteins", 32, 10, 553, 1.5));
        productList.add(Product.createProduct("Whey Protein Powder", "Proteins", 80, 7, 399, 6.6));

// Dairy
        productList.add(Product.createProduct("Whole Milk", "Dairy", 3.4, 4.8, 61, 5));
        productList.add(Product.createProduct("Cheddar Cheese", "Dairy", 25, 1.3, 403, 0.5));
        productList.add(Product.createProduct("Yogurt (Plain)", "Dairy", 10, 4, 59, 4));
        productList.add(Product.createProduct("Cottage Cheese", "Dairy", 11, 3.4, 98, 2.7));
        productList.add(Product.createProduct("Mozzarella Cheese", "Dairy", 22, 2.2, 280, 1));
        productList.add(Product.createProduct("Greek Yogurt (Plain)", "Dairy", 10, 4, 59, 4));
        productList.add(Product.createProduct("Butter", "Dairy", 0.85, 0.06, 717, 0.06));
        productList.add(Product.createProduct("Ice Cream (Vanilla)", "Dairy", 3.5, 23, 207, 21));
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
