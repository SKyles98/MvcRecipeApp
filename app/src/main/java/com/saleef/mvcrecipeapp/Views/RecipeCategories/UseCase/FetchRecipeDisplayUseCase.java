package com.saleef.mvcrecipeapp.Views.RecipeCategories.UseCase;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.Networking.MealDbApi;
import com.saleef.mvcrecipeapp.Networking.RecipeCategorySchema;
import com.saleef.mvcrecipeapp.Networking.RecipeWrapperSchema;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchRecipeDisplayUseCase extends BaseObservable<FetchRecipeDisplayUseCase.Listener> {


    public interface Listener{
        void onRecipeDisplayFetchedSuccess(List<RecipeCategory> recipeCategories);
        void onRecipeDisplayFetchedFailure(Throwable t);
    }


    private MealDbApi mMealDbApi;
    private ArrayList<RecipeCategory> mRecipes;
      public FetchRecipeDisplayUseCase(MealDbApi mealDbApi){
          mMealDbApi = mealDbApi;
          mRecipes = new ArrayList<>();
      }

    /**
     *
     *  response body returns a jsonObject formatted based off the Call schema provided
     */
      public void fetchRecipeInfo(){

          Call<RecipeWrapperSchema> call = mMealDbApi.getRecipeDetailsSchema();
              call.enqueue(new Callback<RecipeWrapperSchema>() {
                  @Override
                  public void onResponse(Call<RecipeWrapperSchema> call, Response<RecipeWrapperSchema> response) {
                         if (response.isSuccessful()){
                              notifySuccess(response.body().getRecipeCategorySchemas());
                         }
                  }

                  @Override
                  public void onFailure(Call<RecipeWrapperSchema> call, Throwable t) {
                           notifyFailure(t);
                  }
              });
          }


    /**
     * If successful get the data from the response body and add it to our list then we send the list to our
     * subscribers
     * @param recipeCategorySchemas
     */
      private void notifySuccess(List<RecipeCategorySchema> recipeCategorySchemas){

              ArrayList<RecipeCategory> recipeListArrayCategory = new ArrayList<>(recipeCategorySchemas.size());
              for (RecipeCategorySchema recipeCategorySchema:recipeCategorySchemas){
                  recipeListArrayCategory.add(new RecipeCategory(recipeCategorySchema.getCategory(),
                          recipeCategorySchema.getId(),recipeCategorySchema.getThumbnail(),recipeCategorySchema.getDescription()));
              }
              // send to subs
              for (Listener listener:getListeners()){
                  listener.onRecipeDisplayFetchedSuccess(recipeListArrayCategory);
              }

      }


      private void notifyFailure(Throwable t){
          for (Listener listener:getListeners()){
              listener.onRecipeDisplayFetchedFailure(t);
          }
      }

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }
}
