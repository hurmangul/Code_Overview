package com.tae.dagger2withmvp.dagger2.module;

import android.util.Log;

import com.tae.dagger2withmvp.interactor.ArtistInteractor;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduardo on 08/02/2016.
 */
@Singleton
@Module
public class PresenterServiceModule {

    private static final String TAG = PresenterServiceModule.class.getSimpleName();


    @Provides
    ArtistPresenter providePresenter(ArtistInteractor interactor) {
        Log.i(TAG, "providePresenter: with Interactor");
        return new ArtistPresenter(interactor);
    }
}
