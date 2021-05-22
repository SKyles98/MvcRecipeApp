package com.saleef.mvcrecipeapp.Networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

// interface for retrofit
public interface MealDbApi {
    // url manipulation with the two curly brackets
    @GET("search.php")
    Call<RecipeDetailsWrapperSchema>  getRecipeSchema(@Query("s") String name);


     @GET("categories.php")
     Call<RecipeWrapperSchema> getRecipeDetailsSchema();

     @GET("filter.php")
     Call<RecipeWrapperSchema> getRecipeCategoryItemSchema(@Query("c") String category);




}
