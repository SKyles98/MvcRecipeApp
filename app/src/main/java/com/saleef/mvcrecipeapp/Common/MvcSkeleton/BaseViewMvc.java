package com.saleef.mvcrecipeapp.Common.MvcSkeleton;


import android.content.Context;
import android.view.View;

public abstract class BaseViewMvc implements ViewMvc {
    private View mRootView;
    @Override
    public View getRootView() {
        return mRootView;
    }

    protected Context getContext(){
       return  getRootView().getContext();
    }
    protected void setRootView(View rootView){
        mRootView = rootView;
    }

    protected <T extends View>T findViewById(int id){
        return getRootView().findViewById(id);
    }

}
