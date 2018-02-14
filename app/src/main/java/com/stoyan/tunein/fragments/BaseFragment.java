package com.stoyan.tunein.fragments;

import android.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base fragment that has a {@link BaseFragment#compositeDisposable} for adding and
 * removing subscriptions of events
 * <p>
 * Created by stoyan on 2/9/18.
 */
public class BaseFragment extends Fragment {
    public static final String ID_KEY = "id_key";

    protected CompositeDisposable compositeDisposable;

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }
}
