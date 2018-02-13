package com.stoyan.tunein.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stoyan.tunein.adapters.MusicAdapter;
import com.stoyan.tunein.adapters.MusicGenreAdapter;
import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.FragmentSubCategoryBinding;
import com.stoyan.tunein.network.api.AudioParentApi;
import com.stoyan.tunein.network.api.AudioResponseApi;
import com.stoyan.tunein.network.interfaces.TuneInInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stoyan on 2/12/18.
 */

public class SubCategoryFragment extends BaseFragment implements MusicGenreAdapter.OnCategoryClick {
    public static final String ID_KEY = "id_key";

    private String subKey;

    @Inject
    TuneInInterface api;

    FragmentSubCategoryBinding binding;

    public static SubCategoryFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putSerializable(SubCategoryFragment.ID_KEY, id);
        SubCategoryFragment fragment = new SubCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if(args != null){
            subKey = args.getString(SubCategoryFragment.ID_KEY);
        }

        if(subKey == null){
            Toast.makeText(getActivity(), "Something went wrong, subKey not set", Toast.LENGTH_LONG);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TuneInApp.appComponent.inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //todo
    }

    @Override
    public void onResume() {
        super.onResume();

        compositeDisposable.add(api.getSubCatById(subKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AudioResponseApi>() {
                    @Override
                    public void accept(AudioResponseApi response) throws Exception {
                        initAdapter(response.audioParentApi);
                    }
                }));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false);
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


    private void initAdapter(List<AudioParentApi> audioParentApi) {
        binding.categoriesRv.setAdapter( new MusicAdapter(this, audioParentApi));
    }

    @Override
    public void onCategoryClick(String id) {

    }
}
