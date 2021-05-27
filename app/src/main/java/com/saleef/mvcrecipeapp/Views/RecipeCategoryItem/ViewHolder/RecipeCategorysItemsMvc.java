package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Networking.SharedPrefs;

public interface RecipeCategorysItemsMvc extends ObservableMvc<RecipeCategorysItemsMvc.Listener> {
    public interface Listener{
        void onRecipeItemClicked(RecipeItem recipeItem);
        void onFavoriteButtonClicked(RecipeItem recipeItem,boolean checked);
    }

    void bindRecipeItem(RecipeItem recipeItem, SharedPrefs sharedPrefs);
}
