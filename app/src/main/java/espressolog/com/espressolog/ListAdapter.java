package espressolog.com.espressolog;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hudson49423 on 1/1/15.
 */
public class ListAdapter extends ArrayAdapter<LogItem> {

    public ListAdapter (Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

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
            TextView dateText = (TextView) v.findViewById((R.id.text_date));
            TextView shotTimeText = (TextView) v.findViewById(R.id.text_details);
            TextView ratingText = (TextView) v.findViewById(R.id.text_rating);

            if (dateText != null) {
                dateText.setText(l.getDate());
            }
            if (shotTimeText != null) {
                shotTimeText.setText("Shot Time: " + (l.getShotTime()));
            }
            if (ratingText != null){
                ratingText.setText((l.getRating()) + "/10");
            }
        }

        return v;
    }
}
