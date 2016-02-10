package com.tae.dagger2withmvp.model.api.retrofit;

import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Eduardo on 04/02/2016.
 * This class create a singleton with an instance of the Retrofit object
 *
 * TODO is this need it?
 * NOT IN USE UNTIL KNOW THE ANSWER
 */
public class ItunesApiAdapter {

    private static Retrofit instance;

    private ItunesApiAdapter () {}

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(NetworkConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return instance;
    }
}
