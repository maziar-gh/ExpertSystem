package com.github.aliakseikaraliou.expertsystem.ui.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.aliakseikaraliou.expertsystem.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private final TextView mProperty;

    public RecyclerViewHolder(final View itemView) {
        super(itemView);
        mProperty = itemView.findViewById(R.id.text);
    }

    public void setText(final String pText) {
        mProperty.setText(pText);
    }
}
