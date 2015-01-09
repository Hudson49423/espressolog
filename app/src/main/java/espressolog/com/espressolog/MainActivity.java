package espressolog.com.espressolog;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create db.
        LogSQL db = new LogSQL(this);

        ArrayList<LogItem> logsFromSQL = db.getAllLogs();

        ListAdapter mLogAdapter;
        mLogAdapter = new ListAdapter(
                //the context
                this,
                // the id of the list item layout.
                R.layout.list_item_log,
                // The raw data.
                logsFromSQL);

        // Get a reference to the ListView and attach this adapter to it.
        ListView listView = (ListView) findViewById(R.id.list_view_log);
        listView.setAdapter(mLogAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Context context = getApplicationContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("LogPosition", position);
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
                LogSQL db = new LogSQL(this);
                db.clearAll();
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

}


