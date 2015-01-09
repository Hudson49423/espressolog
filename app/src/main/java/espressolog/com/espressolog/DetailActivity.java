package espressolog.com.espressolog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Create the db.
        LogSQL db = new LogSQL(this);

        // Get the log's position
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("LogPosition");
        Log.v("Position: ", "" + position);

        // Get the log.
        LogItem log = db.getAllLogs().get(position);
        Log.v("Log id: ", "" + log.getId());

        // Get the arrays
        ArrayList<String[]> data = log.getDataInArray();

        // Create the adapter.
        GridAdapter mGridAdapter;
        mGridAdapter = new GridAdapter(this, R.layout.grid_item_log, data);

        // Get a reference to the GridView and attach the adapter to it.
        GridView gridView = (GridView) findViewById(R.id.detail_grid);
        gridView.setAdapter(mGridAdapter);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
