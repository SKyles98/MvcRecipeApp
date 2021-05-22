package com.saleef.mvcrecipeapp.Networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeCategorySchema {
    @SerializedName("idCategory")
    private String id;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strCategoryThumb")
    private String thumbnail;
    @SerializedName("strCategoryDescription")
    private String Description;

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return Description;
    }
}
