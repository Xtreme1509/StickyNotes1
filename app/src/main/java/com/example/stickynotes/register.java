package com.example.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class register extends AppCompatActivity {
    EditText email,password;
    Button goback,regiter;
    private FirebaseAuth fb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


//        DECLARE
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        regiter=findViewById(R.id.button);
        goback=findViewById(R.id.button2);
        fb=FirebaseAuth.getInstance();





//          REGISTERING--->
        regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if(emaill.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(register.this, "ENTER ALL FIELDS", Toast.LENGTH_SHORT).show();
                }

                else if (pass.length()<7)
                {
                    Toast.makeText(register.this, "Password Should Be Greater than 7", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fb.createUserWithEmailAndPassword(emaill,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(register.this, "Sucessfully Registered", Toast.LENGTH_SHORT).show();
                                sendemail();
                            }
                            else
                            {
                                Toast.makeText(register.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });








                }




            }
        });



//    -----GO BACK---
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent D= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(D);
            }
        });





    }


    private void sendemail()
    {
        FirebaseUser fu=fb.getCurrentUser();
        if(fu!=null)
        {
            fu.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Toast.makeText(register.this, "Verification email sent to you", Toast.LENGTH_SHORT).show();
                    fb.signOut();
                    finish();
                    Intent D= new Intent(register.this,MainActivity.class);
                    startActivity(D);



                }
            });

        }
    else{
            Toast.makeText(register.this, "Failed to send Verification email ", Toast.LENGTH_SHORT).show();
        }


    }

}
