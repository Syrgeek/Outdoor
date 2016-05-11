package com.example.hassan.outdoor;

/**
 * Created by Osama on 5/4/2016.
 */

import java.util.*;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama on 4/5/2015.
 */
public class PlaceAdapter extends BaseAdapter {

    java.util.List<Comment> list;
    Context context;

    public PlaceAdapter(java.util.List<Comment> list, Context context) {
        this.list = list;
        this.context = context;
    }

    PlaceAdapter(Context c) {
        context = c;
        list = new ArrayList<Comment>();
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PlaceHolder holder = new PlaceHolder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.place_list_item, null);

            TextView u = (TextView) convertView.findViewById(R.id.placeCommentUsername);
            TextView d = (TextView) convertView.findViewById(R.id.placeCommentDate);
            TextView s = (TextView) convertView.findViewById(R.id.placeCommentText);

            holder.date = d;
            holder.text = s;
            holder.username = u;

            String date = list.get(position).getDate().toString();
            String username = list.get(position).getUser();
            String text = list.get(position).getText();
            //String likes = Integer.toString(list.get(position).getLikes());
            //int liked = list.get(position).getIf_liked();

            holder.date.setText(date);
            holder.username.setText(username);
            holder.text.setText(text);
            convertView.setTag(holder);
        } else {
            holder = (PlaceHolder) convertView.getTag();

            String date = list.get(position).getDate().toString();
            String username = list.get(position).getUser();
            String text = list.get(position).getText();

            holder.date.setText(date);
            holder.username.setText(username);
            holder.text.setText(text);
        }
        return convertView;
    }
}

class PlaceHolder {
    TextView username;
    TextView text;
    TextView date;
}