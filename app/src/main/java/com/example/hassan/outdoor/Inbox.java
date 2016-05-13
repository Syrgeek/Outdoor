package com.example.hassan.outdoor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * The messaging system of the application
 */

public class Inbox extends ActionBarActivity {
    ListView inbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_inbox);

        inbox = (ListView) findViewById(R.id.inbox_list);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                List<Message> list = new ArrayList<Message>();

                JSONObject json = new JSONObject(jsonString);
                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);

                for(int i=0; i<jar.length();++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String sender = jobj.getString("sender_user_name");
                    String text = jobj.getString("text");
                    String date = jobj.getString("date");
                    String senderEmail = jobj.getString("sender_user_email");

                    list.add(new Message(text,sender,date,senderEmail));
                }

                InboxAdapter adapter = new InboxAdapter(this,list);
                inbox.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
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
