package com.saleef.mvcrecipeapp.Views.HomeScreen;


import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.Enums.DrawerItems;
import com.saleef.mvcrecipeapp.Views.DrawerToggleController;
import com.saleef.mvcrecipeapp.R;
import com.saleef.mvcrecipeapp.Views.ActionBarController;

import java.util.Set;

public class NavDrawerViewImpl extends BaseObservable<NavDrawerMvc.Listener> implements NavDrawerMvc {

    private final DrawerLayout mDrawerLayout;
    private final FrameLayout mFrameLayout;
    private final NavigationView mNavigationView;
    private final ActionBarController mActionBarController;
    private final DrawerToggleController mDrawerToggleController;
    private Toolbar mToolbar;

        public NavDrawerViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ActionBarController actionBarController,
                                 DrawerToggleController drawerToggleController){
            setRootView(layoutInflater.inflate(R.layout.activity_main,viewGroup,false));
            mDrawerLayout = findViewById(R.id.drawerLayout);
            mFrameLayout = findViewById(R.id.frameLayoutContainer);
            mToolbar = findViewById(R.id.home_toolBar);
            mDrawerToggleController = drawerToggleController;
            mActionBarController = actionBarController;
            mNavigationView = findViewById(R.id.nav_view);
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.favorite:
                           drawerItemClicked(DrawerItems.FAVORITE);
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });

            initToolBar();
            initDrawer();
        }


        private void initToolBar(){
            mActionBarController.initToolbar(mToolbar);
        }


        private void initDrawer(){
            mDrawerToggleController.initToggle(mDrawerLayout,mToolbar);
        }

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mFrameLayout;
    }



    @Override
    public boolean isDrawerOpen() {
            return  mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
          mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void openDrawer() {

    }


    private void drawerItemClicked(DrawerItems drawerItems){
            switch (drawerItems){
                case FAVORITE:
                    for (Listener listener:getListeners()){
                        listener.onFavoriteItemClicked();
                    }
                    break;
                default:
                    break;
            }
    }
}
