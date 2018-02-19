package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.aliakseikaraliou.expertsystem.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.property).setOnClickListener(this);
        findViewById(R.id.item).setOnClickListener(this);
    }

    @Override
    public void onClick(final View pView) {
        final int id = pView.getId();

        @Nullable
        final Intent pIntent;

        switch (id) {
            case R.id.property:
                pIntent = new Intent(this, PropertyActivity.class);
                break;
            case R.id.item:
                pIntent = new Intent(this, ItemActivity.class);
                break;
            default:
                pIntent = null;
        }

        startActivity(pIntent);
    }

}
