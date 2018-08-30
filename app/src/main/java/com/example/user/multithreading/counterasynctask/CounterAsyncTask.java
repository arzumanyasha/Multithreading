package com.example.user.multithreading.counterasynctask;

import android.os.AsyncTask;
import android.os.SystemClock;

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    private IAsyncTaskEvents mIAsyncTaskEvents;

    public CounterAsyncTask(IAsyncTaskEvents iAsyncTaskEvents) {
        mIAsyncTaskEvents = iAsyncTaskEvents;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int start = integers[0];
        int end = integers[1];

        for (int i = start; i <= end; i++) {
            if (isCancelled()) {
                return i;
            }
            publishProgress(i);
            SystemClock.sleep(500);
        }
        return end;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onProgressUpdate(values[0]);
        }
    }
}
