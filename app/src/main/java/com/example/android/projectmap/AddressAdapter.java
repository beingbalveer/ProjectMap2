package com.example.android.projectmap;


import android.content.Context;
import android.os.LocaleList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.projectmap.ListActivity.checkedLlocation;
import static com.example.android.projectmap.ListActivity.checkedLan;
import static com.example.android.projectmap.ListActivity.checkedLon;
import static com.example.android.projectmap.ListActivity.count;

public class AddressAdapter extends BaseAdapter {

    ArrayList<LocationClass> locationClassArrayList;


    Context context;


    public AddressAdapter(Context _context, ArrayList<LocationClass> locationClassArrayList) {
        context = _context;
        this.locationClassArrayList = locationClassArrayList;


    }

    private class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.address_item, null);

            holder = new ViewHolder();

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.textView = (TextView) convertView.findViewById(R.id.addressView);
            convertView.setTag(holder);

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    LocationClass locationClass = (LocationClass) cb.getTag();
                    locationClass.setSelected(cb.isChecked());

                    if (cb.isChecked()) {
                        if (count < 5) {
                            checkedLlocation.add(locationClass.getLocation());
                            checkedLan.add(locationClass.getLan());
                            checkedLon.add(locationClass.getLon());
                            count++;
                        } else {
                            cb.setChecked(false);
                            locationClass.setSelected(cb.isChecked());
                        }
                    } else {
                        checkedLlocation.remove(locationClass.getLocation());
                        checkedLan.remove(locationClass.getLan());
                        checkedLon.remove(locationClass.getLon());
                        count--;
                    }
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LocationClass locationClass = locationClassArrayList.get(position);
        holder.textView.setText(locationClass.getLocation());
        Log.v("location " , locationClass.getLocation());
        holder.checkBox.setChecked(locationClass.isSelected());
        holder.checkBox.setTag(locationClass);


        return convertView;
    }

    @Override
    public int getCount() {
        return locationClassArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationClassArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
