package com.olio.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.olio.lutemon.LutemonClasses.Storage;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        Storage.getInstance().loadLutemons(this.context);
    }

    public void switchToGameMode(View view) {
        Intent intent = new Intent(this, TabGameMode.class);
        startActivity(intent);
    }

}