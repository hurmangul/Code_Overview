package com.tae.dagger2withmvp.dagger2.component;

import com.tae.dagger2withmvp.dagger2.module.ApplicationModule;
import com.tae.dagger2withmvp.dagger2.module.RealmInteractorModule;
import com.tae.dagger2withmvp.dagger2.module.RealmModule;
import com.tae.dagger2withmvp.interactor.RealmInteractor;

import dagger.Component;

/**
 * Created by Bruno on 09/02/2016.
 */

@Component(
        modules = {
                RealmModule.class,
                RealmInteractorModule.class,
                ApplicationModule.class
        }
)
public interface RealmComponent extends AppComponent{

    RealmInteractor getRealmInteractor();

}
