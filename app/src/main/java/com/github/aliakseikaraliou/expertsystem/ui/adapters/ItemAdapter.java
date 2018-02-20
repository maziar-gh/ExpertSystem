package com.github.aliakseikaraliou.expertsystem.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.Property;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.ui.viewHolders.ItemViewHolder;
import com.github.aliakseikaraliou.expertsystem.ui.viewHolders.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final Context mContext;
    private final List<String> mProperties;
    private Item mItem;

    public ItemAdapter(final Context pContext, final List<String> pProperties, final Item pItem) {
        mContext = pContext;
        mItem = pItem;
        mProperties = new ArrayList<>(pProperties);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_checkline, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.setText(mProperties.get(position));
        holder.setChecked(mItem.getProperties().get(position) > 0);
    }

    @Override
    public int getItemCount() {
        return mProperties.size();
    }
}
