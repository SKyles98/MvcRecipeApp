package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder.RecipeCategoryItems;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.ViewHolder.RecipeCategorysItemsMvc;

import java.util.ArrayList;
import java.util.List;

public class RecipeCategoryItemAdapter extends RecyclerView.Adapter<RecipeCategoryItemAdapter.RecipeCategoryViewHolder>
        implements RecipeCategorysItemsMvc.Listener {



    public interface Listener{
             void onRecipeItemClicked(RecipeItem recipeItem);
          }

          private RecipeCategoryItems mRecipeCategoryItems;
          private final Listener mListener;
          private List<RecipeItem> mRecipeItems;
          private final ViewMvcFactory mViewMvcFactory;


          public RecipeCategoryItemAdapter(Listener listener, ViewMvcFactory viewMvcFactory){
                 mListener = listener;
                 mViewMvcFactory = viewMvcFactory;
                 mRecipeItems = new ArrayList<>();
          }

    @NonNull
    @Override
    public RecipeCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mRecipeCategoryItems = mViewMvcFactory.getRecipeCategoryItems(parent);
         mRecipeCategoryItems.register(this);

         return new RecipeCategoryViewHolder(mRecipeCategoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCategoryViewHolder holder, int position) {
           holder.mCategoryItems.bindRecipeItem(mRecipeItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipeItems.size();
    }


    public void bindRecipeCategoryItems(List<RecipeItem> recipeItemList){
           mRecipeItems = recipeItemList;
           notifyDataSetChanged();
    }
    @Override
    public void onRecipeItemClicked(RecipeItem recipeItem) {
               mListener.onRecipeItemClicked(recipeItem);
    }

    static class RecipeCategoryViewHolder extends RecyclerView.ViewHolder {
              private final RecipeCategoryItems mCategoryItems;
        public RecipeCategoryViewHolder(@NonNull RecipeCategoryItems recipeCategoryItems) {
            super(recipeCategoryItems.getRootView());
            mCategoryItems = recipeCategoryItems;
        }
    }


}
