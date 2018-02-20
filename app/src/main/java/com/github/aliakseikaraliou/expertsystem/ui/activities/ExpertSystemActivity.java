package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.aliakseikaraliou.andromeda.interfaces.OnItemClickListener;
import com.github.aliakseikaraliou.expertsystem.ExpertSystem;
import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.Property;
import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.holders.ItemHolder;
import com.github.aliakseikaraliou.expertsystem.holders.PropertyHolder;
import com.github.aliakseikaraliou.expertsystem.ui.adapters.ExpertSystemAdapter;

import java.util.List;
import java.util.Locale;

public class ExpertSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_expert_system);

        final PropertyHolder propertyHolder = PropertyHolder.getInstance();

        final ExpertSystemAdapter adapter = new ExpertSystemAdapter(this);

        final RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        final List<Item> items = ItemHolder.getInstance().getAll(this);
        final ExpertSystem expertSystem = new ExpertSystem(items, propertyHolder.getAll(this));

        showQuestions(adapter, expertSystem);

        adapter.setItemClickListener(new OnItemClickListener<Property>() {

            @Override
            public void onItemClick(final Property pProperty) {
                expertSystem.setAnswer(pProperty.isChecked());
                showQuestions(adapter, expertSystem);
            }
        });

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Expert System");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showQuestions(final ExpertSystemAdapter pAdapter, final ExpertSystem pExpertSystem) {

        if (pExpertSystem.isFinish()) {
            showAnswer(pExpertSystem.getResult());
        } else {
            final String question = pExpertSystem.getQuestion();
            pAdapter.add(question);
        }

    }

    private void showAnswer(final String pResult) {
        final TextView textView = findViewById(R.id.answer);

        textView.setText(String.format(Locale.US, getString(R.string.answer_template), pResult));
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu pMenu) {

        getMenuInflater().inflate(R.menu.expert, pMenu);

        return super.onCreateOptionsMenu(pMenu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem pItem) {

        if (pItem.getItemId() == R.id.restart) {
            recreate();
        } else if (pItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(pItem);

    }
}
