package com.stoyan.tunein.fragments;

import android.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by stoyan on 2/9/18.
 */
public class BaseFragment extends Fragment {
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
