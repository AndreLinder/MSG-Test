package com.example.msg_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String host = "192.168.50.219";
    private int port = 8005;
    private Socket S = null;
    private Connection connect = null;

    private EditText login = null;
    private EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Авторизация пользователя
    public void onClick(View view){

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        connect = new Connection(host, port);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    connect.openConnection();
                    String text = "01#"+login.getText()+"~"+password.getText()+"~";
                    connect.sendData(text.getBytes(StandardCharsets.UTF_8));
                    byte[] data = connect.recieveData();
                    text = new String(data, StandardCharsets.UTF_8);
                    login.setText(text);
                    connect.closeConnection();
                        Log.e("Server","Successfull");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

