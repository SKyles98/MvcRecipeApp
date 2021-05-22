package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

import java.util.List;

public interface RecipeCategoryItemViewMvc extends ObservableMvc<RecipeCategoryItemViewMvc.Listener> {

    interface Listener{
       void onRecipeItemClicked(RecipeItem recipeItem);
    }


    void bindRecipeItem(List<RecipeItem> recipeCategories);
    void showProgressIndication();
    void hideProgressIndication();
}
