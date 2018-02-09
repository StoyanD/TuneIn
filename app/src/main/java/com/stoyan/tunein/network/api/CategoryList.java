package com.stoyan.tunein.network.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stoyan on 2/9/18.
 */

public class CategoryList {
    @SerializedName("body")
   public List<Category> categoryList;
}
