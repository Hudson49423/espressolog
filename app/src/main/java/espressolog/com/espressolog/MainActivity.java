package espressolog.com.espressolog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ArrayList<String> data = getFormatedData(dataString);

        ArrayAdapter<String> mLogAdapter;
        mLogAdapter = new ArrayAdapter<String>(
                //the context
                this,
                // the id of the list item layout.
                R.layout.list_item_log,
                // the id of the text view to populate
                R.id.text_title,
                // The raw data.
                data) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String item = "Sample item";

                TextView subTitleView = (TextView) view.findViewById(R.id.test_subtitle);
                subTitleView.setText("Sample subtitle");

                return view;
            }
        };

        // Get a reference to the ListView and attach this adapter to it.
        ListView listView = (ListView) findViewById(R.id.list_view_log);
        listView.setAdapter(mLogAdapter);
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
        }

        return super.onOptionsItemSelected(item);
    }

    private void newLog(){
        Intent intent = new Intent(this, newLog.class);
        startActivity(intent);
    }

    private ArrayList<String> getFormatedData(String dataString) {
        // Initializing the strings we may use to hold data.
        String shotTime = null;
        String weight = null;
        String temperature = null;
        String date = null;

        String[] splitString = dataString.split("##");

        for (String s : splitString) {
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
        }

        ArrayList<String> returnArray = new ArrayList<>();
        //errors are not handled if data is not complete.
        if ((shotTime != null) && (weight != null) && (temperature != null)) {
            returnArray.add(shotTime);
            returnArray.add(weight);
            returnArray.add(temperature);
            returnArray.add(date);
        }
        else {
            returnArray.add("No data was found!");
        }
        return returnArray;
    }
}


