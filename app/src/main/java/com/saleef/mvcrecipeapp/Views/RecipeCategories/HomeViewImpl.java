package com.saleef.mvcrecipeapp.Views.RecipeCategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcrecipeapp.Views.ActionBarController;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;
import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.Adapter.RecipeAdapterMvc;


import java.util.List;
import java.util.Set;


// HomeScreen View class
public class HomeViewImpl extends BaseObservable<HomeView.Listener> implements HomeView, RecipeAdapterMvc.Listener {



   private final RecyclerView mRecyclerView;
   private final ProgressBar mProgressBar;
   private RecipeAdapterMvc mRecipeAdapterMvc;
   private final ViewMvcFactory mViewMvcFactory;




    public HomeViewImpl(LayoutInflater layoutInflater, ViewGroup parent, ActionBarController actionBarController){
       setRootView(layoutInflater.inflate(R.layout.recipes_list,parent,false));
       mRecyclerView = findViewById(R.id.recycler_recipe_list);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3,
                GridLayoutManager.VERTICAL, false);
       mRecyclerView.setLayoutManager(layoutManager);
       mViewMvcFactory = new ViewMvcFactory(layoutInflater,actionBarController);
       mRecipeAdapterMvc = new RecipeAdapterMvc(mViewMvcFactory,this,null);
       mRecyclerView.setAdapter(mRecipeAdapterMvc);
       mProgressBar = findViewById(R.id.homeProgress);


    }







    @Override
    protected void notifySubscribers(Set<HomeView.Listener> listeners) {
        for (HomeView.Listener listener:listeners){

        }
    }

    @Override
    public void onRecipeClicked(RecipeCategory recipeCategory) {
        for (HomeView.Listener listener:getListeners()){
            listener.onRecipeClicked(recipeCategory);
        }
    }



    @Override
    public void bindRecipes(List<RecipeCategory> recipeCategories) {
           mRecipeAdapterMvc.bindRecipes(recipeCategories);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
        //mShimmerFrameLayout.startShimmer();
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
        //mShimmerFrameLayout.stopShimmer();
    }



}
