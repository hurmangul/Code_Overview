package com.tae.dagger2withmvp.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tae.dagger2withmvp.MyApp;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;
import com.tae.dagger2withmvp.dagger2.component.RealmComponent;

import butterknife.ButterKnife;

/**
 * Created by Eduardo on 04/02/2016.
 */
public abstract class BaseFragment extends Fragment {

    protected Context context;

    /**
     * Get the context when the fragment will be attached to the activity
     * @param activity
     */
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        context = activity;
    }

    /**
     * Init the Fragments view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    /**
     * Bind views with Butterknife
     * Inject your dependencies
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        injectDependencies();
    }

    /**
     * Attach the Presenter
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }

    /**
     * Detach the Presenter
     */
    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    /**
     * Unbind the views with Butterknife
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindViews();

    }


    /**
     * Specify the xml layout of the child fragment to be inflated
     * */
    protected abstract int getFragmentLayout();

    /**
     * @return The presenter attached to the fragment. This must extends from {@link BasePresenter}
     * */
    protected abstract BasePresenter getPresenter();

    private void injectDependencies() {
        setUpComponent(
                MyApp.getApp(getActivity()).getNetworkComponent(),
                MyApp.getApp(getActivity()).getRealmComponent());
    }

    /**
     * Replace all the annotated fields with ButterKnife annotations with the proper value
     * */
    private void bindViews(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    private void unbindViews() {
        ButterKnife.unbind(this);
    }

    /**
     * This method will setup the injector and will commit the dependencies injections.
     * */
    protected abstract void setUpComponent(NetworkComponent component, RealmComponent realmComponent);
}
