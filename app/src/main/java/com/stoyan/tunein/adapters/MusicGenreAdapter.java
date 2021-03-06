package com.stoyan.tunein.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.ViewCategoryListItemBinding;
import com.stoyan.tunein.network.api.SubCategoryApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by stoyan on 2/9/18.
 */

public class MusicGenreAdapter extends  RecyclerView.Adapter<MusicGenreAdapter.ViewHolder> {
    private OnCategoryClick onCategoryClick;
    private List<SubCategoryApi> categoryList;

    public interface OnCategoryClick {
        void onCategoryClick(String id);
    }

    public MusicGenreAdapter(OnCategoryClick onCategoryClick, List<SubCategoryApi> subCategoryApis) {
        this.onCategoryClick = onCategoryClick;
        this.categoryList = subCategoryApis;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewCategoryListItemBinding itemBinding =
                ViewCategoryListItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(onCategoryClick, itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private OnCategoryClick onCategoryClick;
        private ViewCategoryListItemBinding mViewBinding;
        @Inject
        Context context;

        public ViewHolder(OnCategoryClick onCategoryClick, ViewCategoryListItemBinding viewBinding) {
            super(viewBinding.getRoot());
            this.onCategoryClick = onCategoryClick;
            mViewBinding = viewBinding;
            TuneInApp.appComponent.inject(this);
        }

        private void bind(final SubCategoryApi api) {
            mViewBinding.setCategory(api);
            RxView.clicks(mViewBinding.categoryName)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            onCategoryClick.onCategoryClick(api.key);
                        }
                    });
        }
    }
}