package com.saleef.mvcrecipeapp.Views.RecipeCategories.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;


import java.util.Set;
/*
 This is a viewCLass that binds the item from the adapter onBindViewHolder method
 */
public class RecipeItemViewMvcImpl extends BaseObservable<RecipeItemViewMvc.Listener> implements RecipeItemViewMvc{

    private final TextView recipeTitle;
    private final ImageView recipeImage;
    private final RequestOptions mRequestOptions;
    private RecipeCategory mRecipe;
    public RecipeItemViewMvcImpl(LayoutInflater layoutInflater, ViewGroup parent){
        setRootView(layoutInflater.inflate(R.layout.recycler_recipe_categories,parent,false));
        recipeImage = findViewById(R.id.categoryThumb);
        recipeTitle = findViewById(R.id.categoryName);
        mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background);
        // notifies subscribes of the current recipe being clicked on
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:getListeners()){
                    listener.onRecipeClicked(mRecipe);
                }
            }
        });
        recipeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:getListeners()){
                    listener.onRecipeClicked(mRecipe);
                }
            }
        });
    }


    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    // recycler adapter view population responsibilities is transferred to here
    @Override
    public void bindItem(RecipeCategory recipeCategory) {
        mRecipe = recipeCategory;
        // replace with glide implementation later
        // recipeImage.setImageResource(mRecipe.getRecipeImage());
        Glide.with(getContext()).
                load(recipeCategory.getThumbnail()) // loads image based off a url
                .apply(mRequestOptions). // apply image based off options
                into(recipeImage);
        recipeTitle.setText(mRecipe.getCategory());
    }
}
