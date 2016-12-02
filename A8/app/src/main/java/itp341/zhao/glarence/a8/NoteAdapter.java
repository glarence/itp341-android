package itp341.zhao.glarence.a8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by glarencezhao on 4/3/16.
 */
public class NoteAdapter extends android.widget.ArrayAdapter<Note> {

    public NoteAdapter(Context context, ArrayList<Note> notesArray){
        super(context, 0, notesArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView date = (TextView) convertView.findViewById(R.id.dateTextView);

        title.setText(note.getTitle());
        date.setText(note.getDate());

        return convertView;
    }
}
