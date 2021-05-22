package com.saleef.mvcrecipeapp.Common.ScreenNavigator;

import com.saleef.mvcrecipeapp.Common.FragmentHelper.FragmentHandler;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.RecipeCateogoriesFragment;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.RecipeCategoryItemFragment;
import com.saleef.mvcrecipeapp.Views.RecipeDetails.RecipeDetailsFragment;

public class ScreenNavigator {

    private FragmentHandler mFragmentHandler;

    public ScreenNavigator(FragmentHandler fragmentHandler){
        mFragmentHandler = fragmentHandler;
    }





    public void toRecipeDetails(RecipeCategory recipeCategory){
        // In the id of the frame layout we replace the items with another fragment view ?????
          mFragmentHandler.replaceFragment(RecipeCategoryItemFragment.newInstance(recipeCategory.getCategory()));
    }

    public void toRecipeCategory(){
        mFragmentHandler.replaceFragment(RecipeCateogoriesFragment.newInstance());
    }

    public void toRecipeItemDetails(RecipeItem recipeItem){
        mFragmentHandler.replaceFragment(RecipeDetailsFragment.newInstance(recipeItem.getName()));
    }
    public void navigateUp(){
        //mFragmentTransaction.replace(R.id.frameContainer,)
    }
}
