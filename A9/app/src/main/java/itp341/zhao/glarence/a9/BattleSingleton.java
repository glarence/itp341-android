package itp341.zhao.glarence.a9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import itp341.zhao.glarence.a9.database.DbHelper;
import itp341.zhao.glarence.a9.database.DbSchema;

/**
 * Created by glarencezhao on 4/18/16.
 */
public final class BattleSingleton {
    private static BattleSingleton instance;

    private Context mAppContext;
    SQLiteDatabase mDatabase;

    private BattleSingleton(Context appContext){
        mAppContext = appContext;
        mDatabase = new DbHelper(appContext).getWritableDatabase();
    }

    public static BattleSingleton getInstance(Context context){
        if(instance == null)
            return new BattleSingleton(context);
        return instance;
    }

    public Cursor getUniquePowers(){
        Cursor cursor = mDatabase.query(
                true,
                DbSchema.TABLE_POWERS.NAME,
                null,
                null,
                null,
                DbSchema.TABLE_POWERS.KEY_OWN_POWER,
                null,
                null,
                null);

        return cursor;
    }

    public void addHero(Hero hero){
        ContentValues cv = new ContentValues();
        cv.put(DbSchema.TABLE_HEROES.KEY_NAME, hero.getName());
        cv.put(DbSchema.TABLE_HEROES.KEY_POWER1, hero.getFirstPower());
        cv.put(DbSchema.TABLE_HEROES.KEY_POWER2, hero.getSecondPower());
        cv.put(DbSchema.TABLE_HEROES.KEY_NUM_WINS, hero.getNumWins());
        cv.put(DbSchema.TABLE_HEROES.KEY_NUM_LOSSES, hero.getNumLosses());
        cv.put(DbSchema.TABLE_HEROES.KEY_NUM_TIES, hero.getNumTies());

        mDatabase.insert(DbSchema.TABLE_HEROES.NAME, null, cv);
    }

    public Cursor getRankings(){
        Cursor cursor = mDatabase.query(
              DbSchema.TABLE_HEROES.NAME,
                null,
                null,
                null,
                DbSchema.TABLE_HEROES.KEY_NAME,
                null,
                DbSchema.TABLE_HEROES.KEY_NUM_WINS + " DESC"
        );

        return cursor;
    }

    public Cursor getHeroes(){
        Cursor cursor = mDatabase.query(
                DbSchema.TABLE_HEROES.NAME,
                null,
                null,
                null,
                DbSchema.TABLE_HEROES.KEY_NAME,
                null,
                DbSchema.TABLE_HEROES.KEY_NAME + " ASC"
        );

        return cursor;
    }

    public int getPowerResult(String power1, String power2){
        Cursor powerCursor = mDatabase.query(
                DbSchema.TABLE_POWERS.NAME,
                null,
                DbSchema.TABLE_POWERS.KEY_OWN_POWER + "=?"
                    + " AND " + DbSchema.TABLE_POWERS.KEY_OPPOSING_POWER + "=?",
                new String[] { power1, power2 },
                DbSchema.TABLE_POWERS.KEY_OWN_POWER,
                null,
                null,
                null);
        if(powerCursor != null)
            powerCursor.moveToFirst();

        int result = powerCursor.getInt(DbSchema.TABLE_POWERS.COLUMN_WINNING_POWER);
        if(!powerCursor.isClosed())
            powerCursor.close();

        return result;
    }

    public void addBattleResult(Hero hero, int result){
        ContentValues cv = new ContentValues();
        if(result == 1){
            cv.put(DbSchema.TABLE_HEROES.KEY_NUM_WINS, hero.getNumWins());
        }
        else if(result == -1){
            cv.put(DbSchema.TABLE_HEROES.KEY_NUM_LOSSES, hero.getNumLosses());
        }
        else if(result == 0){
            cv.put(DbSchema.TABLE_HEROES.KEY_NUM_WINS, hero.getNumTies());
        }
        String[] whereValues = new String[] { hero.getID() + "" };
        mDatabase.update(DbSchema.TABLE_HEROES.NAME, cv, "_id = ?", whereValues);
    }
}
