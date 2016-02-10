package com.tae.dagger2withmvp.dagger2.module;

import android.content.Context;
import android.util.Log;

import com.tae.dagger2withmvp.interactor.ArtistInteractor;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;
import com.tae.dagger2withmvp.view.adapter.ArtistAdapter;
import com.tae.dagger2withmvp.view.viewmodel.ArtistViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduardo on 07/02/2016.
 */
@Singleton
@Module
public class ArtistPresenterModule {

    private static final String TAG = ArtistPresenterModule.class.getSimpleName();
    private ArtistViewModel viewModel;


    public ArtistPresenterModule(ArtistViewModel viewModel) {
        Log.i(TAG, "ArtistPresenterModule: Constructor");
        this.viewModel = viewModel;
    }

    @Provides ArtistViewModel provideViewModel() {
        return viewModel;
    }

    @Provides
    public ArtistPresenter providePresenter(ArtistInteractor interactor, ArtistViewModel viewModel) {
        Log.i(TAG, "providePresenter: with Interactor and ViewModel");
        return new ArtistPresenter(interactor, viewModel);
    }


    @Provides
    public ArtistAdapter provideAdapter(Context context) {
        Log.i(TAG, "provideAdapter: ");
        return new ArtistAdapter(context);
    }
}
