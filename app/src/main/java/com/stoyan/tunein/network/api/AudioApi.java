package com.stoyan.tunein.network.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stoyan on 2/12/18.
 *
 * {
 "element": "outline",
 "type": "audio",
 "text": "90.7 | KALX (Eclectic Music)",
 "URL": "http://opml.radiotime.com/Tune.ashx?id=s31427",
 "bitrate": "128",
 "reliability": "100",
 "guide_id": "s31427",
 "subtext": "UC Berkeley and Listener Supported Radio",
 "genre_id": "g78",
 "formats": "mp3",
 "item": "station",
 "image": "http://cdn-radiotime-logos.tunein.com/s31427q.png",
 "now_playing_id": "s31427",
 "preset_id": "s31427"
 },
 */

public class AudioApi {
    public String type;
    @SerializedName("text")
    public String name;
    @SerializedName("subtext")
    public String subText;
    @SerializedName("URL")
    public String url;
    @SerializedName("image")
    public String imageUrl;
    @SerializedName("guide_id")
    public String id;

    public boolean isFake;

    public AudioApi(boolean isFake) {
        this.isFake = isFake;
    }

    public boolean isAudio() {
        return type != null && "audio".equalsIgnoreCase((type));
    }

}
