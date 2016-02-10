package com.tae.dagger2withmvp.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tae.dagger2withmvp.MyApp;
import com.tae.dagger2withmvp.R;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eduardo on 04/02/2016.
 *
 * Base class for all the activities.
 * Provides methods to return ItunesComponent, xml layout,
 *
 * If the Activity child works as a fragment container, set getPresenter as null
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    /**
     * SetContent view with the provided xml by the children
     * Inject Dagger dependencies
     * Bind Butterknife views
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectDependencies();
        bindViews();
        setSupportActionBar(toolbar);
    }

    /**
     * Attach the Presenter to the Activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null)
            getPresenter().onStart();
    }

    /**
     * Detach the Presenter to the Activity
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null)
            getPresenter().onStop();
    }

    /**
     * Set the ActionBar with the Toolbar
     */
    public void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    /**
     * @return The layout provided by the Activity child that's gonna be the activity view.
     */
    protected abstract int getLayout();

    /**
     * @return The presenter attached to the activity. The Presenter must extends BasePresenter
     */
    @Nullable
    protected abstract BasePresenter getPresenter();

    /**
     * Setup the object graph and inject the dependencies needed on this activity.
     */
    private void injectDependencies() {
        setUpComponent(MyApp.getApp(this).getNetworkComponent());
    }

    /**
     * Bind the views with Butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }


    /**
     * Set up the Itunes Streamer Component
     * @param appComponent
     */
    public abstract void setUpComponent(NetworkComponent appComponent);
}
