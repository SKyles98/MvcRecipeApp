package com.saleef.mvcrecipeapp.Networking;

import com.google.gson.annotations.SerializedName;

public class RecipeCategoryItemSchema {

    @SerializedName("strMeal")
    private String meal;

    @SerializedName("strMealThumb")
    private String thumbnail;

   @SerializedName("idMeal")
   private String id;

    public String getId() {
        return id;
    }

    public String getMeal() {
        return meal;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
