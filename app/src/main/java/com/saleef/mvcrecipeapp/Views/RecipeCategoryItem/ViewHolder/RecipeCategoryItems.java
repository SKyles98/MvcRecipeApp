package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder;

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

public class RecipeCategoryItems extends BaseObservable<RecipeCategorysItemsMvc.Listener> implements RecipeCategorysItemsMvc {


    private final ImageView mImageView;
    private final TextView mTextView;
    private final RequestOptions mRequestOptions;
    private RecipeItem mRecipeItem;
    public RecipeCategoryItems(LayoutInflater layoutInflater, ViewGroup viewGroup){
        setRootView(layoutInflater.inflate(R.layout.recycler_recipe_item_category,viewGroup,false));
        mImageView = findViewById(R.id.itemThumb);
        mTextView = findViewById(R.id.itemName);
        mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:getListeners()){
                    listener.onRecipeItemClicked(mRecipeItem);
                }
            }
        });
    }

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    @Override
    public void bindRecipeItem(RecipeItem recipeItem) {
       mRecipeItem = recipeItem;

        Glide.with(getContext()).
                load(mRecipeItem.getThumbnail()) // loads image based off a url
                .apply(mRequestOptions). // apply image based off options
                into(mImageView);

        mTextView.setText(recipeItem.getName());
    }
}
