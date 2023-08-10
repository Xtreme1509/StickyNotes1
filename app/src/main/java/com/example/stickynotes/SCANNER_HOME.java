package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SCANNER_HOME extends AppCompatActivity {

    Button snap,recognise;
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_home);
        getSupportActionBar().hide();

        snap=findViewById(R.id.snapp);
        recognise=findViewById(R.id.recog);
        image=findViewById(R.id.imagee);
        text=findViewById(R.id.)







    }
}