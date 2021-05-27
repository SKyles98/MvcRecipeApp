package com.saleef.mvcrecipeapp.Common;

import android.os.Bundle;
import android.widget.FrameLayout;


import com.saleef.mvcrecipeapp.Common.FragmentHelper.FragmentFrameWrapper;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Views.HomeScreen.NavDrawerMvc;
import com.saleef.mvcrecipeapp.Networking.SharedPrefs;

import java.util.List;

/**
 * This a an  recipe app to showcase knowledge learned so far in Mvc Architecture pattern
 * Things to note is all Ui logic will be moved to a view classes and the mainactivities/fragments will act as controllers
 * Features of this app will include pre-loaded recipes to click on with a preview of the image and the name;
 * User will be able to click on the recipe to open fragment showing instructions and ingredients.
 * Libraries ill use is Retrofit api loading and caching and glide for image loading
 *
 * So what we know so far about the Mvc design is the extraction of view logic into separate view classes.
 * These View classes implement their own interfaces with functionality.
 * As a way to allow loose coupling our controller classes implement those interfaces and act as subscribers to those views
 * This allows connectivity between the controller and views without needing to know what that class does.
 * The Model in this pattern acts as format for oup app holding any apis,databases,or schemas.
 */
 //TODO Add a view class that allows for the creation of custom recipes
    //TODO Add functionality for the sharing of recipes



public class MainActivity extends BaseActivity implements FragmentFrameWrapper, NavDrawerMvc.Listener {
    private ScreenNavigator mScreenNavigator;
    private NavDrawerMvc mNavDrawerMvc;
    private SharedPrefs mSharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Benefits of using a composition root for some class implementations is the controller(MainActivity) doesnt have to know the implementation of these classe

       mScreenNavigator = getControllerCompositionRoot().getScreenNavigator();
       mNavDrawerMvc = getControllerCompositionRoot().getViewMvcFactory().getNavDrawerViewImpl(null);
        setContentView(mNavDrawerMvc.getRootView());
        if (savedInstanceState==null) {
            mScreenNavigator.toRecipeCategory();
        }
        mSharedPrefs = getControllerCompositionRoot().getSharedPrefs();

    }


    @Override
    protected void onStart() {
        super.onStart();
        mNavDrawerMvc.register(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
       mNavDrawerMvc.register(this);
    }


    @Override
    public void onBackPressed() {
        if (mNavDrawerMvc.isDrawerOpen()){
            mNavDrawerMvc.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mNavDrawerMvc.getFragmentFrame();
    }

    private List<String> getFavorites(){
       return mSharedPrefs.getFavoriteDishes();
    }

    @Override
    public void onFavoriteItemClicked() {
        mNavDrawerMvc.closeDrawer();
        mScreenNavigator.toFavoritedRecipes(getFavorites());
    }
}