package com.example.user.multithreading.counterasynctask;

public interface IAsyncTaskEvents {
    void onPostExecute();
    void onProgressUpdate(Integer integer);
}

