package com.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.mh.mvp.R;
import com.mh.mvp.databinding.ItemRecyclerViewUserBinding;
import com.models.Address;
import com.models.Company;
import com.models.Geo;
import com.models.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewUsers extends RecyclerView.Adapter<RecyclerViewUsers.ViewHolder> {
    private List<User> modelArrayList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerViewUserBinding binding = DataBindingUtil.inflate(LayoutInflater
                        .from(parent.getContext())
                , R.layout.item_recycler_view_user, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User users = modelArrayList.get(position);
        holder.binding.setUser(users);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void setListUser(List<User> models) {
        this.modelArrayList = models;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerViewUserBinding binding;

        public ViewHolder(@NonNull ItemRecyclerViewUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}




