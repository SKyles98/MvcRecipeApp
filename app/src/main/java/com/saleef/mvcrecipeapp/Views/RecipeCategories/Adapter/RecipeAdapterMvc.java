package com.saleef.mvcrecipeapp.Views.RecipeCategories.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;
import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.ViewHolder.RecipeItemViewMvc;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapterMvc extends RecyclerView.Adapter<RecipeAdapterMvc.RecipeViewHolder> implements RecipeItemViewMvc.Listener {

    // a class will implement this interface to become a subscriber
    public interface Listener{
       void  onRecipeClicked(RecipeCategory recipeCategory);
    }

  // instantiation of a listener in constructor  is used here because the adapter has to extend adapter abstract class instead of BaseObservable one
    // so in turn we access the listener by it being passed in from a another class
   private final Listener mListener;
   private ArrayList<RecipeCategory> mRecipes;
   private final ViewMvcFactory mViewMvcFactory;
     public RecipeAdapterMvc(ViewMvcFactory mvcFactory, Listener listener, ViewGroup viewGroup){

         mListener = listener;
         mViewMvcFactory = mvcFactory;
         mRecipes = new ArrayList<>();
     }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       RecipeItemViewMvc recipeItemViewMvc = mViewMvcFactory.getRecipeItemViewMvcImpl(parent);
       // Adapter is now a subscriber of recipeItemView
       recipeItemViewMvc.register(this);
       return new RecipeViewHolder(recipeItemViewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
         // this is how the questions are formatted
         holder.mRecipeItemViewMvc.bindItem(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
    // Listens to RecipeListItemView
    @Override
    public void onRecipeClicked(RecipeCategory recipeCategory) {
         // calls any class thats implemented this classes interface
         mListener.onRecipeClicked(recipeCategory);
    }

    public void bindRecipes(List<RecipeCategory> recipeCategories){
         mRecipes = (ArrayList<RecipeCategory>) recipeCategories;
         notifyDataSetChanged();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder{
           private final RecipeItemViewMvc mRecipeItemViewMvc;

        public RecipeViewHolder(RecipeItemViewMvc recipeItemViewMvc) {
            // the root view was instantiated in the viewClass
            super(recipeItemViewMvc.getRootView());
            mRecipeItemViewMvc = recipeItemViewMvc;
        }
    }
}
