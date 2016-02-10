package com.tae.dagger2withmvp.model.realm;

import android.content.Context;
import android.util.Log;

import com.tae.dagger2withmvp.model.itunesmodel.Result;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Eduardo on 09/02/2016.
 */
public class ArtistRealmDAO {

    private static final String TAG = ArtistRealmDAO.class.getSimpleName();
    private Realm realm;

    public ArtistRealmDAO(Realm realm) {
        Log.i(TAG, "ArtistRealmDAO: ");
        this.realm = realm;
    }

    public void saveAllArtists(final List<ArtistRealm> artists) {
        Log.i(TAG, "saveAllArtists: ");
//        beginTransaction();
//        realm.copyToRealmOrUpdate(artists);
//        commitTransaction();
        /**
         * This is another way to do the same as the lines above.
         * The diff is the transaction will handle for you begin and commit transaction
         * It will also run as an asynchronous task
         */
        RealmAsyncTask transaction = realm.executeTransaction(new Realm.Transaction() { // TODO can cancel() in onStop the  transaction
            @Override
            public void execute(Realm realm) {
                Log.i(TAG, "execute: ");
                realm.copyToRealmOrUpdate(artists);
            }
        }, new Realm.Transaction.Callback(){
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: transaction success");
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: transaction failed", e);
                // transaction is automatically rolled-back, do any cleanup here
            }
        });
    }

    public void queryAll() {
        RealmResults<ArtistRealm> query = realm.allObjects(ArtistRealm.class);
        Log.i(TAG, "queryAll: artist in data base: " + query.size());
        for (ArtistRealm realm : query) {
            Log.i(TAG, "queryAll: artist: "  + realm.getArtist());
        }
    }

    private void beginTransaction() {
        realm.beginTransaction();
    }

    private void commitTransaction() {
        realm.commitTransaction();
    }
}
