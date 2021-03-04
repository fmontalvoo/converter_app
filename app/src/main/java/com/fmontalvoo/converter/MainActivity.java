package com.fmontalvoo.converter;

import androidx.appcompat.app.AppCompatActivity;

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

    private EditText txtNumber;
    private Spinner spnBase;
    private Spinner spnGoal;
    private TextView txtResult;
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

        txtNumber = findViewById(R.id.txtNumber);
        txtNumber.setInputType(InputType.TYPE_NULL);

        spnBase = findViewById(R.id.spnBase);
        String[] spnBaseValues = {getString(R.string.binary), getString(R.string.octal),
                getString(R.string.decimal), getString(R.string.hexadecimal)};
        spnBase.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spnBaseValues));
        spnBase = findViewById(R.id.spnBase);
        spnBase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int a = optionA, b = -1;
                String txt = txtNumber.getText().toString();
                optionA = spnBase.getSelectedItemPosition();
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
                txtNumber.setText(converterController.convert(txt, options(a, b)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnGoal = findViewById(R.id.spnGoal);
        String[] spnGoalValues = {getString(R.string.hexadecimal), getString(R.string.decimal),
                getString(R.string.octal), getString(R.string.binary)};
        spnGoal.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spnGoalValues));
        spnGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                optionB = spnGoal.getSelectedItemPosition();
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtResult = findViewById(R.id.txtResult);
        txtResult.setTextIsSelectable(true);

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
        int len = txtNumber.length();
        switch (v.getId()) {
            case R.id.btnZero:
                txtNumber.append(getString(R.string.btn_0));
                break;
            case R.id.btnOne:
                txtNumber.append(getString(R.string.btn_1));
                break;
            case R.id.btnTwo:
                txtNumber.append(getString(R.string.btn_2));
                break;
            case R.id.btnThree:
                txtNumber.append(getString(R.string.btn_3));
                break;
            case R.id.btnFour:
                txtNumber.append(getString(R.string.btn_4));
                break;
            case R.id.btnFive:
                txtNumber.append(getString(R.string.btn_5));
                break;
            case R.id.btnSix:
                txtNumber.append(getString(R.string.btn_6));
                break;
            case R.id.btnSeven:
                txtNumber.append(getString(R.string.btn_7));
                break;
            case R.id.btnEight:
                txtNumber.append(getString(R.string.btn_8));
                break;
            case R.id.btnNine:
                txtNumber.append(getString(R.string.btn_9));
                break;

            case R.id.btnA:
                txtNumber.append(getString(R.string.btn_a));
                break;
            case R.id.btnB:
                txtNumber.append(getString(R.string.btn_b));
                break;
            case R.id.btnC:
                txtNumber.append(getString(R.string.btn_c));
                break;
            case R.id.btnD:
                txtNumber.append(getString(R.string.btn_d));
                break;
            case R.id.btnE:
                txtNumber.append(getString(R.string.btn_e));
                break;
            case R.id.btnF:
                txtNumber.append(getString(R.string.btn_f));
                break;

            case R.id.btnClear:
                txtNumber.setText("");
                txtResult.setText("");
                break;
            case R.id.btnDelete:
                if (len != 0) {
                    txtNumber.setText(txtNumber.getText().toString().substring(0, len - 1));
                    txtResult.setText("");
                }
                break;
        }
        convert();
    }

    public void convert() {
        if (options(optionA, optionB) == -1)
            txtResult.setText(txtNumber.getText());
        else
            txtResult.setText(converterController.convert(txtNumber.getText().toString(), options(optionA, optionB)));
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