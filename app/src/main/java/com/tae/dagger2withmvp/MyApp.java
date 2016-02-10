package com.tae.dagger2withmvp;

import android.app.Application;
import android.content.Context;


import com.tae.dagger2withmvp.dagger2.component.DaggerNetworkComponent;
import com.tae.dagger2withmvp.dagger2.component.DaggerRealmComponent;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;
import com.tae.dagger2withmvp.dagger2.component.RealmComponent;
import com.tae.dagger2withmvp.dagger2.module.ApplicationModule;
import com.tae.dagger2withmvp.dagger2.module.InteractorModule;
import com.tae.dagger2withmvp.dagger2.module.NetworkModule;
import com.tae.dagger2withmvp.dagger2.module.RealmInteractorModule;
import com.tae.dagger2withmvp.dagger2.module.RealmModule;
import com.tae.dagger2withmvp.presenter.RealmPresenter;

/**
 * Created by Eduardo on 04/02/2016.
 */
public class MyApp extends Application {

    private NetworkComponent networkComponent;
    private RealmComponent realmComponent;

    public static MyApp getApp(Context context) {
        return ((MyApp) context.getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setUpNetworkComponent();
        setUpRealmComponent();

    }

    private void setUpRealmComponent() {
        realmComponent = DaggerRealmComponent.builder()
                .applicationModule(new ApplicationModule(this))
//                .realmInteractorModule(new RealmInteractorModule())
                .realmModule(new RealmModule())
                .build();
    }

    private void setUpNetworkComponent() {
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
//                .interactorModule(new InteractorModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public RealmComponent getRealmComponent() {
        return realmComponent;
    }
}