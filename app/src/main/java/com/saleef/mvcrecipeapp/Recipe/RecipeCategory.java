package com.saleef.mvcrecipeapp.Recipe;

public class RecipeCategory {


    private final String id;

    private final String category;

    private final String thumbnail;

    private final String Description;


    public RecipeCategory(String category, String id, String thumbnail, String description) {
        this.category = category;
        this.id = id;
        this.thumbnail = thumbnail;
        this.Description = description;
    }

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


