package com.saleef.mvcrecipeapp.Recipe;

import androidx.annotation.NonNull;

public class RecipeDetailItem {



    private String Instructions;
    private String allIngredients;
    private String name;
    private String thumbnail;

    public RecipeDetailItem(String Instructions,String allIngredients,String name,String thumbnail){
        this.Instructions = Instructions;
        this.allIngredients = allIngredients;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getInstructions() {
        return Instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }


    public String getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(String allIngredients) {
        this.allIngredients = allIngredients;
    }


}
