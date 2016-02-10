package com.tae.dagger2withmvp.view.viewmodel;

import com.tae.dagger2withmvp.model.itunesmodel.Itunes;
import com.tae.dagger2withmvp.model.itunesmodel.Result;
import com.tae.dagger2withmvp.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 04/02/2016.
 * This is the view model, it defines the behaview of the View in the MVP
 * This interface operations must be used to update the UI in any activity
 * like {@link MainActivity}
 */
public interface ArtistViewModel {

    void setupList();

    void setupAdapter();

    void displayFoundArtists(List<Result> artists); // TODO create a model for the bands to display in the RV adapter

    void displayFailedSearch();

    void displayNetworkError();

    void displayApiError();

}
