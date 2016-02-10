package com.tae.dagger2withmvp.model.api.retrofit;

/**
 * Created by Eduardo on 04/02/2016.
 */
public class NetworkConstants {
    public static final String BASE_URL = "https://itunes.apple.com";                                   //change limit here
//    public static final String GENDER_ENDPOINT = "/search?term=classick&amp;media=music&amp;entity=song&amp;limit=10"; // TODO create the endpoint with "term" as a query
    public static final String GENDER_ENDPOINT = "/search?amp;media=music&amp;entity=song&amp;limit=10";
    public static final String QUERY_TERM = "term";
}
