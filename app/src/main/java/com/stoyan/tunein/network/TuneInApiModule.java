package com.stoyan.tunein.network;

import com.stoyan.tunein.network.interfaces.TuneInInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by stoyan on 2/9/18.
 */
@Module
public class TuneInApiModule {

    @Provides
    @Singleton
    public TuneInInterface provideTuneInInterface(Retrofit retrofit){
        return retrofit.create(TuneInInterface.class);
    }
}
