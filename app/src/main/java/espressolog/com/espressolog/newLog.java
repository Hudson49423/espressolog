package espressolog.com.espressolog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class newLog extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        // Set the title
        setTitle("New Log");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void save(View view){
        //saves the data from the new log screen.

        // The file data will be saved in.
        String filename = "myFile";

        // They key which means this is a new log.
        String newLogKey = "@";


        // The date.
        String date = getDate();

        // The data entered in by the user.
        String[] data = new String[6];
        EditText v = (EditText) findViewById(R.id.shot_time_input);
        data[0] = "#s" + v.getText().toString();
        v = (EditText) findViewById(R.id.weight_input);
        data[1] = "#w" + v.getText().toString();
        v = (EditText) findViewById(R.id.temperature_input);
        data[2] = "#t" + v.getText().toString();
        data[3] = "#d" + date;
        data[4] = "#b47%";
        v = (EditText) findViewById(R.id.rating_input);
        data[5] = "#r" + v.getText().toString();

        // The output stream.
        FileOutputStream outputStream;

        try {
            // open the output stream. The MODE_APPEND means that we add onto the file.
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE | MODE_APPEND);
            outputStream.write(newLogKey.getBytes());
            for (String s : data) {
                outputStream.write(s.getBytes());

            }

            // Create a toast to notify the user if the log was saved.
            Context context = getApplicationContext();
            CharSequence text = "Log Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        // takes user back to main activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
