package espressolog.com.espressolog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hudson49423 on 1/5/15.
 */
public class LogSQL extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // The database name.
    private static final String DATABASE_NAME = "LogDB";

    // Constructor.
    public LogSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create log table.
        String CREATE_BOOK_TABLE = "CREATE TABLE logs ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "shotTime TEXT )";

        // Create the logs table.
        db.execSQL(CREATE_BOOK_TABLE);
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

    private static final String[] COLUMNS = {KEY_ID, KEY_DATE, KEY_SHOTTIME};

    public void addLog(LogItem log){
        Log.v("addLog", log.toString());

        // Get a reference to writable db.
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/value.
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, log.getDate());
        values.put(KEY_SHOTTIME, log.getShotTime());

        // Insert into db.
        db.insert(TABLE_LOGS, // Table
                null, // nullColumnHack.
                values);

        // Close the db.
        db.close();
    }

    public LogItem getLog(int id) {

        // Get a reference to readable db.
        SQLiteDatabase db = this.getReadableDatabase();

        // Build query.
        Cursor cursor =
                db.query(TABLE_LOGS, // Table
                COLUMNS, // Column names
                " id = ?", // Selections
                new String[] { String.valueOf(id) }, // selections args.
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

        Log.v("Get log(" + id + ")", log.toString());

        return log;
    }

    // Updating single book
    public int updateLog(LogItem log) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("date", log.getDate()); // get date
        values.put("shotTime", log.getShotTime()); // get shot time

        // 3. updating row
        int i = db.update(TABLE_LOGS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(log.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

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

    public List<LogItem> getAllLogs() {
        List<LogItem> logs = new LinkedList<LogItem>();

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

                // Add book to books
                logs.add(log);
            } while (cursor.moveToNext());
        }

        Log.d("getAllLogs()", logs.toString());

        // return books
        return logs;
    }
}
