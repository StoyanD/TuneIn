package com.stoyan.tunein.network.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stoyan on 2/12/18.
 */

public class AudioResponseApi {
    @SerializedName("head")
    public HeadApi headApi;
    @SerializedName("body")
    public List<AudioParentApi> audioParentApi;
}
