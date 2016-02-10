package com.tae.dagger2withmvp.interactor;

import android.util.Log;

import com.tae.dagger2withmvp.model.itunesmodel.Result;
import com.tae.dagger2withmvp.model.realm.ArtistRealm;
import com.tae.dagger2withmvp.model.realm.ArtistRealmDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 09/02/2016.
 */
public class RealmInteractor {

    private static final String TAG = RealmInteractor.class.getSimpleName();
    private ArtistRealmDAO realmDAO;

    public RealmInteractor(ArtistRealmDAO realmDAO) {
        Log.i(TAG, "RealmInteractor: ");
        this.realmDAO = realmDAO;
    }

    private List<ArtistRealm> convertResultToArtistRealm(List<Result> results) {
        List<ArtistRealm> artistRealms = new ArrayList<>(results.size());
        for (Result result : results) {
            ArtistRealm realm = new ArtistRealm();
            realm.setArtist(result.getArtistName());
            realm.setAlbum(result.getCollectionName());
            realm.setSong(result.getTrackName());
            realm.setArtworkUrl100(result.getArtworkUrl100());
            realm.setTrackId(result.getTrackId());
            artistRealms.add(realm);
        }
        return artistRealms;
    }

    public void saveAllArtist(List<Result> artists) {
        Log.i(TAG, "saveAllArtist: ");
        realmDAO.saveAllArtists(convertResultToArtistRealm(artists));

    }

    public void queryAllArtist() { // TODO REFACTOR
        Log.i(TAG, "queryAllArtist: ");
         realmDAO.queryAll();
    }

}
