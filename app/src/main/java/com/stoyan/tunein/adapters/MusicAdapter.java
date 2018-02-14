package com.stoyan.tunein.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.ViewAdapterItemBinding;
import com.stoyan.tunein.databinding.ViewAdapterTitleBinding;
import com.stoyan.tunein.network.api.AudioApi;
import com.stoyan.tunein.network.api.AudioParentApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by stoyan on 2/12/18.
 */
public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int FAKE_RETURN_VAL = 1;
    private MusicGenreAdapter.OnCategoryClick onCategoryClick;
    private List<AudioApi> audioApi;

    public MusicAdapter(MusicGenreAdapter.OnCategoryClick onCategoryClick, List<AudioParentApi> audioParentApi) {
        this.onCategoryClick = onCategoryClick;

        mapApi(audioParentApi);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());


        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case FAKE_RETURN_VAL:
                ViewAdapterTitleBinding titleBinding =
                        ViewAdapterTitleBinding.inflate(layoutInflater, parent, false);
                viewHolder = new TitleViewHolder(titleBinding);
                break;
            default:
                ViewAdapterItemBinding itemBinding =
                        ViewAdapterItemBinding.inflate(layoutInflater, parent, false);
                viewHolder = new ItemViewHolder(onCategoryClick, itemBinding);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (audioApi.get(position).isFake) {
            ((TitleViewHolder) holder).bind(audioApi.get(position));
        } else {
            ((ItemViewHolder) holder).bind(audioApi.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return audioApi.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (audioApi.get(position).isFake) {
            return FAKE_RETURN_VAL;
        }
        return -1;
    }

    /**
     * Map the api into a single list with the sub categories appearing
     * as fake {@link AudioApi}
     * @param audioParentApi
     */
    private void mapApi(List<AudioParentApi> audioParentApi) {
        audioApi = new ArrayList<>();
        for (AudioParentApi parentApi : audioParentApi) {
            AudioApi a = new AudioApi(true);
            a.name = parentApi.name;
            audioApi.add(a);
            if (parentApi.audioList != null) {
                audioApi.addAll(parentApi.audioList);
            }
        }
    }

    /**
     * View holder for the items
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ViewAdapterItemBinding mViewBinding;
        private MusicGenreAdapter.OnCategoryClick onCategoryClick;
        @Inject
        Context context;

        public ItemViewHolder(MusicGenreAdapter.OnCategoryClick onCategoryClick, ViewAdapterItemBinding viewBinding) {
            super(viewBinding.getRoot());
            mViewBinding = viewBinding;
            this.onCategoryClick = onCategoryClick;
            TuneInApp.appComponent.inject(this);
        }

        private void bind(final AudioApi api) {
            mViewBinding.setAudio(api);
            Picasso.with(context).load(api.imageUrl).into(mViewBinding.itemIv);
            RxView.clicks(mViewBinding.getRoot())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            if (api.isAudio()) {
                                Toast.makeText(context, "Playing Audio (Not really ;)", Toast.LENGTH_SHORT).show();
                            } else if (api.id != null) { //fetch url "http://opml.radiotime.com/Tune.ashx?c=pbrowse&id=####"
                                onCategoryClick.onCategoryClick(api.id);
                            }
                        }
                    });
        }
    }

    /**
     * View holder for the sub category titles
     */
    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private final ViewAdapterTitleBinding mViewBinding;

        public TitleViewHolder(ViewAdapterTitleBinding viewBinding) {
            super(viewBinding.getRoot());
            mViewBinding = viewBinding;
        }

        private void bind(final AudioApi api) {
            mViewBinding.setAudio(api);
        }
    }
}
