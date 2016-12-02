package itp341.zhao.glarence.a5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

public class UnitFragment extends Fragment {

    private EditText measureInput;
    private RadioGroup fromRadioGroup;
    private RadioGroup toRadioGroup;
    private TextView resultDisplay;
    private Button convertButton;

    private int fromID;
    private int toID;
    private double measurement;
    private double result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fromID = -1;
        toID = -1;
        measurement = -1;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.unit_fragment_layout, container, false);

        measureInput = (EditText) v.findViewById(R.id.measureInput);
        fromRadioGroup = (RadioGroup) v.findViewById(R.id.fromRadioGroup);
        toRadioGroup = (RadioGroup) v.findViewById(R.id.toRadioGroup);
        resultDisplay = (TextView) v.findViewById(R.id.resultDisplay);
        convertButton = (Button) v.findViewById(R.id.convButton);

        listeners();

        return v;

    }

    private void listeners(){

        measureInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().equals(""))
                    measurement = Double.parseDouble(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        fromRadioGroup.setOnCheckedChangeListener(new RadioListener());

        toRadioGroup.setOnCheckedChangeListener(new RadioListener());

        convertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                update(fromID, toID);

            }

        });

    }

    private class RadioListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            int groupID = group.getId();

            if(groupID == R.id.fromRadioGroup){

                fromID = checkedId;

            }
            else{

                toID = checkedId;

            }

        }

    }

    private void update(int fromID, int toID){

        if(fromID != -1 && toID != -1 && measurement != -1){

            if(fromID == R.id.fromCentimetersButton){

                if(toID == R.id.toCentimetersButton){

                    result = measurement;

                }
                if(toID == R.id.toMetersButton){

                    result = measurement*0.01;

                }
                if(toID == R.id.toFeetButton){

                    result = measurement*0.0328;

                }
                if(toID == R.id.toMilesButton){

                    result = measurement*(6.21*Math.pow(10, -6));

                }
                if(toID == R.id.toKilometersButton){

                    result = measurement*0.00001;

                }

            }
            if(fromID == R.id.fromMetersButton){

                if(toID == R.id.toCentimetersButton){

                    result = measurement*100;

                }
                if(toID == R.id.toMetersButton){

                    result = measurement;

                }
                if(toID == R.id.toFeetButton){

                    result = measurement*3.2808;

                }
                if(toID == R.id.toMilesButton){

                    result = measurement*(6.21*Math.pow(10, -4));

                }
                if(toID == R.id.toKilometersButton){

                    result = measurement*0.01;

                }

            }
            if(fromID == R.id.fromFeetButton){

                if(toID == R.id.toCentimetersButton){

                    result = measurement*30.48;

                }
                if(toID == R.id.toMetersButton){

                    result = measurement*0.3048;

                }
                if(toID == R.id.toFeetButton){

                    result = measurement;

                }
                if(toID == R.id.toMilesButton){

                    result = measurement*(1.89*Math.pow(10, -4));

                }
                if(toID == R.id.toKilometersButton){

                    result = measurement*(3.04*Math.pow(10, -4));

                }

            }
            if(fromID == R.id.fromMilesButton){

                if(toID == R.id.toCentimetersButton){

                    result = measurement*160934;

                }
                if(toID == R.id.toMetersButton){

                    result = measurement*1609.34;

                }
                if(toID == R.id.toFeetButton){

                    result = measurement*5280;

                }
                if(toID == R.id.toMilesButton){

                    result = measurement;

                }
                if(toID == R.id.toKilometersButton){

                    result = measurement*1.60934;

                }

            }
            if(fromID == R.id.fromKilometersButton){

                if(toID == R.id.toCentimetersButton){

                    result = measurement*(1*Math.pow(10, -5));

                }
                if(toID == R.id.toMetersButton){

                    result = measurement*1000;

                }
                if(toID == R.id.toFeetButton){

                    result = measurement*3280.84;

                }
                if(toID == R.id.toMilesButton){

                    result = measurement*0.62137;

                }
                if(toID == R.id.toKilometersButton){

                    result = measurement;

                }

            }

            resultDisplay.setText(result + "");

        }

    }

}
