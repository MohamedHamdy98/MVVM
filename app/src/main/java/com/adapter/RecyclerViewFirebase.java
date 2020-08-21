package com.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.mh.mvp.R;
import com.mh.mvp.databinding.RecyclerViewFirebaseBinding;
import com.models.Peoples;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFirebase extends RecyclerView.Adapter<RecyclerViewFirebase.ViewHolder> {
    private List<Peoples> modelArrayList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewFirebaseBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext())
                , R.layout.recycler_view_firebase, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Peoples peoples = modelArrayList.get(position);
        holder.binding.setPeople(peoples);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void setList(List<Peoples> models) {
        this.modelArrayList = models;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewFirebaseBinding binding;

        public ViewHolder(@NonNull RecyclerViewFirebaseBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}




