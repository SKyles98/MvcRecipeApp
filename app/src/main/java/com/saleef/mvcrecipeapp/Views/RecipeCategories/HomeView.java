package com.saleef.mvcrecipeapp.Views.RecipeCategories;



import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;

import java.util.List;

// interface for notifying subs that a event has occurred
public interface HomeView extends ObservableMvc<HomeView.Listener> {

         interface Listener{
             void onRecipeClicked(RecipeCategory recipeCategory);
             void getFavoritedRecipes();
         }

         void bindRecipes(List<RecipeCategory> recipeCategories);
         void showProgressIndication();
         void hideProgressIndication();

}
