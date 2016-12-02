package itp341.zhao.glarence.a6;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView colorPuzzleView;
    private TextView desirePuzzleView;
    private TextView namePuzzleView;
    private TextView schoolPuzzleView;

    private Button room1Button;
    private Button room2Button;
    private Button room3Button;
    private Button room4Button;

    private Button solveButton;

    private Button cheatButton;

    private int RValue = 255;
    private int GValue = 0;
    private int BValue = 0;

    private String size = "";

    private String name = "";

    private String school = "";

    private boolean colorPuzzleResult = false;
    private boolean desirePuzzleResult = false;
    private boolean namePuzzleResult = false;
    private boolean schoolPuzzleResult = false;
    private boolean gameWon = false;

    private int numOfPuzzlesCompleted = 0;

    public static final String puzzlesCompleted = "Puzzles_COMPLETED";
    public static final String GAMERESULT = "Game_RESULT";

    private int correctRValue;
    private int correctGValue;
    private int correctBValue;
    private String correctSize;
    private String correctName;
    private final String correctSchool = "USC";

    private String[] colors;
    private int colorIndex;
    private String[] sizes;
    private int sizeIndex;
    private String[] names;
    private int nameIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomize();
        setCorrectValues();
        initialize();
        listeners();

    }

    private void setCorrectValues(){

        String randomColor = colors[colorIndex];
        setColor(randomColor);
        String randomSize = sizes[sizeIndex];
        setSize(randomSize);
        String randomName = names[nameIndex];
        setName(randomName);


    }

    private void randomize(){

        colors = getResources().getStringArray(R.array.colorValues);
        colorIndex = random(colors.length);
        sizes = getResources().getStringArray(R.array.sizeValues);
        sizeIndex = random(sizes.length);
        names = getResources().getStringArray(R.array.nameValues);
        nameIndex = random(names.length);

    }

    private int random(int length){

        Random rand = new Random();
        int index = rand.nextInt(length);

        return index;

    }

    private void initialize(){

        colorPuzzleView = (TextView) findViewById(R.id.colorPuzzle);
        String colorPuzzle = colorPuzzleView.getText().toString();
        colorPuzzle += " " + colors[colorIndex];
        colorPuzzleView.setText(colorPuzzle);
        colorPuzzleView.setTextColor(Color.rgb(RValue, GValue, BValue));
        desirePuzzleView = (TextView) findViewById(R.id.desirePuzzle);
        String desirePuzzle = desirePuzzleView.getText().toString();
        desirePuzzle += " " + correctSize;
        desirePuzzleView.setText(desirePuzzle);
        desirePuzzleView.setTextColor(Color.rgb(RValue, GValue, BValue));
        namePuzzleView = (TextView) findViewById(R.id.namePuzzle);
        String namePuzzle = namePuzzleView.getText().toString();
        namePuzzle += " \"" + correctName + "\"";
        namePuzzleView.setText(namePuzzle);
        namePuzzleView.setTextColor(Color.rgb(RValue, GValue, BValue));
        schoolPuzzleView = (TextView) findViewById(R.id.schoolPuzzle);
        schoolPuzzleView.setTextColor(Color.rgb(RValue, GValue, BValue));

        room1Button = (Button) findViewById(R.id.room1Button);
        room2Button = (Button) findViewById(R.id.room2Button);
        room3Button = (Button) findViewById(R.id.room3Button);
        room4Button = (Button) findViewById(R.id.room4Button);

        solveButton = (Button) findViewById(R.id.solveButton);

        cheatButton = (Button) findViewById(R.id.cheatButton);

    }

    private void listeners() {

        room1Button.setOnClickListener(new roomListener());
        room2Button.setOnClickListener(new roomListener());
        room3Button.setOnClickListener(new roomListener());
        room4Button.setOnClickListener(new roomListener());

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                gameWon = checkResult();
                i.putExtra(GAMERESULT, gameWon);
                startActivityForResult(i, 4);

            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answer =
                        "R Value : " + correctRValue + " G Value : " + correctGValue + " B Value : " + correctBValue
                        + "\n" + "Size Value: " + correctSize
                        + "\n" + "Name Value: " + correctName
                        + "\n" + "School: " + correctSchool;

                Toast.makeText(MainActivity.this, answer, Toast.LENGTH_LONG).show();

            }
        });

    }

    class roomListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            int id = v.getId();

            if(id == R.id.room1Button){

                Intent i = new Intent(getApplicationContext(), Room1.class);
                i.putExtra(Room1.R_VALUE, RValue);
                i.putExtra(Room1.G_VALUE, GValue);
                i.putExtra(Room1.B_VALUE, BValue);
                startActivityForResult(i, 1);

            }
            if(id == R.id.room2Button){

                Intent i = new Intent(getApplicationContext(), Room2.class);
                startActivityForResult(i, 2);

            }
            if(id == R.id.room3Button){

                Intent i = new Intent(getApplicationContext(), Room3.class);
                i.putExtra(Room3.NAME, name);
                startActivityForResult(i, 3);

            }
            if(id == R.id.room4Button){

                Intent i = new Intent(getApplicationContext(), Room4.class);
                i.putExtra(Room4.SCHOOL, school);
                startActivityForResult(i, 4);

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Room 1 Activity
        if(requestCode == 1){

            RValue = data.getIntExtra(Room1.R_VALUE, 0);
            GValue = data.getIntExtra(Room1.G_VALUE, 0);
            BValue = data.getIntExtra(Room1.B_VALUE, 0);

            if((RValue == correctRValue) && (GValue == correctGValue) && (BValue == correctBValue)){

                colorPuzzleResult = true;
                colorPuzzleView.setTextColor(Color.GREEN);

            }
            else{
                colorPuzzleResult = false;
                colorPuzzleView.setTextColor(Color.rgb(255, 0, 0));
            }

        }

        //Room 2 Activity
        if(requestCode == 2){

            size = data.getStringExtra(Room2.selectedSize);

            if(size.equals(correctSize)){

                desirePuzzleResult = true;

                if(colorPuzzleResult)
                    desirePuzzleView.setTextColor(Color.GREEN);

            }
            else{
                desirePuzzleResult = false;
                desirePuzzleView.setTextColor(Color.rgb(255, 0, 0));
            }

        }

        //Room 3 Activity
        if(requestCode == 3){

            name = data.getStringExtra(Room3.NAME);

            if(name.equals(correctName)){

                namePuzzleResult = true;

                if(colorPuzzleResult && desirePuzzleResult)
                    namePuzzleView.setTextColor(Color.GREEN);

            }
            else{
                namePuzzleResult = false;
                namePuzzleView.setTextColor(Color.rgb(255, 0, 0));
            }

        }

        //Room 4 Activity
        if(requestCode == 4){

            school = data.getStringExtra(Room4.SCHOOL);

            if(school.equals(correctSchool)){

                schoolPuzzleResult = true;

                if(colorPuzzleResult && desirePuzzleResult && namePuzzleResult)
                    schoolPuzzleView.setTextColor(Color.GREEN);

            }
            else{
                schoolPuzzleResult = false;
                schoolPuzzleView.setTextColor(Color.rgb(255, 0, 0));
            }

        }

    }

    private boolean checkResult(){

        if(colorPuzzleResult && desirePuzzleResult && namePuzzleResult && schoolPuzzleResult)
            gameWon = true;

        return gameWon;

    }

    private void setColor(String color){

        if(color.equals(colors[0])){

            correctRValue = 0;
            correctGValue = 0;
            correctBValue = 255;

        }
        if(color.equals(colors[1])){

            correctRValue = 0;
            correctGValue = 0;
            correctBValue = 0;

        }
        if(color.equals(colors[2])){

            correctRValue = 99;
            correctGValue = 0;
            correctBValue = 0;

        }
        if(color.equals(colors[3])){

            correctRValue = 255;
            correctGValue = 205;
            correctBValue = 0;

        }

    }

    private void setSize(String size){

        correctSize = size;

    }

    private void setName(String name){

        correctName = name;

    }
}
