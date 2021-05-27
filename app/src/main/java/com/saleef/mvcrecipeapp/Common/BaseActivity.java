package com.saleef.mvcrecipeapp.Common;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.saleef.mvcrecipeapp.Common.DependencyInjection.ActivityCompositionRoot;
import com.saleef.mvcrecipeapp.Common.DependencyInjection.ControllerCompositionRoot;
import com.saleef.mvcrecipeapp.CustomApplication;
import com.saleef.mvcrecipeapp.R;

public class BaseActivity extends AppCompatActivity {
    private ControllerCompositionRoot mControllerCompositionRoot;
    private ActivityCompositionRoot mActivityCompositionRoot;
   //
    protected ControllerCompositionRoot getControllerCompositionRoot() {
        if (mControllerCompositionRoot==null){
            mControllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }

        return mControllerCompositionRoot;
    }

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if (mActivityCompositionRoot == null) {
            mActivityCompositionRoot = new ActivityCompositionRoot(
                    ((CustomApplication) getApplication()).getCompositionRoot(),
                    this,
                    this);

        }
        return mActivityCompositionRoot;
    }


}
