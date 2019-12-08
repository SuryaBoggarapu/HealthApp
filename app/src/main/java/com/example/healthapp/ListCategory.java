package com.example.healthapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.Inflater;

public class ListCategory extends AppCompatActivity {



    ListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allcategories);



        listView = findViewById(R.id.listView);

        new MyTask().execute();
    }

    public class MyTask extends AsyncTask<Void, Void, Void>{

        String id, type;

        @Override
        protected Void doInBackground(Void... voids) {
            URL url = null;

            try{
                url = new URL("http://192.168.5.16:8080/WebApplication5/webresources/mobile/Allcategories");

                HttpURLConnection httpURLConnection = null;

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("GET");

                int responseCode = httpURLConnection.getResponseCode();

                System.out.println("URL: " + url);
                System.out.println("Response Code: " + responseCode);

                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());

                BufferedReader in = new BufferedReader(inputStreamReader);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response: " + response.toString());

                JSONObject mainObj = new JSONObject(response.toString());

                JSONArray jsonArray = mainObj.getJSONArray("All categories");
                listAdapter = new ListAdapter(getApplicationContext(), jsonArray);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listView.setAdapter(listAdapter);
            super.onPostExecute(aVoid);
        }
    }
}
