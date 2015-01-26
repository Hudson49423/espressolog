package espressolog.com.espressolog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class newLog extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        // Set the title
        setTitle("New Log");

        // Only set the fields we want to be visible
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("shot_time_checkbox", true)) {
            findViewById(R.id.shot_time).setVisibility(TextView.GONE);
            findViewById(R.id.shot_time_input).setVisibility(EditText.GONE);
        }
        if(!prefs.getBoolean("shot_weight_checkbox", true)) {
            findViewById(R.id.weight).setVisibility(TextView.GONE);
            findViewById(R.id.weight_input).setVisibility(EditText.GONE);
        }
        if(!prefs.getBoolean("temperature_checkbox", true)) {
            findViewById(R.id.temperature).setVisibility(TextView.GONE);
            findViewById(R.id.temperature_input).setVisibility(EditText.GONE);
        }
        if(!prefs.getBoolean("rating_checkbox", true)) {
            findViewById(R.id.rating).setVisibility(TextView.GONE);
            findViewById(R.id.rating_input).setVisibility(EditText.GONE);
        }
        if(!prefs.getBoolean("dose_checkbox", true)) {
            findViewById(R.id.dose).setVisibility(TextView.GONE);
            findViewById(R.id.dose_input).setVisibility(EditText.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void save(View view){

        LogItem logToAdd = new LogItem();

        // Tells us if the user has filled all fields.
        boolean hasBeenFilled = true;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean("shot_time_checkbox", true)) {
            String s = ((EditText)findViewById(R.id.shot_time_input)).getText().toString();
            if (!s.isEmpty()) {
                logToAdd.setShotTime(s);
            }
            else {
                hasBeenFilled = false;
            }
        }
        if(prefs.getBoolean("shot_weight_checkbox", true)) {
            String s = ((EditText)findViewById(R.id.weight_input)).getText().toString();
            if (!s.isEmpty()) {
                logToAdd.setShotWeight(s);
            }
            else {
                hasBeenFilled = false;
            }
        }
        if(prefs.getBoolean("temperature_checkbox", true)) {
            String s = ((EditText)findViewById(R.id.temperature_input)).getText().toString();
            if (!s.isEmpty()) {
                logToAdd.setTemperature(s);
            }
            else {
                hasBeenFilled = false;
            }
        }
        if(prefs.getBoolean("rating_checkbox", true)) {
            String s = ((EditText)findViewById(R.id.rating_input)).getText().toString();
            if (!s.isEmpty()) {
                logToAdd.setRating(s);
            }
            else {
                hasBeenFilled = false;
            }
        }
        if(prefs.getBoolean("dose_checkbox", true)) {
            String s = ((EditText)findViewById(R.id.dose_input)).getText().toString();
            if (!s.isEmpty()) {
                logToAdd.setDose(s);
            }
            else {
                hasBeenFilled = false;
            }
        }

        if (!hasBeenFilled) {
            Context context = getApplicationContext();
            CharSequence text = "Please complete all fields";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            // The date. If the log is not new then we need to get it.
            logToAdd.setDate(getDate());
            try {
                // Create the database.
                LogSQL db = new LogSQL(this);

                // Add this log to the SQL database or update it if it already exists.
                // We might get into trouble if some fields are filled or not.
                db.addLog(logToAdd);

                // Create a toast to notify the user if the log was saved.
                Context context = getApplicationContext();
                CharSequence text = "Log Saved";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
            // takes user back to main activity.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void cancel(View view){

        // Create a toast to notify the user if the log was cancelled
        Context context = getApplicationContext();
        CharSequence text = "Log Cancelled";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Simply starts the main activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("M/dd");
        return sdf.format(c.getTime());
    }
}