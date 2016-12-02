package itp341.zhao.glarence.a5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TipFragment extends Fragment {

    private EditText billText;
    private SeekBar pSlider;
    private TextView percentDisplay;
    private TextView tipDisplay;
    private TextView totalDisplay;
    private Spinner splitBill;
    private TextView perPersonDisplay;
    private TextView perPersonLabel;

    private double billAmount;
    private int percentAmount;
    private double tipAmount;
    private double totalAmount;
    private double perPersonAmount;

    private String splitBillOption;

    private static final String noOption = "No";
    private static final String twOption = "2 Ways";
    private static final String thwption = "3 Ways";
    private static final String fwOption = "4 Ways";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        percentAmount = 0;
        billAmount = 0.00;
        tipAmount = 0.00;
        totalAmount = 0.00;
        perPersonAmount = 0.00;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tip_fragment_layout, container, false);

        billText = (EditText) v.findViewById(R.id.billInput);
        pSlider = (SeekBar) v.findViewById(R.id.percentSlider);
        percentDisplay = (TextView) v.findViewById(R.id.percentAmount);
        tipDisplay = (TextView) v.findViewById(R.id.tipDisplay);
        totalDisplay = (TextView) v.findViewById(R.id.totalDisplay);
        splitBill = (Spinner) v.findViewById(R.id.billSplit);
        perPersonDisplay = (TextView) v.findViewById(R.id.perPersonDisplay);
        perPersonLabel = (TextView) v.findViewById(R.id.perPersonLabel);
        perPersonDisplay.setVisibility(View.INVISIBLE);
        perPersonLabel.setVisibility(View.INVISIBLE);

        percentDisplay.setText(percentAmount + "%");
        tipDisplay.setText(new DecimalFormat("0.00").format(tipAmount));
        totalDisplay.setText(new DecimalFormat("0.00").format(totalAmount));
        perPersonDisplay.setText(new DecimalFormat("0.00").format(perPersonAmount));

        splitBillOption = (String)splitBill.getItemAtPosition(0);

        listeners();

        return v;
    }

    private void listeners(){

        billText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(billText.getText().toString().matches(""))
                    billAmount = 0.00;
                else
                    billAmount = Double.parseDouble(billText.getText().toString());

                updateTip();
                updateTotal();
                updatePerPerson(splitBillOption);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                percentAmount = pSlider.getProgress();
                percentDisplay.setText(percentAmount + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                updateTotal();
                updateTip();
                updatePerPerson(splitBillOption);

            }

        });

        splitBill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                splitBillOption = (String) splitBill.getSelectedItem();
                updatePerPerson(splitBillOption);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });

    }

    private void updateTip(){

        tipAmount = billAmount * (percentAmount/100.0);
        tipDisplay.setText(new DecimalFormat("0.00").format(tipAmount));

    }

    private void updateTotal(){

        totalAmount = billAmount + tipAmount;
        totalDisplay.setText(new DecimalFormat("0.00").format(totalAmount));

    }

    private void updatePerPerson(String option){

        switch(option) {
            case noOption:
                perPersonDisplay.setVisibility(View.INVISIBLE);
                perPersonLabel.setVisibility(View.INVISIBLE);
                break;
            case twOption:
                perPersonAmount = totalAmount/2;
                perPersonDisplay.setText(new DecimalFormat("0.00").format(perPersonAmount));
                perPersonDisplay.setVisibility(View.VISIBLE);
                perPersonLabel.setVisibility(View.VISIBLE);
                break;
            case thwption:
                perPersonAmount = totalAmount/3;
                perPersonDisplay.setText(new DecimalFormat("0.00").format(perPersonAmount));
                perPersonDisplay.setVisibility(View.VISIBLE);
                perPersonLabel.setVisibility(View.VISIBLE);
                break;
            case fwOption:
                perPersonAmount = totalAmount/4;
                perPersonDisplay.setText(new DecimalFormat("0.00").format(perPersonAmount));
                perPersonDisplay.setVisibility(View.VISIBLE);
                perPersonLabel.setVisibility(View.VISIBLE);
                break;
        }

    }

}
