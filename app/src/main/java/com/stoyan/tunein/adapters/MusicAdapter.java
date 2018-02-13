package com.stoyan.tunein.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.ViewAdapterItemBinding;
import com.stoyan.tunein.databinding.ViewAdapterTitleBinding;
import com.stoyan.tunein.network.api.AudioApi;
import com.stoyan.tunein.network.api.AudioParentApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by stoyan on 2/12/18.
 */

public class MusicAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>  {
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
        if(audioApi.get(position).isFake){
            ((TitleViewHolder)holder).bind(audioApi.get(position));
        }else{
            ((ItemViewHolder)holder).bind(audioApi.get(position));
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

    private void mapApi(List<AudioParentApi> audioParentApi) {
        audioApi = new ArrayList<>();
        for(AudioParentApi parentApi : audioParentApi){
            audioApi.add(new AudioApi(true));
            audioApi.addAll(parentApi.audioList);
        }
    }

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
//            mViewBinding.setCategory(api);
////            Picasso.with(context).load(api.coverImgUrl).into(mViewBinding.listCoverImage);
//            RxView.clicks(mViewBinding.categoryName)
//                    .subscribe(new Consumer<Object>() {
//                        @Override
//                        public void accept(Object o) throws Exception {
////                            Toast.makeText(context, "RxView.clicks", Toast.LENGTH_SHORT).show();
//                            onCategoryClick.onCategoryClick(api.key);
//                        }
//                    });
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(ViewAdapterTitleBinding viewBinding) {
            super(viewBinding.getRoot());
//            mViewBinding = viewBinding;
//            this.onCategoryClick = onCategoryClick;
//            TuneInApp.appComponent.inject(this);
        }
        private void bind(final AudioApi api) {

        }
    }
}
