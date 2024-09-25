package com.example.nutrition.Helper;

public class EdamamAPI {
    public static final String apiKey = "73852b082619618ae5eb814953750853";
    public static final String appId = "e7be29f8";
    public static final String baseUrl = "https://api.edamam.com/api/nutrition-data";

    public static String getUrl(String searchedText){
        return baseUrl + "?app_id=" + appId + "&app_key=" + apiKey + "&ingr=" + searchedText;
    }
}
