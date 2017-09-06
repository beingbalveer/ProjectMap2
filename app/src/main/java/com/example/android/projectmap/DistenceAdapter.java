package com.example.android.projectmap;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DistenceAdapter extends BaseAdapter {

    ArrayList<String> source;
    ArrayList<String> destination;
    ArrayList<Double> distence;

    Context context;
    int count = 0;


    public DistenceAdapter(Context _context,ArrayList<String> source, ArrayList<String> destination, ArrayList<Double> distence )
    {
        context = _context;
        this.source = source;
        this.destination = destination;
        this.distence = distence;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.distence_list_item,parent,false);
        }
        TextView source_text = (TextView)convertView.findViewById(R.id.source_text);
        TextView destination_text = (TextView)convertView.findViewById(R.id.destination_text);
        TextView distence_text = (TextView)convertView.findViewById(R.id.distence_text);
        source_text.setText(source.get(position));
        destination_text.setText(destination.get(position));
        String result = String.format("%.2f", distence.get(position));
        distence_text.setText("" + result+  " KM");

        return convertView;
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Object getItem(int position) {
        return source.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
