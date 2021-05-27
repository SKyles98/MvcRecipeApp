package com.saleef.mvcrecipeapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseViewMvc;

// The purpose of the this controller is to use methods tied to activity and appcompat activity
public class ActionBarController extends BaseViewMvc {

      private final AppCompatActivity mAppCompatActivity;

       public ActionBarController(AppCompatActivity appCompatActivity){

           mAppCompatActivity = appCompatActivity;


       }

       public void initToolbar(androidx.appcompat.widget.Toolbar toolbar){
           mAppCompatActivity.setSupportActionBar(toolbar);
           if (mAppCompatActivity.getSupportActionBar()!=null){
                 mAppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           }
       }


}
