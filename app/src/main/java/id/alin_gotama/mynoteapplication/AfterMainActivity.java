package id.alin_gotama.mynoteapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;

import id.alin_gotama.mynoteapplication.adapter.CustomAdapter;
import id.alin_gotama.mynoteapplication.db.DatabaseHelper;
import id.alin_gotama.mynoteapplication.db.NoteHelper;
import id.alin_gotama.mynoteapplication.entity.Note;
import id.alin_gotama.mynoteapplication.animation.anim;

public class AfterMainActivity extends AppCompatActivity implements View.OnClickListener{
    public static int status = 1;

    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private NoteHelper noteHelper;
    private CustomAdapter customAdapter;

    private Button btnTambah;
    private Dialog dialog;

    private ArrayList<Note> notes = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_main);
        getSupportActionBar().hide();
        anim anim = new anim(this);
        anim.runFadeOutAnimation(R.id.MainView);

        this.btnTambah = findViewById(R.id.btnTambah);
        this.btnTambah.setOnClickListener(this);
        CustomScrollListener customScrollListener = new CustomScrollListener(this.btnTambah);

        this.recyclerView = findViewById(R.id.rvNote);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.recyclerView.addOnScrollListener(customScrollListener);

        this.noteHelper = new NoteHelper(this);

        noteHelper = new NoteHelper(this);
        noteHelper.open();
        notes = noteHelper.query();
        noteHelper.close();

        customAdapter = new CustomAdapter(notes,this);
        this.recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnTambah){
            Intent intent = new Intent(this,TambahActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void dialog(Note note,Context context){
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Hai User !");

        alertdialog.setMessage("yakin Menghapus ?")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteHelper.open();
                        noteHelper.delete(note.getId());
                        noteHelper.close();

                        Intent intent = new Intent(AfterMainActivity.this,AfterMainActivity.class);
                        anim anim = new anim(context);
                        anim.runFadeOutAnimation(R.id.MainView);

                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        alertdialog.create();
        alertdialog.show();
    }



}