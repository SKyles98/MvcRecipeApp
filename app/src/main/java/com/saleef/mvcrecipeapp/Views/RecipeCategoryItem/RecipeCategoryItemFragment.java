package com.saleef.mvcrecipeapp.Views.RecipeCategoryItem;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saleef.mvcrecipeapp.Common.BaseFragment;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Views.RecipeCategoryItem.UseCases.FetchRecipeCateogoryItemsUseCase;

import java.util.ArrayList;


public class RecipeCategoryItemFragment extends BaseFragment implements FetchRecipeCateogoryItemsUseCase.Listener, RecipeCategoryItemViewMvc.Listener {


   public static final String CATEGORY_ARG = "CATEGORY_NAME";
  private ScreenNavigator mScreenNavigator;
  private FetchRecipeCateogoryItemsUseCase mFetchRecipeCateogoryItemsUseCase;
  private RecipeCategoryItemsImpl mRecipeCategoryItems;
    public RecipeCategoryItemFragment() {
        // Required empty public constructor
    }



    public static RecipeCategoryItemFragment newInstance(String categoryName) {
        RecipeCategoryItemFragment fragment = new RecipeCategoryItemFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_ARG, categoryName);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mScreenNavigator = getControllerCompositionRoot().getScreenNavigator();
        mFetchRecipeCateogoryItemsUseCase = getControllerCompositionRoot().getRecipeCategoryItemUseCase();

    }

    @Override
    public void onStart() {
        super.onStart();
        mFetchRecipeCateogoryItemsUseCase.register(this);
        mRecipeCategoryItems.register(this);
        fetchRecipeCategoryItemInfo();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchRecipeCateogoryItemsUseCase.unregister(this);
        mRecipeCategoryItems.unregister(this);
    }

    private String getCategoryArg(){
       return getArguments().getString(CATEGORY_ARG);
    }

    private void fetchRecipeCategoryItemInfo(){
        mRecipeCategoryItems.showProgressIndication();
        mFetchRecipeCateogoryItemsUseCase.fetchRecipeCategoryItemInfo(getCategoryArg());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRecipeCategoryItems = getControllerCompositionRoot().getViewMvcFactory().getRecipeCategoryItemsImpl(container);
        return mRecipeCategoryItems.getRootView();
    }




    @Override
    public void onRecipeItemRetrievedSuccess(ArrayList<RecipeItem> recipeItems) {
                mRecipeCategoryItems.hideProgressIndication();
                mRecipeCategoryItems.bindRecipeItem(recipeItems);
    }

    @Override
    public void onRecipeItemRetrievedFailure(String errorMessage) {
               mRecipeCategoryItems.hideProgressIndication();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecipeItemClicked(RecipeItem recipeItem) {
        //TODO Navigate to detail screen showing the recipeItem full detail (insturctions ingredient)
        mScreenNavigator.toRecipeItemDetails(recipeItem);
    }
}