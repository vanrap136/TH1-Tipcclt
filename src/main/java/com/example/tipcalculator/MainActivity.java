package com.example.tipcalculator;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.text.Editable;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billamount = 0.0;
    private double percent = 0.15;
    private TextView  amountTextView;
    private TextView  percentTextView;
    private TextView  tipTextView;
    private TextView  totalTextView;
    private SeekBar percentSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            amountTextView = (TextView) findViewById(R.id.amountTextView);
            percentTextView = (TextView) findViewById(R.id.percentTextView);
            tipTextView = (TextView) findViewById(R.id.tipTextView);
            totalTextView = (TextView) findViewById(R.id.totalTextView);
            percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
            tipTextView.setText(currencyFormat.format( 0 ));
            totalTextView.setText(currencyFormat.format( 0 ));

        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);




    }
    private final SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    percent = progress/100.0;
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billamount = Double.parseDouble(s.toString())/100.0;
                amountTextView.setText(currencyFormat.format(billamount));
            }
            catch(NumberFormatException e){
                amountTextView.setText("");
                billamount = 0.0;

            }
            calculate();

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void calculate(){
        percentTextView.setText(percentFormat.format(percent));

        double tip = billamount*percent;
        double total = billamount+tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));

    }


}
