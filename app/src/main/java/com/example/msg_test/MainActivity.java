package com.example.msg_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Toast message = Toast.makeText(getApplicationContext(),"Дальше не доделал...", Toast.LENGTH_LONG);
        message.show();
    }
}