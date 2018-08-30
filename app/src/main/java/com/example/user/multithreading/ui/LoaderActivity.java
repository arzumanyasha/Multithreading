package com.example.user.multithreading.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.multithreading.R;
import com.example.user.multithreading.loader.CounterLoader;

public class LoaderActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Integer>{

    private static final int LOADER_COUNTER = 1;

    private TextView mCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        setViews();
    }

    private void setViews() {
        mCounterTextView = findViewById(R.id.countTextView2);

        Button btnStart = findViewById(R.id.startButton2);
        Button btnCancel = findViewById(R.id.cancelButton2);

        btnStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.startButton2:
                Bundle mBundle = new Bundle();
                mBundle.putInt(CounterLoader.ARG_COUNT, 10);
                getSupportLoaderManager().initLoader(LOADER_COUNTER, mBundle, this);
                break;

            case R.id.cancelButton2:
                getSupportLoaderManager().destroyLoader(LOADER_COUNTER);
                break;
        }
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LOADER_COUNTER) {
            return new CounterLoader(this, args);
        } else {
            throw new IllegalArgumentException("Unknown loader id");
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if (loader.getId() == LOADER_COUNTER) {
            mCounterTextView.setText("Done");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {
        if (loader.getId() == LOADER_COUNTER) {
            mCounterTextView.setText(null);
        }
    }


}
