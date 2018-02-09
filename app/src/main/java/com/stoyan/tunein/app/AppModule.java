package com.stoyan.tunein.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stoyan on 1/30/18.
 */
@Module
public class AppModule {
    private Application app;

    public AppModule(Application application) {
        app = application;
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return app;
    }

    @Provides
    @Singleton
    public Context providesContext(){
        return app;
    }
}
