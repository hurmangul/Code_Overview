package com.tae.dagger2withmvp.model.api.callback;

/**
 * Created by Eduardo on 04/02/2016.
 * Callback used to comunicate Presenter and Interactor with the data needed in Presenter
 *
 */
public interface ApiCallback {

    void onNetworkError();
    void onApiError();
}
