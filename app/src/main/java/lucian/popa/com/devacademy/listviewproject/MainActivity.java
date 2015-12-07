package lucian.popa.com.devacademy.listviewproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final CustomViewHolderArrayAdapter adapter =
                new CustomViewHolderArrayAdapter(this, getResources().getStringArray(R.array.listitem_array));
        listview.setAdapter(adapter);
    }

    String date="",km="", timer="", totalTime="";
    int nrIcon=1, faceIcon=1, roadIcon=1, sunIcon=1;

    private void decodeString(String s){
        String[] result = s.split(",");
        date = result[0];
        km = result[1];
        timer = result[2];
        totalTime = result[3];
        nrIcon = Integer.parseInt(result[4]);
        faceIcon = Integer.parseInt(result[5]);
        roadIcon = Integer.parseInt(result[6]);
        sunIcon = Integer.parseInt(result[7]);
    }


    static class ViewHolderItem {
        TextView dateHolderTextView, kmHolderTextView, timerHolderTextView, totalTimeHolderTextView;
        ImageView nrHolderIcon, faceHolderIcon, roadHolderIcon, sunHolderIcon;
    }

    public class CustomViewHolderArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public CustomViewHolderArrayAdapter(Context context, String[] values) {
            super(context, -1, values );
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderItem viewHolder;
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview, parent, false);

                viewHolder = new ViewHolderItem();
                viewHolder.dateHolderTextView = (TextView) convertView.findViewById(R.id.dateTextView);
                viewHolder.kmHolderTextView = (TextView) convertView.findViewById(R.id.kmTextView);
                viewHolder.timerHolderTextView = (TextView) convertView.findViewById(R.id.timerTextView);
                viewHolder.totalTimeHolderTextView = (TextView) convertView.findViewById(R.id.totalTimeTextView);
                viewHolder.nrHolderIcon = (ImageView) convertView.findViewById(R.id.nricon);
                viewHolder.faceHolderIcon = (ImageView) convertView.findViewById(R.id.faceicon);
                viewHolder.roadHolderIcon = (ImageView) convertView.findViewById(R.id.roadicon);
                viewHolder.sunHolderIcon = (ImageView) convertView.findViewById(R.id.sunicon);

                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolderItem) convertView.getTag();
            }

            String listItemString = values[position];
            decodeString(listItemString);

            viewHolder.dateHolderTextView.setText(date);
            viewHolder.dateHolderTextView.setTag(date);
            viewHolder.kmHolderTextView.setText(km);
            viewHolder.kmHolderTextView.setTag(km);
            Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Medium.ttf");
            viewHolder.kmHolderTextView.setTypeface(type);
            viewHolder.timerHolderTextView.setText(timer);
            viewHolder.timerHolderTextView.setTag(timer);
            viewHolder.totalTimeHolderTextView.setText(totalTime);
            viewHolder.totalTimeHolderTextView.setTag(totalTime);
            if (faceIcon == 1) {
                viewHolder.faceHolderIcon.setBackgroundResource(R.drawable.face1);
                viewHolder.faceHolderIcon.setBackgroundResource(R.drawable.face1);
            }
            else if (faceIcon == 2){
                viewHolder.faceHolderIcon.setBackgroundResource(R.drawable.face2);
                viewHolder.faceHolderIcon.setTag(R.drawable.face2);
            }
            if (roadIcon == 1) {
                viewHolder.roadHolderIcon.setBackgroundResource(R.drawable.road1);
                viewHolder.roadHolderIcon.setBackgroundResource(R.drawable.road1);
            }
            else if (faceIcon == 2){
                viewHolder.roadHolderIcon.setBackgroundResource(R.drawable.road2);
                viewHolder.roadHolderIcon.setTag(R.drawable.road2);
            }
            if (sunIcon == 1) {
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun1);
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun1);
            }
            else if (sunIcon == 2){
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun2);
                viewHolder.sunHolderIcon.setTag(R.drawable.sun2);
            }
            else if (sunIcon == 3){
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun3);
                viewHolder.sunHolderIcon.setTag(R.drawable.sun3);
            }

            return convertView;
        }
    }
}
