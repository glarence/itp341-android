package itp341.zhao.glarence.a4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        billText = (EditText) findViewById(R.id.billInput);
        pSlider = (SeekBar) findViewById(R.id.percentSlider);
        percentDisplay = (TextView) findViewById(R.id.percentAmount);
        tipDisplay = (TextView) findViewById(R.id.tipDisplay);
        totalDisplay = (TextView) findViewById(R.id.totalDisplay);
        splitBill = (Spinner) findViewById(R.id.billSplit);
        perPersonDisplay = (TextView) findViewById(R.id.perPersonDisplay);
        perPersonLabel = (TextView) findViewById(R.id.perPersonLabel);
        perPersonDisplay.setVisibility(View.INVISIBLE);
        perPersonLabel.setVisibility(View.INVISIBLE);

        listeners();

        percentAmount = 0;
        percentDisplay.setText(percentAmount + "%");
        billAmount = 0.00;
        tipAmount = 0.00;
        tipDisplay.setText(new DecimalFormat("0.00").format(tipAmount));
        totalAmount = 0.00;
        totalDisplay.setText(new DecimalFormat("0.00").format(totalAmount));
        perPersonAmount = 0.00;
        perPersonDisplay.setText(new DecimalFormat("0.00").format(perPersonAmount));

        splitBillOption = (String)splitBill.getItemAtPosition(0);

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
