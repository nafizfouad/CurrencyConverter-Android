package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText amountEditText;
    TextView hehe;
    private RadioGroup conversionDirectionRadioGroup;
    private Button convertButton;
    private TextView resultTextView;

    private double usdToBdtRate = 106;
    private double inrToBdtRate = 1.63;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        conversionDirectionRadioGroup = findViewById(R.id.conversionDirectionRadioGroup);
        convertButton = findViewById(R.id.convertButton);
        hehe  = (TextView) findViewById(R.id.cancelButton);
        resultTextView = findViewById(R.id.resultTextView);

        convertButton.setOnClickListener(this);
        hehe.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.convertButton) {
            convertCurrency();
        }

        if (v.getId() == R.id.cancelButton) {
            amountEditText.setText("");
        }
    }

    private void convertCurrency() {
        String amountString = amountEditText.getText().toString().trim();
        if (amountString.isEmpty()) {
            resultTextView.setText("Please enter an amount");
            return;
        }

        double amount = Double.parseDouble(amountString);
        int selectedRadioButtonId = conversionDirectionRadioGroup.getCheckedRadioButtonId();

        double convertedAmount;
        String resultCurrency;

        if (selectedRadioButtonId == R.id.usdToBdtRadioButton) {
            convertedAmount = amount * usdToBdtRate;
            resultCurrency = "BDT";
        } else if (selectedRadioButtonId == R.id.bdtToUsdRadioButton) {
            convertedAmount = amount / usdToBdtRate;
            resultCurrency = "USD";
        } else if (selectedRadioButtonId == R.id.bdtToInrRadioButton) {
            convertedAmount = amount / inrToBdtRate;
            resultCurrency = "INR";
        } else if (selectedRadioButtonId == R.id.inrToBdtRadioButton) {
            convertedAmount = amount * inrToBdtRate;
            resultCurrency = "BDT";
        } else {
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String result = decimalFormat.format(convertedAmount) + " " + resultCurrency;
        resultTextView.setText(result);
    }

}
