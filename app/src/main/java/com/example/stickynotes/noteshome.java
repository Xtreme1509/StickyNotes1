package com.example.stickynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class noteshome extends AppCompatActivity {
    FirebaseAuth fbb;
    FloatingActionButton add;
    FirebaseUser fu;
    FirebaseFirestore fs;
    FirestoreRecyclerAdapter<fmodel,NoteViewHolder> na;

    RecyclerView mrr;
    StaggeredGridLayoutManager sw;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("All Notes");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteshome);
        fbb=FirebaseAuth.getInstance();
        fu=FirebaseAuth.getInstance().getCurrentUser();
        fs=FirebaseFirestore.getInstance();
        add = findViewById(R.id.adddd);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa = new Intent(noteshome.this, adding.class);
                startActivity(aa);

            }
        });
        Query q= fs.collection("notes").document(fu.getUid()).collection("mynote").orderBy("Title",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<fmodel> all= new FirestoreRecyclerOptions.Builder<fmodel>().setQuery(q,fmodel.class).build();

        na=new FirestoreRecyclerAdapter<fmodel, NoteViewHolder>(all) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull fmodel model) {

                ImageView popup=holder.itemView.findViewById(R.id.menupopup);


                holder.notetitle.setText(model.getTitle());
                holder.notecont.setText(model.getContent());

                String doc= na.getSnapshots().getSnapshot(position).getId();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        Intent ab = new Intent(view.getContext(), NOTEDETAILS.class);
                        ab.putExtra("title",model.getTitle());
                       ab.putExtra("content",model.getContent());
                        ab.putExtra("NOTEID",doc);

                        view.getContext().startActivity(ab);

//                        Toast.makeText(noteshome.this, "CLICKING", Toast.LENGTH_SHORT).show();

                    }
                });

                popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu x= new PopupMenu(view.getContext(),view);
                        x.setGravity(Gravity.END);
                            x.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                                    Intent ab = new Intent(view.getContext(), editnote.class);
                                    ab.putExtra("title",model.getTitle());
                                    ab.putExtra("content",model.getContent());
                                    ab.putExtra("NOTEID", doc);
                                    view.getContext().startActivity(ab);
                                    return false;
                                }
                            });

                        x.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                                DocumentReference xx=fs.collection("notes").document(fu.getUid()).collection("mynote").document(doc);
                                xx.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(noteshome.this, "Deleted Sucessfully", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(noteshome.this, "Delete failed", Toast.LENGTH_SHORT).show();

                                    }
                                });


                                return false;
                            }
                        });


                        x.show();


                    }
                });
            }
            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout,parent,false);
                return new NoteViewHolder(view);
            }
        };


       mrr=findViewById(R.id.ddd);
       mrr.setHasFixedSize(true);
       sw=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
       mrr.setLayoutManager(sw);
       mrr.setAdapter(na);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menunote, menu);
        return true;

    }







    //                 MENU OPTIONS
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.imagescanner:
                Intent CCC=new Intent(noteshome.this,SCANNER_HOME.class);
                startActivity(CCC);
                break;

            case R.id.lot:
                Intent X=new Intent(noteshome.this,contact.class);
                startActivity(X);
                break;

            case R.id.lout:
                fbb.signOut();
                finish();
                Intent aqw=new Intent(noteshome.this,MainActivity.class);
                startActivity(aqw);
                break;

//---------------------COLORS-------------------------------------------------------------

            case R.id.bglr:
                View layout = findViewById(R.id.ddd);
                int LRED = getResources().getColor(R.color.lightred);
                layout.setBackgroundColor(LRED);
                break;

            case R.id.bglg:
                View layout1 = findViewById(R.id.ddd);
                int LGREEN = getResources().getColor(R.color.lightgreen);
                layout1.setBackgroundColor(LGREEN);
                break;


            case R.id.bglb:
                View layout2 = findViewById(R.id.ddd);
                int Lblue = getResources().getColor(R.color.lightblue);
                layout2.setBackgroundColor(Lblue);
                break;


            case R.id.orange:
                View layo = findViewById(R.id.ddd);
                int or= getResources().getColor(R.color.ORANGE);
                layo.setBackgroundColor(or);
                break;


            case R.id.pnk:
                View la1 = findViewById(R.id.ddd);
                int pnk= getResources().getColor(R.color.lightpink);
                la1.setBackgroundColor(pnk);
                break;

            case R.id.ylw:
                View t = findViewById(R.id.ddd);
                int tq= getResources().getColor(R.color.lightyellow);
                t.setBackgroundColor(tq);
                break;

            case R.id.blk:
                View ty = findViewById(R.id.ddd);
                int blk= getResources().getColor(R.color.black);
                ty.setBackgroundColor(blk);
                break;

            case R.id.pur:
                View dde = findViewById(R.id.ddd);
                int pur= getResources().getColor(R.color.purple_200);
                dde.setBackgroundColor(pur);
                break;

//    --------------------IMAGES----------------------------------------------------------------------

            case R.id.sm:
                View lay = findViewById(R.id.ddd);
                lay.setBackgroundResource(R.drawable.snowmn);
                break;

            case R.id.rd:
                View ii1 = findViewById(R.id.ddd);
                ii1.setBackgroundResource(R.drawable.road);
                break;

            case R.id.i2:
                View ii2 = findViewById(R.id.ddd);
                ii2.setBackgroundResource(R.drawable.mixed);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public class NoteViewHolder extends RecyclerView.ViewHolder
    {
        private TextView notetitle;
        private TextView notecont;
        LinearLayout mnote;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            notetitle=itemView.findViewById(R.id.notetitle);
            notecont=itemView.findViewById(R.id.rrtt);
            mnote=itemView.findViewById(R.id.note);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        na.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(na!=null)
        {
            na.stopListening();
        }
    }


    private void nameentry()
    {
        Intent intent= getIntent();
        if(intent.getExtras()!=null)
        {
            String e;
            e=intent.getStringExtra("EMAIL");
        }
    }
}

