package com.fmontalvoo.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNumero;
    private Spinner spnInicio;
    private Spinner spnObjetivo;
    private TextView txtResultado;
    private Button numbers[];
    private Button btnClear;
    private Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        numbers = new Button[16];

        txtNumero = findViewById(R.id.txtNumber);
        txtNumero.setInputType(InputType.TYPE_NULL);

        spnInicio = findViewById(R.id.spnInicio);
        String[] spnInicioValues = {getString(R.string.binary), getString(R.string.octal),
                getString(R.string.decimal), getString(R.string.hexadecimal)};
        spnInicio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spnInicioValues));
        spnInicio = findViewById(R.id.spnInicio);
        spnInicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int option = spnInicio.getSelectedItemPosition();
                if (option == 0)
                    binaryConfig();
                if (option == 1)
                    octalConfig();
                if (option == 2)
                    decimalConfig();
                if (option == 3)
                    hexadecimalConfig();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnObjetivo = findViewById(R.id.spnObjetivo);
        String[] spnObjetivoValues = {getString(R.string.hexadecimal), getString(R.string.decimal),
                getString(R.string.octal), getString(R.string.binary)};
        spnObjetivo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spnObjetivoValues));
        spnObjetivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtResultado = findViewById(R.id.txtResult);

        numbers[0] = findViewById(R.id.btnZero);
        numbers[1] = findViewById(R.id.btnOne);
        numbers[2] = findViewById(R.id.btnTwo);
        numbers[3] = findViewById(R.id.btnThree);
        numbers[4] = findViewById(R.id.btnFour);
        numbers[5] = findViewById(R.id.btnFive);
        numbers[6] = findViewById(R.id.btnSix);
        numbers[7] = findViewById(R.id.btnSeven);
        numbers[8] = findViewById(R.id.btnEight);
        numbers[9] = findViewById(R.id.btnNine);
        numbers[10] = findViewById(R.id.btnA);
        numbers[11] = findViewById(R.id.btnB);
        numbers[12] = findViewById(R.id.btnC);
        numbers[13] = findViewById(R.id.btnD);
        numbers[14] = findViewById(R.id.btnE);
        numbers[15] = findViewById(R.id.btnF);

        for (int i = 0; i < numbers.length; i++)
            numbers[i].setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnZero:
                txtNumero.append("0");
                break;
            case R.id.btnOne:
                txtNumero.append("1");
                break;
            case R.id.btnTwo:
                txtNumero.append("2");
                break;
            case R.id.btnThree:
                txtNumero.append("3");
                break;
            case R.id.btnFour:
                txtNumero.append("4");
                break;
            case R.id.btnFive:
                txtNumero.append("5");
                break;
            case R.id.btnSix:
                txtNumero.append("6");
                break;
            case R.id.btnSeven:
                txtNumero.append("7");
                break;
            case R.id.btnEight:
                txtNumero.append("8");
                break;
            case R.id.btnNine:
                txtNumero.append("9");
                break;

            case R.id.btnA:
                txtNumero.append("A");
                break;
            case R.id.btnB:
                txtNumero.append("B");
                break;
            case R.id.btnC:
                txtNumero.append("C");
                break;
            case R.id.btnD:
                txtNumero.append("D");
                break;
            case R.id.btnE:
                txtNumero.append("E");
                break;
            case R.id.btnF:
                txtNumero.append("F");
                break;

            case R.id.btnClear:
                txtNumero.setText("");
                txtResultado.setText("");
                break;
            case R.id.btnDelete:
                int len = txtNumero.length();
                if (len != 0) {
                    txtNumero.setText(txtNumero.getText().toString().substring(0, len - 1));
                    txtResultado.setText("");
                }
                break;
        }
    }

    public void binaryConfig() {
        activateAndDeactivateNumbers(2, numbers.length, false);
    }

    public void octalConfig() {
        activateAndDeactivateNumbers(0, 8, true);
        activateAndDeactivateNumbers(8, numbers.length, false);
    }

    public void decimalConfig() {
        activateAndDeactivateNumbers(0, 10, true);
        activateAndDeactivateNumbers(10, numbers.length, false);
    }

    public void hexadecimalConfig() {
        activateAndDeactivateNumbers(0, numbers.length, true);
    }

    public void activateAndDeactivateNumbers(int min, int max, boolean value) {
        for (int i = min; i < max; i++)
            numbers[i].setEnabled(value);
    }
}