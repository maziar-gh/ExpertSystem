package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.github.aliakseikaraliou.andromeda.utils.java.CollectionUtils;
import com.github.aliakseikaraliou.andromeda.utils.java.StringUtils;
import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.holders.ItemHolder;
import com.github.aliakseikaraliou.expertsystem.holders.PropertyHolder;
import com.github.aliakseikaraliou.expertsystem.ui.adapters.ItemListDialogAdapter;
import com.github.aliakseikaraliou.expertsystem.ui.adapters.PropertyListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PropertyActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_property);

        mRecycler = findViewById(R.id.recycler);

        forceLoad();
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Properties");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu pMenu) {
        getMenuInflater().inflate(R.menu.items, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem pMenuItem) {
        final int itemId = pMenuItem.getItemId();

        switch (itemId) {
            case R.id.add:
                addProperty();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(pMenuItem);
    }

    private void addProperty() {
        final ItemHolder itemHolder = ItemHolder.getInstance();

        final List<Item> items = itemHolder.getAll(this);
        final List<Boolean> booleans = new ArrayList<>(Collections.nCopies(items.size(), false));

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add, null);
        final EditText itemName = dialogView.findViewById(R.id.item_name);
        final RecyclerView recycler = dialogView.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final ItemListDialogAdapter adapter = new ItemListDialogAdapter(this, items, booleans);
        recycler.setAdapter(adapter);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        final String name = itemName.getText().toString();

                        if (!StringUtils.isEmpty(name)) {

                            final Collection<Integer> values = CollectionUtils.map(booleans, new CollectionUtils.CollectionMapper<Boolean, Integer>() {

                                @Override
                                public Integer map(final Boolean pBoolean) {
                                    return pBoolean ? 1 : 0;
                                }
                            });

                            PropertyHolder.getInstance().add(PropertyActivity.this, name);
                            ItemHolder.getInstance().addProperty(PropertyActivity.this, ((List<Integer>) values));

                            forceLoad();
                        }

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        alertDialog.show();
    }

    private void forceLoad() {
        final PropertyListAdapter propertyListAdapter = new PropertyListAdapter(this, PropertyHolder.getInstance().getAll(this));
        mRecycler.setAdapter(propertyListAdapter);
    }
}
