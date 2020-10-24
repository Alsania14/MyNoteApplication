package id.alin_gotama.mynoteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import id.alin_gotama.mynoteapplication.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private static final int WAKTU = 3000;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent move = new Intent(MainActivity.this,AfterMainActivity.class);
                startActivity(move);
                finish();
            }
        },WAKTU);


    }
}