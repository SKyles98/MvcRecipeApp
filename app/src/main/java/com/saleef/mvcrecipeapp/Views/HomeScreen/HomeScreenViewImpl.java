package com.saleef.mvcrecipeapp.Views.HomeScreen;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import com.saleef.mvcrecipeapp.Views.ActionBarController;
import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.R;

import java.util.Set;

// HomeScreen Activity Main
public class HomeScreenViewImpl extends BaseObservable<HomeScreenViewMvc.Listener> implements HomeScreenViewMvc {

    private  Toolbar mToolbar;
    private final FrameLayout mFrameLayout;
    private final ActionBarController mActionBarController;
    private final ProgressBar mProgressBar;


       public HomeScreenViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup,ActionBarController actionBarController){
           setRootView(layoutInflater.inflate(R.layout.activity_main,viewGroup,false));
           mFrameLayout = findViewById(R.id.frameLayoutContainer);
           mActionBarController = actionBarController;
           mProgressBar = findViewById(R.id.homeProgress);
           initToolbar();

       }

    private void initToolbar() {
        mToolbar = findViewById(R.id.home_toolBar);
        mActionBarController.initToolbar(mToolbar);
    }

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }





    @Override
    public FrameLayout getFragmentFrame() {
        return mFrameLayout;
    }
}
