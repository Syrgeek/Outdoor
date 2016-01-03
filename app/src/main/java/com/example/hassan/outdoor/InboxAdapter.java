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
 * Created by Hassan on 03/01/2016.
 */
public class InboxAdapter extends BaseAdapter{
    List<Message> list;
    Context context;

    public InboxAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    public InboxAdapter(Context context) {
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
        MsgHolder holder = new MsgHolder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.message_form, null);

            TextView s = (TextView) convertView.findViewById(R.id.sender);
            TextView d = (TextView) convertView.findViewById(R.id.date);
            TextView m = (TextView) convertView.findViewById(R.id.message);

            holder.date = d;
            holder.sender = s;
            holder.message = m;

            String date = list.get(position).getDate();
            String sender = list.get(position).getSender();
            String text = list.get(position).getText();

            holder.date.setText(date);
            holder.sender.setText(sender);
            holder.message.setText(text);

        } else {
            holder = (MsgHolder) convertView.getTag();
        }

        final Button replay = (Button) convertView.findViewById(R.id.replay);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String userEmail = list.get(position).getUserEmail();
               Intent intent = new Intent(context,SendMessage.class);
                intent.putExtra("userEmail",userEmail);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}


class MsgHolder {
    TextView sender;
    TextView message;
    TextView date;
}