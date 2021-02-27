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

import com.fmontalvoo.converter.controller.ConverterController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNumero;
    private Spinner spnInicio;
    private Spinner spnObjetivo;
    private TextView txtResultado;
    private Button numbers[];
    private Button btnClear;
    private Button btnDelete;

    private ConverterController converterController;

    private int optionA;
    private int optionB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        converterController = new ConverterController();

        txtNumero = findViewById(R.id.txtNumber);
        txtNumero.setInputType(InputType.TYPE_NULL);

        spnInicio = findViewById(R.id.spnInicio);
        String[] spnInicioValues = {getString(R.string.binary), getString(R.string.octal),
                getString(R.string.decimal), getString(R.string.hexadecimal)};
        spnInicio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spnInicioValues));
        spnInicio = findViewById(R.id.spnInicio);
        spnInicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int a = optionA, b = -1;
                String txt = txtNumero.getText().toString();
                optionA = spnInicio.getSelectedItemPosition();
                switch (optionA) {
                    case 0:
                        binaryConfig();
                        b = 3;
                        break;
                    case 1:
                        octalConfig();
                        b = 2;
                        break;
                    case 2:
                        decimalConfig();
                        b = 1;
                        break;
                    case 3:
                        hexadecimalConfig();
                        b = 0;
                        break;
                }
                txtNumero.setText(converterController.convert(txt, options(a, b)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnObjetivo = findViewById(R.id.spnObjetivo);
        String[] spnObjetivoValues = {getString(R.string.hexadecimal), getString(R.string.decimal),
                getString(R.string.octal), getString(R.string.binary)};
        spnObjetivo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spnObjetivoValues));
        spnObjetivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                optionB = spnObjetivo.getSelectedItemPosition();
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtResultado = findViewById(R.id.txtResult);
        txtResultado.setTextIsSelectable(true);

        numbers = new Button[16];

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
        int len = txtNumero.length();
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
                if (len != 0) {
                    txtNumero.setText(txtNumero.getText().toString().substring(0, len - 1));
                    txtResultado.setText("");
                }
                break;
        }
        if (len >= 0) convert();
    }

    public void convert() {
        if (options(optionA, optionB) == -1)
            txtResultado.setText(txtNumero.getText());
        else
            txtResultado.setText(converterController.convert(txtNumero.getText().toString(), options(optionA, optionB)));
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

    public int options(int optionA, int optionB) {
        //Binario
        if (optionA == 0 && optionB == 0)
            return 1;
        if (optionA == 0 && optionB == 1)
            return 2;
        if (optionA == 0 && optionB == 2)
            return 3;

        //Octal
        if (optionA == 1 && optionB == 0)
            return 4;
        if (optionA == 1 && optionB == 1)
            return 5;
        if (optionA == 1 && optionB == 3)
            return 6;

        //Decimal
        if (optionA == 2 && optionB == 0)
            return 7;
        if (optionA == 2 && optionB == 2)
            return 8;
        if (optionA == 2 && optionB == 3)
            return 9;

        //Hexadecimal
        if (optionA == 3 && optionB == 1)
            return 10;
        if (optionA == 3 && optionB == 2)
            return 11;
        if (optionA == 3 && optionB == 3)
            return 12;

        return -1;
    }
}