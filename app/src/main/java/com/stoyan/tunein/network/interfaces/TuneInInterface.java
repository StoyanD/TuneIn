package com.stoyan.tunein.network.interfaces;

import com.stoyan.tunein.network.api.CategoryList;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by stoyan on 2/9/18.
 */

public interface TuneInInterface {
    @GET("Browse.ashx")
    Flowable<CategoryList> getCategoriesList();
}
