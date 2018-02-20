package com.github.aliakseikaraliou.expertsystem.ui.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.github.aliakseikaraliou.andromeda.utils.android.UiUtils;
import com.github.aliakseikaraliou.expertsystem.R;

public class ExpertSystemViewHolder extends RecyclerView.ViewHolder {

    private final Button mButton;
    private final CheckBox mCheckBox;

    public ExpertSystemViewHolder(final View pItemView) {
        super(pItemView);

        mButton = pItemView.findViewById(R.id.button);
        mCheckBox = pItemView.findViewById(R.id.text);
    }

    public boolean isChecked() {
        return mCheckBox.isChecked();
    }

    public void setChecked(final boolean checked) {
        mCheckBox.setChecked(checked);
    }

    public void setButtonClickListener(final View.OnClickListener pButtonClickListener) {
        mButton.setOnClickListener(pButtonClickListener);
    }

    public void setText(final CharSequence text) {
        mCheckBox.setText(text);
    }

    public void setTag(final Object tag) {
        itemView.setTag(tag);
    }

    public Object getTag() {
        return itemView.getTag();
    }

    public void setEnabled(final boolean pEnabled) {
        UiUtils.setVisibility(mButton, pEnabled);
        mCheckBox.setEnabled(pEnabled);
    }
}
