package com.example.mobile_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCalculateEmiBtnClick(View view) {
        //define input and output on view
        boolean err = false;
        EditText mortgageInput = findViewById(R.id.mortgageValue);
        EditText yearsInput = findViewById(R.id.yearsValue);
        EditText interestInput = findViewById(R.id.interestValue);
        TextView emi = findViewById(R.id.emiValue);

        //check if input is there before trying to calculate
        if (TextUtils.isEmpty(mortgageInput.getText())) {
            mortgageInput.setError("Mortgage is required");
            err = true;
        }
        if (TextUtils.isEmpty(yearsInput.getText())) {
            yearsInput.setError("Amortization period required");
            err = true;
        }
        if (TextUtils.isEmpty(interestInput.getText())) {
            interestInput.setError("Yearly Interest is required");
            err = true;
        }

        if (!err) {
            //convert input to doubles
            double mortgage = Double.parseDouble(mortgageInput.getText().toString());
            double years = Double.parseDouble(yearsInput.getText().toString());
            double interest = Double.parseDouble(interestInput.getText().toString());

            //display output
            emi.setText(calculateEmi(mortgage, years, interest));
        }
    }

    //calculate em
    private String calculateEmi(double mort, double yrs, double rate) {
        double months = yrs * 12, monthlyInterest=rate/(100*12);
        double ans = mort * monthlyInterest * Math.pow(1+monthlyInterest, months)/(Math.pow(1+monthlyInterest,months)-1);
        return "$ "+String.format("%.2f", ans);
    }
}