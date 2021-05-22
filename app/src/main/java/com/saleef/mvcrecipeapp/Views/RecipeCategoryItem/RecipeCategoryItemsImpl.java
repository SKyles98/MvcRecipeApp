package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcrecipeapp.Views.ActionBarController;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.Adapters.RecipeCategoryItemAdapter;

import java.util.List;
import java.util.Set;

public class RecipeCategoryItemsImpl extends BaseObservable<RecipeCategoryItemViewMvc.Listener> implements RecipeCategoryItemViewMvc,
        RecipeCategoryItemAdapter.Listener {


    private final RecyclerView mRecyclerView;
    private final ProgressBar mProgressBar;
    private final RecipeCategoryItemAdapter mRecipeCategoryItemAdapter;
    private final ViewMvcFactory mViewMvcFactory;
    private final ActionBarController mActionBarController;
    //TODO Delegate network functions to Controller which is 0ur second fragment
    //TODO The Controller should start the loading
    // TODO Create Recycler Adapter to load meals the correspond to the category
    // TODO Create another USE CASE to handle network functions to load meals based off of category
    public RecipeCategoryItemsImpl(LayoutInflater layoutInflater, ViewGroup parent,ActionBarController actionBarController){
        setRootView(layoutInflater.inflate(R.layout.recipe_item_category,parent,false));
       mRecyclerView = findViewById(R.id.recycler_recipe_item_category);
       mProgressBar = findViewById(R.id.homeProgress);
        mActionBarController = actionBarController;
        mViewMvcFactory = new ViewMvcFactory(layoutInflater,actionBarController);
        mRecipeCategoryItemAdapter = new RecipeCategoryItemAdapter(this,mViewMvcFactory);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       mRecyclerView.setAdapter(mRecipeCategoryItemAdapter);


    }












    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    @Override
    public void bindRecipeItem(List<RecipeItem> recipeCategories) {
               mRecipeCategoryItemAdapter.bindRecipeCategoryItems(recipeCategories);
    }

    @Override
    public void showProgressIndication() {
           mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
         mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRecipeItemClicked(RecipeItem recipeItem) {
              for (Listener listener:getListeners()){
                  listener.onRecipeItemClicked(recipeItem);
              }
    }
}
