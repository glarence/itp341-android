package itp341.zhao.glarence.a8;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by glarencezhao on 4/3/16.
 */
public final class NoteSingleton {

    private static final NoteSingleton instance = new NoteSingleton();

    private static ArrayList<Note> notesArray;

    private Context _context;

    private NoteSingleton(){

        Note groceryNote = new Note("Groceries", "Chicken \nPineapple \nToast \nPaper Towels",
                Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")));
        Note diaryNote = new Note("Monday 21st Feb Rant", "OMG WHY DOES MY LIFE SUCK SUCK SUCK?!?!?!? " +
                "\nLIKE JASON is so cute and all, but he never like ever sees me! \nURRGH",
                Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")));

        notesArray = new ArrayList<Note>();
        notesArray.add(groceryNote);
        notesArray.add(diaryNote);

    }

    public static NoteSingleton getInstance(){ return instance; }

    protected static Note createNote(){
        Note newNote = new Note("New Note", "", Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")));
        //notesArray.add(newNote);
        return newNote;
    }

    protected static Note getNote(int index){
        return notesArray.get(index);
    }

    protected static void removeNote(int index){
        notesArray.remove(index);
    }

    public void init(Context context){
        _context = context.getApplicationContext();
    }

    public static ArrayList<Note> getArray(){ return notesArray; }

}
