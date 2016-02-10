package com.tae.dagger2withmvp.dagger2.module;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.tae.dagger2withmvp.model.api.retrofit.ItunesApiAdapter;
import com.tae.dagger2withmvp.model.api.retrofit.ItunesApiService;
import com.tae.dagger2withmvp.model.api.retrofit.NetworkConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Eduardo on 05/02/2016.
 *
 * This module will provide the instances needed to create the dependencies for the NetworkComponent
 */
@Module
public class NetworkModule {

    private static final String TAG = NetworkModule.class.getSimpleName();

    public NetworkModule() {
        Log.i(TAG, "NetworkModule: Constructor");
    }

    /**
     * Instance of the Retrofit's interface that will connect with the API
     * @param retrofit
     * @return
     */
    @Provides
    public ItunesApiService providesItunesApiService(Retrofit retrofit) {
        Log.i(TAG, "providesItunesApiService: ");
        return retrofit.create(ItunesApiService.class);
    }

    /**
     * In order to create the Retrofit object we need:
     * -Gson
     * -OkHttpCLient
     * -RxCallAdapter
     * @param client
     * @param gson
     * @return
     */
    @Provides
    public Retrofit providesRetrofit(OkHttpClient client, Gson gson) {
        Log.i(TAG, "providesRetrotit: ");
        return new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
//        return ItunesApiAdapter.getInstance();
    }

    /**
     * Use GsonBuilder to create the Gson Object
     * This is a simple one
     * @return
     */
    @Provides
    public Gson providesGson() {
        Log.i(TAG, "providesGson: ");
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

    /**
     * Simple OkHttpClient with logging interceptor
     * @param interceptor
     * @return
     */
    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor interceptor) {
        Log.i(TAG, "providesOkHttpClient: ");
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);
        return client;
    }

    /**
     * Interceptor object to log the body of the JSON
     * We can log the Headers, etc
     * @return
     */
    @Provides
    public HttpLoggingInterceptor providesInterceptor() {
        Log.i(TAG, "providesInterceptor: ");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
