package com.saleef.mvcrecipeapp.Networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeDetailsWrapperSchema {


    @SerializedName("meals")
    @Expose
    private final List<RecipeListSchema> mRecipeListSchemas;





    public RecipeDetailsWrapperSchema(List<RecipeListSchema> recipeListSchemas){
        mRecipeListSchemas = recipeListSchemas;
    }


    public List<RecipeListSchema> getRecipeListSchemas() {
        return mRecipeListSchemas;
    }
}

