package com.saleef.mvcrecipeapp.Networking;

import android.content.Context;
import android.content.SharedPreferences;


import androidx.fragment.app.FragmentActivity;

import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SharedPrefs {


       private final SharedPreferences mSharedPreferences;
     public SharedPrefs(FragmentActivity fragmentActivity){

         mSharedPreferences = fragmentActivity.getSharedPreferences(fragmentActivity.getPackageName(),Context.MODE_PRIVATE);
     }



     public void addFavoriteDish(RecipeItem recipeItem){
         SharedPreferences.Editor editor  = mSharedPreferences.edit();
         editor.putString(recipeItem.getId(),recipeItem.getName());
         editor.apply();
     }


     public List<String> getFavoriteDishes(){
         ArrayList<String> favoritedRecipes = new ArrayList<>();

         Map<String,?> data = mSharedPreferences.getAll();

         for (Map.Entry<String, ?> entries:data.entrySet()){
             favoritedRecipes.add((String) entries.getValue());
         }
         return favoritedRecipes;
     }


     public void removeFavoritedDish(RecipeItem recipeItem){
         SharedPreferences.Editor editor = mSharedPreferences.edit();
         editor.remove(recipeItem.getId());
         editor.apply();
     }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}
