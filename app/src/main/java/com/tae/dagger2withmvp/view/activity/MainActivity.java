package com.tae.dagger2withmvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.tae.dagger2withmvp.R;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;
import com.tae.dagger2withmvp.dagger2.module.ApplicationModule;
import com.tae.dagger2withmvp.dagger2.module.ArtistPresenterModule;
import com.tae.dagger2withmvp.model.itunesmodel.Result;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;
import com.tae.dagger2withmvp.view.base.BaseActivity;
import com.tae.dagger2withmvp.view.base.BasePresenter;
import com.tae.dagger2withmvp.view.fragment.ArtistListFragment;
import com.tae.dagger2withmvp.view.viewmodel.ArtistViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindString(R.string.fragment_artist_list) String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        displayFragment(ArtistListFragment.newInstance(), tag);
    }

    private void displayFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, fragment, tag).commit();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(NetworkComponent component) {
    }

}
