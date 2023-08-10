package com.example.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recovery extends AppCompatActivity {
    EditText email;
    Button recover;
    TextView goback;
    FirebaseAuth fbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery);

        email=findViewById(R.id.editTextTextEmailAddress);
        recover=findViewById(R.id.button);
        fbb=FirebaseAuth.getInstance();

        goback=findViewById(R.id.textView2);

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill=email.getText().toString().trim();


                if(emaill.isEmpty())
                {
                    Toast.makeText(recovery.this, "ENTER ALL FIELDS", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    fbb.sendPasswordResetEmail(emaill).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(recovery.this, "RECOVERY EMAIL SENT !", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent q= new Intent(recovery.this,MainActivity.class);
                                startActivity(q);

                            }
                            else
                            {
                                Toast.makeText(recovery.this, "Error Occured", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent C= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(C);
            }
        });
    }
}