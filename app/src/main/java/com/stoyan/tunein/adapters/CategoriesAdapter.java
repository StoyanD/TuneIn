package com.stoyan.tunein.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stoyan.tunein.app.TuneInApp;
import com.stoyan.tunein.databinding.ViewCategoryListItemBinding;
import com.stoyan.tunein.network.api.CategoryApi;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by stoyan on 2/9/18.
 */

public class CategoriesAdapter extends  RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    List<CategoryApi> categoryList;

    public CategoriesAdapter( List<CategoryApi> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewCategoryListItemBinding itemBinding =
                ViewCategoryListItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
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
        @Inject
        public Context context;
        private ViewCategoryListItemBinding mViewBinding;

        public ViewHolder(ViewCategoryListItemBinding viewBinding) {
            super(viewBinding.getRoot());
            mViewBinding = viewBinding;
            TuneInApp.appComponent.inject(this);
        }

        private void bind(CategoryApi api) {
            mViewBinding.setCategory(api);
//            Picasso.with(context).load(api.coverImgUrl).into(mViewBinding.listCoverImage);
        }
    }
}