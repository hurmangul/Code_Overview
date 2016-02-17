package com.tae.dagger2withmvp.dagger2.component;

import com.tae.dagger2withmvp.dagger2.module.InteractorModule;
import com.tae.dagger2withmvp.dagger2.module.NetworkModule;
import com.tae.dagger2withmvp.interactor.ArtistInteractor;

import dagger.Component;

/**
 * Created by Hurman on 05/02/2016.
 * NetworkComponent requires:
 * -NetworkModule
 * -InteractorModule
 */

@Component (
        modules = {
                NetworkModule.class,
                InteractorModule.class
        }
)
public interface NetworkComponent extends AppComponent{


        ArtistInteractor getArtistInteractor();

}
