package com.tae.dagger2withmvp.model.api.callback;

import com.tae.dagger2withmvp.model.itunesmodel.Itunes;
import com.tae.dagger2withmvp.model.itunesmodel.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 04/02/2016.
 *
 * Callback to communicate the results to ArtistSearchPresenter
 */
public interface ArtistCallback extends ApiCallback{

    void onArtistsFound(List<Result> artists); // TODO maybe we dont need all the atributes of the object, in that case --> customize loca object

    void onFailedSearch();

}
