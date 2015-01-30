package espressolog.com.espressolog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class TimerFragment extends Fragment {

    private boolean keepGoing;
    private TextView textView;
    private final StopWatch timer = new StopWatch();
    private int stopTime;
    private int stopTimeUpdated;

    public TimerFragment() {
        keepGoing = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopTime = 0;
        stopTimeUpdated = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_timer, container, false);
        Button start = (Button) relativeLayout.findViewById(R.id.button_start);
        Button stop = (Button) relativeLayout.findViewById(R.id.button_stop);
        Button reset = (Button) relativeLayout.findViewById(R.id.button_reset);
        textView = (TextView) relativeLayout.findViewById(R.id.time_display);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        return relativeLayout;
    }

    @Override
    public void onStop() {
        super.onStop();
        keepGoing = false;
    }

    @Override
    public void onPause(){
        super.onPause();
        keepGoing = false;
    }


    public void startTimer() {
        keepGoing = true;
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    timer.start();
                    while(keepGoing) {
                        sleep(500);
                        getActivity().runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                if (keepGoing) {
                                    int seconds = timer.getFormatedTime() + stopTime;
                                    textView.setText("" + seconds);
                                }
                            }
                        });
                    }
                    //timer.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void stopTimer() {
        keepGoing = false;
        setStopTime();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if((prefs.getBoolean("shot_time_checkbox", true)) && stopTime > 9) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Would you like to use this time in a new log?");
            builder.setNegativeButton("No", null);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getActivity(), newLog.class);
                    intent.putExtra("time", textView.getText().toString());
                    startActivity(intent);
                }
            });
            builder.show();
        }
    }

    public void resetTimer(){
        keepGoing = false;
        setStopTime();

        stopTime = 0;
        textView.setText("0");
    }

    private void setStopTime(){
        try {
            stopTime = Integer.parseInt(textView.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Acts as a very simple stop watch. Only deals with whole seconds.
     */
    public class StopWatch {
        private long startTime;


        public StopWatch() {
        }

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public long getElapsedTime() {
            return (System.currentTimeMillis() - startTime);
        }

        public int getFormatedTime(){
            long time = getElapsedTime();
            long seconds = time / 1000;
            int secondsInt = 0;
            try {
                secondsInt = Math.round(seconds);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return secondsInt;
        }
    }
}
