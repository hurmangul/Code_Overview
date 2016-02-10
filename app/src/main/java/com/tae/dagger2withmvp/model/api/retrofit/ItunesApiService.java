package com.tae.dagger2withmvp.model.api.retrofit;

import com.tae.dagger2withmvp.model.itunesmodel.Itunes;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Eduardo on 04/02/2016.
 * Connect to the RestApi
 */
public interface ItunesApiService {

    @GET(NetworkConstants.GENDER_ENDPOINT)
    Observable<Itunes> search(@Query(NetworkConstants.QUERY_TERM) String query);
//    Call<Itunes> search(@Query(NetworkConstants.QUERY_TERM) String query);
}
