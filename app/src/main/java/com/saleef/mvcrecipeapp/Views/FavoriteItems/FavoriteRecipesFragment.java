package com.saleef.mvcrecipeapp.Views.FavoriteItems;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saleef.mvcrecipeapp.Common.BaseFragment;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;
import com.saleef.mvcrecipeapp.Views.FavoriteItems.UseCases.FetchFavoriteRecipesUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public class FavoriteRecipesFragment extends BaseFragment implements FavoriteItemsViewMvc.Listener , FetchFavoriteRecipesUseCase.Listener {



   private static final String FAVORITES = "FAVORITE_RECIPES";
   private ScreenNavigator mScreenNavigator;
   private FetchFavoriteRecipesUseCase mFetchFavoriteRecipesUseCase;
   private CompositeDisposable mCompositeDisposable;
   private FavoriteItemsViewMvc mFavoriteItemsViewMvc;
    public FavoriteRecipesFragment() {
        // Required empty public constructor
    }



    public static FavoriteRecipesFragment newInstance(List<String> favorites) {
        FavoriteRecipesFragment fragment = new FavoriteRecipesFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(FAVORITES, (ArrayList<String>) favorites);
        fragment.setArguments(args);
        return fragment;
    }

    private List<String> getFavorites(){
        return getArguments().getStringArrayList(FAVORITES);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFetchFavoriteRecipesUseCase = getControllerCompositionRoot().getFavoriteRecipesUseCase();
            mScreenNavigator = getControllerCompositionRoot().getScreenNavigator();
            mCompositeDisposable = new CompositeDisposable();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFavoriteItemsViewMvc = getControllerCompositionRoot().getViewMvcFactory().getFavoriteItemsViewImpl(container);
        return mFavoriteItemsViewMvc.getRootView();
    }


    @Override
    public void onStart() {
        super.onStart();
        mFetchFavoriteRecipesUseCase.register(this);
        mFavoriteItemsViewMvc.register(this);
        fetchtheFavorites();
    }


    @Override
    public void onStop() {
        super.onStop();
        mFetchFavoriteRecipesUseCase.unregister(this);
        mFavoriteItemsViewMvc.unregister(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        mCompositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    private void fetchtheFavorites(){
        mFavoriteItemsViewMvc.showProgressIndication();
        mFetchFavoriteRecipesUseCase.fetchFavoritedRecipesInfo(getFavorites());
    }
    @Override
    public void onFavoritedRecipeItemClicked(RecipeItem recipeItem) {
         mScreenNavigator.toRecipeItemDetails(recipeItem);
    }

    @Override
    public void onRecipeFetchedSuccess(List<RecipeItem> recipeDetails) {
        mFavoriteItemsViewMvc.hideProgressIndication();
        mFavoriteItemsViewMvc.bindRecipeItems(recipeDetails);
    }

    @Override
    public void onRecipeFetchedFailure(String errorMessage) {
        mFavoriteItemsViewMvc.hideProgressIndication();
        Toast.makeText(getContext(), errorMessage.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObservableSubscription(Disposable compositeDisposable) {
              mCompositeDisposable.add(compositeDisposable);
    }
}