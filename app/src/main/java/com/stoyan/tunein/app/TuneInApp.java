package com.stoyan.tunein.app;

import android.app.Application;

/**
 * Created by stoyan on 2/9/18.
 */

public class TuneInApp extends Application {
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent =  DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
