package itp341.zhao.glarence.a8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by glarencezhao on 4/3/16.
 */
public class Note {

    private String _title;
    private String _content;
    private Calendar _date;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    public Note(String title, String content, Calendar date){

        _title = title;
        _content = content;
        _date = date;

        dateFormatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

    }

    public String getTitle(){
        return _title;
    }

    public void setTitle(String title){
        _title = title;
    }

    public String getContent(){
        return _content;
    }

    public void setContent(String content){ _content = content; }

    public String getDate(){
        return dateFormatter.format(_date.getTime());
    }

    public void setDate(Calendar date){
        _date = date;
    }

}
