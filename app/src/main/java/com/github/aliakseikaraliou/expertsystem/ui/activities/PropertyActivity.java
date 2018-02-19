package com.github.aliakseikaraliou.expertsystem.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.github.aliakseikaraliou.expertsystem.R;
import com.github.aliakseikaraliou.expertsystem.holders.PropertyHolder;
import com.github.aliakseikaraliou.expertsystem.ui.SingleLineAdapter;

public class PropertyActivity extends AppCompatActivity {

    private static final String PROPERTIES = "properties";
    private EditText mEditText;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_property);

        final PropertyHolder propertyHolder = PropertyHolder.getInstance();

        mEditText = findViewById(R.id.edittext);
        mEditText.clearComposingText();

        final SingleLineAdapter singleLineAdapter = new SingleLineAdapter(this, propertyHolder.getAll(this));

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final String entered = mEditText.getText().toString();
                final Context context = PropertyActivity.this;

                if (!entered.isEmpty()) {
                    propertyHolder.add(context, entered);
                    mEditText.setText("");
                    singleLineAdapter.reload(propertyHolder.getAll(context));
                }
            }
        });

        final RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setAdapter(singleLineAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
