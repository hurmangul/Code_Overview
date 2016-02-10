package com.tae.dagger2withmvp.dagger2.component;

import com.tae.dagger2withmvp.dagger2.scope.AppScope;

import dagger.Component;

/**
 * Created by Eduardo on 09/02/2016.
 * As Artist SearchPresenter has 2 dependencies we need to make them extend this class
 * in order to avoid an error (1 component cannot have 2 different dependencies)
 */
@AppScope
@Component
public interface AppComponent {
}
