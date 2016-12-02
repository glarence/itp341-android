package itp341.zhao.glarence.a7;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by glarencezhao on 3/24/16.
 */
public class ViewOrder extends AppCompatActivity {

    private TextView sizeTextView;
    private TextView brewTextView;
    private TextView milkTextView;
    private TextView sugarTextView;
    private TextView instrTextView;

    private Button confirmButton;
    private Button cancelButton;

    private ButtonListener BL;

    private final String COFFEE_ORDER = "Coffee order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order);

        sizeTextView = (TextView) findViewById(R.id.sizeDisplay);
        brewTextView = (TextView) findViewById(R.id.brewDisplay);
        milkTextView = (TextView) findViewById(R.id.milkDisplay);
        sugarTextView = (TextView) findViewById(R.id.sugarDisplay);
        instrTextView = (TextView) findViewById(R.id.instrDisplay);

        confirmButton = (Button) findViewById(R.id.confirmButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        BL = new ButtonListener();
        listeners();

        Bundle b = this.getIntent().getExtras();
        if(b != null){

            CoffeeOrder order = (CoffeeOrder)b.getSerializable(COFFEE_ORDER);
            sizeTextView.setText(order.getSize());
            brewTextView.setText(order.getBrew());

            if(order.isMilk())
                milkTextView.setText(getString(R.string.yesMilk));
            else
                milkTextView.setText(getString(R.string.noMilk));

            if(order.isSugar())
                sugarTextView.setText(getString(R.string.yesSugar));
            else
                sugarTextView.setText(getString(R.string.noSugar));

            instrTextView.setText(order.getInstr());

        }

    }

    private void listeners(){

        confirmButton.setOnClickListener(BL);
        cancelButton.setOnClickListener(BL);

    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            int id = v.getId();

            if(id == R.id.confirmButton){

                setResult(Activity.RESULT_OK);

                finish();

            }
            if(id == R.id.cancelButton){

                setResult(Activity.RESULT_CANCELED);
                finish();

            }

        }
    }
}
