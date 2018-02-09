package com.stoyan.tunein.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoyan.tunein.adapters.CategoriesAdapter;
import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.FragmentCategoriesBinding;
import com.stoyan.tunein.network.api.CategoryApi;
import com.stoyan.tunein.network.api.CategoryList;
import com.stoyan.tunein.network.interfaces.TuneInInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stoyan on 2/9/18.
 */

public class CategoriesFrag extends BaseFragment {
    private static final String TAG = CategoriesFrag.class.getSimpleName();
    @Inject
    TuneInInterface api;

    FragmentCategoriesBinding binding;

    public static CategoriesFrag newInstance() {
        return new CategoriesFrag();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TuneInApp.appComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable.add(api.getCategoriesList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryList>() {
                    @Override
                    public void accept(CategoryList categoryList) throws Exception {
                        initAdapter(categoryList.categoryList);
                    }
                }));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = binding.categoriesRv;
        rv.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void initAdapter(List<CategoryApi> categoryList) {
        binding.categoriesRv.setAdapter( new CategoriesAdapter(categoryList));
    }
}
