package com.saleef.mvcrecipeapp.Views.FavoriteItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Views.ActionBarController;
import com.saleef.mvcrecipeapp.Views.FavoriteItems.Adapter.RecipeFavoritedItemRecyclerAdapter;

import java.util.List;
import java.util.Set;
// View class for the favorite button navigation in the drawer
public class FavoriteItemsViewImpl extends BaseObservable<FavoriteItemsViewMvc.Listener> implements FavoriteItemsViewMvc, RecipeFavoritedItemRecyclerAdapter.Listener {

    private final ProgressBar mProgressBar;
    private final RecipeFavoritedItemRecyclerAdapter mAdapter;
    private final RecyclerView mRecyclerView;
    private final ViewMvcFactory mViewMvcFactory;

    public FavoriteItemsViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ActionBarController actionBarController){
       setRootView(layoutInflater.inflate(R.layout.fragment_favorite_recipes,viewGroup,false));
       mProgressBar = findViewById(R.id.homeProgress);
       mRecyclerView = findViewById(R.id.recycler_recipe_favorite_items);
       mViewMvcFactory = new ViewMvcFactory(layoutInflater,actionBarController,null);
       mAdapter = new RecipeFavoritedItemRecyclerAdapter(mViewMvcFactory,this);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       mRecyclerView.setAdapter(mAdapter);
    }






    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    @Override
    public void bindRecipeItems(List<RecipeItem> recipeItemList) {
               mAdapter.bindRecipeFavorites(recipeItemList);
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
    public void onFavoriteRecipeClicked(RecipeItem recipeItem) {
                for (Listener listener:getListeners()){
                    listener.onFavoritedRecipeItemClicked(recipeItem);
                }
    }
}
