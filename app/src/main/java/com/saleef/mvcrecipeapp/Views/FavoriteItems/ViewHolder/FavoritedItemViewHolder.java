package com.saleef.mvcrecipeapp.Views.FavoriteItems.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

import java.util.Set;

public class FavoritedItemViewHolder extends BaseObservable<FavoritedItemMvc.Listener> implements FavoritedItemMvc {


    private final TextView mTextView;
    private final ImageView mImageView;
    private RecipeItem mRecipeItem;
    private final RequestOptions mRequestOptions;
    public FavoritedItemViewHolder(LayoutInflater layoutInflater, ViewGroup parent){
        setRootView(layoutInflater.inflate(R.layout.recycler_recipe_favorited,parent,false));
        mImageView = findViewById(R.id.favoriteitemThumb);
        mTextView = findViewById(R.id.favoriteitemName);
        mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:getListeners()){
                    // Begins the process of loading favorited recipe details
                    listener.onFavoriteRecipeClicked(mRecipeItem);
                }
            }
        });
    }







    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    @Override
    public void bindFavoriteRecipes(RecipeItem recipeItem) {
             mRecipeItem = recipeItem;
             mTextView.setText(recipeItem.getName());
        Glide.with(getContext()).
                load(recipeItem.getThumbnail()) // loads image based off a url
                .apply(mRequestOptions). // apply image based off options
                into(mImageView);
    }
}
