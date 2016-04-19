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

/**
 * Created by Hassan on 27/12/2015.
 */
public class Adapter extends BaseAdapter {

    List<Checkin> list;
    Context context;

    public Adapter(List<Checkin> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Adapter(Context c) {
        context = c;
        list = new ArrayList<Checkin>();
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
        Holder holder = new Holder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            TextView u = (TextView) convertView.findViewById(R.id.username);
            TextView p = (TextView) convertView.findViewById(R.id.place);
            TextView d = (TextView) convertView.findViewById(R.id.date);
            TextView s = (TextView) convertView.findViewById(R.id.status);
            TextView l = (TextView) convertView.findViewById(R.id.likes);
            TextView lb = (TextView) convertView.findViewById(R.id.like_button);

            holder.date = d;
            holder.place = p;
            holder.status = s;
            holder.username = u;
            holder.likes = l;
            holder.like = lb;

            String date = list.get(position).getDate();
            String place = list.get(position).getPlace();
            String username = list.get(position).getUsername();
            String status = list.get(position).getStatus();
            String likes = Integer.toString(list.get(position).getLikes());
            int liked = list.get(position).getIf_liked();

            holder.date.setText(date);
            holder.username.setText(username);
            holder.status.setText(status);
            holder.place.setText(place);
            holder.likes.setText(likes);
            if(liked == 1)
                holder.like.setText("Unlike");
            else
                holder.like.setText("Like");

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
            String date = list.get(position).getDate();
            String place = list.get(position).getPlace();
            String username = list.get(position).getUsername();
            String status = list.get(position).getStatus();
            String likes = Integer.toString(list.get(position).getLikes());
            int liked = list.get(position).getIf_liked();

            holder.date.setText(date);
            holder.username.setText(username);
            holder.status.setText(status);
            holder.place.setText(place);
            holder.likes.setText(likes);
            if(liked == 1)
                holder.like.setText("Unlike");
            else
                holder.like.setText("Like");

        }

        final TextView like = (TextView) convertView.findViewById(R.id.like_button);
        final TextView likes = (TextView) convertView.findViewById(R.id.likes);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkId = list.get(position).getId();
                int current = list.get(position).getIf_liked();
                int num = list.get(position).getLikes();
                if(current == 1) {
                    like.setText("Like");
                    likes.setText(Integer.toString(num-1));
                    list.get(position).setLikes(num-1);
                    list.get(position).setIf_liked(0);
                    like.refreshDrawableState();
                    likes.refreshDrawableState();
                } else {
                    like.setText("Unlike");
                    likes.setText(Integer.toString(num+1));
                    list.get(position).setLikes(num+1);
                    list.get(position).setIf_liked(1);
                    like.refreshDrawableState();
                    likes.refreshDrawableState();
                }
                new LikeTask().execute(Integer.toString(checkId));
            }
        });
        return convertView;
    }
}

class LikeTask extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * Creating account
     * */
    protected String doInBackground(String... strings) {

        JSONObject json = new System().like(strings);
        return null;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String file_url) {
        // dismiss the dialog once done
        //pDialog.dismiss();
        // Intent back = new Intent(getApplicationContext(),MainActivity.class);
        // startActivity(back);
    }

}

class Holder {
    TextView username;
    TextView status;
    TextView place;
    TextView date;
    TextView likes;
    TextView like;
}