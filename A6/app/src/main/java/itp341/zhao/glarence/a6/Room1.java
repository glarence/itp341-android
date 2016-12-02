package itp341.zhao.glarence.a6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

/**
 * Created by glarencezhao on 3/5/16.
 */
public class Room1 extends Activity{

    public static final String R_VALUE = "RVALUE";
    public static final String G_VALUE = "GVALUE";
    public static final String B_VALUE = "BVALUE";

    private TextView RValueDisplay;
    private TextView GValueDisplay;
    private TextView BValueDisplay;

    private SeekBar RSlider;
    private SeekBar GSlider;
    private SeekBar BSlider;

    private TextView colorDisplay;

    private Button setChangesButton;

    private int RValue;
    private int GValue;
    private int BValue;

    private int numOfPuzzlesCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room1);

        Intent i = getIntent();
        RValue = i.getIntExtra(R_VALUE, 0);
        GValue = i.getIntExtra(G_VALUE, 0);
        BValue = i.getIntExtra(B_VALUE, 0);
        numOfPuzzlesCompleted = i.getIntExtra(MainActivity.puzzlesCompleted, 0);

        initialize();
        listeners();

    }

    private void initialize(){

        RValueDisplay = (TextView) findViewById(R.id.RValueDisplay);
        RValueDisplay.setText(String.valueOf(RValue));
        GValueDisplay = (TextView) findViewById(R.id.GValueDisplay);
        GValueDisplay.setText(String.valueOf(GValue));
        BValueDisplay = (TextView) findViewById(R.id.BValueDisplay);
        BValueDisplay.setText(String.valueOf(BValue));

        RSlider = (SeekBar) findViewById(R.id.RSlider);
        RSlider.setProgress(RValue);
        GSlider = (SeekBar) findViewById(R.id.GSlider);
        GSlider.setProgress(GValue);
        BSlider = (SeekBar) findViewById(R.id.BSlider);
        BSlider.setProgress(BValue);

        colorDisplay = (TextView) findViewById(R.id.colorDisplay);
        colorDisplay.setBackgroundColor(Color.rgb(RValue, GValue, BValue));

        setChangesButton = (Button) findViewById(R.id.setChangesButton);

    }

    private void listeners(){

        RSlider.setOnSeekBarChangeListener(new SliderListener());
        GSlider.setOnSeekBarChangeListener(new SliderListener());
        BSlider.setOnSeekBarChangeListener(new SliderListener());

        setChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();

                i.putExtra(Room1.R_VALUE, RValue);
                i.putExtra(Room1.G_VALUE, GValue);
                i.putExtra(Room1.B_VALUE, BValue);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

    }

    class SliderListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            int id = seekBar.getId();

            if(id == R.id.RSlider){

                RValue = progress;
                RValueDisplay.setText(String.valueOf(RValue));

            }

            if(id == R.id.GSlider){

                GValue = progress;
                GValueDisplay.setText(String.valueOf(GValue));

            }

            if(id == R.id.BSlider){

                BValue = progress;
                BValueDisplay.setText(String.valueOf(BValue));

            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            colorDisplay.setBackgroundColor(Color.rgb(RValue, GValue, BValue));

        }
    }

}
