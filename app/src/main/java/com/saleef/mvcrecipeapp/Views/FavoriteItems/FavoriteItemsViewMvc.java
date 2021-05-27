package com.saleef.mvcrecipeapp.Views.FavoriteItems;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

import java.util.List;

public interface FavoriteItemsViewMvc extends ObservableMvc<FavoriteItemsViewMvc.Listener> {
    interface Listener{
       void onFavoritedRecipeItemClicked(RecipeItem recipeItem);
    }

   void bindRecipeItems(List<RecipeItem> recipeItemList);
    void showProgressIndication();
    void hideProgressIndication();
}
