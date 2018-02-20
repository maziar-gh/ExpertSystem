package com.github.aliakseikaraliou.expertsystem.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.aliakseikaraliou.andromeda.interfaces.OnItemClickListener;
import com.github.aliakseikaraliou.expertsystem.Property;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.ui.viewHolders.ExpertSystemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ExpertSystemAdapter extends RecyclerView.Adapter<ExpertSystemViewHolder> {

    private Context mContext;
    private List<Property> mProperties;
    private OnItemClickListener<Property> mItemClickListener;

    public ExpertSystemAdapter(final Context pContext) {
        mContext = pContext;
        mProperties = new ArrayList<>();
    }

    @Override
    public ExpertSystemViewHolder onCreateViewHolder(final ViewGroup pParent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_expert_system, pParent, false);
        final ExpertSystemViewHolder expertSystemViewHolder = new ExpertSystemViewHolder(view);

        expertSystemViewHolder.setButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final Property property = (Property) expertSystemViewHolder.getTag();

                if (property != null) {
                    property.setChecked(expertSystemViewHolder.isChecked());

                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(property);
                    }
                }

                expertSystemViewHolder.setEnabled(false);
            }
        });

        return expertSystemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ExpertSystemViewHolder pHolder, final int pPosition) {
        final Property property = mProperties.get(pPosition);

        pHolder.setText(property.getName());
        pHolder.setChecked(property.isChecked());

        pHolder.setTag(property);

        if (pPosition != mProperties.size() - 1) {
            pHolder.setEnabled(false);
        } else {
            pHolder.setEnabled(true);
        }
    }

    public void add(final String pS) {
        mProperties.add(new Property(pS));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProperties.size();
    }

    public void setItemClickListener(final OnItemClickListener<Property> pItemClickListener) {
        mItemClickListener = pItemClickListener;
    }

}
