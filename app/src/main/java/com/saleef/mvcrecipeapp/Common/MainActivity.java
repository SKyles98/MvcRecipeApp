package com.saleef.mvcrecipeapp.Common;

import android.os.Bundle;
import android.widget.FrameLayout;


import com.saleef.mvcrecipeapp.Common.FragmentHelper.FragmentFrameWrapper;
import com.saleef.mvcrecipeapp.Common.ScreenNavigator.ScreenNavigator;
import com.saleef.mvcrecipeapp.Views.HomeScreen.HomeScreenViewMvc;

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
 //TODO When user clicks on title navigate to another screen displaying all foods in the category in the recycler view;
    //TODO Implement a screen Navigator class for processing fragment transactions based off user events(i.e, navigate to food fragment when user clicks)
    //TODO Implement Search feature where user can search for foods by name;
    //TODO Create a search by name Use Case
    //TODO Create a filter by FoodCategory Use Case


public class MainActivity extends BaseActivity implements FragmentFrameWrapper {
    private HomeScreenViewMvc homeView;
    private ScreenNavigator mScreenNavigator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Benefits of using a composition root for some class implementations is the controller(MainActivity) doesnt have to know the implementation of these classes
        homeView = getControllerCompositionRoot().getViewMvcFactory().getHomeScreenViewImpl(null);
       mScreenNavigator = getControllerCompositionRoot().getScreenNavigator();
        setContentView(homeView.getRootView());
        mScreenNavigator.toRecipeCategory();

    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onStop() {
        super.onStop();

    }








    @Override
    public FrameLayout getFragmentFrame() {
        return homeView.getFragmentFrame();
    }
}