package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NOTEDETAILS extends AppCompatActivity {
    private TextView title;
    private TextView cont;
    FloatingActionButton edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notedetails);


        title=findViewById(R.id.atittle);
        cont=findViewById(R.id.vcontent);
        edit=findViewById(R.id.editicon);


        Intent d=getIntent();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent xx= new Intent(view.getContext(),editnote.class);
                xx.putExtra("title",d.getStringExtra("title"));
                xx.putExtra("content",d.getStringExtra("content"));
                xx.putExtra("NOTEID",d.getStringExtra("NOTEID"));
                view.getContext().startActivity(xx);




            }
        });

        cont.setText(d.getStringExtra("content"));
        title.setText(d.getStringExtra("title"));





    }
}