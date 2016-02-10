package com.tae.dagger2withmvp.interactor;

import android.util.Log;

import com.tae.dagger2withmvp.model.api.callback.ArtistCallback;
import com.tae.dagger2withmvp.model.api.retrofit.ItunesApiService;
import com.tae.dagger2withmvp.model.itunesmodel.Itunes;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Eduardo on 04/02/2016.
 * The Interactor query the data to servers, data bases, etc and send the result/response.
 * This guy is hidden.
 */
public class ArtistInteractor {

    private static final String TAG = ArtistInteractor.class.getSimpleName();
    /**
     * Instance of the Retrofit interface that will hit the api endpoint
     */
    private ItunesApiService itunesApiService;

    /**
     * The Constructor receives the retrofit interface in order to create the object that will
     * connect to the API
     * @param itunesApiService
     */
    public ArtistInteractor(ItunesApiService itunesApiService) {
        Log.i(TAG, "ArtistInteractor: itunes api service");
        this.itunesApiService = itunesApiService;
    }

    /**
     * This method will make a request to the API to get the artist searching by gender
     * Instead of the classic Retrofit callback uses RxJava
     * @param query
     * @param callback
     */
    public void searchInItunes(String query, final ArtistCallback callback) {
        Log.i(TAG, "searchInItunes: ");
        /**
         * RxJava with Lambda expresion
         */
//        itunesApiService.search(query)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(itunes -> {callback.onArtistsFound(itunes.getResults());}
//                        ,throwable -> {callback.onFailedSearch();}
//                );
        /**
         * RxJava with Observable, etc
         */
        Observable<Itunes> observable = itunesApiService.search(query);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread()) //TODO also works with Schedulers.io... :/
                .subscribe(new Observer<Itunes>() { // TODO also works with Observer... :/
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(Itunes itunes) {
                        Log.i(TAG, "onNext: ");
                        callback.onArtistsFound(itunes.getResults());
                    }
                });

    }
}
