package com.tae.dagger2withmvp.view.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tae.dagger2withmvp.MyApp;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;

/**
 * Created by Eduardo on 08/02/2016.
 */
public abstract class BaseService extends IntentService {

    private static final String TAG = BaseService.class.getSimpleName();

    public BaseService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    protected abstract BasePresenter getPresenter();

    protected abstract void setUpComponent(NetworkComponent component);

    private void injectDependencies() {
        setUpComponent(MyApp.getApp(getApplicationContext()).getNetworkComponent());
    }

}
