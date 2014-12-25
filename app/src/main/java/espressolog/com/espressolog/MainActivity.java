package espressolog.com.espressolog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String string = "";

        InputStream in = null;
        try {
            in = openFileInput("myFile");
            int content;
            while ((content = in.read()) != -1) {
                // convert to char.
                string = string + ((char) content);
            }
        }
        catch (Exception e) {
            string = "No data was found";
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

        TextView text = (TextView) findViewById(R.id.data_display);
        text.setText(string);
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
