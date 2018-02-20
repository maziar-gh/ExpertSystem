package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.holders.PropertyHolder;
import com.github.aliakseikaraliou.expertsystem.ui.adapters.ItemAdapter;

import java.util.List;

public class ItemActivity extends AppCompatActivity {

    protected static final String ITEM = "item";

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_item);

        final Item item = (Item) getIntent().getSerializableExtra(ITEM);

        final RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final List<String> properties = PropertyHolder.getInstance().getAll(this);
        recyclerView.setAdapter(new ItemAdapter(this, properties, item));

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(item.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem pMenuItem) {
        final int itemId = pMenuItem.getItemId();

        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(pMenuItem);
    }
}
