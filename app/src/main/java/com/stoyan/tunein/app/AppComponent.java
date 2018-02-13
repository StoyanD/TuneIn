package com.stoyan.tunein.app;

import com.stoyan.tunein.adapters.MusicAdapter;
import com.stoyan.tunein.adapters.MusicGenreAdapter;
import com.stoyan.tunein.fragments.CategoryFragment;
import com.stoyan.tunein.fragments.SubCategoryFragment;
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

    void inject(CategoryFragment frag);
    void inject(SubCategoryFragment frag);
    void inject(MusicGenreAdapter.ViewHolder holder);
    void inject(MusicAdapter.ItemViewHolder holder);
}