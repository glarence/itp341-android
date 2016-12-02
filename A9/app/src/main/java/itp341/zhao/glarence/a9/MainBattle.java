package itp341.zhao.glarence.a9;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import itp341.zhao.glarence.a9.database.DbSchema;

public class MainBattle extends AppCompatActivity {

    private Button addHeroButton;
    private ListView rankingsLV;
    private Spinner heroFirstSpinner;
    private Spinner heroSecondSpinner;
    private Button fightButton;
    private TextView resultsTV;

    private String firstHeroSelected;
    private String secondHeroSelected;

    private SimpleCursorAdapter rankingsAdapter = null;
    private ArrayAdapter<String> heroAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_battle);

        addHeroButton = (Button) findViewById(R.id.addHeroButton);
        rankingsLV = (ListView) findViewById(R.id.rankingsLV);
        heroFirstSpinner = (Spinner) findViewById(R.id.heroFirstSpinner);
        heroSecondSpinner = (Spinner) findViewById(R.id.heroSecondSpinner);
        fightButton = (Button) findViewById(R.id.fightButton);
        resultsTV = (TextView) findViewById(R.id.resultsTV);

        populate();
        listeners();
    }

    private void populate(){
        //Rankings ListView
        String[] columns = new String[] { DbSchema.TABLE_HEROES.KEY_NAME,
                                            DbSchema.TABLE_HEROES.KEY_NUM_WINS,
                                            DbSchema.TABLE_HEROES.KEY_NUM_LOSSES,
                                            DbSchema.TABLE_HEROES.KEY_NUM_TIES,
                                            DbSchema.TABLE_HEROES.KEY_POWER1,
                                            DbSchema.TABLE_HEROES.KEY_POWER2 };

        int[] to = new int[] { R.id.heroNameTV,
                                R.id.heroWinTV,
                                R.id.heroLossTV,
                                R.id.heroTieTV,
                                R.id.heroFirstPowerTV,
                                R.id.heroSecondPowerTV };

        rankingsAdapter = new SimpleCursorAdapter(this, R.layout.list_item_ranking,
                BattleSingleton.getInstance(getApplicationContext()).getRankings(), columns, to);
        rankingsLV.setAdapter(rankingsAdapter);

        //Creating heroAdapter
        Cursor heroCursor = BattleSingleton.getInstance(getApplicationContext()).getHeroes();
        ArrayList<String> heroes = new ArrayList<String>();
        while(heroCursor.moveToNext()){
            heroes.add(heroCursor.getString(DbSchema.TABLE_HEROES.COLUMN_NAME));
        }
        heroAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, heroes);
        heroCursor.close();

        //First Hero Spinner
        heroFirstSpinner.setAdapter(heroAdapter);
        //Second Hero Spinner
        heroSecondSpinner.setAdapter(heroAdapter);
    }

    private void listeners(){
        addHeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddHero.class);
                startActivityForResult(i, 0);
            }
        });
        heroFirstSpinner.setOnItemSelectedListener(new heroSpinnerListener());
        heroSecondSpinner.setOnItemSelectedListener(new heroSpinnerListener());

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstHeroSelected.isEmpty() && !secondHeroSelected.isEmpty()){
                    resultsTV.setText("");
                    fight(firstHeroSelected, secondHeroSelected);
                    rankingsAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainBattle.this, "Please select two heroes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class heroSpinnerListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent;
            if(spinner.getId() == R.id.heroFirstSpinner){
                firstHeroSelected = (String) spinner.getSelectedItem();
            }
            else if(spinner.getId() == R.id.heroSecondSpinner){
                secondHeroSelected = (String) spinner.getSelectedItem();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void fight(String firstHeroName, String secondHeroName){
        //Create hero from lookup and get all values in row
        Hero firstHero = findHero(firstHeroName);
        //Same for second
        Hero secondHero = findHero(secondHeroName);

        String firstHeroPower = "";
        String secondHeroPower = "";

        String headline = firstHero.getName() + " vs. " + secondHero.getName() + "!\n";
        resultsTV.append(headline.toString());

        int round = 1;
        while(firstHero.isAlive() && secondHero.isAlive()){
            //Randomize for first hero
            int firstHeroPowerIndex = (Math.random() <= 0.5) ? 1 : 2;
            if(firstHeroPowerIndex == 1){
                firstHeroPower = firstHero.getFirstPower();
            }
            else if(firstHeroPowerIndex == 2){
                firstHeroPower = firstHero.getSecondPower();
            }
            //Randomize for second hero
            int secondHeroPowerIndex = (Math.random() <= 0.5) ? 1 : 2;
            if(secondHeroPowerIndex == 1){
                secondHeroPower = secondHero.getFirstPower();
            }
            else if(secondHeroPowerIndex == 2){
                secondHeroPower = secondHero.getFirstPower();
            }

            String roundTitle = "==========Round " + round + "==========\n";
            resultsTV.append(roundTitle.toString());
            String firstHeroLine = firstHero.getName() + " uses " + firstHeroPower + "!\n";
            resultsTV.append(firstHeroLine.toString());
            String secondHeroLine = secondHero.getName() + " uses " + secondHeroPower + "!\n";
            resultsTV.append(secondHeroLine.toString());

            int result = BattleSingleton.getInstance(getApplicationContext()).getPowerResult(firstHeroPower, secondHeroPower);
            String scoreResult = "";
            if(result == 1){
                scoreResult = firstHero.getName() + " wins this round with " + firstHeroPower + "\n";
                secondHero.setHealth(secondHero.getHealth() - 1);
            }
            else if(result == -1){
                scoreResult = secondHero.getName() + " wins this round with " + secondHeroPower + "\n";
                firstHero.setHealth(firstHero.getHealth() - 1);
            }
            else if (result == 0){
                scoreResult = firstHero.getName() + " and " + secondHero.getName() + " tie this round\n";
                firstHero.setHealth(firstHero.getHealth() - 1);
                secondHero.setHealth(secondHero.getHealth() - 1);
            }
            resultsTV.append(scoreResult.toString());
            round += 1;
        }

        String finalResult = "";
        if(!firstHero.isAlive() && !secondHero.isAlive()){
            finalResult = firstHero.getName() + " and " + secondHero.getName() + " are tied!\n";
            resultsTV.append(finalResult.toString());
            //first hero gets tie
            firstHero.setNumTies(firstHero.getNumTies() + 1);
            BattleSingleton.getInstance(getApplicationContext()).addBattleResult(firstHero, 0);
            //second hero gets tie
            secondHero.setNumTies(secondHero.getNumTies() + 1);
            BattleSingleton.getInstance(getApplicationContext()).addBattleResult(secondHero, 0);
        }
        else if(!firstHero.isAlive()){
            finalResult = secondHero.getName() + " is the winner!\n";
            resultsTV.append(finalResult.toString());
            //first hero gets a loss
            firstHero.setNumLosses(firstHero.getNumLosses() + 1);
            BattleSingleton.getInstance(getApplicationContext()).addBattleResult(firstHero, -1);
            //second hero gets a win
            secondHero.setNumWins(secondHero.getNumWins() + 1);
            BattleSingleton.getInstance(getApplicationContext()).addBattleResult(secondHero, 1);
        }
        else if(!secondHero.isAlive()){
            finalResult = firstHero.getName() + " is the winner!\n";
            resultsTV.append(finalResult.toString());
            //first hero gets a win
            firstHero.setNumTies(firstHero.getNumWins() + 1);
            BattleSingleton.getInstance(getApplicationContext()).addBattleResult(firstHero, 1);
            //second hero gets a loss
            secondHero.setNumLosses(firstHero.getNumLosses() + 1);
            BattleSingleton.getInstance(getApplicationContext()).addBattleResult(secondHero, -1);
        }

        rankingsAdapter.notifyDataSetChanged();
    }

    private Hero findHero(String heroName){
        Hero hero = null;
        Cursor heroCursor = BattleSingleton.getInstance(getApplicationContext()).getHeroes();
        while(heroCursor.moveToNext()){
            if(heroCursor.getString(DbSchema.TABLE_HEROES.COLUMN_NAME).equals(heroName)){
                long id = heroCursor.getLong(DbSchema.TABLE_HEROES.COLUMN_ID);
                String name = heroCursor.getString(DbSchema.TABLE_HEROES.COLUMN_NAME);
                String power1 = heroCursor.getString(DbSchema.TABLE_HEROES.COLUMN_POWER1);
                String power2 = heroCursor.getString(DbSchema.TABLE_HEROES.COLUMN_POWER2);
                int num_wins = heroCursor.getInt(DbSchema.TABLE_HEROES.COLUMN_NUM_WINS);
                int num_losses = heroCursor.getInt(DbSchema.TABLE_HEROES.COLUMN_NUM_LOSSES);
                int num_ties = heroCursor.getInt(DbSchema.TABLE_HEROES.COLUMN_NUMS_TIES);
                hero = new Hero(id, name, power1, power2, 5, num_wins, num_losses, num_ties);
            }
        }

        heroCursor.close();
        return hero;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        rankingsAdapter.notifyDataSetChanged();
        ((ArrayAdapter)heroFirstSpinner.getAdapter()).notifyDataSetChanged();
        ((ArrayAdapter)heroSecondSpinner.getAdapter()).notifyDataSetChanged();
    }
}
