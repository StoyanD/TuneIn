package com.stoyan.tunein.network.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stoyan on 2/9/18.
 *
 * "element": "outline",
 * "type": "link",
 * "text": "Local Radio",
 * "URL": "http://opml.radiotime.com/Browse.ashx?c=local",
 * "key": "local"
 */
public class CategoryApi {

    public String element;
    public String type;
    @SerializedName("text")
    public String name;
    @SerializedName("URL")
    public String url;
    public String key;
}
