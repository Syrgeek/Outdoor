package com.example.hassan.outdoor;

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

import java.util.*;

/**
 * Created by SyrGeek on 5/5/2016.
 */
public class CommentAdapter extends BaseAdapter {
    List<Comment> list;
    Context context;

    public CommentAdapter(Context context, List<Comment> list) {
        this.list = list;
        this.context = context;
    }

    public CommentAdapter(Context context) {
        this.context = context;
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
        CommHolder holder = new CommHolder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comments_item, null);

            TextView u = (TextView) convertView.findViewById(R.id.username);
            TextView d = (TextView) convertView.findViewById(R.id.date);
            TextView t = (TextView) convertView.findViewById(R.id.text);

            holder.date = d;
            holder.user = u;
            holder.text = t;

            String date = list.get(position).getDate();
            String user = list.get(position).getUser();
            String text = list.get(position).getText();

            holder.date.setText(date);
            holder.user.setText(user);
            holder.text.setText(text);
            convertView.setTag(holder);
        } else {
            holder = (CommHolder) convertView.getTag();
            String date = list.get(position).getDate();
            String user = list.get(position).getUser();
            String text = list.get(position).getText();
            holder.date.setText(date);
            holder.user.setText(user);
            holder.text.setText(text);

        }

        return convertView;
    }
}

class CommHolder {
    TextView user;
    TextView text;
    TextView date;
}