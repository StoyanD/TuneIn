package com.stoyan.tunein.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoyan.tunein.activities.MainActivity;
import com.stoyan.tunein.adapters.MusicGenreAdapter;
import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.FragmentCategoryBinding;
import com.stoyan.tunein.network.api.SubCategoryApi;
import com.stoyan.tunein.network.api.SubCategoryApiResponse;
import com.stoyan.tunein.network.interfaces.TuneInInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.stoyan.tunein.network.NetworkConstants.TUNE_IN_MUSIC_KEY;

/**
 * Created by stoyan on 2/9/18.
 */

public class CategoryFragment extends BaseFragment implements MusicGenreAdapter.OnCategoryClick {
    @Inject
    TuneInInterface api;

    FragmentCategoryBinding binding;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TuneInApp.appComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable.add(api.getMusicList(TUNE_IN_MUSIC_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SubCategoryApiResponse>() {
                    @Override
                    public void accept(SubCategoryApiResponse subCategoryApiResponse) throws Exception {
                        initAdapter(subCategoryApiResponse.musicCategoryApiList);
                    }
                }));
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
        rv.addItemDecoration(new DividerItemDecoration(
                rv.getContext(),
                manager.getOrientation()
        ));

    }

    @Override
    public void onCategoryClick(String id) {
        ((MainActivity) getActivity()).addSubCategoryFrag(id, false);
    }

    private void initAdapter(List<SubCategoryApi> subCategoryApis) {
        binding.categoriesRv.setAdapter(new MusicGenreAdapter(this, subCategoryApis));
    }
}
