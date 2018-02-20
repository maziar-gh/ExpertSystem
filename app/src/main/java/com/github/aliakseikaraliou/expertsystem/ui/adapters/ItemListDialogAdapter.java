package com.github.aliakseikaraliou.expertsystem.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.ui.viewHolders.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ItemListDialogAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context mContext;
    private final List<Boolean> mValues;
    private List<Item> mList;

    public ItemListDialogAdapter(final Context pContext, final List<Item> pList, final List<Boolean> pValues) {
        mContext = pContext;
        mList = new ArrayList<>(pList);
        mValues = pValues;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_checkline, parent, false);

        ((CompoundButton) view.findViewById(R.id.text)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                final int position = (int) view.getTag();
                mValues.set(position, isChecked);
            }
        });

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final Item item = mList.get(position);
        holder.setText(item.getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
