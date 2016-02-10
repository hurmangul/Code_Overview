package com.tae.dagger2withmvp.view.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Eduardo on 04/02/2016.
 */

public abstract class BasePresenter {
    /**
     * This method will be executed on
     * {@link AppCompatActivity#onStart()} in case presenter is attached to activity <br>
     * {@link Fragment#onStart()}  in case presenter is attached to fragment
     * */
    public abstract void onStart();

    /**
     * This method will be executed on
     * {@link AppCompatActivity#onStop()} in case presenter is attached to activity <br>
     * {@link Fragment#onStop()}  in case presenter is attached to fragment
     * */
    public abstract void onStop();

}
