package com.saleef.mvcrecipeapp.Common.DependencyInjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.saleef.mvcrecipeapp.Views.ActionBarController;
import com.saleef.mvcrecipeapp.Common.FragmentHelper.FragmentFrameWrapper;
import com.saleef.mvcrecipeapp.Common.FragmentHelper.FragmentHandler;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Common.ViewFactory.ViewMvcFactory;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.UseCase.FetchRecipeDisplayUseCase;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.UseCases.FetchRecipeCateogoryItemsUseCase;
import com.saleef.mvcrecipeapp.Views.RecipeDetails.UseCases.FetchRecipeDetailsUseCase;


public class ControllerCompositionRoot {

     // needed just in case we need to use api like toolbar methods and nav drawer methods
   private ActivityCompositionRoot mActivityCompositionRoot;
    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot){
      mActivityCompositionRoot = activityCompositionRoot;

    }


    private ActivityCompositionRoot getActivityCompositionRoot() {
        return mActivityCompositionRoot;
    }

    private FragmentActivity getActivity() {
        return mActivityCompositionRoot.getActivity();
    }
    //always return our fragmentActivity context which is always fragment activity
    private Context getContext() {
        return getActivity();
    }
    // gets our fragmentManager for transactions
    private FragmentManager getFragmentManager(){
       return  getActivity().getSupportFragmentManager();
    }
    // one stop shop for a layout inflater
    private LayoutInflater getLayoutInflater(){
       return LayoutInflater.from(getActivityCompositionRoot().getActivity());
    }

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(getLayoutInflater(),getActionBarController());
    }

    public ActionBarController getActionBarController(){
        return new ActionBarController(getActivityCompositionRoot().getAppCompatActivity());
    }

    public FetchRecipeDisplayUseCase getRecipeDisplayUseCase(){
        return new FetchRecipeDisplayUseCase(getActivityCompositionRoot().getCompositionRoot().getMealDbApi());
    }

    public FetchRecipeCateogoryItemsUseCase getRecipeCategoryItemUseCase(){
        return new FetchRecipeCateogoryItemsUseCase(getActivityCompositionRoot().getCompositionRoot().getMealDbApi());
    }

    public FetchRecipeDetailsUseCase getRecipeDetailsUsecase(){
        return new FetchRecipeDetailsUseCase(getActivityCompositionRoot().getCompositionRoot().getMealDbApi());
    }

    // How to return interfaces in compositonRoot cast the interface with the activity
    private FragmentFrameWrapper getFragmentFrameWrapper(){
        return (FragmentFrameWrapper) getActivity();
    }

    public ScreenNavigator getScreenNavigator(){
        return new ScreenNavigator(getFragmentHandler());
    }

    public FragmentHandler getFragmentHandler(){
        return new FragmentHandler(getActivity(),getFragmentManager(),getFragmentFrameWrapper());
    }
}
