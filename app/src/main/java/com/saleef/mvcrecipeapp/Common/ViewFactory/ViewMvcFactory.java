package com.saleef.mvcrecipeapp.Common.ViewFactory;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.saleef.mvcrecipeapp.Views.ActionBarController;
import com.saleef.mvcrecipeapp.Views.HomeScreen.HomeScreenViewImpl;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.HomeViewImpl;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.ViewHolder.RecipeItemViewMvcImpl;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder.RecipeCategoryItems;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.RecipeCategoryItemsImpl;
import com.saleef.mvcrecipeapp.Views.RecipeDetails.RecipeDetailViewImpl;


/*
A factory classes purpose is to return a single instance of a object or hierarchy of objects without
needed dependencies from outer layers in the project
 */
public class ViewMvcFactory {
    private final LayoutInflater mLayoutInflater;
   private final ActionBarController mActionBarController;
    public ViewMvcFactory(LayoutInflater layoutInflater, ActionBarController actionBarController){
        mLayoutInflater = layoutInflater;
        mActionBarController = actionBarController;

    }

    public HomeScreenViewImpl getHomeScreenViewImpl(ViewGroup parent){
        return new HomeScreenViewImpl(mLayoutInflater,parent,mActionBarController);
    }

    public HomeViewImpl getHomeViewImpl(ViewGroup parent){
        return new HomeViewImpl(mLayoutInflater,parent,mActionBarController);
    }

    public RecipeItemViewMvcImpl getRecipeItemViewMvcImpl(ViewGroup parent){
        return new RecipeItemViewMvcImpl(mLayoutInflater,parent);
    }

    public RecipeCategoryItemsImpl getRecipeCategoryItemsImpl(ViewGroup parent){
        return new RecipeCategoryItemsImpl(mLayoutInflater,parent,mActionBarController);
    }

    public RecipeCategoryItems getRecipeCategoryItems(ViewGroup parent){
        return new RecipeCategoryItems(mLayoutInflater,parent);
    }

    public RecipeDetailViewImpl getRecipeDetailViewImpl(ViewGroup parent){
        return new RecipeDetailViewImpl(mLayoutInflater,parent);
    }



}
