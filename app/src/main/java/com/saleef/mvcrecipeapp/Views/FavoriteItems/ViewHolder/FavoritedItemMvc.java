package com.saleef.mvcrecipeapp.Views.FavoriteItems.ViewHolder;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

public interface FavoritedItemMvc extends ObservableMvc<FavoritedItemMvc.Listener> {
    interface Listener{
       void onFavoriteRecipeClicked(RecipeItem recipeItem);
    }

   void bindFavoriteRecipes(RecipeItem recipeItem);
}
