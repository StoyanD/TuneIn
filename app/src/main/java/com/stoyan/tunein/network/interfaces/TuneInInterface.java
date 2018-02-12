package com.stoyan.tunein.network.interfaces;

import com.stoyan.tunein.network.api.AudioResponseApi;
import com.stoyan.tunein.network.api.CategoryList;
import com.stoyan.tunein.network.api.OutlineWithChildrenApiResponse;
import com.stoyan.tunein.network.api.SubCategoryApiResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by stoyan on 2/9/18.
 */

public interface TuneInInterface {
    @GET("Browse.ashx")
    Flowable<CategoryList> getCategoriesList();



    @GET("Browse.ashx?c=local")
    Flowable<AudioResponseApi> getLocalList();

    @GET("Browse.ashx?c=music")
    Flowable<SubCategoryApiResponse> getMusicList();
    @GET("Browse.ashx?c=talk")
    Flowable<SubCategoryApiResponse> getTalkList();//do subs
    @GET("Browse.ashx?c=sports")
    Flowable<SubCategoryApiResponse> getSportsList();//do subs
    @GET("Browse.ashx?c=r0")
    Flowable<SubCategoryApiResponse> getByLocationList();//do subs
    @GET("Browse.ashx?c=lang")
    Flowable<SubCategoryApiResponse> getByLanguageList();//do subs



    @GET("Browse.ashx?c=podcast")
    Flowable<OutlineWithChildrenApiResponse> getPodcastsList();//do subs

    @GET("Browse.ashx")
    Flowable<OutlineWithChildrenApiResponse> getById(@Query("id") String guideId);//do subs






//            "Local Radio"
//            "Music"
//            "Talk"
//            "Sports"
//            "By Location"
//            "By Language"
//            "Podcasts"
}
