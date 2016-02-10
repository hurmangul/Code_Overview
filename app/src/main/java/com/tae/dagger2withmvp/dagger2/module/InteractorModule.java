package com.tae.dagger2withmvp.dagger2.module;

import android.util.Log;

import com.tae.dagger2withmvp.interactor.ArtistInteractor;
import com.tae.dagger2withmvp.model.api.retrofit.ItunesApiService;
import com.tae.dagger2withmvp.model.realm.ArtistRealmDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduardo on 07/02/2016.
 * This module returns the interactor to do the job for the Presenter
 *
 * At the moment no constructor with parameters needed
 */
@Module
public class InteractorModule {

    private static final String TAG = InteractorModule.class.getSimpleName();

    /**
     * Provides the Interactor
     * @param apiService Retrofit interface to connect with Api
     * @return
     */
    @Provides
    public ArtistInteractor provideInteractor(ItunesApiService apiService) {
        Log.i(TAG, "provideInteractor: ");
        return new ArtistInteractor(apiService);
    }

}
