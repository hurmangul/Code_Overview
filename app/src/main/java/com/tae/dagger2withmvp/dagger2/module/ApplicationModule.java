package com.tae.dagger2withmvp.dagger2.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduardo on 07/02/2016.
 */
@Module
public class ApplicationModule {

    private static final String TAG = ApplicationModule.class.getSimpleName();
    Application app;

    public ApplicationModule(Application app) {
        Log.i(TAG, "ApplicationModule: Constructor");
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        Log.i(TAG, "provideApplication: ");
        return app;
    }

    @Provides
    public Context provideContext() {
        Log.i(TAG, "provideContext: ");
        return app.getApplicationContext();
    }
}
