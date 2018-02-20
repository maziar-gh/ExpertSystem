package com.github.aliakseikaraliou.expertsystem.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.github.aliakseikaraliou.expertsystem.Property;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.ui.viewHolders.RecyclerViewHolder;

import java.util.List;

public class PropertyListDialogAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context mContext;
    private List<Property> mList;

    public PropertyListDialogAdapter(final Context pContext, final List<Property> pList) {
        mContext = pContext;
        mList = pList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_checkline, parent, false);

        ((CompoundButton) view.findViewById(R.id.text)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                final Property property = (Property) view.getTag();
                property.setChecked(isChecked);
            }
        });

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final Property property = mList.get(position);
        holder.setText(property.getName());
        holder.itemView.setTag(property);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
