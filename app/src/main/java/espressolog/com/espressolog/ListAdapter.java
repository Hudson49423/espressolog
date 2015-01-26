package espressolog.com.espressolog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hudson49423 on 1/1/15.
 * Populates the list items that display a user's saved logs.
 */
public class ListAdapter extends ArrayAdapter<LogItem> {

    public ListAdapter(Context context, int resource, List<LogItem> logs) {
        super(context, resource, logs);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_log, null);
        }

        LogItem l = getItem(position);

        if (l != null) {
            TextView title = (TextView) v.findViewById((R.id.text_date));
            TextView subtitle = (TextView) v.findViewById(R.id.subtitle);
            TextView middle = (TextView) v.findViewById(R.id.middle);
            TextView right = (TextView) v.findViewById(R.id.right);

            // Various booleans to see if a variable has already been used.
            boolean shotWeight = false;
            boolean shotTime = false;
            boolean dose = false;
            boolean rating = false;
            boolean brewRatio = false;
            boolean temperature = false;
            boolean date = false;

            if (title != null) {
                if ((l.getShotWeight() != null) && (!l.getShotWeight().equals("null"))) {
                    title.setText("Shot Weight: " + (l.getShotWeight()));
                    shotWeight = true;
                } else if ((l.getShotTime() != null) && (!l.getShotTime().equals("null"))) {
                    title.setText("Shot Time: " + (l.getShotTime()));
                    shotTime = true;
                } else if ((l.getDose() != null) && (!l.getDose().equals("null"))) {
                    title.setText("Dose: " + (l.getDose()));
                    dose = true;
                } else if ((l.getBrewRatio() != null) && (!l.getBrewRatio().equals("null"))) {
                    title.setText("Brew Ratio: " + (l.getBrewRatio()));
                    brewRatio = true;
                } else if ((l.getTemperature() != null) && (!l.getTemperature().equals("null"))) {
                    title.setText("Shot temperature: " + (l.getTemperature()));
                    temperature = true;
                }
            }

            if (subtitle != null) {
                if ((l.getShotWeight() != null) && (!l.getShotWeight().equals("null"))
                        && !shotWeight) {
                    subtitle.setText("Shot Weight: " + (l.getShotWeight()));
                    shotWeight = true;
                } else if ((l.getShotTime() != null) && (!l.getShotTime().equals("null"))
                        && !shotTime) {
                    subtitle.setText("Shot Time: " + (l.getShotTime()));
                    shotTime = true;
                } else if ((l.getDose() != null) && (!l.getDose().equals("null"))
                        && !dose) {
                    subtitle.setText("Dose: " + (l.getDose()));
                    dose = true;
                } else if ((l.getBrewRatio() != null) && (!l.getBrewRatio().equals("null"))
                        && !brewRatio) {
                    subtitle.setText("Brew Ratio: " + (l.getBrewRatio()));
                    brewRatio = true;
                } else if ((l.getTemperature() != null) && (!l.getTemperature().equals("null"))
                        && !temperature) {
                    subtitle.setText("Shot temperature: " + (l.getTemperature()));
                    temperature = true;
                }
            }

            if (middle != null) {
                if ((l.getBrewRatio() != null) && (!l.getBrewRatio().equals("null"))
                        && !brewRatio) {
                    middle.setText("" + (l.getBrewRatio()));
                    brewRatio = true;
                } else if ((l.getTemperature() != null) && (!l.getTemperature().equals("null"))
                        && !temperature) {
                    middle.setText("" + (l.getTemperature()));
                    temperature = true;
                } else if ((l.getDate() != null) && (!l.getDate().equals("null"))
                        && !temperature) {
                    middle.setText("" + (l.getDate()));
                    date = true;
                }
            }

            if (right != null) {
                if ((l.getRating() != null) && (!l.getRating().equals("null"))) {
                    right.setText("" + l.getRating() + "/10");
                }
                else if ((l.getBrewRatio() != null) && (!l.getBrewRatio().equals("null"))
                        && !brewRatio) {
                    right.setText(l.getBrewRatio());
                } else if ((l.getTemperature() != null) && (!l.getTemperature().equals("null"))
                        && !temperature) {
                    right.setText(l.getTemperature());
                } else if ((l.getDate() != null) && (!l.getDate().equals("null"))
                        && !date) {
                    right.setText("" + (l.getDate()));
                }
            }
        }

        return v;
    }
}
