package com.jansipos.percentagecalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class PercentageFragment extends Fragment {

    private TextView finalPriceView;
    private TextView percentageView;

    private EditText initialPriceEdit;
    private SeekBar percentageBar;

    private int percent;
    private double initialPrice;
    private double finalPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, parent, false);

        finalPriceView = (TextView) v.findViewById(R.id.final_price_text_view);
        percentageView = (TextView) v.findViewById(R.id.percentage_text_view);

        initialPriceEdit = (EditText) v.findViewById(R.id.initial_price);
        percentageBar = (SeekBar) v.findViewById(R.id.seek_bar);

        percentageView.setText("20 %");

        percent = 20;
        initialPrice = 0;
        finalPrice = 0;

        initialPriceEdit.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String currPrice = initialPriceEdit.getText().toString();

                if (currPrice.equals("")) {
                    initialPrice = 0;
                    finalPriceView.setText("0");
                } else {
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

        return v;
    }

    private void calculateAndSetFinalPrice() {

        finalPrice = initialPrice - (((double)percent / 100) * initialPrice);

        finalPriceView.setText(String.format("%.2f", finalPrice));
    }

}
