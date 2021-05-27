package com.saleef.mvcrecipeapp.Common.DependencyInjection;

import com.saleef.mvcrecipeapp.Constants.Constant;
import com.saleef.mvcrecipeapp.Networking.MealDbApi;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/* In handmade dependency injection we are using this class to inject dependencies/apis into our
   application before it begins to offload latency

 */
public class CompositionRoot {

    private  Retrofit mRetrofit;


    private Retrofit getRetrofit(){
        if (mRetrofit==null){
            mRetrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).
                    addConverterFactory(GsonConverterFactory.create()).build();

        }
        return mRetrofit;
    }

    public MealDbApi getMealDbApi(){
        return getRetrofit().create(MealDbApi.class);
    }


}
