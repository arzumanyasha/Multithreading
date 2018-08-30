package com.example.user.multithreading.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.multithreading.R;
import com.example.user.multithreading.counterasynctask.CounterAsyncTask;
import com.example.user.multithreading.counterasynctask.IAsyncTaskEvents;

public class AsyncTaskActivity extends AppCompatActivity implements IAsyncTaskEvents, View.OnClickListener{

    private TextView mCounterTextView;

    private CounterAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        setViews();
    }

    private void setViews() {
        mCounterTextView = findViewById(R.id.countTextView1);

        Button btnCreate = findViewById(R.id.createButton1);
        Button btnStart = findViewById(R.id.startButton1);
        Button btnCancel = findViewById(R.id.cancelButton1);

        btnCreate.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createButton1:
                mAsyncTask = new CounterAsyncTask(this);
                break;

            case R.id.startButton1:
                if(mAsyncTask!=null && (!mAsyncTask.isCancelled()) &&
                        mAsyncTask.getStatus()!= AsyncTask.Status.RUNNING &&
                        mAsyncTask.getStatus() != AsyncTask.Status.FINISHED){
                    mAsyncTask.execute(1, 10);
                }
                break;

            case R.id.cancelButton1:
                if(mAsyncTask!=null){
                    mAsyncTask.cancel(true);
                }
                break;
        }
    }

    @Override
    public void onPostExecute() {
        mCounterTextView.setText("Done!");
    }

    @Override
    public void onProgressUpdate(Integer integer) {
        mCounterTextView.setText(String.valueOf(integer));
    }

    @Override
    protected void onDestroy() {
        if (mAsyncTask != null) {
            mAsyncTask.cancel(false);
            mAsyncTask = null;
        }
        super.onDestroy();
    }
}
