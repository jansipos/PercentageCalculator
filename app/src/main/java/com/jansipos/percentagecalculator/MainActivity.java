package com.jansipos.percentagecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private TextView finalPriceView;
    private TextView percentageView;

    private EditText initialPriceEdit;
    private SeekBar percentageBar;

    private int percent;
    private double initialPrice;
    private double finalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finalPriceView = (TextView) findViewById(R.id.final_price_text_view);
        percentageView = (TextView) findViewById(R.id.percentage_text_view);

        initialPriceEdit = (EditText) findViewById(R.id.initial_price);
        percentageBar = (SeekBar) findViewById(R.id.seek_bar);

        percentageView.setText("20 %");

        percent = 20;
        initialPrice = 0;
        finalPrice = 0;

        initialPriceEdit.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String currPrice = initialPriceEdit.getText().toString();

                if (currPrice.equals("")){
                    initialPrice = 0;
                    finalPriceView.setText("0");
                }
                else {
                    initialPrice = Double.parseDouble(currPrice);
                    calculateAndSetFinalPrice();
                }
            }
        });

        percentageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
                finalPriceView.setText("---");
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                percent = progress * 5;
                percentageView.setText(String.valueOf(percent) + " %");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                String currPrice = initialPriceEdit.getText().toString();

                if (!currPrice.equals("")) {

                    initialPrice = Double.parseDouble(initialPriceEdit.getText().toString());
                }

                if (initialPrice != 0) {
                    calculateAndSetFinalPrice();
                }
            }

        });

    }

    private void calculateAndSetFinalPrice() {

        finalPrice = initialPrice - (((double)percent / 100) * initialPrice);

        finalPriceView.setText(String.format("%.2f", finalPrice));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
