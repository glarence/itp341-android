package itp341.zhao.glarence.a8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class NoteListActivity extends AppCompatActivity {

    private Button addButton;
    private ListView notesList;

    private NoteAdapter adapter;

    private static final String ITEM_POSITION = "Position of Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list_activity);

        addButton = (Button) findViewById(R.id.addButton);
        notesList = (ListView) findViewById(R.id.notesListView);
        adapter = new NoteAdapter(this, NoteSingleton.getInstance().getArray());
        notesList.setAdapter(adapter);

        listeners();
    }

    private void listeners(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(NoteSingleton.getInstance().createNote());
            }
        });
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), NoteEditActivity.class);
                i.putExtra(ITEM_POSITION, position);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Saved note
        if(resultCode == 1){
            adapter.notifyDataSetChanged();
        }
        //Delete note
        if(resultCode == 0){
            adapter.notifyDataSetChanged();
        }
    }
}
