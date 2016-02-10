package com.tae.dagger2withmvp.presenter;

import android.util.Log;

import com.tae.dagger2withmvp.interactor.ArtistInteractor;
import com.tae.dagger2withmvp.interactor.RealmInteractor;
import com.tae.dagger2withmvp.model.api.callback.ArtistCallback;
import com.tae.dagger2withmvp.model.itunesmodel.Result;
import com.tae.dagger2withmvp.view.base.BasePresenter;
import com.tae.dagger2withmvp.view.viewmodel.ArtistViewModel;

import java.util.List;

/**
 * Created by Eduardo on 04/02/2016.
 *
 * The ArtistPresenter receive an input from the view, then will use an Interactor to get the data
 * and finally will update the UI
 * The Presenter needs an instance of the ViewModel and the Interactor to be aware of the user inputs
 * and to be in communication with the Interactor to handle the data.
 */
public class ArtistPresenter extends BasePresenter implements ArtistCallback {

    private static final String TAG = ArtistPresenter.class.getSimpleName();
    private ArtistInteractor artistInteractor;
    private ArtistViewModel artistViewModel;



    public ArtistPresenter(ArtistInteractor artistInteractor) {
        this.artistInteractor = artistInteractor;
    }

    public ArtistPresenter(ArtistInteractor artistInteractor, ArtistViewModel artistViewModel) {
        Log.i(TAG, "ArtistPresenter: constructor with interactor and viewmodel");
        this.artistInteractor = artistInteractor;
        this.artistViewModel = artistViewModel;
    }

    /**
     * This method will make a request to the api to search a list of artist by gender depending on
     * the user input.
     * For that, will tell the Interactor to look for that artist using the Retrofit interface.
     * The response will come via Observer Pattern using RxJava instead of a callback
     * This method will be called in the view (Activity, fragment, service, etc)
     * @param query is the user input
     */
    public void search(String query) {
        Log.i(TAG, "search: " + query);
        artistInteractor.searchInItunes(query, this);
    }

    /**
     * ArtistCallback method
     * The view model will update the view with the artist founded in the api
     * @param artists
     */
    @Override
    public void onArtistsFound(List<Result> artists) {
        if (artistViewModelIsNotNull()) {
            artistViewModel.displayFoundArtists(artists);
        }
    }

    /**
     * ArtistCallback method
     */
    @Override
    public void onFailedSearch() {
        if (artistViewModelIsNotNull()) {
            artistViewModel.displayFailedSearch();
        }
    }

    /**
     * BasePresenter method
     */
    @Override
    public void onStart() {
        if (artistViewModelIsNotNull()) {
            artistViewModel.setupList();
            artistViewModel.setupAdapter();
        }
    }

    private boolean artistViewModelIsNotNull() {
        return artistViewModel != null;
    }

    /**
     * BasePresenter method
     */
    @Override
    public void onStop() {

    }

    /**
     * ApiCallback method
     */
    @Override
    public void onNetworkError() {
        if (artistViewModelIsNotNull()) {
            artistViewModel.displayNetworkError();
        }
    }

    /**
     * ApiCallback method
     */
    @Override
    public void onApiError() {
        if (artistViewModelIsNotNull()) {
            artistViewModel.displayApiError();
        }
    }
}
