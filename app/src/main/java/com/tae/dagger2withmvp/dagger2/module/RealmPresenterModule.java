package com.tae.dagger2withmvp.dagger2.module;

import android.util.Log;

import com.tae.dagger2withmvp.interactor.RealmInteractor;
import com.tae.dagger2withmvp.presenter.RealmPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduardo on 09/02/2016.
 */
@Singleton
@Module
public class RealmPresenterModule {

    public static final String TAG = RealmPresenterModule.class.getSimpleName();

    public RealmPresenterModule() {
    }

    @Provides
    public RealmPresenter providePresenter(RealmInteractor interactor) {
        Log.i(TAG, "providePresenter: ");
        return new RealmPresenter(interactor);
    }
}
