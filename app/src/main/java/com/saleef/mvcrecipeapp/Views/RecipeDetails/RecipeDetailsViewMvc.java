package com.saleef.mvcrecipeapp.Views.RecipeDetails;

import com.saleef.mvcrecipeapp.Recipe.RecipeDetailItem;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ViewMvc;

public interface RecipeDetailsViewMvc extends ViewMvc {


    void bindRecipeDetails(RecipeDetailItem recipeDetailItem);
    void showLoading();
    void hideLoading();
}
