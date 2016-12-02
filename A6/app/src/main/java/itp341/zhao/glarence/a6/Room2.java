package itp341.zhao.glarence.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by glarencezhao on 3/5/16.
 */
public class Room2 extends Activity{

    private Spinner sizeSpinner;
    private Button setChangesButton;

    private String size = "";

    public static final String selectedSize = "Selected Size";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room2);

        initialize();
        listeners();

    }

    private void initialize(){

        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        setChangesButton = (Button) findViewById(R.id.setChangesButton);

    }

    private void listeners(){

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = (String) sizeSpinner.getSelectedItem();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        setChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.putExtra(selectedSize, size);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

    }

}
