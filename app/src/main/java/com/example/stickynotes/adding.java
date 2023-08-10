package com.example.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class adding extends AppCompatActivity {
    EditText title,content;
    FloatingActionButton se;
    FirebaseAuth fb;
    FirebaseUser fu;
    FirebaseFirestore ffs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding);

        title=findViewById(R.id.addtittle);
        content=findViewById(R.id.addcontent);
        se=findViewById(R.id.addb);



        fb=FirebaseAuth.getInstance();
        fu=FirebaseAuth.getInstance().getCurrentUser();
        ffs=FirebaseFirestore.getInstance();




        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=title.getText().toString();
                String c=content.getText().toString();


                if(c.isEmpty() || t.isEmpty())
                {
                    Toast.makeText(adding.this, "All fields are required to fill", Toast.LENGTH_SHORT).show();

                }


                else
                {
                    DocumentReference df=ffs.collection("notes").document(fu.getUid()).collection("mynote").document();
                    Map<String,Object> NOTE= new HashMap<>();
                    NOTE.put("Title",t);
                    NOTE.put("Content",c);

                    df.set(NOTE).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(adding.this, "Created Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent asd=new Intent(adding.this,noteshome.class);
                            startActivity(asd);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(adding.this, "Failed", Toast.LENGTH_SHORT).show();
                            Intent ad=new Intent(adding.this,noteshome.class);
                            startActivity(ad);
                        }
                    });

                }




            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}