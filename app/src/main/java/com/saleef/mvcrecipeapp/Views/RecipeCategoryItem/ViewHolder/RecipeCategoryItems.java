package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Networking.SharedPrefs;

import java.util.Set;

public class RecipeCategoryItems extends BaseObservable<RecipeCategorysItemsMvc.Listener> implements RecipeCategorysItemsMvc {


    private final ImageView mImageView;
    private final TextView mTextView;
    private final RequestOptions mRequestOptions;
    private RecipeItem mRecipeItem;
    private final ToggleButton mButton;
    public RecipeCategoryItems(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.recycler_recipe_item_category, viewGroup, false));
        mImageView = findViewById(R.id.itemThumb);
        mTextView = findViewById(R.id.itemName);
        mButton = findViewById(R.id.favoriteButton);
        mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onRecipeItemClicked(mRecipeItem);
                }
            }
        });

        mButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked){
                       // if the item is checked set the background to filled heart
                       mButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_favorite));
                       // tell listeners that an recipe has been favored;
                       for(Listener listener:getListeners()){
                           listener.onFavoriteButtonClicked(mRecipeItem,isChecked);
                       }
                   } else{
                       mButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_favorite_unchecked));
                       for(Listener listener:getListeners()){
                           listener.onFavoriteButtonClicked(mRecipeItem,isChecked);
                       }
                   }
            }
        });
    }

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    @Override
    public void bindRecipeItem(RecipeItem recipeItem, SharedPrefs sharedPrefs) {
       mRecipeItem = recipeItem;

        Glide.with(getContext()).
                load(mRecipeItem.getThumbnail()) // loads image based off a url
                .apply(mRequestOptions). // apply image based off options
                into(mImageView);

        mTextView.setText(recipeItem.getName());
        if (sharedPrefs.getSharedPreferences().contains(recipeItem.getId())){
            // If sharedpreferences contains item then set the button to favorited so user knows that the button was already checked
            mButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_favorite));
        }
    }
}
