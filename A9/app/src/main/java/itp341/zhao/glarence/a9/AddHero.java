package itp341.zhao.glarence.a9;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

import itp341.zhao.glarence.a9.database.DbSchema;

/**
 * Created by glarencezhao on 4/18/16.
 */
public class AddHero extends AppCompatActivity{

    private EditText heroNameET;
    private Spinner firstPowerSpinner;
    private Spinner secondPowerSpinner;
    private Button submitHeroButton;

    private String heroName = "";
    private String firstPower = "";
    private String secondPower = "";

    public AddHero(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);

        heroNameET = (EditText) findViewById(R.id.heroNameET);
        firstPowerSpinner = (Spinner) findViewById(R.id.firstPowerSpinner);
        secondPowerSpinner = (Spinner) findViewById(R.id.secondPowerSpinner);
        submitHeroButton = (Button) findViewById(R.id.submitHeroButton);

        populate();
        listeners();
    }

    private void listeners(){
        heroNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                heroName = s.toString();
            }
        });
        firstPowerSpinner.setOnItemSelectedListener(new SpinnerListener());
        secondPowerSpinner.setOnItemSelectedListener(new SpinnerListener());
        submitHeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHero();
                setResult(0);
                finish();
            }
        });
    }

    private void populate(){
        Cursor powerCursor = BattleSingleton.getInstance(getApplicationContext()).getUniquePowers();
        ArrayList<String> powers = new ArrayList<String>();
        while(powerCursor.moveToNext()){
            String power = powerCursor.getString(DbSchema.TABLE_POWERS.COLUMN_OWN_POWER);
            powers.add(power);
        }
        ArrayAdapter<String> powerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, powers);
        firstPowerSpinner.setAdapter(powerAdapter);
        secondPowerSpinner.setAdapter(powerAdapter);
    }

    class SpinnerListener implements AdapterView.OnItemSelectedListener{

        private SpinnerListener(){}

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;
            if(spinner.getId() == R.id.firstPowerSpinner){
                firstPower = (String) spinner.getSelectedItem();
            }
            else if(spinner.getId() == R.id.secondPowerSpinner){
                secondPower = (String) spinner.getSelectedItem();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private void addHero(){
        if(!heroName.isEmpty()){
            Hero newHero = new Hero();
            newHero.setName(heroName);
            newHero.setFirstPower(firstPower);
            newHero.setSecondPower(secondPower);
            newHero.setHealth(5);
            newHero.setNumWins(0);
            newHero.setNumLosses(0);
            newHero.setNumTies(0);
            BattleSingleton.getInstance(getApplicationContext()).addHero(newHero);
        }
    }
}
