package itp341.zhao.glarence.a2;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import android.app.Activity;

public class MainActivity extends Activity {

    ImageView amerImg;
    ImageView chinImg;
    ImageView indImg;
    ImageView italImg;
    ImageView middImg;
    ImageView portImg;

    int amerCount;
    int chinCount;
    int indCount;
    int italCount;
    int middCount;
    int portCount;

    public static final String amer_count = "amerCount";
    public static final String chin_count = "chinCount";
    public static final String ind_count = "indCount";
    public static final String ital_count = "italCount";
    public static final String midd_count = "middCount";
    public static final String port_count = "portCount";

    ButtonListener bL;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(amer_count, amerCount);
        outState.putInt(chin_count, chinCount);
        outState.putInt(ind_count, indCount);
        outState.putInt(ital_count, italCount);
        outState.putInt(midd_count, middCount);
        outState.putInt(port_count, portCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        amerCount = savedInstanceState.getInt(amer_count);
        chinCount = savedInstanceState.getInt(chin_count);
        indCount = savedInstanceState.getInt(ind_count);
        italCount = savedInstanceState.getInt(ital_count);
        middCount = savedInstanceState.getInt(midd_count);
        portCount = savedInstanceState.getInt(port_count);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bL = new ButtonListener();

        amerImg = (ImageView) findViewById(R.id.americanImg);
        amerImg.setOnClickListener(bL);

        chinImg = (ImageView) findViewById(R.id.chineseImg);
        chinImg.setOnClickListener(bL);

        indImg = (ImageView) findViewById(R.id.indianImg);
        indImg.setOnClickListener(bL);

        italImg = (ImageView) findViewById(R.id.italianImg);
        italImg.setOnClickListener(bL);

        middImg = (ImageView) findViewById(R.id.middleEastImg);
        middImg.setOnClickListener(bL);

        portImg = (ImageView) findViewById(R.id.portugueseImg);
        portImg.setOnClickListener(bL);

        Glide.with(MainActivity.this).load(getResources().getString(R.string.american_url)).into(amerImg);
        Glide.with(MainActivity.this).load(getResources().getString(R.string.chinese_url)).into(chinImg);
        Glide.with(MainActivity.this).load(getResources().getString(R.string.indian_url)).into(indImg);
        Glide.with(MainActivity.this).load(getResources().getString(R.string.italian_url)).into(italImg);
        Glide.with(MainActivity.this).load(getResources().getString(R.string.middle_east_url)).into(middImg);
        Glide.with(MainActivity.this).load(getResources().getString(R.string.portuguese_url)).into(portImg);

        amerCount = 0;
        chinCount = 0;
        indCount = 0;
        italCount = 0;
        middCount = 0;
        portCount = 0;

    }

    private class ButtonListener implements OnClickListener{


        @Override
        public void onClick(View v) {
            int id = v.getId();

            String toastFormat = "You Liked %1$s %2$d Times";

            if(id == amerImg.getId()){

                amerCount += 1;

                TextView tv = (TextView) findViewById(R.id.americanText);
                String cuisine = tv.getText().toString();
                String toast = String.format(toastFormat, cuisine, amerCount);

                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();

                amerImg.setImageResource(R.drawable.american_clicked);

            }

            if(id == chinImg.getId()){

                chinCount += 1;

                TextView tv = (TextView) findViewById(R.id.chineseText);
                String cuisine = tv.getText().toString();
                String toast = String.format(toastFormat, cuisine, chinCount);

                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();

                chinImg.setImageResource(R.drawable.chinese_clicked);

            }

            if(id == indImg.getId()){

                indCount += 1;

                TextView tv = (TextView) findViewById(R.id.indianText);
                String cuisine = tv.getText().toString();
                String toast = String.format(toastFormat, cuisine, indCount);

                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();

                indImg.setImageResource(R.drawable.indian_clicked);

            }

            if(id == italImg.getId()){

                italCount += 1;

                TextView tv = (TextView) findViewById(R.id.italianText);
                String cuisine = tv.getText().toString();
                String toast = String.format(toastFormat, cuisine, italCount);

                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();

                italImg.setImageResource(R.drawable.italian_clicked);

            }

            if(id == middImg.getId()){

                middCount += 1;

                TextView tv = (TextView) findViewById(R.id.middleEastText);
                String cuisine = tv.getText().toString();
                String toast = String.format(toastFormat, cuisine, middCount);

                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();

                middImg.setImageResource(R.drawable.middle_east_clicked);

            }

            if(id == portImg.getId()){

                portCount += 1;

                TextView tv = (TextView) findViewById(R.id.portugueseText);
                String cuisine = tv.getText().toString();
                String toast = String.format(toastFormat, cuisine, portCount);

                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();

                portImg.setImageResource(R.drawable.portuguese_clicked);

            }

        }
    }


}
