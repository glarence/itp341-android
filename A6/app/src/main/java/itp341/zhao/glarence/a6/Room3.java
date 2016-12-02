package itp341.zhao.glarence.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by glarencezhao on 3/5/16.
 */
public class Room3 extends Activity{

    private EditText nameField;
    private Button setChangesButton;

    private String name;

    public static final String NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room3);

        Intent i = getIntent();
        name = i.getStringExtra(NAME);

        initialize();
        listeners();

    }

    private void initialize(){

        nameField = (EditText) findViewById(R.id.nameField);
        setChangesButton = (Button) findViewById(R.id.setChangesButton);

    }

    private void listeners(){

        setChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.putExtra(NAME, name);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                name = s.toString();

            }
        });

    }

}
