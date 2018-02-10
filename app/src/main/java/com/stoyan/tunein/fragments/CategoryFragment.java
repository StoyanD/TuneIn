package com.stoyan.tunein.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.FragmentCategoryBinding;
import com.stoyan.tunein.network.interfaces.TuneInInterface;

import javax.inject.Inject;

/**
 * Created by stoyan on 2/9/18.
 */

public class CategoryFragment extends BaseFragment {

    private String url;
    @Inject
    TuneInInterface api;

    FragmentCategoryBinding binding;
    public static CategoriesFragment newInstance(String url) {
        return new CategoriesFragment();
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TuneInApp.appComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = binding.categoriesRv;
        rv.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);

    }
}
