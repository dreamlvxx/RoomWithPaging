package com.dream.roomandpaging;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

public class PeopleAdapter extends PagedListAdapter<People,PeopleViewHolder> {


    protected PeopleAdapter(@NonNull DiffUtil.ItemCallback<People> diffCallback) {
        super(diffCallback);
    }

    protected PeopleAdapter(@NonNull AsyncDifferConfig<People> config) {
        super(config);
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PeopleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public void onCurrentListChanged(@Nullable PagedList<People> previousList, @Nullable PagedList<People> currentList) {
        super.onCurrentListChanged(previousList, currentList);
    }
}
