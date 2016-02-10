package com.tae.dagger2withmvp.presenter;

import android.util.Log;

import com.tae.dagger2withmvp.interactor.RealmInteractor;
import com.tae.dagger2withmvp.model.itunesmodel.Result;
import com.tae.dagger2withmvp.model.realm.ArtistRealm;

import java.util.List;

/**
 * Created by Eduardo on 09/02/2016.
 *
 */
public class RealmPresenter {

    private static final String TAG = RealmPresenter.class.getSimpleName();
    private RealmInteractor interactor;

    public RealmPresenter(RealmInteractor interactor) {
        Log.i(TAG, "RealmPresenter: COnstructor with realm interactor");
        this.interactor = interactor;
    }

    public void saveAllResults(List<Result> results) {
        Log.i(TAG, "saveAllResults: ");
        interactor.saveAllArtist(results);
    }

    public void queryAll() {
        interactor.queryAllArtist();
    }
}
