package com.github.aliakseikaraliou.expertsystem.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.aliakseikaraliou.expertsystem.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SingleLineAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context mContext;
    private List<String> mList;

    public SingleLineAdapter(final Context pContext, final List<String> pList) {
        mContext = pContext;
        mList = new ArrayList<>(pList);
    }

    public SingleLineAdapter(final Context pContext) {
        this(pContext, new ArrayList<String>());
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_singleline, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final String item = mList.get(position);
        holder.setText(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void reload(final Collection<String> pItems) {
        mList = new ArrayList<>(pItems);
        notifyDataSetChanged();
    }
}
