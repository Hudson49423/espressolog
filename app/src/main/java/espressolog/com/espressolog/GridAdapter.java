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
public class GridAdapter extends ArrayAdapter<String[]> {

    public GridAdapter(Context context, int resource, List<String[]> logData) {
        super(context, resource, logData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.grid_item_log, null);
        }

        String[] data = getItem(position);


        if (data != null) {
            TextView heading = (TextView) v.findViewById((R.id.grid_text_heading));
            TextView information = (TextView) v.findViewById((R.id.grid_text_information));


            if (heading != null) {
                String headingText = data[0];
                heading.setText(headingText);
            }
            if (information != null) {
                String informationText = data[1];
                information.setText(informationText);
            }
        }
        return v;
    }
}
