package lucian.popa.com.devacademy.listviewproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {

    int state[] = new int[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ListItem> itemsFromList = new ArrayList<ListItem>();
        Random rand = new Random();
        for (int i=1; i<=1000; i++){
            int kmRand = rand.nextInt(1000) + 1;
            int timerMinRand = rand.nextInt(10) + 1;
            int timerSecRand = rand.nextInt(59) + 1;
            int totalTimeMinRand = rand.nextInt(59) + 1;
            int totalTimeSecRand = rand.nextInt(59) + 1;
            int faceIconRand = rand.nextInt(2) + 1;
            int roadIconRand = rand.nextInt(2) + 1;
            int sunIconRand = rand.nextInt(3) + 1;
            int boolRand = rand.nextInt(2) + 1;
            boolean checked;
            if (boolRand % 2 == 0) checked = true;
            else checked = false;
            ListItem item = new ListItem("Today",
                    ""+kmRand/100+"."+kmRand%100,
                    ""+timerMinRand+"\'"+timerSecRand+"\"",
                    ""+totalTimeMinRand+":"+totalTimeSecRand,
                    1,faceIconRand,roadIconRand,sunIconRand, checked);
            itemsFromList.add(item);
        }

        final ListView listview = (ListView) findViewById(R.id.listview);

        final CustomViewHolderArrayAdapter2 adapter2 =
                new CustomViewHolderArrayAdapter2(this, itemsFromList);
        listview.setAdapter(adapter2);
    }

    private class ListItem {
        String date,km,timer,totalTime;
        int nrIcon,faceIcon,roadIcon,sunIcon;
        boolean selected;

        public ListItem(String Date, String Km, String Timer, String TotalTime,
                        int NrIcon, int FaceIcon, int RoadIcon, int SunIcon,
                        boolean Selected){
            date = Date;
            km = Km;
            timer = Timer;
            totalTime = TotalTime;
            nrIcon = NrIcon;
            faceIcon = FaceIcon;
            roadIcon = RoadIcon;
            sunIcon = SunIcon;
            selected = Selected;
        }
    }

    static class ViewHolderItem {
        TextView dateHolderTextView, kmHolderTextView, timerHolderTextView, totalTimeHolderTextView;
        ImageView nrHolderIcon, faceHolderIcon, roadHolderIcon, sunHolderIcon;
        CheckBox checkHolderBox;
    }

    public class CustomViewHolderArrayAdapter2 extends ArrayAdapter<ListItem> {
        private final Context context;
        private final ArrayList<ListItem> values;

        public CustomViewHolderArrayAdapter2(Context context, ArrayList<ListItem> values) {
            super(context, -1, values );
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolderItem viewHolder;

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
                viewHolder.checkHolderBox = (CheckBox) convertView.findViewById(R.id.checkBox);

                convertView.setTag(viewHolder);

                viewHolder.checkHolderBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        ListItem item = (ListItem) cb.getTag();
                        item.selected = cb.isChecked();
                    }
                });
            }
            else{
                viewHolder = (ViewHolderItem) convertView.getTag();
            }

            ListItem currentItem = values.get(position);

            viewHolder.dateHolderTextView.setText(currentItem.date);
            viewHolder.kmHolderTextView.setText(currentItem.km);
            Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Medium.ttf");
            viewHolder.kmHolderTextView.setTypeface(type);
            viewHolder.timerHolderTextView.setText(currentItem.timer);
            viewHolder.totalTimeHolderTextView.setText(currentItem.totalTime);
            if (currentItem.faceIcon == 1) {
                viewHolder.faceHolderIcon.setBackgroundResource(R.drawable.face1);
            }
            else if (currentItem.faceIcon == 2){
                viewHolder.faceHolderIcon.setBackgroundResource(R.drawable.face2);
            }
            if (currentItem.roadIcon == 1) {
                viewHolder.roadHolderIcon.setBackgroundResource(R.drawable.road1);
            }
            else if (currentItem.roadIcon == 2){
                viewHolder.roadHolderIcon.setBackgroundResource(R.drawable.road2);
            }
            if (currentItem.sunIcon == 1) {
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun1);
            }
            else if (currentItem.sunIcon == 2){
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun2);
            }
            else if (currentItem.sunIcon == 3){
                viewHolder.sunHolderIcon.setBackgroundResource(R.drawable.sun3);
            }
            viewHolder.checkHolderBox.setChecked(currentItem.selected);
            viewHolder.checkHolderBox.setTag(currentItem);

            return convertView;
        }
    }
}
