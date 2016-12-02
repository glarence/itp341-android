package itp341.zhao.glarence.a5;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    Button tipCalcButton;
    Button unitConvButton;
    Button moneyExchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipCalcButton = (Button)findViewById(R.id.tipCalcButton);
        tipCalcButton.setOnClickListener(new buttonListener());
        unitConvButton = (Button)findViewById(R.id.unitConvButton);
        unitConvButton.setOnClickListener(new buttonListener());
        moneyExchButton = (Button)findViewById(R.id.moneyExchButton);
        moneyExchButton.setOnClickListener(new buttonListener());

    }

    private class buttonListener implements OnClickListener {

        @Override
        public void onClick(View v) {

            int id = v.getId();

            if(id == R.id.tipCalcButton){

                FragmentManager fm = getSupportFragmentManager();
                Fragment container = fm.findFragmentById(R.id.fragmentContainer);
                TipFragment tf = new TipFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, tf);
                ft.commit();

            }
            if(id == R.id.unitConvButton) {

                FragmentManager fm = getSupportFragmentManager();
                Fragment container = fm.findFragmentById(R.id.fragmentContainer);
                UnitFragment uf = new UnitFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, uf);
                ft.commit();

            }
            if(id == R.id.moneyExchButton) {

                FragmentManager fm = getSupportFragmentManager();
                Fragment container = fm.findFragmentById(R.id.fragmentContainer);
                MoneyFragment mf = new MoneyFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, mf);
                ft.commit();

            }

        }

    }
}
