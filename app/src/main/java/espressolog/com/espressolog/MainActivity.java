package espressolog.com.espressolog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

        ArrayList<String> data = new ArrayList<String>();
        // Dummy data for the array adapter.
        data.add("Sample data");
        data.add("1 data");
        data.add("2 data");
        data.add("4 data");
        data.add("8 data");

        ArrayAdapter<String> mLogAdapter;
        mLogAdapter = new ArrayAdapter<String>(
                //the context
                this,
                // the id of the list item layout.
                R.layout.list_item_log,
                // the id of the text view to populate
                R.id.list_item_log_text_view,
                // The raw data.
                data);

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
}
