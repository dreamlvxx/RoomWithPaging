package com.dream.roomandpaging;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PeopleViewHolder extends RecyclerView.ViewHolder {

    PeopleViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
    }

    private TextView tv_name;
    private People mPeople;

    public void bind(People p) {
        if (null != p) {
            this.mPeople = p;
            tv_name.setText(p.getName());
        }
    }
}
