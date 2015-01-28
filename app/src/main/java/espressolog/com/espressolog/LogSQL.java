package espressolog.com.espressolog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hudson49423 on 1/5/15.
 */
public class LogSQL extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    // The database name.
    private static final String DATABASE_NAME = "LogDB";

    // Constructor.
    public LogSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create log table.
        String CREATE_LOG_TABLE = "CREATE TABLE logs ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "shotTime TEXT, " +
                "shotWeight TEXT, " +
                "temperature TEXT, " +
                "brewRatio TEXT, " +
                "rating Text, " +
                "dose TEXT, " +
                "grind TEXT, " +
                "notes TEXT, " +
                "volume TEXT)";

        // Create the logs table.
        db.execSQL(CREATE_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older logs if they exist.
        db.execSQL("DROP TABLE IF EXISTS logs");

        // Create fresh logs table.
        this.onCreate(db);
    }

    // Database CRUD operations.

    // Logs table name.
    private static final String TABLE_LOGS = "logs";

    // Logs Table Columns names.
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_SHOTTIME = "shotTime";
    private static final String KEY_SHOTWEIGHT = "shotWeight";
    private static final String KEY_TEMPERATURE = "temperature";
    private static final String KEY_BREWRATIO = "brewRatio";
    private static final String KEY_RATING = "rating";
    private static final String KEY_DOSE = "dose";
    private static final String KEY_GRIND = "grind";
    private static final String KEY_NOTES = "notes";
    private static final String KEY_VOLUME = "volume";

    private static final String[] COLUMNS = {
                                KEY_ID,
                                KEY_DATE,
                                KEY_SHOTTIME,
                                KEY_SHOTWEIGHT,
                                KEY_TEMPERATURE,
                                KEY_BREWRATIO,
                                KEY_RATING,
                                KEY_DOSE,
                                KEY_GRIND,
                                KEY_NOTES,
                                KEY_VOLUME};

    public void addLog(LogItem log){
        Log.v("addLog", log.toString());

        // Get a reference to writable db.
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/value.
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_SHOTTIME, log.getShotTime());
        values.put(KEY_SHOTWEIGHT, log.getShotWeight());
        values.put(KEY_TEMPERATURE, log.getTemperature());
        values.put(KEY_BREWRATIO, log.getBrewRatio());
        values.put(KEY_RATING, log.getRating());
        values.put(KEY_DOSE, log.getDose());
        values.put(KEY_GRIND, log.getGrind());
        values.put(KEY_NOTES, log.getNotes());
        values.put(KEY_VOLUME, log.getVolume());

        // Insert into db.
        db.insert(TABLE_LOGS, // Table
                null, // nullColumnHack.
                values);

        // Close the db.
        db.close();
    }

    public LogItem getLog(String shotTime) {

        // Get a reference to readable db.
        SQLiteDatabase db = this.getReadableDatabase();

        // Build query.
        Cursor cursor =

                db.query(TABLE_LOGS, // Table
                COLUMNS, // Column names
                " shotTime = ?", // Selections
                new String[] { shotTime }, // selections args.
                null, // group by
                null, // having
                null, // order by
                null); // limit


        // If we got results get the first one.
        if (cursor != null) {
            cursor.moveToFirst();
        }

        // Build log object.
        LogItem log = new LogItem();
        log.setId(Integer.parseInt(cursor.getString(0)));
        log.setDate(cursor.getString(1));
        log.setShotTime(cursor.getString(2));
        log.setShotWeight(cursor.getString(3));
        log.setTemperature(cursor.getString(4));
        log.setBrewRatio(cursor.getString(5));
        log.setRating(cursor.getString(6));
        log.setDose(cursor.getString(7));
        log.setGrind(cursor.getString(8));
        log.setNotes(cursor.getString(9));
        log.setVolume(cursor.getString(10));

        cursor.close();
        db.close();

        return log;
    }

    // Updating single book
    public void updateLog(LogItem log) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("date", log.getDate()); // get date
        values.put("shotTime", log.getShotTime()); // get shot time
        values.put("shotWeight", log.getShotWeight());
        values.put("temperature", log.getTemperature());
        values.put("brewRatio", log.getBrewRatio());
        values.put("rating", log.getRating());
        values.put("dose", log.getDose());
        values.put("volume", log.getVolume());

        // 3. updating row
        db.update(TABLE_LOGS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(log.getId()) }); //selection args

        // 4. close
        db.close();
    }

    // Deleting single book
    public void deleteLog(LogItem log) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_LOGS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(log.getId()) });

        // 3. close
        db.close();

        Log.d("deleteLog", log.toString());

    }

    public LinkedList<LogItem> getAllLogs() {
        LinkedList<LogItem> logs = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_LOGS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build log and add it to list
        LogItem log = null;
        if (cursor.moveToFirst()) {
            do {
                log = new LogItem();
                log.setId(Integer.parseInt(cursor.getString(0)));
                log.setDate(cursor.getString(1));
                log.setShotTime(cursor.getString(2));
                log.setShotWeight(cursor.getString(3));
                log.setTemperature(cursor.getString(4));
                log.setBrewRatio(cursor.getString(5));
                log.setRating(cursor.getString(6));
                log.setDose(cursor.getString(7));
                log.setGrind(cursor.getString(8));
                log.setNotes(cursor.getString(9));
                log.setVolume(cursor.getString(10));
                logs.addFirst(log);
            } while (cursor.moveToNext());
        }

        Log.d("getAllLogs()", logs.toString());

        // return books
        return logs;
    }

    public void logAutoIncrements(){
        String query = "SELECT * FROM SQLITE_SEQUENCE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Log.v("Log Table name: ", cursor.getString(cursor.getColumnIndex("name")));
                Log.v("Id value: ", cursor.getString(cursor.getColumnIndex("seq")));

            }while (cursor.moveToNext());
        }

        cursor.close();

    }

    public void clearAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("logs", null, null);
    }
}
