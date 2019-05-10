package com.ChandraRamdhanPurnama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Output extends AppCompatActivity {

    private TextView KKTz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        Bundle extras = getIntent().getExtras();

        Double KKTOT = extras.getDouble("KKT");
        KKTz = findViewById(R.id.outputKKT);

        Log.i("Logh Output", "KKT = " + KKTOT);

        KKTz.setText(String.valueOf(KKTOT));
    }
}
