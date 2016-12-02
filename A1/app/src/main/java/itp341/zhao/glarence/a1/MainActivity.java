package itp341.zhao.glarence.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button _coffeeButton;
    private Button _teaButton;
    private ButtonListener cBL;
    private ButtonListener tBL;

    private int coffeeCount;
    private int teaCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cBL = new ButtonListener();
        tBL = new ButtonListener();

        _coffeeButton = (Button) findViewById(R.id.coffeeButton);
        _coffeeButton.setOnClickListener(cBL);
        _teaButton = (Button) findViewById(R.id.teaButton);
        _teaButton.setOnClickListener(tBL);

        coffeeCount = 0;
        teaCount = 0;

    }

    private class ButtonListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.coffeeButton){

                coffeeCount += 1;
                String coffeeFormat = getResources().getString(R.string.coffeeOrder);
                String coffeeMsg = String.format(coffeeFormat, coffeeCount);

                Toast.makeText(MainActivity.this, coffeeMsg, Toast.LENGTH_SHORT).show();

            }
            if(id == R.id.teaButton){

                teaCount += 1;
                String teaFormat = getResources().getString(R.string.teaOrder);
                String teaMsg = String.format(teaFormat, teaCount);

                Toast.makeText(MainActivity.this, teaMsg, Toast.LENGTH_SHORT).show();

            }

        }
    }
}
