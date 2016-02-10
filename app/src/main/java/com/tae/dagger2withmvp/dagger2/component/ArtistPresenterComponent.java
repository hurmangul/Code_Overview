package com.tae.dagger2withmvp.dagger2.component;

import com.tae.dagger2withmvp.dagger2.module.ApplicationModule;
import com.tae.dagger2withmvp.dagger2.module.ArtistPresenterModule;
import com.tae.dagger2withmvp.dagger2.module.RealmPresenterModule;
import com.tae.dagger2withmvp.dagger2.scope.ActivityScope;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;
import com.tae.dagger2withmvp.presenter.RealmPresenter;
import com.tae.dagger2withmvp.view.activity.MainActivity;
import com.tae.dagger2withmvp.view.adapter.ArtistAdapter;
import com.tae.dagger2withmvp.view.fragment.ArtistListFragment;

import dagger.Component;

/**
 * Created by Eduardo on 07/02/2016.
 * Component that will give access to the Presenter
 * We define the scope of this component for the Activity
 * Here we define where are we going to inject the Presenter
 */

@ActivityScope
@Component(
        dependencies = {
                NetworkComponent.class,
                RealmComponent.class
        },
        modules = {
                ArtistPresenterModule.class,
                ApplicationModule.class,
                RealmPresenterModule.class //
        }
)
public interface ArtistPresenterComponent extends NetworkComponent, RealmComponent  {

        void inject(MainActivity activity);
        void inject(ArtistListFragment fragment);

        ArtistPresenter getPresenter();
        ArtistAdapter getAdapter();
        RealmPresenter getRealmPresenter(); //


}
