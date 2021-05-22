package com.saleef.mvcrecipeapp;

import android.app.Application;

import com.saleef.mvcrecipeapp.Common.DependencyInjection.CompositionRoot;

public class CustomApplication extends Application {
       // Maintains a global instance of the composition root and intializes it before the application begins.
       // By following this pattern of pre-initializing dependencies it saves time
    private CompositionRoot mCompositionRoot;
    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }


    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
