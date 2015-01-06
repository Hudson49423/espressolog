package espressolog.com.espressolog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<LogItem> logItems = getLogItems(getFormatedData());

        ListAdapter mLogAdapter;
        mLogAdapter = new ListAdapter(
                //the context
                this,
                // the id of the list item layout.
                R.layout.list_item_log,
                // The raw data.
                logItems);

        // Get a reference to the ListView and attach this adapter to it.
        ListView listView = (ListView) findViewById(R.id.list_view_log);
        listView.setAdapter(mLogAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, DetailActivity.class);
                String test = "test data";

                intent.putExtra(Intent.EXTRA_TEXT, test);
                startActivity(intent);
            }
        });
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

        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.action_help:
                return true;

            case R.id.action_newLog:
                newLog();
                return true;

            case R.id.action_clear:
                deleteFile("myFile");
                // Refresh the activity.
                Intent refresh = new Intent(this, MainActivity.class);
                startActivity(refresh);
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newLog(){
        Intent intent = new Intent(this, newLog.class);
        startActivity(intent);
    }

    private ArrayList<String[]> getFormatedData() {
        // Initializing the strings we may use to hold data.
        String shotTime = null;
        String weight = null;
        String temperature = null;
        String date = null;
        String rating = null;

        String[] array = new String[5];

        // Initialize the return arraylist
        ArrayList<String[]> returnArray = new ArrayList<>();

        // Get the unformated data.
        String dataString = "";

        // The input stream.
        InputStream in = null;
        try {
            in = openFileInput("myFile");
            int content;
            while ((content = in.read()) != -1) {
                dataString = dataString + ((char) content);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        // This gives us the individual log.
        String[] splitLogs = dataString.split("@");

        // Loops over each saved log.
        for (String x : splitLogs) {
            // Splits the data up.
            String[] splitLogData = x.split("#");
            // Loops over the split data in the individual log.
            for (String s : splitLogData) {
                if (s.startsWith("s")) {
                    shotTime = s.substring(1);
                }
                else if (s.startsWith("w")) {
                    weight = s.substring(1);
                }
                else if (s.startsWith("t")) {
                    temperature = s.substring(1);
                }
                else if (s.startsWith("d")) {
                    date = s.substring(1);
                }
                else if (s.startsWith("r")) {
                    rating = s.substring(1);
                }


            }
            if( (shotTime != null) && (weight != null) && (temperature != null) &&
                    (date != null) && (rating != null)) {
                array[0] = shotTime;
                array[1] = weight;
                array[2] = temperature;
                array[3] = date;
                array[4] = rating;
                returnArray.add(array);
            }



        }

        return returnArray;
    }

    private ArrayList<LogItem> getLogItems(ArrayList<String[]> formatedData){

        ArrayList<LogItem> returnArray = new ArrayList<>();

        LogItem logToAdd;

        if (formatedData != null){
            for (String[] array : formatedData) {
                logToAdd = new LogItem();
                logToAdd.setDataFromArray(array);
                returnArray.add(logToAdd);
            }
        }

        return returnArray;
    }

}


