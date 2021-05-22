package com.saleef.mvcrecipeapp.Views.RecipeDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseViewMvc;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Recipe.RecipeDetailItem;


public class RecipeDetailViewImpl extends BaseViewMvc implements RecipeDetailsViewMvc {



        private final TextView mealName, mealContents,mealInstructions;
        private final ImageView mealImg;
        private final ProgressBar mProgressBar;
        private RequestOptions mRequestOptions;

         public RecipeDetailViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup){
             setRootView(layoutInflater.inflate(R.layout.recipe_item_details,viewGroup,false));
             mealName = findViewById(R.id.mealTitle);
             mealContents = findViewById(R.id.mealContents);
             mealInstructions = findViewById(R.id.mealInstructions);
             mealImg = findViewById(R.id.mealImage);
             mProgressBar = findViewById(R.id.homeProgress);
             mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                     error(R.drawable.ic_launcher_background);
         }










    @Override
    public void bindRecipeDetails(RecipeDetailItem recipeDetailItem) {
             mealName.setText(recipeDetailItem.getName());
             mealContents.setText(recipeDetailItem.getAllIngredients());
             mealInstructions.setText(recipeDetailItem.getInstructions());
        Glide.with(getContext()).
                load(recipeDetailItem.getThumbnail()) // loads image based off a url
                .apply(mRequestOptions). // apply image based off options
                into(mealImg);
    }

    @Override
    public void showLoading() {
         mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
       mProgressBar.setVisibility(View.GONE);
    }
}
