package com.saleef.mvcrecipeapp.Views.FavoriteItems.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Views.FavoriteItems.ViewHolder.FavoritedItemMvc;
import com.saleef.mvcrecipeapp.Views.FavoriteItems.ViewHolder.FavoritedItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecipeFavoritedItemRecyclerAdapter extends RecyclerView.Adapter<RecipeFavoritedItemRecyclerAdapter.FavoriteItemViewHolder>
        implements FavoritedItemMvc.Listener {




    public interface Listener{
           void  onFavoriteRecipeClicked(RecipeItem recipeItem);
         }

         private final Listener mListener;
         private FavoritedItemViewHolder mFavoritedItemViewHolder;
         private final ViewMvcFactory mViewMvcFactory;
         private List<RecipeItem> mRecipeItems;
         public RecipeFavoritedItemRecyclerAdapter(ViewMvcFactory viewMvcFactory,Listener listener){
             mListener = listener;
          mViewMvcFactory = viewMvcFactory;
             mRecipeItems = new ArrayList<>();
         }

    @NonNull
    @Override
    public FavoriteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mFavoritedItemViewHolder = mViewMvcFactory.getFavoritedItemViewHolder(parent);
        mFavoritedItemViewHolder.register(this);

        return new FavoriteItemViewHolder(mFavoritedItemViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteItemViewHolder holder, int position) {
              holder.mFavoritedItemViewHolder.bindFavoriteRecipes(mRecipeItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipeItems.size();
    }

    public void bindRecipeFavorites(List<RecipeItem> recipeItems){
             mRecipeItems = recipeItems;
             notifyDataSetChanged();
    }
    @Override
    public void onFavoriteRecipeClicked(RecipeItem recipeItem) {
               mListener.onFavoriteRecipeClicked(recipeItem);
    }

    static class FavoriteItemViewHolder extends RecyclerView.ViewHolder{
             private final FavoritedItemViewHolder mFavoritedItemViewHolder;
        public FavoriteItemViewHolder(@NonNull FavoritedItemViewHolder favoritedItemViewHolder) {
            super(favoritedItemViewHolder.getRootView());
            mFavoritedItemViewHolder = favoritedItemViewHolder;
        }
    }
}
