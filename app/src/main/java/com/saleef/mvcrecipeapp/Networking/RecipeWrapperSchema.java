package com.saleef.mvcrecipeapp.Networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
 // gets the json array out of object format
public class RecipeWrapperSchema {
    @SerializedName("meal")
    @Expose
    private final List<RecipeListSchema> mMealDbApis;

    @SerializedName("categories")
    @Expose
    private final List<RecipeCategorySchema> mRecipeCategorySchemas;

    @SerializedName("meals")
    @Expose
    private final List<RecipeCategoryItemSchema> mRecipeCategoryItemSchemas;

    public RecipeWrapperSchema(List<RecipeListSchema> recipeListSchemas, List<RecipeCategorySchema> recipeCategorySchemas,
                               List<RecipeCategoryItemSchema> recipeCategoryItemSchemas){
           mMealDbApis = recipeListSchemas;
           mRecipeCategorySchemas = recipeCategorySchemas;
           mRecipeCategoryItemSchemas = recipeCategoryItemSchemas;
       }

     public List<RecipeCategorySchema> getRecipeCategorySchemas() {
         return mRecipeCategorySchemas;
     }

     public List<RecipeCategoryItemSchema> getRecipeCategoryItemSchemas() {
         return mRecipeCategoryItemSchemas;
     }

     public  List<RecipeListSchema> getMealDbApis(){
           return  mMealDbApis;
       }


}
