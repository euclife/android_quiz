package com.ChandraRamdhanPurnama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ChandraRamdhanPurnama.classes.JenisAktifitas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    RadioGroup radioGroup;
    Spinner spiJenisAktfitas;
    String jenisKelaminSelected;
    Integer kkalJenisKelamin;
    EditText PersonName;
    EditText TinggiBadan;
    EditText TahunLahir;
    Button btnSubmit;
    JenisAktifitas JenisAktivitasSelected;
    double BBI = 0;
    double Umur = 0;
    double KKB = 0;
    double KKT = 0;
    double AF = 0;
    double FK = 0;
    double PresentaseFaktorUmur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonName = findViewById(R.id.PersonName);
        TinggiBadan= findViewById(R.id.TinggiBadan);
        TahunLahir = findViewById(R.id.TahunLahir);
        radioGroup = findViewById(R.id.rgJenisKelamin);
        btnSubmit = findViewById(R.id.btnSumbit);
        spiJenisAktfitas = findViewById(R.id.spiJenisAktfitas);
        JenisAktivitasSelected = new JenisAktifitas();
        final ArrayList<JenisAktifitas> dataArrayList = new ArrayList<>();

        dataArrayList.add(new JenisAktifitas("Aktifitas Ringan", (float) 0.1));
        dataArrayList.add(new JenisAktifitas("Aktifitas Sedang", (float) 0.2));
        dataArrayList.add(new JenisAktifitas("Aktifitas Berat", (float) 0.4));

        ArrayAdapter adapterJenisAktifitas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataArrayList);
        adapterJenisAktifitas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiJenisAktfitas.setAdapter(adapterJenisAktifitas);

        spiJenisAktfitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                JenisAktifitas jenisAktifitas = (JenisAktifitas) adapterView.getItemAtPosition(i);
                JenisAktivitasSelected.setPresentase(jenisAktifitas.getPresentase());
                JenisAktivitasSelected.setJenis(jenisAktifitas.getJenis());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioLaki) {
                    jenisKelaminSelected = "Laki-Laki";
                    kkalJenisKelamin = 30;
                } else {
                    jenisKelaminSelected = "Perempuan";
                    kkalJenisKelamin = 25;
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer TB =  Integer.parseInt(TinggiBadan.getText().toString()) -100;
                BBI = TB * 0.9;

                KKB = BBI * kkalJenisKelamin;


                AF = KKB * JenisAktivitasSelected.getPresentase();


                Umur = 2019 - Integer.parseInt(TahunLahir.getText().toString());

                if (Umur >= 0 && Umur <=39 ){
                    PresentaseFaktorUmur = 0;
                }else if (Umur >= 40 && Umur <=59 ){
                    PresentaseFaktorUmur = 0.05;
                }else if (Umur >= 60 && Umur <=69 ){
                    PresentaseFaktorUmur = 0.1;
                }else{
                    PresentaseFaktorUmur = 0.2;
                }

                FK = KKB * PresentaseFaktorUmur;

                KKT = KKB + AF - FK;

                Log.i(TAG, "TB = " + TB);
                Log.i(TAG, "BBI = " + BBI);
                Log.i(TAG, "AF = " + AF);
                Log.i(TAG, "UMur = " + Umur);
                Log.i(TAG, "PresenTaseFaktor = " + PresentaseFaktorUmur);
                Log.i(TAG, "FK = " + FK);
                Log.i(TAG, "KKT = " + KKT);

                Bundle bun = new Bundle();
                bun.putDouble("KKT", KKT);
                Intent intent = new Intent(MainActivity.this, Output.class);
                intent.putExtras(bun);
                startActivity(intent);

            }
        });
    }
}
