package espressolog.com.espressolog;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
                                textView.setText(timer.getFormatedTime());
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

        public String getFormatedTime(){
            long time = getElapsedTime();
            long seconds = time / 1000;
            return "" + seconds;
        }
    }
}
