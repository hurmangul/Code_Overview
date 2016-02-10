package com.tae.dagger2withmvp.dagger2.module;

import android.util.Log;

import com.tae.dagger2withmvp.interactor.RealmInteractor;
import com.tae.dagger2withmvp.model.realm.ArtistRealmDAO;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduardo on 09/02/2016.
 */
@Module
public class RealmInteractorModule {

    private static final String TAG = RealmInteractorModule.class.getSimpleName();

    @Provides
    public RealmInteractor provideRealmInteractor(ArtistRealmDAO realmDAO) {
        Log.i(TAG, "provideRealmInteractor: ");
        return new RealmInteractor(realmDAO);
    }
}
