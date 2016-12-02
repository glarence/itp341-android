package itp341.zhao.glarence.a7;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup sizeGroup;
    private Spinner brewSpinner;
    private Switch sugarSwitch;
    private CheckBox milkCheckBox;
    private EditText instrEditText;

    private Button loadFavButton;
    private Button saveFavButton;
    private Button orderButton;
    private Button clearButton;
    private ButtonListener BL;

    private String _size;
    private String _brew;
    private Boolean _sugar;
    private Boolean _milk;
    private String _instr;

    private final String PREFERENCE_SIZE = "Preference";
    private final String PREFERENCE_BREW = "Brew";
    private final String PREFERENCE_SUGAR = "Sugar";
    private final String PREFERENCE_MILK = "Milk";
    private final String PREFERENCE_INSTR = "Instructions";

    private final String COFFEE_ORDER = "Coffee order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sizeGroup = (RadioGroup)findViewById(R.id.sizeButtonGroup);
        brewSpinner = (Spinner)findViewById(R.id.brewSpinner);
        sugarSwitch = (Switch)findViewById(R.id.sugarSwitch);
        milkCheckBox = (CheckBox)findViewById(R.id.milkCheckBox);
        instrEditText = (EditText)findViewById(R.id.instrTextField);

        loadFavButton = (Button)findViewById(R.id.loadFavButton);
        saveFavButton = (Button)findViewById(R.id.saveFavButton);
        orderButton = (Button)findViewById(R.id.orderButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        BL = new ButtonListener();
        listeners();

        _size = "";
        _brew = "";
        _sugar = false;
        _milk = false;
        _instr = "";

    }

    private void listeners(){

        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.smallRadioButton){

                    _size = "Small";

                }
                if(checkedId == R.id.mediumRadioButton){

                    _size = "Medium";

                }
                if(checkedId == R.id.largeRadioButton){

                    _size = "Large";

                }

            }
        });

        brewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                _brew = (String) parent.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sugarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                _sugar = isChecked;

            }
        });

        milkCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                _milk = isChecked;

            }
        });

        instrEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                _instr = s.toString();

            }
        });

        loadFavButton.setOnClickListener(BL);

        saveFavButton.setOnClickListener(BL);

        orderButton.setOnClickListener(BL);

        clearButton.setOnClickListener(BL);

    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            int id = v.getId();

            if(id == R.id.loadFavButton){

                Toast.makeText(MainActivity.this, R.string.loadToast, Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                _size = prefs.getString(PREFERENCE_SIZE, "");
                _brew = prefs.getString(PREFERENCE_BREW, "");
                _sugar = prefs.getBoolean(PREFERENCE_SUGAR, false);
                _milk = prefs.getBoolean(PREFERENCE_MILK, false);
                _instr = prefs.getString(PREFERENCE_INSTR, "");
                load();

            }
            if(id == R.id.saveFavButton){

                Toast.makeText(MainActivity.this, R.string.saveToast, Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefs.edit();
                prefEditor.putString(PREFERENCE_SIZE, _size);
                prefEditor.putString(PREFERENCE_BREW, _brew);
                prefEditor.putBoolean(PREFERENCE_SUGAR, _sugar);
                prefEditor.putBoolean(PREFERENCE_MILK, _milk);
                prefEditor.putString(PREFERENCE_INSTR, _instr);
                prefEditor.commit();

            }
            if(id == R.id.orderButton){

                if(!_size.equals("") && !_brew.equals("")){

                    CoffeeOrder order = new CoffeeOrder(_size, _brew, _instr, _sugar, _milk);

                    Bundle b = new Bundle();
                    b.putSerializable(COFFEE_ORDER, order);

                    Intent i = new Intent(getApplicationContext(), ViewOrder.class);
                    i.putExtras(b);
                    startActivityForResult(i, 0);

                }
                else{

                    Toast.makeText(MainActivity.this, R.string.orderErrorToast, Toast.LENGTH_SHORT).show();

                }

            }
            if(id == R.id.clearButton){

                clear();


            }

        }

    }

    private void clear(){

        sizeGroup.clearCheck();
        _size = "";
        brewSpinner.setSelection(0);
        _brew = "";
        sugarSwitch.setChecked(false);
        _sugar = false;
        milkCheckBox.setChecked(false);
        _milk = false;
        instrEditText.setText("");
        _instr = "";

    }

    private void load(){

        if(!_size.equals("")){

            if(_size.equals("Small")){

                sizeGroup.check(R.id.smallRadioButton);

            }
            if(_size.equals("Medium")){

                sizeGroup.check(R.id.mediumRadioButton);

            }
            if(_size.equals("Large")){

                sizeGroup.check(R.id.largeRadioButton);

            }

        }

        if(!_brew.equals("")){

            String[] brews = getResources().getStringArray(R.array.brew_type);

            if(_brew.equals("Chemex")){

                brewSpinner.setSelection(0);

            }
            if(_brew.equals("Cold Brew")){

                brewSpinner.setSelection(1);

            }
            if(_brew.equals("French Press")){

                brewSpinner.setSelection(2);

            }
            if(_brew.equals("Instant Mix")){

                brewSpinner.setSelection(3);

            }
            if(_brew.equals("Single Serve")){

                brewSpinner.setSelection(4);

            }
            if(_brew.equals("Standard Drip")){

                brewSpinner.setSelection(5);

            }
            if(_brew.equals("Cowboy")){

                brewSpinner.setSelection(6);

            }

        }

        sugarSwitch.setChecked(_sugar);
        milkCheckBox.setChecked(_milk);

        instrEditText.setText(_instr);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){

            Toast.makeText(MainActivity.this, R.string.confirmMessage, Toast.LENGTH_SHORT).show();
            clear();

        }
        if(resultCode == Activity.RESULT_CANCELED){

            Toast.makeText(MainActivity.this, R.string.cancelMessage, Toast.LENGTH_SHORT).show();

        }

    }
}
