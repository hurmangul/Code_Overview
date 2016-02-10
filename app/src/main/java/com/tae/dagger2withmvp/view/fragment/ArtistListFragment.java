package com.tae.dagger2withmvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.tae.dagger2withmvp.R;
import com.tae.dagger2withmvp.dagger2.component.DaggerArtistPresenterComponent;
import com.tae.dagger2withmvp.dagger2.component.NetworkComponent;
import com.tae.dagger2withmvp.dagger2.component.RealmComponent;
import com.tae.dagger2withmvp.dagger2.module.ApplicationModule;
import com.tae.dagger2withmvp.dagger2.module.ArtistPresenterModule;
import com.tae.dagger2withmvp.dagger2.module.RealmPresenterModule;
import com.tae.dagger2withmvp.model.itunesmodel.Result;
import com.tae.dagger2withmvp.model.utils.RxUtils;
import com.tae.dagger2withmvp.presenter.ArtistPresenter;
import com.tae.dagger2withmvp.presenter.RealmPresenter;
import com.tae.dagger2withmvp.view.adapter.ArtistAdapter;
import com.tae.dagger2withmvp.view.base.BaseFragment;
import com.tae.dagger2withmvp.view.base.BasePresenter;
import com.tae.dagger2withmvp.view.custom.ClearableEditText;
import com.tae.dagger2withmvp.view.viewmodel.ArtistViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Eduardo on 08/02/2016.
 */
public class ArtistListFragment extends BaseFragment implements ArtistViewModel{

    private static final String TAG = ArtistListFragment.class.getSimpleName();
    @Bind(R.id.rv_itunes_list)
    RecyclerView recyclerView;
    @Bind((R.id.et_query_gender))
    ClearableEditText editText;
    @Inject
    ArtistPresenter presenter;
    @Inject
    ArtistAdapter adapter;
    @Inject
    RealmPresenter realmPresenter;
    private Subscription subscription;


    public static ArtistListFragment newInstance () {
        return new ArtistListFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeToEditText();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxUtils.unsubscribeIfNotNull(subscription);
    }

    /*BaseFragment implementation*/
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_artist_list;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void setUpComponent(NetworkComponent component, RealmComponent realmComponent) {
        DaggerArtistPresenterComponent.builder()
                .networkComponent(component)
                .realmComponent(realmComponent)
                .artistPresenterModule(new ArtistPresenterModule(this))
                .applicationModule(new ApplicationModule(getActivity().getApplication()))
                .realmPresenterModule(new RealmPresenterModule())
                .build().inject(this);
    }

    /*ArtistViewModel implementation*/
    @Override
    public void setupList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * Init listener here
     */
    @Override
    public void setupAdapter() {

    }

    @Override
    public void displayFoundArtists(List<Result> artists) {
        adapter.replace(artists);
        Log.i(TAG, "displayFoundArtists: save all artist");
        realmPresenter.saveAllResults(artists); // TESTING REALM
        Log.i(TAG, "displayFoundArtists: query all");
        realmPresenter.queryAll(); // TESTING REALM
    }

    @Override
    public void displayFailedSearch() {
        Log.e(TAG, "displayFailedSearch: ");
    }

    @Override
    public void displayNetworkError() {
        Log.e(TAG, "displayNetworkError: ");
    }

    @Override
    public void displayApiError() {
        Log.e(TAG, "displayApiError: ");
    }


    /**
     * This subscription tracks what happend with the EditText Event (the observable)
     * with a delay and set the Observer with TextViewTextChangeEvent (compile bindingrxjava)
     */
    private void subscribeToEditText() {
        subscription = RxTextView.textChangeEvents(editText)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSearchObserver());
    }


    /**
     * Observer Pattern to track the user sequence in his search in Itunes
     * @return Oberver to track the user input in the Edit Text
     */
    private Observer<TextViewTextChangeEvent> getSearchObserver() {
           return new Observer<TextViewTextChangeEvent>() {
               @Override
               public void onCompleted() {
                   Log.i(TAG, "TextViewChanged onCompleted: ");
               }

               @Override
               public void onError(Throwable e) {
                   Log.e(TAG, "TextViewChanged  onError: ",e );
               }

               @Override
               public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                   Log.i(TAG, "TextViewChanged onNext: ");
                   search(textViewTextChangeEvent.text());
               }
           };
    }

    private void search(CharSequence sequence) {
        if (sequence.length() > 2) {
            presenter.search(sequence.toString());
//            getActivity().startService(ArtistService.makeIntent(getActivity().getApplicationContext(), sequence.toString()));
        } else if (sequence.length() <= 2) {
            adapter.clear();
        }
    }


}
