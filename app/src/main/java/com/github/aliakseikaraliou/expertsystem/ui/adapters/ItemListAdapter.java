package com.github.aliakseikaraliou.expertsystem.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.aliakseikaraliou.andromeda.interfaces.OnItemClickListener;
import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.ui.viewHolders.RecyclerViewHolder;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context mContext;
    private final List<Item> mItemList;
    private OnItemClickListener<Item> mListener;

    public ItemListAdapter(final Context pContext, final List<Item> pItemList) {
        mContext = pContext;
        mItemList = pItemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_singleline, parent, false);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final Item item = (Item) v.getTag();

                if (mListener != null && item != null) {
                    mListener.onItemClick(item);
                }
            }
        });
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final Item item = mItemList.get(position);
        final String name = item.getName();
        holder.setText(name);

        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setItemClickListener(final OnItemClickListener<Item> pListener) {
        mListener = pListener;
    }
}
