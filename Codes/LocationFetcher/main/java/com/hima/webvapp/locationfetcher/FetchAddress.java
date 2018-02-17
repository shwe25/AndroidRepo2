package com.hima.webvapp.locationfetcher;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hima on 17/2/18.
 */

public class FetchAddress extends AsyncTask {
    String data="";
    String res = "";
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+LocationActivity.location);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            Log.d("Connection","Opened");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line!=null) {
                Log.d("Line",line);
                line = reader.readLine();
                data += line;
            }
            Log.d("Data",data);

            JSONObject object = new JSONObject(data);
            String status = object.getString("status");
            String long_name = object.getJSONArray("results")
                    .getJSONObject(0).getJSONArray("address_components")
                    .getJSONObject(0).getString("long_name");
            double lng = object.getJSONArray("results")
                    .getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").getDouble("lng");
            double lat = object.getJSONArray("results")
                    .getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").getDouble("lat");
            String place_id = object.getJSONArray("results")
                    .getJSONObject(0).getString("place_id");

            res = "Status: "+status+"\nLong Name: "+long_name
                    +"\nLongitude: "+lng+"\nLatitude: "+lat+"\nPlace_id: "+place_id;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                JSONObject object = new JSONObject(data);
                String error_message = object.getString("error_message");
                String status = object.getString("status");
                res = "Error Message: "+error_message+"\nStatus: "+status;
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        LocationActivity.textView.setText(res);
    }
}
