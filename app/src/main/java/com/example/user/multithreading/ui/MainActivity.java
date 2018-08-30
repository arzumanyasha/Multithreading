package com.example.user.multithreading.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.multithreading.R;

public class MainActivity extends AppCompatActivity {

    private Button asyncTaskButton, loaderButton, threadsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTaskButton = (Button) findViewById(R.id.button1);
        loaderButton    = (Button) findViewById(R.id.button2);
        threadsButton   = (Button) findViewById(R.id.button3);

        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.button1:
                        Intent intent1 = new Intent(MainActivity.this, AsyncTaskActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.button2:
                        Intent intent2 = new Intent(MainActivity.this, LoaderActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.button3:
                        Intent intent3 = new Intent(MainActivity.this, ThreadsActivity.class);
                        startActivity(intent3);
                        break;
                    default:
                }
            }
        };

        asyncTaskButton.setOnClickListener(btnClick);
        loaderButton.setOnClickListener(btnClick);
        threadsButton.setOnClickListener(btnClick);
    }
}
