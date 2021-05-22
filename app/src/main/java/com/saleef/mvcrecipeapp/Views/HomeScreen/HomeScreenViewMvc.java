package com.saleef.mvcrecipeapp.Views.HomeScreen;

import android.widget.FrameLayout;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;

public interface HomeScreenViewMvc extends ObservableMvc<HomeScreenViewMvc.Listener> {

    interface Listener{
        void onRecipeClicked(RecipeCategory recipeCategory);
    }


    FrameLayout getFragmentFrame();
}

