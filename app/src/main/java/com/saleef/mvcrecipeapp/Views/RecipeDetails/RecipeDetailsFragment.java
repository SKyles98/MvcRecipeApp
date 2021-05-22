package com.saleef.mvcrecipeapp.Views.RecipeDetails;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saleef.mvcrecipeapp.Common.BaseFragment;
import com.saleef.mvcrecipeapp.Recipe.RecipeDetailItem;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Views.RecipeDetails.UseCases.FetchRecipeDetailsUseCase;

import java.util.List;


public class RecipeDetailsFragment extends BaseFragment implements FetchRecipeDetailsUseCase.Listener {



   private static final String RECIPE_NAME = "RECIPE NAME";
   private ScreenNavigator mScreenNavigator;
   private FetchRecipeDetailsUseCase mFetchRecipeDetailsUseCase;
   private RecipeDetailsViewMvc mRecipeDetailsViewMvc;
    public RecipeDetailsFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static RecipeDetailsFragment newInstance(String recipeName) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(RECIPE_NAME,recipeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mScreenNavigator = getControllerCompositionRoot().getScreenNavigator();
            mFetchRecipeDetailsUseCase = getControllerCompositionRoot().getRecipeDetailsUsecase();
        }

    }

    private String getRecipeName(){
       return  getArguments().getString(RECIPE_NAME);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRecipeDetailsViewMvc = getControllerCompositionRoot().getViewMvcFactory().getRecipeDetailViewImpl(container);
        return mRecipeDetailsViewMvc.getRootView();
    }


    @Override
    public void onStart() {
        super.onStart();
        mFetchRecipeDetailsUseCase.register(this);
       fetchRecipeDetails();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchRecipeDetailsUseCase.unregister(this);
    }


    private void fetchRecipeDetails(){
        mRecipeDetailsViewMvc.showLoading();
        mFetchRecipeDetailsUseCase.fetchRecipeDetailInfo(getRecipeName());
    }

    @Override
    public void onRecipeDetailRetrievalSuccess(List<RecipeDetailItem> recipeDetailItems) {
        //Call ViewClass to Bind the Recipe Item to the view
        mRecipeDetailsViewMvc.hideLoading();
        mRecipeDetailsViewMvc.bindRecipeDetails(recipeDetailItems.get(0));
    }

    @Override
    public void onRecipeDetailRetrievalFailure(String errorMessage) {
        mRecipeDetailsViewMvc.hideLoading();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}