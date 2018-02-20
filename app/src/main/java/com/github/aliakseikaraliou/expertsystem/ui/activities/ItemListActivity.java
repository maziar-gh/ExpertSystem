package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
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

import com.github.aliakseikaraliou.andromeda.interfaces.OnItemClickListener;
import com.github.aliakseikaraliou.andromeda.utils.java.StringUtils;
import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.Property;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.holders.ItemHolder;
import com.github.aliakseikaraliou.expertsystem.holders.PropertyHolder;
import com.github.aliakseikaraliou.expertsystem.ui.adapters.ItemListAdapter;
import com.github.aliakseikaraliou.expertsystem.ui.adapters.PropertyListDialogAdapter;

import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_itemlist);

        mRecycler = findViewById(R.id.recycler);

        forceLoad();

        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Items");
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
                addItem();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(pMenuItem);
    }

    private void addItem() {
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add, null);
        final EditText itemName = dialogView.findViewById(R.id.item_name);

        final RecyclerView recycler = dialogView.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final List<Property> properties = Property.createFrom(PropertyHolder.getInstance().getAll(this));
        final PropertyListDialogAdapter adapter = new PropertyListDialogAdapter(this, properties);
        recycler.setAdapter(adapter);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        final String name = itemName.getText().toString();
                        if (!StringUtils.isEmpty(name)) {
                            final List<Integer> response = Property.getResponse(properties);
                            final Item item = new Item(name, response);

                            ItemHolder.getInstance().add(ItemListActivity.this, item);

                            forceLoad();
                        }

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        alertDialog.show();

    }

    private void forceLoad() {
        final List<Item> items = ItemHolder.getInstance().getAll(this);

        final ItemListAdapter adapter = new ItemListAdapter(this, items);
        adapter.setItemClickListener(new OnItemClickListener<Item>() {

            @Override
            public void onItemClick(final Item pItem) {
                final Intent intent = new Intent(ItemListActivity.this, ItemActivity.class);
                intent.putExtra(ItemActivity.ITEM, pItem);
                startActivity(intent);
            }
        });
        mRecycler.setAdapter(adapter);
    }
}
