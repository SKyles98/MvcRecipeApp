package com.saleef.mvcrecipeapp.Networking;






import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;


// interface for retrofit
public interface MealDbApi {
    // url manipulation with the two curly brackets
    @GET("search.php")
    Observable<RecipeDetailsWrapperSchema> getObservableRecipeSchema(@Query("s") String name);

    @GET("search.php")
    Call<RecipeDetailsWrapperSchema> getRecipeSchema(@Query("s") String name);


     @GET("categories.php")
     Call<RecipeWrapperSchema> getRecipeDetailsSchema();

    @GET("filter.php")
    Call<RecipeWrapperSchema> getRecipeCategoryItemSchema(@Query("c") String category);

     @GET("filter.php")
    Observable<RecipeWrapperSchema> getObservableRecipeCategoryItemSchema(@Query("c") String category);




}
