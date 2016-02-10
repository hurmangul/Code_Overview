package com.tae.dagger2withmvp.dagger2.component;

import com.tae.dagger2withmvp.dagger2.module.ApplicationModule;
import com.tae.dagger2withmvp.dagger2.module.RealmInteractorModule;
import com.tae.dagger2withmvp.dagger2.module.RealmModule;
import com.tae.dagger2withmvp.dagger2.scope.ActivityScope;
import com.tae.dagger2withmvp.interactor.RealmInteractor;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by Eduardo on 09/02/2016.
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
