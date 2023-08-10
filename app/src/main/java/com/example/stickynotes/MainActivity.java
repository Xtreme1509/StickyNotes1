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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button signin,register;
    TextView forgot;
    FirebaseAuth fbbb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //DECLARE
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        signin=findViewById(R.id.button);
        register=findViewById(R.id.button2);
        forgot=findViewById(R.id.textView2);


        fbbb=FirebaseAuth.getInstance();
        FirebaseUser fuu=fbbb.getCurrentUser();






//          SIGN-IN  CLICK
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill=email.getText().toString().trim();
                String pass=password.getText().toString().trim();

                if(emaill.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "ENTER ALL FIELDS", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    fbbb.signInWithEmailAndPassword(emaill,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                verified();

                                getIntent().putExtra("EMAIL",emaill);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "account does not exist", Toast.LENGTH_LONG).show();
                            }




                        }
                    });



                }
            }
        });




//        FORGOT PASSWORD
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(getApplicationContext(),recovery.class);
                startActivity(a);
            }
        });




//        REGISTER

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(getApplicationContext(),register.class);
                startActivity(b);

            }
        });
    }
    private void verified()
    {
        FirebaseUser fuu= fbbb.getCurrentUser();;


        if(fuu.isEmailVerified()==true)
        {
            Toast.makeText(this, "Login Sucessful", Toast.LENGTH_SHORT).show();
            finish();
            Intent b= new Intent(getApplicationContext(),noteshome.class);
            startActivity(b);

        }
        else
        {
            Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
        }
    }


}