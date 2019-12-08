package com.example.healthapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoveProduct extends AppCompatActivity {
    TextView out1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //out1 = (TextView) findViewById(R.id.textView1);

        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        String o1;

        protected Void doInBackground(Void... params) {

            URL url = null;

            Intent myNewIntent = getIntent();

            int InfoReceivedId = myNewIntent.getIntExtra("ID", 99);

            try {

                url = new URL("http://192.168.5.16:8080/WebApplication5/webresources/mobile/removeproduct&" + InfoReceivedId);


                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n sending 'GET' request to URL : " + url);

                System.out.println("Response code : " + responseCode);

                InputStreamReader myInput = new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                JSONObject obj = new JSONObject(response.toString());
                o1 = "" +obj.getString("message");


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
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(),o1,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RemoveProduct.this, MainActivity.class);
            startActivity(intent);


            super.onPostExecute(result);
        }
    }
}
