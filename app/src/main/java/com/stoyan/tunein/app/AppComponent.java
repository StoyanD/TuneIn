package com.stoyan.tunein.app;

import com.stoyan.tunein.adapters.CategoriesAdapter;
import com.stoyan.tunein.fragments.CategoriesFragment;
import com.stoyan.tunein.fragments.CategoryFragment;
import com.stoyan.tunein.network.NetworkModule;
import com.stoyan.tunein.network.TuneInApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stoyan on 1/30/18.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, TuneInApiModule.class})
public interface AppComponent {

    void inject(CategoriesFragment frag);
    void inject(CategoryFragment frag);
    void inject(CategoriesAdapter.ViewHolder holder);
}