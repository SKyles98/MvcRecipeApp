package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Networking.SharedPrefs;

import java.util.List;

public interface RecipeCategoryItemViewMvc extends ObservableMvc<RecipeCategoryItemViewMvc.Listener> {

    interface Listener{
       void onRecipeItemClicked(RecipeItem recipeItem);
       void onFavoriteItemClicked(RecipeItem recipeItem,boolean checked);
    }


    void bindRecipeItem(List<RecipeItem> recipeCategories, SharedPrefs sharedPrefs);
    void showProgressIndication();
    void hideProgressIndication();
}
