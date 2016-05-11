package com.example.hassan.outdoor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.*;

/**
 * Created by Osama on 5/11/2016.
 */
public class NotificationAdapter extends BaseAdapter {
    java.util.List<Notification> list;
    Context context;

    public NotificationAdapter(Context context, java.util.List<Notification> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotificationHolder holder = new NotificationHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notification_form, null);

            holder.user = (TextView) convertView.findViewById(R.id.user);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            String user = list.get(position).getUsername();
            String type = list.get(position).getType();
            String status = list.get(position).getStatus();

            String header = user + " " + type + "ed" + (type.equals("Comment")?" on":"") + " your checkin";
            holder.user.setText(header);
            holder.status.setText("Checkin: " + status);

        } else {
            holder = (NotificationHolder) convertView.getTag();
            String user = list.get(position).getUsername();
            String type = list.get(position).getType();
            String status = list.get(position).getStatus();

            String header = user + " " + type + "ed" + (type.equals("Comment")?" on":"") + " your checkin";
            holder.user.setText(header);
            holder.status.setText("Checkin: " + status);

        }


        return null;
    }
}
class NotificationHolder{
    TextView user;
    TextView status;
}