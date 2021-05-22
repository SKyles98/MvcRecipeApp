package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.UseCases;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.Networking.MealDbApi;
import com.saleef.mvcrecipeapp.Networking.RecipeCategoryItemSchema;
import com.saleef.mvcrecipeapp.Networking.RecipeWrapperSchema;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchRecipeCateogoryItemsUseCase extends BaseObservable<FetchRecipeCateogoryItemsUseCase.Listener> {

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    public interface Listener{
         void onRecipeItemRetrievedSuccess(ArrayList<RecipeItem> recipeItems);
         void onRecipeItemRetrievedFailure(String errorMessage);
     }

     private final MealDbApi mMealDbApi;
     public FetchRecipeCateogoryItemsUseCase(MealDbApi mealDbApi){
          mMealDbApi = mealDbApi;
     }

     public void fetchRecipeCategoryItemInfo(String categoryName){

         Call<RecipeWrapperSchema> call = mMealDbApi.getRecipeCategoryItemSchema(categoryName);
           call.enqueue(new Callback<RecipeWrapperSchema>() {
               @Override
               public void onResponse(Call<RecipeWrapperSchema> call, Response<RecipeWrapperSchema> response) {
                           onSuccess(response.body().getRecipeCategoryItemSchemas());
               }

               @Override
               public void onFailure(Call<RecipeWrapperSchema> call, Throwable t) {
                           notifyFailure(t.toString());
               }
           });
     }

     private void onSuccess(List<RecipeCategoryItemSchema> list){
         ArrayList<RecipeItem> recipeItems = new ArrayList<>(list.size());
         for (RecipeCategoryItemSchema recipeCategoryItemSchema:list){
             recipeItems.add(new RecipeItem(recipeCategoryItemSchema.getThumbnail(),recipeCategoryItemSchema.getMeal()));
         }

         for (Listener listener:getListeners()){
             listener.onRecipeItemRetrievedSuccess(recipeItems);
         }
     }


     private void notifyFailure(String errorMessage){
              for(Listener listener:getListeners()){
                  listener.onRecipeItemRetrievedFailure(errorMessage);
              }
     }
}
