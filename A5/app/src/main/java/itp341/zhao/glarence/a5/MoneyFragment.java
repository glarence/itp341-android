package itp341.zhao.glarence.a5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MoneyFragment extends Fragment {

    private EditText moneyInput;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private TextView resultDisplay;
    private Button convertButton;

    private double money;
    private double result;
    private int fromID;
    private int toID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        money = -1;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.money_fragment_layout, container, false);

        moneyInput = (EditText) v.findViewById(R.id.moneyInput);
        fromSpinner = (Spinner) v.findViewById(R.id.fromSpinner);
        fromSpinner.setSelection(0);
        toSpinner = (Spinner) v.findViewById(R.id.toSpinner);
        toSpinner.setSelection(0);
        resultDisplay = (TextView) v.findViewById(R.id.resultDisplay);
        convertButton = (Button) v.findViewById(R.id.convButton);

        listeners();

        fromID = fromSpinner.getSelectedItemPosition();
        toID = toSpinner.getSelectedItemPosition();

        return v;

    }

    private void listeners(){

        moneyInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().equals(""))
                 money = Double.parseDouble(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        convertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                update(fromID, toID);

            }

        });

        toSpinner.setOnItemSelectedListener(new spinnerListener());
        fromSpinner.setOnItemSelectedListener(new spinnerListener());

    }

    private class spinnerListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            int ID = parent.getId();

            if(ID == R.id.fromSpinner){

                fromID = fromSpinner.getSelectedItemPosition();

            }
            else{

                toID = toSpinner.getSelectedItemPosition();

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

    }

    private void update(int fromID, int toID){

        if(fromID != -1 && toID != -1 && money != -1){

            if(fromID == 0){


                if(toID == 0){

                    result = money;

                }
                if(toID == 1){

                    result = money*6.51;

                }
                if(toID == 2){

                    result = money*0.90;

                }

            }
            if(fromID == 1){


                if(toID == 0){

                    result = money*0.15;

                }
                if(toID == 1){

                    result = money;

                }
                if(toID == 2){

                    result = money*0.14;

                }

            }
            if(fromID == 2){


                if(toID == 0){

                    result = money*1.12;

                }
                if(toID == 1){

                    result = money*7.27;

                }
                if(toID == 2){

                    result = money;

                }

            }

            resultDisplay.setText(result + "");

        }

    }

}
