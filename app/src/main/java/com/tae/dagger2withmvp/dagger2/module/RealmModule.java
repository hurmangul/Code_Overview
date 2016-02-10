package com.tae.dagger2withmvp.dagger2.module;

import android.content.Context;
import android.util.Log;

import com.tae.dagger2withmvp.model.realm.ArtistRealmDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Eduardo on 09/02/2016.
 */

@Module
public class RealmModule {

    private static final String TAG = RealmModule.class.getSimpleName();

    @Provides
    public Realm provideRealm(Context context) {
        Log.i(TAG, "provideRealm: ");
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(config);
        return Realm.getDefaultInstance();
    }

    @Provides
    public ArtistRealmDAO provideArtistRealmDAO(Realm realm) {
        Log.i(TAG, "provideArtistRealmDAO: ");
        return new ArtistRealmDAO(realm);
    }
}
