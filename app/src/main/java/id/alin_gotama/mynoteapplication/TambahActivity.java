package id.alin_gotama.mynoteapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.alin_gotama.mynoteapplication.db.NoteHelper;
import id.alin_gotama.mynoteapplication.entity.Note;
import id.alin_gotama.mynoteapplication.animation.anim;

public class TambahActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etTambahTitle;
    private EditText etTambahDescription;
    private TextView tvTambahDate;

    private Button btnTambahCancel;
    private Button btnTambahSubmit;

    private NoteHelper noteHelper;

    private Calendar cal;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        runFadeOutAnimation();
        this.btnTambahCancel = findViewById(R.id.btnTambahCancel);
        this.btnTambahCancel.setOnClickListener(this);

        this.btnTambahSubmit = findViewById(R.id.btnTambahSubmit);
        this.btnTambahSubmit.setOnClickListener(this);

        this.etTambahDescription = findViewById(R.id.etTambahDescription);
        this.etTambahTitle = findViewById(R.id.etTambahTitle);
        this.tvTambahDate = findViewById(R.id.tvTambahDate);

        cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Makassar"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("dd/MM/YYYY");

        String localTime = date.format(currentLocalTime);
        this.tvTambahDate.setText(localTime);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnTambahCancel){
            Intent intent = new Intent(this,AfterMainActivity.class);
            runFadeOutAnimation();
            finish();
            startActivity(intent);
        }

        if(v.getId() == R.id.btnTambahSubmit){
            Note note = new Note();
            note.setTitle(this.etTambahTitle.getText().toString());
            note.setDescription(this.etTambahDescription.getText().toString());
            note.setDate(this.tvTambahDate.getText().toString());


            noteHelper = new NoteHelper(this);
            noteHelper.open();
            noteHelper.insert(note);
            noteHelper.close();

            Intent intent = new Intent(this,AfterMainActivity.class);
            runFadeOutAnimation();
            finish();
            startActivity(intent);
        }
    }

    private void runFadeInAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fadein);
        a.reset();
        RelativeLayout ll = (RelativeLayout) this.findViewById(R.id.TambahMainView);
        ll.clearAnimation();
        ll.startAnimation(a);
    }

    private void runFadeOutAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        a.reset();
        RelativeLayout ll = (RelativeLayout) this.findViewById(R.id.TambahMainView);
        ll.clearAnimation();
        ll.startAnimation(a);
    }
}