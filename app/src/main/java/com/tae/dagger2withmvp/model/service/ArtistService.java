package com.tae.dagger2withmvp.model.service;

import android.content.Context;
import android.content.Intent;

import com.tae.dagger2withmvp.constants.Constants;
import com.tae.dagger2withmvp.dagger2.component.DaggerPresenterServiceComponent;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;
import com.tae.dagger2withmvp.dagger2.module.PresenterServiceModule;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;
import com.tae.dagger2withmvp.view.base.BasePresenter;
import com.tae.dagger2withmvp.view.base.BaseService;

import javax.inject.Inject;

/**
 * Created by Eduardo on 08/02/2016.
 */
public class ArtistService extends BaseService {

    @Inject
    ArtistPresenter presenter;

    public static Intent makeIntent(Context context, String query) {
        return new Intent(context, ArtistService.class).putExtra(Constants.EXTRA_QUERY_GENDER, query);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void setUpComponent(NetworkComponent component) {
         DaggerPresenterServiceComponent.builder()
                .networkComponent(component)
                .presenterServiceModule(new PresenterServiceModule())
                .build().inject(this);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        presenter.search(intent.getStringExtra(Constants.EXTRA_QUERY_GENDER));
    }
}
