package com.example.healthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class ListAdapter extends BaseAdapter {

    Context c;
    LayoutInflater inflater;
    JSONArray jsonArray;

    public ListAdapter(Context c, JSONArray jsonArray) {
        this.c = c;
        this.jsonArray = jsonArray;
    }



    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return jsonArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.listadapter,parent,false);
        }


        TextView id = convertView.findViewById(R.id.id);
        TextView type = convertView.findViewById(R.id.type);

        try {

            id.setText("CATEGORY ID - " + String.valueOf(jsonArray.getJSONObject(position).getInt("c_id")));
            type.setText("CATEGORY TYPE - " + jsonArray.getJSONObject(position).getString("type_c"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
