package com.example.hassan.outdoor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * activity to display notifications
 */
public class Notifications extends ActionBarActivity {
    ListView notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notifications);
        notifications =  (ListView)findViewById(R.id.notifications_list);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                java.util.List<Notification> list = new ArrayList<Notification>();

                JSONObject json = new JSONObject(jsonString);
                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);

                for(int i=0; i<jar.length();++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String username = jobj.getString("username");
                    String status = jobj.getString("status");
                    String type = jobj.getString("type");

                    list.add(new Notification(username, status, type));
                }

                NotificationAdapter adapter = new NotificationAdapter(this,list);
                notifications.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
