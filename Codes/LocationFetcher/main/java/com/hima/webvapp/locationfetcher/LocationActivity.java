package com.hima.webvapp.locationfetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    SearchView searchView;
    static String location;
    static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        searchView = findViewById(R.id.searchView);
        textView = findViewById(R.id.textView);
    }
    public void click(View view){
        location = searchView.getQuery().toString();
        //textView.setText(location);
        FetchAddress address = new FetchAddress();
        address.execute();
    }
}
