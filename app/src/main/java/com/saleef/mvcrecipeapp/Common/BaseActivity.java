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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_home_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.recipe_Search);
        SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search for a recipe...");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.recipe_Search:
                Toast.makeText(this,"Y u no collapse",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
