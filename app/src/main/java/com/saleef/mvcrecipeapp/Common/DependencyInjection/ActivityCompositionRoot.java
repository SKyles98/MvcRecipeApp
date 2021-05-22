package com.saleef.mvcrecipeapp.Common.DependencyInjection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.saleef.mvcrecipeapp.Networking.MealDbApi;

public class ActivityCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final AppCompatActivity mAppCompatActivity;
    private final FragmentActivity mActivity;

    public ActivityCompositionRoot(CompositionRoot compositionRoot, AppCompatActivity appCompatActivity, FragmentActivity activity) {
        mCompositionRoot = compositionRoot;
        mAppCompatActivity = appCompatActivity;
        mActivity = activity;
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }

    public FragmentActivity getActivity(){
        return mActivity;
    }

    public MealDbApi getMealDbApi(){
        return getCompositionRoot().getMealDbApi();
    }

    public AppCompatActivity getAppCompatActivity(){return mAppCompatActivity;}



}