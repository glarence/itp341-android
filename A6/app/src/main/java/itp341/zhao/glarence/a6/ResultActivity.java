package itp341.zhao.glarence.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by glarencezhao on 3/5/16.
 */
public class ResultActivity extends Activity{

    private TextView resultTextView;
    private boolean puzzlesCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent i = getIntent();
        puzzlesCompleted = i.getBooleanExtra(MainActivity.GAMERESULT, false);

        initialize();

    }

    private void initialize(){

        resultTextView = (TextView) findViewById(R.id
                .resultTextView);

        if(puzzlesCompleted)
            resultTextView.setText(getResources().getString(R.string.winString));
        else
            resultTextView.setText(getResources().getString(R.string.loseString));

    }

    @Override
    public void onBackPressed(){

        startActivity(new Intent(this, MainActivity.class));

    }
}
