package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.aliakseikaraliou.expertsystem.ExpertSystem;
import com.github.aliakseikaraliou.expertsystem.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text).setOnClickListener(this);
        findViewById(R.id.item).setOnClickListener(this);
        findViewById(R.id.expert).setOnClickListener(this);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Home");
        }
    }

    @Override
    public void onClick(final View pView) {
        final int id = pView.getId();

        @Nullable
        final Intent pIntent;

        switch (id) {
            case R.id.text:
                pIntent = new Intent(this, PropertyActivity.class);
                break;
            case R.id.item:
                pIntent = new Intent(this, ItemListActivity.class);
                break;
            case R.id.expert:
                pIntent = new Intent(this, ExpertSystemActivity.class);
                break;
            default:
                pIntent = null;
        }

        startActivity(pIntent);
    }

}
