package com.tae.dagger2withmvp.dagger2.component;

import com.tae.dagger2withmvp.dagger2.module.PresenterServiceModule;
import com.tae.dagger2withmvp.dagger2.scope.ServiceScope;
import com.tae.dagger2withmvp.model.service.ArtistService;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;

import dagger.Component;

/**
 * Created by Eduardo on 08/02/2016.
 */
@ServiceScope
@Component(
        dependencies = NetworkComponent.class,
        modules = PresenterServiceModule.class

)
public interface PresenterServiceComponent {

    void inject(ArtistService service);
    ArtistPresenter getPresenter();
}
