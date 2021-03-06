package com.saleef.mvcrecipeapp.Views;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.saleef.mvcrecipeapp.R;

public class DrawerToggleController {


    private final FragmentActivity mFragmentActivity;
    private ActionBarDrawerToggle mToggle;
    public  DrawerToggleController(FragmentActivity fragmentActivity){
        mFragmentActivity = fragmentActivity;

    }

    public void initToggle(DrawerLayout drawerLayout, Toolbar toolbar){
        mToggle = new ActionBarDrawerToggle(mFragmentActivity,drawerLayout,
                toolbar, R.string.Navigation_Drawer_Open,R.string.Navigation_Drawer_Close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }

}

