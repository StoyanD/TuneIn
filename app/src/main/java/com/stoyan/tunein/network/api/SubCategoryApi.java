package com.stoyan.tunein.network.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stoyan on 2/12/18.
 */

public class SubCategoryApi {

    public String element;
    public String type;
    @SerializedName("text")
    public String name;
    @SerializedName("URL")
    public String url;
    @SerializedName("guide_id")
    public String key;
}
