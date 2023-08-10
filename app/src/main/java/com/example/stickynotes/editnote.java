package com.example.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

public class editnote extends AppCompatActivity {

    Intent d;
    private TextView title;
    private TextView cont;
    FloatingActionButton save;
    FirebaseAuth fa;
    FirebaseUser fu;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editnote);
        d=getIntent();

        fa=FirebaseAuth.getInstance();
        fu=FirebaseAuth.getInstance().getCurrentUser();
        fs=FirebaseFirestore.getInstance();

        title=findViewById(R.id.etittle);
        cont=findViewById(R.id.econtent);
        save=findViewById(R.id.addbd);

        String ntitle=d.getStringExtra("title");
        String ncont=d.getStringExtra("content");

        title.setText(ntitle);
        cont.setText(ncont);







        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=title.getText().toString();
                String c=cont.getText().toString();

                if(t.isEmpty()|| c.isEmpty())
                {
                    Toast.makeText(editnote.this, "Enter all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DocumentReference ss=fs.collection("notes").document(fu.getUid()).collection("mynote").document(d.getStringExtra("NOTEID"));
                    Map<String,Object> n= new HashMap<>();
                    n.put("Title",t);
                    n.put("Content",c);
                    ss.set(n).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(editnote.this, "Sucessfully Updated", Toast.LENGTH_SHORT).show();
                           Intent cv=new Intent(getApplicationContext(),noteshome.class);
                            startActivity(cv);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(editnote.this, "Failed To Update", Toast.LENGTH_SHORT).show();
                            Intent cv=new Intent(getApplicationContext(),noteshome.class);
                            startActivity(cv);

                        }
                    });

                }


            }
        });




    }
}