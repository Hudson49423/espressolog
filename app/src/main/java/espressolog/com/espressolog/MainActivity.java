package espressolog.com.espressolog;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create db.
        LogSQL db = new LogSQL(this);

        LinkedList<LogItem> logsFromSQL = db.getAllLogs();

        TextView t1 = (TextView) findViewById(R.id.help_title);
        TextView t2 = (TextView) findViewById(R.id.help_body);
        // Check to see if the user has any saved logs.
        if (logsFromSQL.size() != 0) {
            t1.setVisibility(TextView.GONE);
            t2.setVisibility(TextView.GONE);

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
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_newLog:
                newLog();
                return true;

            case R.id.action_clear:
                // Make sure the user wants to delete logs.
                TextView msg = new TextView(this);
                msg.setText("Are you sure?");
                msg.setPadding(20, 10, 10, 10);
                msg.setGravity(Gravity.CENTER);
                msg.setTextSize(20);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setView(msg);
                alertDialogBuilder.setPositiveButton("Delete All Logs", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAllLogs();
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", null);
                alertDialogBuilder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void newLog(){
        Intent intent = new Intent(this, newLog.class);
        startActivity(intent);
    }

    public void deleteAllLogs() {
        LogSQL db = new LogSQL(this);
        db.clearAll();
        // Refresh the activity.
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }


    // ---------------------------------------------------------------------------------------------

    public static class AdFragment extends Fragment {

        private AdView mAdView;

        public AdFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_ad, container, false);
        }

        @Override
        public void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);
            AdView mAdView = (AdView) getView().findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        /** Called when leaving the activity */
        @Override
        public void onPause() {
            if (mAdView != null) {
                mAdView.pause();
            }
            super.onPause();
        }

        /** Called when returning to the activity */
        @Override
        public void onResume() {
            super.onResume();
            if (mAdView != null) {
                mAdView.resume();
            }
        }

        /** Called before the activity is destroyed */
        @Override
        public void onDestroy() {
            if (mAdView != null) {
                mAdView.destroy();
            }
            super.onDestroy();
        }

    }
}


