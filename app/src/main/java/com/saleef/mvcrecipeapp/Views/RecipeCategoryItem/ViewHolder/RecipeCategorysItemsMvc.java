package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

public interface RecipeCategorysItemsMvc extends ObservableMvc<RecipeCategorysItemsMvc.Listener> {
    public interface Listener{
        void onRecipeItemClicked(RecipeItem recipeItem);
    }

    void bindRecipeItem(RecipeItem recipeItem);
}
