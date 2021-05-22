package com.saleef.mvcrecipeapp.Views.RecipeDetails.UseCases;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.Networking.MealDbApi;
import com.saleef.mvcrecipeapp.Networking.RecipeDetailsWrapperSchema;
import com.saleef.mvcrecipeapp.Networking.RecipeListSchema;
import com.saleef.mvcrecipeapp.Recipe.RecipeDetailItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchRecipeDetailsUseCase extends BaseObservable<FetchRecipeDetailsUseCase.Listener> {



    public interface Listener{
       void onRecipeDetailRetrievalSuccess(List<RecipeDetailItem> recipeDetailItems);
        void onRecipeDetailRetrievalFailure(String errorMessage);
    }

        private MealDbApi mMealDbApi;

        public FetchRecipeDetailsUseCase(MealDbApi mealDbApi){
        mMealDbApi = mealDbApi;
        }



            public void fetchRecipeDetailInfo(String recipeName){

                Call<RecipeDetailsWrapperSchema> call = mMealDbApi.getRecipeSchema(recipeName);

                call.enqueue(new Callback<RecipeDetailsWrapperSchema>() {
                    @Override
                    public void onResponse(Call<RecipeDetailsWrapperSchema> call, Response<RecipeDetailsWrapperSchema> response) {
                        onResponseSuccess(response.body().getRecipeListSchemas());
                    }

                    @Override
                    public void onFailure(Call<RecipeDetailsWrapperSchema> call, Throwable t) {
                         onResponseFailure(t.toString());
                    }
                });
            }



    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

       private void onResponseSuccess(List<RecipeListSchema> listSchemas){
           ArrayList<RecipeDetailItem> recipeDetailItems = new ArrayList<>(listSchemas.size());

           for(RecipeListSchema recipeListSchema:listSchemas){
               String instructions = recipeListSchema.getInstructions();
               String allIngredients = recipeListSchema.getStrIngredient1() + " " + recipeListSchema.getStrMeasure1() + '\n' +
                       recipeListSchema.getStrIngredient2() + " " + recipeListSchema.getStrMeasure2() + '\n'
                       + recipeListSchema.getStrIngredient3() + " " + recipeListSchema.getStrMeasure3() + '\n' +
                       recipeListSchema.getStrIngredient4() + " " + recipeListSchema.getStrMeasure4() + '\n' +
                       recipeListSchema.getStrIngredient5() + " " + recipeListSchema.getStrMeasure5() + '\n' +
                       recipeListSchema.getStrIngredient6() + " " + recipeListSchema.getStrMeasure6() + '\n' +
                       recipeListSchema.getStrIngredient7() + " " + recipeListSchema.getStrMeasure7() + '\n' +
                       recipeListSchema.getStrIngredient8() + " " + recipeListSchema.getStrMeasure8() + '\n' +
                       recipeListSchema.getStrIngredient9() + " " + recipeListSchema.getStrMeasure9() + '\n' +
                       recipeListSchema.getStrIngredient10() + " " + recipeListSchema.getStrMeasure10() + '\n' +
                       recipeListSchema.getStrIngredient11() + " " + recipeListSchema.getStrMeasure11() + '\n' +
                       recipeListSchema.getStrIngredient12() + " " + recipeListSchema.getStrMeasure12() + '\n' +
                       recipeListSchema.getStrIngredient13() + " " + recipeListSchema.getStrMeasure13() + '\n' +
                       recipeListSchema.getStrIngredient14() + " " + recipeListSchema.getStrMeasure14() + '\n' +
                       recipeListSchema.getStrIngredient15() + " " + recipeListSchema.getStrMeasure15() + '\n' +
                       recipeListSchema.getStrIngredient16() + " " + recipeListSchema.getStrMeasure16() + '\n' +
                       recipeListSchema.getStrIngredient17() + " " + recipeListSchema.getStrMeasure17() + '\n' +
                       recipeListSchema.getStrIngredient18() + " " + recipeListSchema.getStrMeasure18() + '\n' +
                       recipeListSchema.getStrIngredient19() + " " + recipeListSchema.getStrMeasure19() + '\n' +
                       recipeListSchema.getStrIngredient20() + " " + recipeListSchema.getStrMeasure20();


                       recipeDetailItems.add(new RecipeDetailItem(instructions,allIngredients,
                               recipeListSchema.getName(),recipeListSchema.getMealImage()));
           }


           for (Listener listener:getListeners()){
                listener.onRecipeDetailRetrievalSuccess(recipeDetailItems);
           }
       }


       private void onResponseFailure(String errorMessage){
            for (Listener listener:getListeners()){
                listener.onRecipeDetailRetrievalFailure(errorMessage);
            }
       }
}
