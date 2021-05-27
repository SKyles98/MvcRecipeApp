package com.saleef.mvcrecipeapp.Views.HomeScreen;

import android.widget.FrameLayout;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ObservableMvc;

public interface NavDrawerMvc extends ObservableMvc<NavDrawerMvc.Listener> {

    interface Listener{
      void  onFavoriteItemClicked();
    }

    FrameLayout getFragmentFrame();

    boolean isDrawerOpen();
    void closeDrawer();
    void openDrawer();
}
