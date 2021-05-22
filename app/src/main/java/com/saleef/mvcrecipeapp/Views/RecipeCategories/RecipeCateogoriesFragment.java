package com.saleef.mvcrecipeapp.Views.RecipeCategories;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saleef.mvcrecipeapp.Common.BaseFragment;
import com.saleef.mvcrecipeapp.Recipe.RecipeCategory;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Views.RecipeCategories.UseCase.FetchRecipeDisplayUseCase;

import java.util.List;


public class RecipeCateogoriesFragment extends BaseFragment implements HomeView.Listener, FetchRecipeDisplayUseCase.Listener {



   private FetchRecipeDisplayUseCase mFetchRecipeDisplayUseCase;
   private HomeView mHomeView;
   private ScreenNavigator mScreenNavigator;




    public RecipeCateogoriesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RecipeCateogoriesFragment newInstance() {
        RecipeCateogoriesFragment fragment = new RecipeCateogoriesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mFetchRecipeDisplayUseCase = getControllerCompositionRoot().getRecipeDisplayUseCase();
        mScreenNavigator = getControllerCompositionRoot().getScreenNavigator();


    }

    @Override
    public void onStart() {
        super.onStart();
        mHomeView.register(this);
        mFetchRecipeDisplayUseCase.register(this);
        fetchRecipeCategoriesandNotify();
    }

    private void fetchRecipeCategoriesandNotify() {
        mHomeView.showProgressIndication();
        mFetchRecipeDisplayUseCase.fetchRecipeInfo();
    }

    @Override
    public void onStop() {
        super.onStop();
        mHomeView.unregister(this);
        mFetchRecipeDisplayUseCase.unregister(this);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mHomeView = getControllerCompositionRoot().getViewMvcFactory().getHomeViewImpl(container);

        return mHomeView.getRootView();
    }

    @Override
    public void onRecipeClicked(RecipeCategory recipeCategory) {
        mScreenNavigator.toRecipeDetails(recipeCategory);
    }

    @Override
    public void onRecipeDisplayFetchedSuccess(List<RecipeCategory> recipeCategories) {
              mHomeView.hideProgressIndication();
               mHomeView.bindRecipes(recipeCategories);
    }

    @Override
    public void onRecipeDisplayFetchedFailure(Throwable t) {
        mHomeView.hideProgressIndication();
        Log.i("Error",t.getMessage());
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }


}