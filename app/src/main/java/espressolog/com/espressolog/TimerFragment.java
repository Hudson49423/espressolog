package espressolog.com.espressolog;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class TimerFragment extends Fragment {

    private boolean keepGoing;
    private TextView textView;
    private final StopWatch timer = new StopWatch();

    public TimerFragment() {
        keepGoing = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                startTimer(null);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(null);
            }
        });
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                resetTimer(null);
            }
        });
        return relativeLayout;
    }


    public void startTimer(View view) {
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
                                textView.setText(timer.getFormatedTime());
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.stop();
            }
        };
        thread.start();
    }

    public void stopTimer(View view) {
        keepGoing = false;
    }

    public void resetTimer(View view) {
        stopTimer(null);
        timer.reset();
    }

    public class StopWatch {
        private long startTime;
        private long stopTime;

        public StopWatch() {
            stopTime = 0;
        }

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public long getElapsedTime() {
            return (System.currentTimeMillis() - startTime) + stopTime;
        }

        public void reset() {
            stopTime = 0;
        }

        public void stop(){
            stopTime = getElapsedTime();
        }

        public String getFormatedTime(){
            long time = getElapsedTime();

            try {
                if(time < 1000) {
                    String seconds = "00";
                    String colon = ":";
                    String milis = "" + time;
                    String end = milis.substring(0, 2);
                    return seconds + colon + end;

                }
                else if (time < 10000) {
                    String timeString = "" + time;
                    String start = "0";
                    String second = "" + timeString.substring(0,1);
                    String colon = ":";
                    String end = timeString.substring(2, 4);
                    return start + second + colon + end;
                }
                else {
                    String timeString = "" + time;
                    String start = timeString.substring(0,2);
                    String colon = ":";
                    String end = timeString.substring(3,5);
                    return start + colon + end;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "ERROR";
            }
        }
    }
}
