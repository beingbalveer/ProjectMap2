package com.example.android.projectmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> locationList;
    ArrayList<Double> lanList;
    ArrayList<Double> lonList;
    static ArrayList<String> checkedLlocation;
    static ArrayList<Double> checkedLan;
    static ArrayList<Double> checkedLon;
    AddressAdapter adapter;
    static int count = 0;
    ListView listView;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkedLlocation = new ArrayList<>(0);
        checkedLan = new ArrayList<>(0);
        checkedLon = new ArrayList<>(0);


        Intent intent = getIntent();
        locationList = intent.getExtras().getStringArrayList("LOCATION");
        lanList = (ArrayList<Double>) intent.getExtras().getSerializable("LAN");
        lonList = (ArrayList<Double>) intent.getExtras().getSerializable("LON");

        ArrayList<LocationClass> locationClassArrayList = new ArrayList<>(0);


        for (int i=0;i<locationList.size();i++)
        {
            LocationClass locationClass = new LocationClass(locationList.get(i),lanList.get(i),lonList.get(i),false);
            locationClassArrayList.add(locationClass);
        }

        adapter = new AddressAdapter(this,locationClassArrayList);
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);


    }




    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.checkAll:
                checkAll(item);
                break;
            case R.id.submit:
                submit();
        }
        return super.onOptionsItemSelected(item);
    }

    void checkAll(MenuItem item)
    {
        String s = item.getTitle().toString();

        for (int i = 0; i < listView.getCount(); i++) {
            View itemLayout = listView.getAdapter().getView(i, null, listView);
            CheckBox cb = (CheckBox) itemLayout.findViewById(R.id.checkbox);
            LocationClass locationClass = (LocationClass) cb.getTag();
            cb.setChecked(false);
            locationClass.setSelected(cb.isChecked());
            checkedLlocation.remove(locationClass.getLocation());
            checkedLan.remove(locationClass.getLan());
            checkedLon.remove(locationClass.getLon());
        }


        if (s.equals("uncheck")) {

            for (int i = 0; i < listView.getCount(); i++) {
                View itemLayout = listView.getAdapter().getView(i, null, listView);
                CheckBox cb = (CheckBox) itemLayout.findViewById(R.id.checkbox);
                LocationClass locationClass = (LocationClass) cb.getTag();
                if (i < 5) {
                    cb.setChecked(true);
                    locationClass.setSelected(cb.isChecked());
                    checkedLlocation.add(locationClass.getLocation());
                    checkedLan.add(locationClass.getLan());
                    checkedLon.add(locationClass.getLon());
                    count = 5;

                } else {
                    cb.setChecked(false);
                    locationClass.setSelected(cb.isChecked());
                    checkedLlocation.remove(locationClass.getLocation());
                    checkedLan.remove(locationClass.getLan());
                    checkedLon.remove(locationClass.getLon());
                }


            }
            item.setTitle("check");
            item.setIcon(android.R.drawable.checkbox_on_background);
        } else {
            for (int i = 0; i < listView.getCount(); i++) {
                View itemLayout = listView.getAdapter().getView(i, null, listView);
                CheckBox cb = (CheckBox) itemLayout.findViewById(R.id.checkbox);
                LocationClass locationClass = (LocationClass) cb.getTag();
                cb.setChecked(false);
                locationClass.setSelected(cb.isChecked());
                checkedLlocation.remove(locationClass.getLocation());
                checkedLan.remove(locationClass.getLan());
                checkedLon.remove(locationClass.getLon());
                count = 0;

            }
            //   Toast.makeText(this, "count " + count, Toast.LENGTH_LONG).show();
            item.setTitle("uncheck");
            item.setIcon(android.R.drawable.checkbox_off_background);
        }
        adapter.notifyDataSetChanged();
    }

    void submit()
    {
        ArrayList<String> source = new ArrayList<>(0);
        ArrayList<String> destination = new ArrayList<>(0);
        ArrayList<Double> distence = new ArrayList<>(0);

        for (int i=0;i<checkedLlocation.size()-1;i++)
        {
            for (int j=i+1;j<checkedLlocation.size();j++)
            {
                double d = findDistance(checkedLan.get(i),checkedLon.get(i),checkedLan.get(j),checkedLon.get(j));
         //       double d = findDistance(checkedLon.get(i),checkedLan.get(i),checkedLon.get(j),checkedLan.get(j));

                Log.d("tag","\n" + checkedLan.get(i) + "  " + checkedLon.get(i));
                distence.add(d);
                source.add(checkedLlocation.get(i));
                destination.add(checkedLlocation.get(j));
            }
        }
        Intent intent = new Intent(this,ShowDistence.class);

        intent.putStringArrayListExtra("SOURCE",source);
        intent.putStringArrayListExtra("DESTINATION",destination);
        intent.putExtra("DISTENCE",distence);
        startActivity(intent);
    }



    static double findDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
