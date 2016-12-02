package itp341.zhao.glarence.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by glarencezhao on 3/6/16.
 */
public class Room4 extends Activity {

    private RadioGroup schoolButtons;
    private Button hintButton;
    private Button submitButton;

    private String school="";

    public static final String SCHOOL = "School";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room4);

        initialize();
        listeners();
    }

    private void initialize(){

        schoolButtons = (RadioGroup) findViewById(R.id.schoolButtons);
        hintButton = (Button) findViewById(R.id.hintButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setEnabled(false);

    }

    private void listeners(){

        schoolButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.USCButton){

                    school = getResources().getString(R.string.USC);
                    submitButton.setEnabled(true);

                }

                if(checkedId == R.id.UCLAButton){

                    school = getResources().getString(R.string.UCLA);
                    submitButton.setEnabled(true);

                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.putExtra(SCHOOL, school);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Room4.this, R.string.hintString, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
