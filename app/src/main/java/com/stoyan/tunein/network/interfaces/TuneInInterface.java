package com.stoyan.tunein.network.interfaces;

import com.stoyan.tunein.network.api.AudioResponseApi;
import com.stoyan.tunein.network.api.SubCategoryApiResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by stoyan on 2/9/18.
 */

public interface TuneInInterface {

    @GET("Browse.ashx?")
    Flowable<SubCategoryApiResponse> getMusicList(@Query("c") String category);

    @GET("Tune.ashx?c=pbrowse")
    Flowable<AudioResponseApi> getAudioById(@Query("id") String category);

    @GET("Browse.ashx")
    Flowable<AudioResponseApi> getSubCatById(@Query("id") String guideId);
}
