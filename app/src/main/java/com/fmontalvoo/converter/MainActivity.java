package com.fmontalvoo.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumero;
    private Spinner spnInicio;
    private Spinner spnObjetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        txtNumero = findViewById(R.id.txtNumber);
        txtNumero.setInputType(InputType.TYPE_NULL);

        spnInicio = findViewById(R.id.spnInicio);
        String[] spnInicioValues = {getString(R.string.binary), getString(R.string.octal),
                getString(R.string.decimal), getString(R.string.hexadecimal)};
        spnInicio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spnInicioValues));
        spnInicio = findViewById(R.id.spnInicio);

        spnObjetivo = findViewById(R.id.spnObjetivo);
        String[] spnObjetivoValues = {getString(R.string.hexadecimal), getString(R.string.decimal),
                getString(R.string.octal), getString(R.string.binary)};
        spnObjetivo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spnObjetivoValues));
    }
}