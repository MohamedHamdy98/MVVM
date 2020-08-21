package com.adapter;

import android.view.LayoutInflater;

import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.mh.mvp.R;
import com.mh.mvp.databinding.ItemRecyclerViewBinding;
import com.models.Posts;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPosts extends RecyclerView.Adapter<RecyclerViewPosts.ViewHolder> {
    private List<Posts> modelArrayList = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_recycler_view, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Posts posts = modelArrayList.get(position);
        holder.binding.setPost(posts);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void setList(List<Posts> models) {
        this.modelArrayList = models;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerViewBinding binding;

        public ViewHolder(@NonNull ItemRecyclerViewBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}




