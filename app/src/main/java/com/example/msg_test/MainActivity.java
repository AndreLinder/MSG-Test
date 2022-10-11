package com.example.msg_test;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String host = "andrelinder.ddns.net";
    private int port = 8005;
    private Socket S = null;
    private Connection connect = null;

    private EditText login = null;
    private EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    //Авторизация пользователя
    public void onClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

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

    public void regClick(View view){

        EditText login = findViewById(R.id.login2);
        EditText name = findViewById(R.id.name2);
        EditText password = findViewById(R.id.password2);
        EditText pass = findViewById(R.id.pass_rep);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    connect.openConnection();
                    String text = "02#"+login.getText()+"~"+name.getText()+"~"+password.getText()+"~";
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

    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}

