package com.example.nutrition.Helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.example.nutrition.Model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class ContentLoader {

    public static Drawable getDrawable(Context context, String name) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("grains/" + name + ".jpg");
            return Drawable.createFromStream(inputStream, null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> createTestList(Context context) {
        List<Product> productList = new ArrayList<>();

// Grains
        productList.add(Product.createProduct("Brown Rice (Cooked)", "Grains", 2.6, 23, 112, 0, "https://veganfamilykitchen.com/wp-content/uploads/2019/04/how-to-cook-brown-rice-easiest-simplest-healthiest-way-featureimage-1.jpg"));
        productList.add(Product.createProduct("Whole Wheat Bread", "Grains", 13, 43, 247, 6, "https://sallysbakingaddiction.com/wp-content/uploads/2024/01/whole-wheat-bread-3.jpg"));
        productList.add(Product.createProduct("Oatmeal (Cooked)", "Grains", 2.4, 12, 71, 0.5, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Oatmeal.jpg/1200px-Oatmeal.jpg"));
        productList.add(Product.createProduct("Quinoa (Cooked)", "Grains", 4.1, 21, 120, 0.9, "https://www.poshtik.in/cdn/shop/files/Quinoa_6a70c2d6-4e41-4ee6-813e-5b9ac37703a5.jpg?v=1697831346"));
        productList.add(Product.createProduct("Corn (Cooked)", "Grains", 3.3, 19, 86, 6.3, "https://feastandfarm.com/wp-content/uploads/LRCanned-Corn-4.jpg"));
        productList.add(Product.createProduct("White Rice (Cooked)", "Grains", 2.7, 28, 130, 0.1, "https://www.realsimple.com/thmb/NI2xqE0QLcUrI-jMb8y1858kPnc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/long-grain-white-rice-gettyimages-806770568-f3c1d7887feb475783bb3e3b592066e3.jpg"));
        productList.add(Product.createProduct("Barley (Cooked)", "Grains", 2.3, 28, 123, 0.3, "https://bakerpedia.com/wp-content/uploads/2018/11/Barley_baking-ingredients-e1543524143272.jpg"));
        productList.add(Product.createProduct("Buckwheat (Cooked)", "Grains", 3.4, 19.9, 92, 0.5, "https://zamaorganics.com/cdn/shop/files/FF_12.png?v=1706774596&width=1080"));
        productList.add(Product.createProduct("Bulgur (Cooked)", "Grains", 3.1, 18.6, 83, 0.1, "https://www.eatingwell.com/thmb/pgw0okPuy0OihBXl6Y72VjOZhes=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/simply-seasoned-bulgur-hero-04-8031238-4000x4000-6723e83516cc45f0be2353bf56ba51d6.jpg"));
        productList.add(Product.createProduct("Millet (Cooked)", "Grains", 3.5, 23.7, 119, 0.2, "https://www.marthastewart.com/thmb/mn20v4pinKUFHDGc9Qhua30CoQA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/2023-year-of-millet-getty-0423-d023bf36fec148bbb1503b79f1e2c8de.jpg"));
        productList.add(Product.createProduct("Rye Bread", "Grains", 8.5, 48.3, 259, 5, "https://www.jocooks.com/wp-content/uploads/2020/05/dark-rye-bread-1-17-500x500.jpg"));
        productList.add(Product.createProduct("Spelt (Cooked)", "Grains", 5.5, 43, 198, 1.2, "https://i0.wp.com/post.healthline.com/wp-content/uploads/2021/10/spelt-grain-in-bowl-1296x728-header.jpg?w=1155&h=1528"));
        productList.add(Product.createProduct("Sorghum (Cooked)", "Grains", 4, 38, 154, 0.9, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS12XjWK4K5XUuG5LUh3z7dBllGGryHQVfVHw&usqp=CAU"));
        productList.add(Product.createProduct("Amaranth (Cooked)", "Grains", 4.7, 23, 102, 1.7, "https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/AN_images/amaranth-on-spoon-1296x728.jpg?w=1155&h=1528"));

// Vegetables
        productList.add(Product.createProduct("Broccoli", "Vegetables", 2.8, 7, 34, 1.7, "https://images.squarespace-cdn.com/content/v1/5b5aa0922487fd1ce32c117a/1547765015801-FSR1DVSKCZU3PAYWIRQG/broccoli.jpg"));
        productList.add(Product.createProduct("Carrot", "Vegetables", 0.9, 9.6, 41, 4.7, "https://blog-images-1.pharmeasy.in/blog/production/wp-content/uploads/2021/04/23175719/shutterstock_440493100-1.jpg"));
        productList.add(Product.createProduct("Spinach", "Vegetables", 2.9, 3.6, 23, 0.4, "https://media.post.rvohealth.io/wp-content/uploads/2019/05/spinach-732x549-thumbnail.jpg"));
        productList.add(Product.createProduct("Bell Pepper", "Vegetables", 1, 6, 26, 4.2, "https://snaped.fns.usda.gov/sites/default/files/seasonal-produce/2018-05/bell%20peppers.jpg"));
        productList.add(Product.createProduct("Tomato", "Vegetables", 0.9, 3.9, 18, 2.6, "https://cdn.britannica.com/16/187216-131-FB186228/tomatoes-tomato-plant-Fruit-vegetable.jpg"));
        productList.add(Product.createProduct("Cucumber", "Vegetables", 0.7, 3.6, 15, 1.7, "https://wh.farm/wp-content/uploads/2022/09/SlicerCucumber-Featured.jpg"));
        productList.add(Product.createProduct("Cauliflower", "Vegetables", 1.9, 5, 25, 1.9, "https://cdn.mos.cms.futurecdn.net/ApkWfRWrKKyHAx3oi3DrQR-1200-80.jpg"));
        productList.add(Product.createProduct("Kale", "Vegetables", 4.3, 8.8, 49, 0.8, "https://media.post.rvohealth.io/wp-content/uploads/2020/09/benefits-of-kale-732x549-thumbnail-732x549.jpg"));
        productList.add(Product.createProduct("Zucchini", "Vegetables", 1.2, 3.1, 17, 2.5, "https://www.diggers.com.au/cdn/shop/products/zucchini-tri-coloured-mix-s254_fc72ad55-41f4-4ced-802e-3bf503f77c27_2048x.jpg?v=1637122576"));
        productList.add(Product.createProduct("Eggplant", "Vegetables", 1, 6, 25, 3.5, "https://www.southernliving.com/thmb/zIYF8tf18hVormy-OxqF-S7CCfI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Eggplant_003-0-acdc626b78c34962b62072863bf78fc7.jpg"));
        productList.add(Product.createProduct("Lettuce", "Vegetables", 1.4, 2.9, 15, 0.8, "https://i5.walmartimages.ca/images/Enlarge/006/949/6000196006949.jpg"));
        productList.add(Product.createProduct("Asparagus", "Vegetables", 2.2, 3.9, 20, 1.9, "https://veryveganish.com/wp-content/uploads/2021/03/Featured-5-minute-Lemon-Pepper-Asparagus-no-oil-2.jpg"));
        productList.add(Product.createProduct("Brussels Sprouts", "Vegetables", 3.4, 9, 43, 2.2, "https://bcfresh.ca/wp-content/uploads/2021/11/Brussels-sprouts.jpg"));
        productList.add(Product.createProduct("Green Beans", "Vegetables", 1.8, 7, 31, 3.3, "https://media.post.rvohealth.io/wp-content/uploads/2020/08/1200x628_FACEBOOK_Green_Beans_Nutrition_Facts_and_Health_Benefits-1200x628.jpg"));
        productList.add(Product.createProduct("Peas", "Vegetables", 5.4, 14, 81, 5.7, "https://static.wixstatic.com/media/e24d40_88f86f3687b546fe9b7f78452591cd21~mv2.jpg/v1/fill/w_852,h_563,al_c,q_85/e24d40_88f86f3687b546fe9b7f78452591cd21~mv2.jpg"));
        productList.add(Product.createProduct("Pumpkin", "Vegetables", 1, 7, 26, 2.8, "https://www.thespruce.com/thmb/WFSkpFrvIfRfDAMaob945M4UgKA=/2116x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-147003040-86f76dfc543d4a719eed6ccd3395c2b7.jpg"));
        productList.add(Product.createProduct("Sweet Potato", "Vegetables", 2, 20, 86, 4.2, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Ipomoea_batatas_006.JPG/800px-Ipomoea_batatas_006.JPG"));
        productList.add(Product.createProduct("Corn", "Vegetables", 3.3, 19, 86, 6.3, "https://chefsmandala.com/wp-content/uploads/2018/03/corn.jpg"));
        productList.add(Product.createProduct("Onion", "Vegetables", 1.1, 9, 40, 4.2, "https://produits.bienmanger.com/36700-0w470h470_Organic_Red_Onion_From_Italy.jpg"));
        productList.add(Product.createProduct("Garlic", "Vegetables", 6.4, 33, 149, 1, "https://www.organics.ph/cdn/shop/products/garlic-peeled-250grams-fruits-vegetables-fresh-produce-982039_1024x.jpg?v=1601483078"));
        productList.add(Product.createProduct("Mushroom", "Vegetables", 3.1, 3.3, 22, 1.7, "https://images.immediate.co.uk/production/volatile/sites/30/2023/08/Chestnut-mushrooms-a223a78.jpg?quality=90&resize=440,400"));
        productList.add(Product.createProduct("Radish", "Vegetables", 0.7, 3.4, 16, 1.9, "https://www.incredibleseeds.ca/cdn/shop/products/Radish-Champion_6b2e14ab-3ed6-44c1-93d1-89108db0479f.jpg?v=1678153949"));
        productList.add(Product.createProduct("Beetroot", "Vegetables", 1.6, 10, 43, 7, "https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2019/11/raw-beetroot-on-a-wooden-surface.jpg"));
        productList.add(Product.createProduct("Celery", "Vegetables", 0.8, 3, 16, 1.8, "https://www.thespruceeats.com/thmb/h5_OYBELY8-WXAbSF2RLJ3evrJg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/what-is-celery-5199268-hero-01-cb9c645dfb614f0a8eef5b0c316ce16d.jpg"));
        productList.add(Product.createProduct("Okra", "Vegetables", 2, 7, 33, 1.5, "https://cdn-prod.medicalnewstoday.com/content/images/articles/311/311977/okra-raw.jpg"));
        productList.add(Product.createProduct("Artichoke", "Vegetables", 3.3, 11, 47, 1.2, "https://cdn.britannica.com/16/191616-050-6FF60400/artichoke-heads-sale.jpg"));
        productList.add(Product.createProduct("Cabbage", "Vegetables", 1.3, 6, 25, 3.2, "https://www.growingproduce.com/wp-content/uploads/2023/07/w_Manessa_Cabbage_Rijk-Zwaan_gallery.jpg"));
        productList.add(Product.createProduct("Bok Choy", "Vegetables", 1.5, 2.2, 13, 1.2, "https://cdn.loveandlemons.com/wp-content/uploads/2022/05/bok-choy-how-to-cook.jpg"));
        productList.add(Product.createProduct("Turnip", "Vegetables", 1.2, 6, 28, 3.8, "https://post.healthline.com/wp-content/uploads/2019/11/turnip-root-vegetable-1296x728-header-1296x728.jpg"));
        productList.add(Product.createProduct("Parsnip", "Vegetables", 1.2, 18, 75, 4.8, "https://snaped.fns.usda.gov/sites/default/files/styles/crop_ratio_7_5/public/seasonal-produce/2018-05/parsnip.jpg?itok=aG9dUeKG"));
        productList.add(Product.createProduct("Collard Greens", "Vegetables", 3.6, 10, 32, 0.5, "https://cdn-prod.medicalnewstoday.com/content/images/articles/277/277957/collard-greens-contain-many-nutrients.jpg"));
        productList.add(Product.createProduct("Swiss Chard", "Vegetables", 1.8, 3.7, 19, 1.1, "https://www.health.com/thmb/m6gcOGCKNjLCv5mnk8zV_MqDX9U=/2121x0/filters:no_upscale():max_bytes(150000):strip_icc()/SwissChard-6193e3b4941b4479979f5df338ae6ea3.jpg"));
        productList.add(Product.createProduct("Leek", "Vegetables", 1.5, 14, 61, 3.9, "https://www.thespruceeats.com/thmb/k_-YP11JJ6Rj6JCfe---nqmOQ1k=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/buttered-leeks-recipe-spring-leeks-4126572-step-01-a27df4fb214e46cfbb127ad561a9f8aa.jpg"));
        productList.add(Product.createProduct("Fennel", "Vegetables", 1.2, 7.3, 31, 3.9, "https://www.thechoppingblock.com/hs-fs/hubfs/Blog/fennel.png?width=600&name=fennel.png"));
        productList.add(Product.createProduct("Snow Peas", "Vegetables", 2.8, 7.5, 42, 4, "https://fastandfresh.in/cdn/shop/products/snowpeas_800x.jpg?v=1655795564"));
        productList.add(Product.createProduct("Bamboo Shoots", "Vegetables", 2.6, 5.2, 27, 3, "https://i0.wp.com/post.healthline.com/wp-content/uploads/2021/05/bamboo-shoots-shoot-food-1296x728-header.jpg?w=1155&h=1528"));
        productList.add(Product.createProduct("Butternut Squash", "Vegetables", 1, 12, 45, 2.2, "https://www.southernliving.com/thmb/K1sT0DyM7OOzF17Fc2KwAYA5mUE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Roasted_Butternut_Squash_007_4x3-4f15d091869649db9d175801e14c085a.jpg"));
        productList.add(Product.createProduct("Acorn Squash", "Vegetables", 1.4, 15, 58, 0.4, "https://thewoodenskillet.com/wp-content/uploads/2016/10/roasted-acorn-squash-1.jpg"));
        productList.add(Product.createProduct("Rutabaga", "Vegetables", 1.2, 9, 37, 4.6, "https://www.foodrepublic.com/img/gallery/how-to-eat-rutabaga-and-why-you-should/l-intro-1687521773.jpg"));
        productList.add(Product.createProduct("Spaghetti Squash", "Vegetables", 1, 6.5, 31, 2.8, "https://www.bhg.com/thmb/Q2w1DquztdHSTFPn0rPe_r_sLgU=/1867x0/filters:no_upscale():strip_icc()/R090707-78af285929d043a8b18de3d175767671.jpg"));

// Fruits
        productList.add(Product.createProduct("Apple", "Fruits", 0.3, 14, 52, 10, "https://5.imimg.com/data5/AK/RA/MY-68428614/apple.jpg"));
        productList.add(Product.createProduct("Avocado", "Fruits", 2, 9, 160, 0.7, "https://snaped.fns.usda.gov/sites/default/files/seasonal-produce/2018-05/avocado.jpg"));
        productList.add(Product.createProduct("Banana", "Fruits", 1.3, 23, 96, 12, "https://static.wixstatic.com/media/53e8bb_a1e88e551162485eb4ff962437300872~mv2.jpeg/v1/crop/x_0,y_105,w_1024,h_919/fill/w_560,h_560,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/Banana.jpeg"));
        productList.add(Product.createProduct("Orange", "Fruits", 0.9, 12, 47, 9, "https://www.hauert.com/fileadmin/MediaValet/_processed_/1/1/csm_Orange_yi1YB_FubH8-unsplash_b6cc0b77ab.jpg"));
        productList.add(Product.createProduct("Grape", "Fruits", 0.6, 18, 69, 16, "https://images.healthshots.com/healthshots/en/uploads/2023/03/01181103/Grapes-1600x900.jpg"));
        productList.add(Product.createProduct("Strawberry", "Fruits", 0.8, 8, 32, 4.9, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Garden_strawberry_%28Fragaria_%C3%97_ananassa%29_single2.jpg/1200px-Garden_strawberry_%28Fragaria_%C3%97_ananassa%29_single2.jpg"));
        productList.add(Product.createProduct("Watermelon", "Fruits", 0.6, 8, 30, 6, "https://5.imimg.com/data5/SELLER/Default/2022/3/ZZ/PW/RZ/36905324/fruits-watermelons-500x500.jpg"));
        productList.add(Product.createProduct("Pineapple", "Fruits", 0.5, 13, 50, 10, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHCebcn-XCvFFmVnX5hAUhTht_uiDzjPDeVg&usqp=CAU"));
        productList.add(Product.createProduct("Mango", "Fruits", 0.8, 15, 60, 14, "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Mangos_-_single_and_halved.jpg/640px-Mangos_-_single_and_halved.jpg"));
        productList.add(Product.createProduct("Kiwi", "Fruits", 1.1, 15, 61, 9, "https://cdn.britannica.com/45/126445-050-4C0FA9F6/Kiwi-fruit.jpg"));
        productList.add(Product.createProduct("Cherry", "Fruits", 1.0, 12, 50, 8, "https://images.immediate.co.uk/production/volatile/sites/10/2021/10/2048x1365-SEO-Morello-cherry-GettyImages-1330319873-e389763.jpg?resize=1200%2C630"));
        productList.add(Product.createProduct("Peach", "Fruits", 0.9, 10, 39, 8, "https://5.imimg.com/data5/SELLER/Default/2023/9/340781037/XT/KA/ZP/189247658/peach-500x500.jpg"));
        productList.add(Product.createProduct("Plum", "Fruits", 0.7, 11, 46, 9.6, "https://www.mashed.com/img/gallery/heres-exactly-what-to-look-for-when-buying-a-plum/l-intro-1678294366.jpg"));
        productList.add(Product.createProduct("Lemon", "Fruits", 0.6, 9, 29, 2.5, "https://cdn.britannica.com/84/188484-050-F27B0049/lemons-tree.jpg"));
        productList.add(Product.createProduct("Lime", "Fruits", 0.7, 11, 30, 1.5, "https://post.healthline.com/wp-content/uploads/2021/09/Fresh-Limes-and-Lime-Drink-1296x728-header.jpg"));
        productList.add(Product.createProduct("Blueberry", "Fruits", 0.7, 14, 57, 10, "https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/AN_images/blueberries-1296x728-feature.jpg?w=1155&h=1528"));
        productList.add(Product.createProduct("Raspberry", "Fruits", 1.2, 12, 52, 4.4, "https://www.diggers.com.au/cdn/shop/products/raspberry-willamette-wraw_611f6bfe-4b8e-44a1-8e0f-d8c661ab493c_2048x.jpg?v=1637122646"));
        productList.add(Product.createProduct("Blackberry", "Fruits", 1.4, 10, 43, 4.9, "https://images.immediate.co.uk/production/volatile/sites/10/2018/02/69fa32f9-f2ca-4005-bd3a-40e98aca45f7-c1ec280.jpg?quality=90&resize=940,627"));
        productList.add(Product.createProduct("Pear", "Fruits", 0.4, 15, 57, 10, "https://www.jiomart.com/images/product/original/590001270/pears-green-imported-3-pcs-pack-product-images-o590001270-p590040932-0-202203170454.jpg?im=Resize=(1000,1000)"));
        productList.add(Product.createProduct("Apricot", "Fruits", 0.5, 12, 48, 9, "https://cdn.britannica.com/36/160636-050-B1DC5C0A/Laetrile-apricot-pits.jpg"));
        productList.add(Product.createProduct("Grapefruit", "Fruits", 0.8, 11, 42, 8, "https://www.jacksonville.com/gcdn/authoring/2016/03/04/NFTU/ghows-LK-b6179534-612c-4781-b1f6-a28143f66131-aec2a7d2.jpeg?width=1200&disable=upscale&format=pjpg&auto=webp"));
        productList.add(Product.createProduct("Papaya", "Fruits", 0.5, 11, 43, 5.9, "https://m.economictimes.com/thumb/msid-97952518,width-1200,height-900,resizemode-4,imgsize-91748/papaya-istock.jpg"));
        productList.add(Product.createProduct("Cantaloupe", "Fruits", 0.8, 8, 34, 8, "https://cdn-prod.medicalnewstoday.com/content/images/articles/279/279176/a-close-up-of-a-sliced-cantaloupe-on-a-blue-table.jpg"));
        productList.add(Product.createProduct("Fig", "Fruits", 0.8, 19, 74, 16, "https://cdn-prod.medicalnewstoday.com/content/images/articles/327/327207/figs-on-a-table-that-may-be-beneficial-to-health.jpg"));
        productList.add(Product.createProduct("Pomegranate", "Fruits", 1.7, 18, 83, 13, "https://i0.wp.com/post.healthline.com/wp-content/uploads/2022/02/pomegranate-seeds-fruit-1296x728-header.jpg?w=1155&h=1528"));
        productList.add(Product.createProduct("Lychee", "Fruits", 0.8, 17, 66, 15, "https://res.cloudinary.com/hz3gmuqw6/image/upload/c_fill,q_auto,w_750/f_auto/lychee-phpjOjjNi"));
        productList.add(Product.createProduct("Dragon Fruit", "Fruits", 1.2, 13, 50, 8, "https://impro.usercontent.one/appid/oneComShop/domain/myexoticfruit.com/media/myexoticfruit.com/webshopmedia/dragonfruit%20red-1561254739488.jpg?&withoutEnlargement&resize=1920+9999&quality=85&progressive"));
        productList.add(Product.createProduct("Passion Fruit", "Fruits", 2.2, 23, 97, 11, "https://www.eatingwell.com/thmb/TrY3ffH54RWOZgUkS48wn7LmZqc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/passion-fruit-2000-44149d9e69b74547bc3212f53502153c.jpg"));
        productList.add(Product.createProduct("Guava", "Fruits", 2.6, 14, 68, 9, "https://images-prod.healthline.com/hlcmsresource/images/AN_images/benefits-of-guavas-1296x728-feature.jpg"));
        productList.add(Product.createProduct("Cranberry", "Fruits", 0.4, 12, 46, 4, "https://media.post.rvohealth.io/wp-content/uploads/2020/08/cranberries-101-732x549-thumbnail-732x549.jpg"));
        productList.add(Product.createProduct("Tangerine", "Fruits", 0.8, 13, 53, 11, "https://draxe.com/wp-content/uploads/2019/11/tangerines_thumb.jpg"));
        productList.add(Product.createProduct("Coconut", "Fruits", 3.3, 15, 354, 6, "https://www.brookstropicals.com/wp-content/uploads/2020/04/halfwholepieces2.jpg"));
        productList.add(Product.createProduct("Jackfruit", "Fruits", 1.7, 24, 95, 19, "https://images.everydayhealth.com/images/diet-nutrition/jackfruit-101-1440x810.jpg"));
        productList.add(Product.createProduct("Nectarine", "Fruits", 1.1, 12, 44, 8, "https://cdn.britannica.com/25/182625-050-327FAC9B/Nectarine-fruits.jpg"));
        productList.add(Product.createProduct("Kumquat", "Fruits", 1.9, 16, 71, 9, "https://cdn.britannica.com/17/136017-050-554676CD/Kumquat-fruit.jpg"));
        productList.add(Product.createProduct("Starfruit", "Fruits", 1, 6.7, 31, 4, "https://draxe.com/wp-content/uploads/2018/06/starfruit_FB.jpg"));
        productList.add(Product.createProduct("Persimmon", "Fruits", 0.6, 18, 81, 12, "https://npr.brightspotcdn.com/dims4/default/c8b0424/2147483647/strip/true/crop/3000x2000+0+0/resize/880x587!/quality/90/?url=http%3A%2F%2Fnpr-brightspot.s3.amazonaws.com%2Fe3%2F2c%2F003461264b678fc3351e0ed09fc2%2Fpersimmon-fruit.jpg"));
        productList.add(Product.createProduct("Rambutan", "Fruits", 0.9, 20, 68, 13, "https://img.onmanorama.com/content/dam/mm/en/lifestyle/health/images/2024/6/2/rambutan.jpg"));
        productList.add(Product.createProduct("Mulberry", "Fruits", 1.4, 10, 43, 8, "https://media.post.rvohealth.io/wp-content/uploads/2020/08/mulberries-732x549-thumbnail.jpg"));
        productList.add(Product.createProduct("Custard Apple", "Fruits", 1.7, 25, 101, 24, "https://cdn.britannica.com/95/126995-050-101B8B8D/Sweetsop.jpg"));

// Meat an Proteins
        productList.add(Product.createProduct("Chicken Breast", "Meat and Proteins", 31, 0, 165, 0, "https://www.southernliving.com/thmb/-TWfCwlFHd-7DIzTIYXh2Zxr_f8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/How_To_Grill_Chicken_010-e4aff40705b247868fe3fb6755e8bdd4.jpg"));
        productList.add(Product.createProduct("Beef", "Meat and Proteins", 26, 0, 250, 0, "https://hips.hearstapps.com/hmg-prod/images/delish-roast-beef-horizontal-1540505165.jpg?crop=1xw:0.84375xh;center,top"));
        productList.add(Product.createProduct("Turkey Breast", "Meat and Proteins", 29, 0, 135, 0, "https://www.southernliving.com/thmb/13oAPmgEoKCWkWxONEFKgpCIqZM=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Roasted-Turkey-Breast-130-step5-beauty-4x3-49e93367fca3427c9580e53403cdfa89.jpg"));
        productList.add(Product.createProduct("Salmon", "Meat and Proteins", 25, 0, 206, 0, "https://www.onceuponachef.com/images/2018/02/pan-seared-salmon-.jpg"));
        productList.add(Product.createProduct("Pork Loin", "Meat and Proteins", 27, 0, 242, 0, "https://www.allrecipes.com/thmb/I5dIo1Nf2FgTSWkned-aoRJoGVs=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/21766-roasted-pork-loin-DDMFS-4x3-42648a2d6acf4ef3a05124ef5010c4fb.jpg"));
        productList.add(Product.createProduct("Cod", "Meat and Proteins", 20, 0, 82, 0, "https://images.getbento.com/accounts/f0bb46b4cd2ed810d3f2dc53bbcc07c2/media/images/12589shutterstock_1739796365.jpg?w=1200&fit=crop&auto=compress,format&crop=focalpoint&fp-x=0.5&fp-y=0.5"));
        productList.add(Product.createProduct("Bacon", "Meat and Proteins", 37, 1.4, 541, 0, "https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco,c_fill,g_auto,w_1500,ar_3:2/k%2Farchive%2Fad4881c2ec9f21cafb7f5d209c83b6849d6b0d23"));
        productList.add(Product.createProduct("Ham", "Meat and Proteins", 21, 1.5, 145, 0, "https://assets2.kansascitysteaks.com/dyn-images/pdp_hero/Bone-in_Spiral_Slice-86b384db85fb7a7ea7866f723168cfe1.jpg"));
        productList.add(Product.createProduct("Sausage", "Meat and Proteins", 19, 1.9, 301, 1.1, "https://keepingitsimpleitalian.com/wp-content/uploads/2022/11/Baked-Italian-Sausage.jpg"));
        productList.add(Product.createProduct("Venison", "Meat and Proteins", 30, 0, 158, 0, "https://peaktoplate.com/wp-content/uploads/2023/10/marinated-venison-steak-on-white-plate-1200x1200-1.jpg"));
        productList.add(Product.createProduct("Duck", "Meat and Proteins", 19, 0, 337, 0, "https://healthyrecipesblogs.com/wp-content/uploads/2022/06/duck-breast-featured.jpg"));
        productList.add(Product.createProduct("Goose", "Meat and Proteins", 23, 0, 305, 0, "https://www.simplyrecipes.com/thmb/ekWUmk5O_TyjTOJLBvYGcCvaIiQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2009__12__roast-goose-horiz-a-1500-e781a3ad6dd945a085313b4ce1264ddb.jpg"));
        productList.add(Product.createProduct("Almonds", "Meat and Proteins", 21, 22, 576, 4.4, "https://cdn.britannica.com/04/194904-050-1B92812A/Raw-Food-Almond-food-Nut-Snack.jpg"));
        productList.add(Product.createProduct("Lentils", "Meat and Proteins", 9, 20, 116, 1.8, "https://i0.wp.com/post.medicalnewstoday.com/wp-content/uploads/sites/3/2019/11/lentils-in-a-jug-and-on-a-spoon.jpg?w=1155&h=1734"));
        productList.add(Product.createProduct("Tofu", "Meat and Proteins", 8, 2, 70, 0.5, "https://sweetsimplevegan.com/wp-content/uploads/2020/09/Easy-Crispy-Baked-Tofu-Recipe-Sweet-Simple-Vegan-6.jpg"));
        productList.add(Product.createProduct("Chickpeas", "Meat and Proteins", 8.9, 27.4, 164, 4.8, "https://www.inspiredtaste.net/wp-content/uploads/2016/06/How-to-Cook-Chickpeas-Recipe-1-1200.jpg"));
        productList.add(Product.createProduct("Peanuts", "Meat and Proteins", 25.8, 16.1, 567, 4.7, "https://d131k5wuh4trw5.cloudfront.net/uploads/9-1-1024x1024.png"));
        productList.add(Product.createProduct("Edamame", "Meat and Proteins", 11, 9, 122, 2.2, "https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2021/12/edamame_shelled_header-1024x575.jpg"));
        productList.add(Product.createProduct("Tempeh", "Meat and Proteins", 19, 9, 192, 0.5, "https://upload.wikimedia.org/wikipedia/commons/e/e1/Tempeh_%288681605421%29.jpg"));
        productList.add(Product.createProduct("Pumpkin Seeds", "Meat and Proteins", 19, 53, 446, 1.4, "https://www.heart.org/-/media/Images/News/2018/October-2018/1016PumpkinSeeds_SC.jpg"));
        productList.add(Product.createProduct("Sunflower Seeds", "Meat and Proteins", 21, 20, 584, 2.6, "https://media.post.rvohealth.io/wp-content/uploads/2020/06/sunflower-seeds-1200x628-facebook-1200x628.jpg"));
        productList.add(Product.createProduct("Black Beans", "Meat and Proteins", 9, 23, 132, 0.3, "https://cdn.loveandlemons.com/wp-content/uploads/2021/02/black-bean-recipes.jpg"));
        productList.add(Product.createProduct("Flax Seeds", "Meat and Proteins", 18, 29, 534, 1.6, "https://post.healthline.com/wp-content/uploads/2017/04/flax-seeds-1296x728-header.jpg"));
        productList.add(Product.createProduct("Hemp Seeds", "Meat and Proteins", 31.6, 8.7, 553, 1.5, "https://images-prod.healthline.com/hlcmsresource/images/AN_images/evidence-based-health-benefits-hemp-seeds-1296x728-feature.jpg"));
        productList.add(Product.createProduct("Chia Seeds", "Meat and Proteins", 16.5, 42, 486, 0, "https://www.eatright.org/-/media/images/eatright-articles/eatright-article-feature-images/whatarechiaseeds_600x450.jpg?as=0&w=967&rev=67939c1bf3da4990822649b8eeeba6e1&hash=2BC38C4931D995BF520645D7FF449D7F"));
        productList.add(Product.createProduct("Walnuts", "Meat and Proteins", 15, 14, 654, 2.6, "https://ayoubs.ca/cdn/shop/articles/806C657F-8252-4AC9-B965-F0299DD9D4B2_1024x1024.png?v=1637699391"));
        productList.add(Product.createProduct("Cashews", "Meat and Proteins", 18, 30, 553, 5.9, "https://i0.wp.com/post.healthline.com/wp-content/uploads/2020/11/cashew-1296x728-header.jpg?w=1155&h=1528"));
        productList.add(Product.createProduct("Brazil Nuts", "Meat and Proteins", 14, 12, 656, 2.3, "https://media.post.rvohealth.io/wp-content/uploads/sites/3/2020/02/325000_2200-732x549.jpg"));
        productList.add(Product.createProduct("Eggs", "Meat and Proteins", 13, 1.1, 155, 1.1, "https://cdn.britannica.com/94/151894-050-F72A5317/Brown-eggs.jpg"));
        productList.add(Product.createProduct("Whey Protein Powder", "Meat and Proteins", 80, 8, 400, 5, "https://silverbacknutrition.in/cdn/shop/articles/how-is-whey-protein-made.webp?v=1705495488"));


// Dairy
        productList.add(Product.createProduct("Whole Milk", "Dairy", 3.4, 4.8, 61, 5, "https://www.mashed.com/img/gallery/what-you-didnt-know-about-whole-milk/intro-1608135126.jpg"));
        productList.add(Product.createProduct("Cheddar Cheese", "Dairy", 25, 1.3, 403, 0.5, "https://www.verywellfit.com/thmb/qCFraQvwmOGLn6oJeaJybaN8Cjc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/cheddar-cheese-crop-3fdf5ad3229e4748bbc0b8acdc442dcd.jpg"));
        productList.add(Product.createProduct("Yogurt (Plain)", "Dairy", 10, 4, 59, 4, "https://www.simplyrecipes.com/thmb/pEW30kDnp62JDYj7ULFVfGZCysw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Simply-Recipes-GreekYogurt-LEAD-5-800a5f70732348c0a9c0a5196bd1d809.jpg"));
        productList.add(Product.createProduct("Cottage Cheese", "Dairy", 11, 3.4, 98, 2.7, "https://www.fermentingforfoodies.com/wp-content/uploads/2019/10/Small-curd-cottage-cheese.jpg"));
        productList.add(Product.createProduct("Mozzarella Cheese", "Dairy", 22, 2.2, 280, 1, "https://www.seriouseats.com/thmb/0LrG8tB4BkzQarr2fqrpykcaDBg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2015__10__20151017-pies-vicky-wasik-2-6f491edb6065485a86d6af639a592298.jpg"));
        productList.add(Product.createProduct("Greek Yogurt (Plain)", "Dairy", 10, 4, 59, 4, "https://cdn-prod.medicalnewstoday.com/content/images/articles/323/323169/greek-yoghurt-in-bowl.jpg"));
        productList.add(Product.createProduct("Butter", "Dairy", 0.85, 0.06, 717, 0.06, "https://cdn.britannica.com/27/122027-050-EAA86783/Butter.jpg"));
        productList.add(Product.createProduct("Milk (Skim)", "Dairy", 3.4, 5, 35, 5, "https://hips.hearstapps.com/hmg-prod/images/gettyimages-88437422-1528485499.jpg"));
        productList.add(Product.createProduct("Sour Cream", "Dairy", 2.4, 4.6, 181, 4, "https://www.biggerbolderbaking.com/wp-content/uploads/2016/09/1C5A7606.jpg"));
        productList.add(Product.createProduct("Parmesan Cheese", "Dairy", 35.8, 3.2, 431, 0.9, "https://assets.clevelandclinic.org/transform/0a272376-d2c4-4936-8239-7c7ef2e5b4e9/ParmesanCheese-471343790-770x533-1_jpg"));
        productList.add(Product.createProduct("Feta Cheese", "Dairy", 14.2, 4.1, 265, 4, "https://media.post.rvohealth.io/wp-content/uploads/2020/08/Feta_Cheese_Good_or_Bad-732x549-thumbnail.jpg"));
        productList.add(Product.createProduct("Swiss Cheese", "Dairy", 27, 5, 380, 0, "https://dfwblobstorage.blob.core.windows.net/ewcmediacontainer/eatwisconsincheese/media/content/cheesemasters-2019/swiss-cheese-header_2.jpg"));
        productList.add(Product.createProduct("Ricotta Cheese", "Dairy", 7.6, 3, 174, 0.3, "https://www.tastingtable.com/img/gallery/Homemade-Ricotta-Cheese-Receipe/from-the-tasting-table-test-kitchen-1668539266.jpg"));
        productList.add(Product.createProduct("Cream Cheese", "Dairy", 6.2, 6.5, 350, 6, "https://cheesemaking.com/cdn/shop/products/cream-cheese.jpg?crop=center&height=1200&v=1529434177&width=1200"));

// Fats, Oils, and Sweets
        productList.add(Product.createProduct("Olive Oil", "Fats, Oils, and Sweets", 0, 0, 884, 0, "https://cdn-prod.medicalnewstoday.com/content/images/articles/321/321246/olive-oil-in-a-bottle-which-may-be-used-on-the-face.jpg"));
        productList.add(Product.createProduct("Peanut Butter", "Fats, Oils, and Sweets", 25, 20, 588, 7, "https://domf5oio6qrcr.cloudfront.net/medialibrary/1980/peanut-butter-healthy.jpg"));
        productList.add(Product.createProduct("Dark Chocolate", "Fats, Oils, and Sweets", 7.8, 46.4, 598, 24, "https://www.health.com/thmb/VPWypVFVWHDpFOOKLDWHIOyMw_w=/2119x0/filters:no_upscale():max_bytes(150000):strip_icc()/DarkChocolate-252203bdcd4146e482bd6556ec7cdd9a.jpg"));
        productList.add(Product.createProduct("Canola Oil", "Fats, Oils, and Sweets", 0, 0, 884, 0, "https://cdn.britannica.com/16/234016-050-0220A0BE/canola-oil-and-canola-blossoms.jpg"));
        productList.add(Product.createProduct("Coconut Oil", "Fats, Oils, and Sweets", 0, 0, 862, 0, "https://www.healthshots.com/wp-content/uploads/2023/09/coconut-oil.jpg"));
        productList.add(Product.createProduct("Sunflower Oil", "Fats, Oils, and Sweets", 0, 0, 884, 0, "https://www.eatingwell.com/thmb/BatmJma8il7UHs58eyBSdGTg_sc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/sunflower-oil-caebdc08b58c4afe8456a40e5e8dd030.jpg"));
        productList.add(Product.createProduct("Honey", "Fats, Oils, and Sweets", 0.3, 82, 304, 82, "https://images.immediate.co.uk/production/volatile/sites/30/2024/03/Honey440-bb52330.jpg"));
        productList.add(Product.createProduct("Maple Syrup", "Fats, Oils, and Sweets", 0, 67, 260, 67, "https://assets.clevelandclinic.org/transform/cc1fbafa-ad14-4db0-a132-0357234bd8a3/MapleSyrup-538184814-770x533-1_jpg"));
        productList.add(Product.createProduct("Granulated Sugar", "Fats, Oils, and Sweets", 0, 100, 387, 100, "https://recipes.net/wp-content/uploads/2024/02/what-is-a-granulated-sugar-substitute-1707787534.jpg"));
        productList.add(Product.createProduct("Brown Sugar", "Fats, Oils, and Sweets", 0, 97, 380, 97, "https://cdn.jwplayer.com/v2/media/F9e0EImz/thumbnails/VCZN7qMW.jpg"));
        productList.add(Product.createProduct("Corn Syrup", "Fats, Oils, and Sweets", 0, 77, 286, 77, "https://static.toiimg.com/photo/89750962.cms"));
        productList.add(Product.createProduct("Margarine", "Fats, Oils, and Sweets", 0, 0, 717, 0, "https://www.bakels.com.au/wp-content/uploads/sites/21/2019/06/Margarine.jpg"));
        productList.add(Product.createProduct("Mayonnaise", "Fats, Oils, and Sweets", 0.3, 0.6, 680, 0.6, "https://drizzleanddip.com/wp-content/uploads/2023/02/pack-shot.jpg"));
        productList.add(Product.createProduct("Jam", "Fats, Oils, and Sweets", 0.4, 65, 239, 65, "https://i0.wp.com/www.pardonyourfrench.com/wp-content/uploads/2022/05/strawberry-jam-5.jpg?fit=1200%2C1798&ssl=1"));
        productList.add(Product.createProduct("Jelly", "Fats, Oils, and Sweets", 0.4, 65, 235, 65, "https://www.comptoir-irlandais.com/26063/chivers-strawberry-jelly.jpg"));
        productList.add(Product.createProduct("Candy", "Fats, Oils, and Sweets", 0, 98, 394, 98, "https://m.media-amazon.com/images/I/91-ZQLmWkhL._AC_UF894,1000_QL80_.jpg"));
        productList.add(Product.createProduct("Syrup", "Fats, Oils, and Sweets", 0, 77, 286, 77, "https://www.allrecipes.com/thmb/hGltqtjyrl-rHWe9ksVbKNpaldQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/152002_homemademaplesyrup_ddmfs_3x4_2639-3d749bab68994949abef4882cecdeafd.jpg"));
        productList.add(Product.createProduct("Pastries", "Fats, Oils, and Sweets", 4, 52, 350, 22, "https://bakerpedia.com/wp-content/uploads/2020/06/Pastry_baking-processes-e1593464950587.jpg"));
        productList.add(Product.createProduct("Cookies", "Fats, Oils, and Sweets", 5, 68, 502, 29, "https://cdn.loveandlemons.com/wp-content/uploads/2020/12/cookie-recipes.jpg"));
        productList.add(Product.createProduct("Cake", "Fats, Oils, and Sweets", 3.2, 62, 390, 35, "https://bakingamoment.com/wp-content/uploads/2023/07/IMG_2051-ice-cream-cake.jpg"));
        productList.add(Product.createProduct("Ice Cream", "Fats, Oils, and Sweets", 3.5, 23, 207, 21, "https://carveyourcraving.com/wp-content/uploads/2021/06/chocolate-icecream-in-an-icecream-maker.jpg"));
        productList.add(Product.createProduct("Chocolate", "Fats, Oils, and Sweets", 7.6, 59.2, 546, 52.4, "https://www.thespruceeats.com/thmb/FhHcgQni8lgV0griUeDJMTAszxI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/chocolate_hero1-d62e5444a8734f8d8fe91f5631d51ca5.jpg"));
        productList.add(Product.createProduct("Ghee", "Fats, Oils, and Sweets", 0, 0, 900, 0, "https://vamshifarms.com/cdn/shop/files/gheefalling_2048x.jpg?v=1717574447"));
        productList.add(Product.createProduct("Lard", "Fats, Oils, and Sweets", 0, 0, 902, 0, "https://cdnimg.webstaurantstore.com/uploads/blog/2019/11/blog-lard_in-blog2.jpg"));
        productList.add(Product.createProduct("Shortening", "Fats, Oils, and Sweets", 0, 0, 884, 0, "https://easyvegancookies.com/wp-content/uploads/2022/05/what-is-shortening-and-how-do-you-use-it.jpg"));
        productList.add(Product.createProduct("Tahini", "Fats, Oils, and Sweets", 17, 22, 595, 1, "https://www.gimmesomeoven.com/wp-content/uploads/2023/05/Homemade-Tahini-Recipe-1.jpg"));
        productList.add(Product.createProduct("Nutella", "Fats, Oils, and Sweets", 6, 57.5, 539, 56.3, "https://upload.wikimedia.org/wikipedia/commons/7/73/Nutella_for_breakfast_-_Flickr_-_love.jsc.jpg"));


// Famous most consumed fast food products
        productList.add(Product.createProduct("Hamburger", "Fast Food", 13, 26, 250, 6, "https://feelgoodfoodie.net/wp-content/uploads/2020/07/Homemade-Hamburgers-TIMG.jpg"));
        productList.add(Product.createProduct("Cheeseburger", "Fast Food", 15, 28, 300, 7, "https://www.shakentogetherlife.com/wp-content/uploads/2022/10/hamburger-baked-in-oven.jpg"));
        productList.add(Product.createProduct("French Fries", "Fast Food", 3, 41, 312, 0.5, "https://www.recipetineats.com/tachyon/2022/09/Fries-with-rosemary-salt_1.jpg"));
        productList.add(Product.createProduct("Chicken Nuggets", "Fast Food", 16, 13, 220, 0.5, "https://kirbiecravings.com/wp-content/uploads/2023/11/3-ingredient-crispy-chicken-nuggets-9.jpg"));
        productList.add(Product.createProduct("Pizza", "Fast Food", 11, 30, 300, 3, "https://alfredough.ae/wp-content/uploads/2023/06/pepperoni-pizza.jpeg"));
        productList.add(Product.createProduct("Hot Dog", "Fast Food", 12, 19, 280, 3, "https://cdn.britannica.com/27/213427-050-869B98FE/Chicago-style-hot-dog.jpg"));
        productList.add(Product.createProduct("Grilled Chicken Sandwich", "Fast Food", 27, 41, 350, 7, "https://thefamilydinnerproject.org/wp-content/uploads/2014/07/Easy-grilled-chicken-sandwich.jpg"));
        productList.add(Product.createProduct("Fish Sandwich", "Fast Food", 18, 36, 300, 5, "https://parade.com/.image/ar_4:3%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTkwNTgxMzQ3Nzk0Mjk3OTgx/crispy-oven-fried-fish-sandwich_ftr.jpg"));
        productList.add(Product.createProduct("Burrito", "Fast Food", 10, 30, 290, 1, "https://cdn.britannica.com/13/234013-050-73781543/rice-and-chorizo-burrito.jpg"));
        productList.add(Product.createProduct("Taco (Beef)", "Fast Food", 11, 19, 210, 1, "https://www.onceuponachef.com/images/2023/08/Beef-Tacos.jpg"));
        productList.add(Product.createProduct("Onion Rings", "Fast Food", 3, 31, 340, 5, "https://kristineskitchenblog.com/wp-content/uploads/2022/03/crispy-air-fryer-onion-rings-recipe-0775.jpg"));
        productList.add(Product.createProduct("Milkshake", "Fast Food", 5, 50, 300, 40, "https://www.dessertfortwo.com/wp-content/uploads/2022/08/How-to-Make-a-Milkshake-11-735x1103.jpg"));
        productList.add(Product.createProduct("Chicken Wrap", "Fast Food", 18, 28, 320, 3, "https://www.hiddenvalley.com/wp-content/uploads/2021/04/buffalo-ranch-chicken-wrap-RDP.jpg?width=720&quality=75"));
        productList.add(Product.createProduct("Caesar Salad", "Fast Food", 12, 15, 250, 3, "https://itsavegworldafterall.com/wp-content/uploads/2023/04/Avocado-Caesar-Salad-FI-500x375.jpg"));
        productList.add(Product.createProduct("Breakfast Sandwich (Eggs and Cheese)", "Fast Food", 15, 30, 350, 5, "https://www.seriouseats.com/thmb/FOFHUZtFUfpPnIPQAseHyzbpMfo=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/09122022-GrilledCheeseEggplosionRecipe-AmandaSuarez-Hero-eb123d43626f43f4bdd3bc16d497918b.JPG"));
        productList.add(Product.createProduct("Fried Chicken (1 piece)", "Fast Food", 14, 12, 250, 0, "https://www.allrecipes.com/thmb/SoBuPU73KcbYHl3Kp3j8Xx4A3fc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/8805-CrispyFriedChicken-mfs-3x2-072-d55b8406d4ae45709fcdeb58a04143c2.jpg"));
        productList.add(Product.createProduct("Quesadilla", "Fast Food", 13, 26, 320, 2, "https://www.onceuponachef.com/images/2024/02/chipotle-chicken-quesadillas.jpg"));
        productList.add(Product.createProduct("Submarine Sandwich (Turkey)", "Fast Food", 18, 30, 300, 5, "https://img.sndimg.com/food/image/upload/q_92,fl_progressive,w_1200,c_scale/v1/img/recipes/13/95/55/56OWIrsSC6Gh6CHGsgxX_Italian%20sub%20SITE-1.jpg"));
        productList.add(Product.createProduct("Nachos", "Fast Food", 9, 21, 300, 2, "https://www.seriouseats.com/thmb/YBUAG17xy1nWYGPmFcJKeoODTzk=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/cheese-sauce-for-cheese-fries-and-nachos-hero-01-e6ccf966688c43ec8025cf9a19678423.jpg"));
        productList.add(Product.createProduct("Fried Shrimp", "Fast Food", 19, 15, 275, 0, "https://www.thespruceeats.com/thmb/5xkGMB8ZXz3KGF_y4Uxf7ZfQAvQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/ebi-fry-fried-shrimp-2031450-hero-01-46c436a89c164a9ab5980f888097fcd2.jpg"));

        return productList;
    }
}
