package com.stoyan.tunein.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.network.api.CategoryList;
import com.stoyan.tunein.network.interfaces.TuneInInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by stoyan on 2/9/18.
 */

public class CategoriesFrag extends BaseFragment {
    private static final String TAG = CategoriesFrag.class.getSimpleName();
    @Inject
    TuneInInterface api;

//    FragmentRestaurantListBinding binding;
//    public RestaurantAdapter adapter;

    public static CategoriesFrag newInstance() {
        return new CategoriesFrag();
    }

    @Override
    public void onResume() {
        super.onResume();
        api.getCategoriesList().enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                response.body().categoryList.get(0);
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//List<RestaurantApi>
        TuneInApp.appComponent.inject(this);

    }
}
