package com.example.user.multithreading.loader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class CounterLoader extends AsyncTaskLoader<Integer>{

    public static final String ARG_COUNT = "count";
    private Integer mData;
    private boolean mLoaded;

    public CounterLoader(@NonNull Context context, Bundle args) {
        super(context);
        if(args!=null){
            mData = args.getInt(ARG_COUNT);
        }
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        if(mData == null){
            return null;
        }
        for (int i = 0; i <= mData; i++) {
            if (isAbandoned()) {
                return i;
            }
            SystemClock.sleep(500);
        }

        return mData;
    }

    @Override
    protected void onStartLoading(){
        if (mLoaded) {
            deliverResult(mData);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(@Nullable Integer data) {
        mLoaded = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        mLoaded = false;
        super.onReset();
    }
}
