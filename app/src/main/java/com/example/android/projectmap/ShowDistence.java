package com.example.android.projectmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowDistence extends AppCompatActivity {

    ArrayList<String> source = new ArrayList<>(0);
    ArrayList<String> destination = new ArrayList<>(0);
    ArrayList<Double> distence = new ArrayList<>(0);

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_distence);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.distence_listview);

        Intent intent = getIntent();

        source = intent.getExtras().getStringArrayList("SOURCE");
        destination = intent.getExtras().getStringArrayList("DESTINATION");
        distence = (ArrayList<Double>) intent.getExtras().getSerializable("DISTENCE");

    //    Toast.makeText(this,"distence " + distence.size(),Toast.LENGTH_LONG).show();

        DistenceAdapter adapter = new DistenceAdapter(this,source,destination,distence);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
