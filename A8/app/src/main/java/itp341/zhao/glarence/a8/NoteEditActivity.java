package itp341.zhao.glarence.a8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by glarencezhao on 4/3/16.
 */
public class NoteEditActivity extends Activity{

    private Button saveNoteButton;
    private Button deleteNoteButton;
    private EditText titleEditText;
    private EditText contentEditText;

    private String _title;
    private String _content;

    private int noteIndex;
    private static final String ITEM_POSITION = "Position of Item";

    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit_activity);

        saveNoteButton = (Button) findViewById(R.id.saveNoteButton);
        deleteNoteButton = (Button) findViewById(R.id.deleteNoteButton);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);

        Intent i = getIntent();
        noteIndex = i.getIntExtra(ITEM_POSITION, 0);
        currentNote = NoteSingleton.getInstance().getNote(noteIndex);
        initialize();
        update();

        listeners();
    }

    private void listeners(){
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNote.setTitle(_title);
                currentNote.setContent(_content);
                currentNote.setDate(Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")));

                setResult(1);
                finish();
            }
        });
        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteSingleton.removeNote(noteIndex);

                setResult(0);
                finish();
            }
        });
        titleEditText.addTextChangedListener(new TextListener(R.id.titleEditText));
        contentEditText.addTextChangedListener(new TextListener(R.id.contentEditText));
    }

    private class TextListener implements TextWatcher{

        private int id;

        public TextListener(int viewID){
            id = viewID;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            switch(id){
                case R.id.titleEditText:
                    _title = text;
                    break;
                case R.id.contentEditText:
                    _content = text;
                    break;
            }
        }
    }

    private void update(){
        titleEditText.setText(currentNote.getTitle());
        contentEditText.setText(currentNote.getContent());
    }

    private void initialize(){
        _title = currentNote.getTitle();
        _content = currentNote.getContent();
    }
}
