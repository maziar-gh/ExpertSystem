package com.github.aliakseikaraliou.expertsystem.ui.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.github.aliakseikaraliou.expertsystem.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private CheckBox mCheckBox;

    public ItemViewHolder(final View itemView) {
        super(itemView);
        mCheckBox = itemView.findViewById(R.id.text);
        mCheckBox.setEnabled(false);
    }

    public void setChecked(final boolean checked) {
        mCheckBox.setChecked(checked);
    }

    public void setText(final CharSequence text) {
        mCheckBox.setText(text);
    }
}
