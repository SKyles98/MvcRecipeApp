package com.saleef.mvcrecipeapp.Views.RecipeCategories.ViewHolder;


import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;

public interface RecipeItemViewMvc extends ObservableMvc<RecipeItemViewMvc.Listener> {

    interface Listener{
       void  onRecipeClicked(RecipeCategory recipeCategory);
    }

    void bindItem(RecipeCategory recipeCategory);
}
