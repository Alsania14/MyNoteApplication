package id.alin_gotama.mynoteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.alin_gotama.mynoteapplication.db.DatabaseContract;
import id.alin_gotama.mynoteapplication.db.NoteHelper;
import id.alin_gotama.mynoteapplication.entity.Note;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDetailCancel;
    private Button btnDetailSubmit;

    private EditText etDetailTitle;
    private EditText etDetailDescription;
    private TextView tvDetailDate;

    private NoteHelper noteHelper;
    private Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        btnDetailCancel = findViewById(R.id.btnDetailCancel);
        btnDetailCancel.setOnClickListener(this);
        btnDetailSubmit = findViewById(R.id.btnDetailSubmit);
        btnDetailSubmit.setOnClickListener(this);


        etDetailTitle = findViewById(R.id.etDetailTitle);
        etDetailDescription = findViewById(R.id.etDetailDescription);
        tvDetailDate = findViewById(R.id.tvDetailDate);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        note = bundle.getParcelable("note");

        this.tvDetailDate.setText(note.getDate());
        this.etDetailDescription.setText(note.getDescription());
        this.etDetailTitle.setText(note.getTitle());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,AfterMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnDetailCancel){
            Intent intent = new Intent(this,AfterMainActivity.class);
            startActivity(intent);
            finish();
        }

        if(v.getId() == R.id.btnDetailSubmit){
            note.setTitle(this.etDetailTitle.getText().toString());
            note.setDescription(this.etDetailDescription.getText().toString());
            noteHelper = new NoteHelper(this);
            noteHelper.open();
            noteHelper.update(this.note);
            noteHelper.close();

            Intent intent = new Intent(this,AfterMainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}