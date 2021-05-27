package com.saleef.mvcrecipeapp.Views.FavoriteItems.UseCases;



import com.saleef.mvcrecipeapp.Common.MvcSkeleton.BaseObservable;
import com.saleef.mvcrecipeapp.Networking.MealDbApi;
import com.saleef.mvcrecipeapp.Networking.RecipeDetailsWrapperSchema;
import com.saleef.mvcrecipeapp.Networking.RecipeListSchema;
import com.saleef.mvcrecipeapp.Recipe.RecipeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;


import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FetchFavoriteRecipesUseCase extends BaseObservable<FetchFavoriteRecipesUseCase.Listener> {

    @Override
    protected void notifySubscribers(Set<Listener> listeners) {

    }

    public interface Listener{
       void onRecipeFetchedSuccess(List<RecipeItem> recipeDetails);
        void onRecipeFetchedFailure(String errorMessage);
        void onObservableSubscription(Disposable compositeDisposable);
    }



    private final MealDbApi mMealDbApi;
    private  List<RecipeItem> mRecipeItems;
    private  List<RecipeListSchema> mRecipeListSchemas;
    public FetchFavoriteRecipesUseCase(MealDbApi mealDbApi){
        mMealDbApi = mealDbApi;

    }


    public void fetchFavoritedRecipesInfo(List<String> recipeItems) {
        // Store all different food request to the api we need to call in the list first
          List<Observable<RecipeDetailsWrapperSchema>> requests = new ArrayList<>(recipeItems.size());
          for (String items:recipeItems){
              requests.add(mMealDbApi.getObservableRecipeSchema(items));
          }
        // The zip method holds all our requests and throws it into an object array for conversion when all request are done
        Observable.zip(requests, new Function<Object[], List<RecipeDetailsWrapperSchema>>() {
            @Override
            public List<RecipeDetailsWrapperSchema> apply(Object[] objects) throws Throwable {
                // Objects is a array of all our requests to the api
               List<RecipeDetailsWrapperSchema> recipeDetailsWrapperSchemas = new ArrayList<>(objects.length);
               // Go through the objects which will be of type requests that we made cast it and assign to a new list
                for (Object object:objects){
                    RecipeDetailsWrapperSchema recipeDetailsWrapperSchema = (RecipeDetailsWrapperSchema) object;
                    recipeDetailsWrapperSchemas.add(recipeDetailsWrapperSchema);
                }
                return recipeDetailsWrapperSchemas;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<RecipeDetailsWrapperSchema>>() {
            @Override // This triggers if all requests were success
            public void accept(List<RecipeDetailsWrapperSchema> recipeDetailsWrapperSchemas) throws Throwable {
                // Do something on successful completion of the requests
                    onRetrievedSuccess(recipeDetailsWrapperSchemas);
            }
        });

    }





    private void onRetrievedSuccess(List<RecipeDetailsWrapperSchema> recipeListSchemas){
        mRecipeListSchemas = new ArrayList<>(recipeListSchemas.size());

        for (int i = 0;i<recipeListSchemas.size();i++){
            mRecipeListSchemas.add(recipeListSchemas.get(i).getRecipeListSchemas().get(0));
        }

        mRecipeItems = new ArrayList<>(recipeListSchemas.size());
        for (RecipeListSchema listSchema:mRecipeListSchemas){
                mRecipeItems.add(new RecipeItem(listSchema.getMealImage(),listSchema.getName(),listSchema.getId()));
        }

        for (Listener listener:getListeners()){
            listener.onRecipeFetchedSuccess(mRecipeItems);
        }
    }
    private void onRetrievedFailure(String errorMessage){
            for (Listener listener:getListeners()){
                listener.onRecipeFetchedFailure(errorMessage);
            }
    }

    private void onSubscription(Disposable d){
        for (Listener listener:getListeners()){
            listener.onObservableSubscription(d);
        }
    }


}
