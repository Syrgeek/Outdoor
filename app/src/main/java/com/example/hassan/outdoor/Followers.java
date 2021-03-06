package com.example.hassan.outdoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.*;

/**
 * activity for displaying a list of followers
 */
public class Followers extends ActionBarActivity {
    ListView followers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_followers);

        followers = (ListView) findViewById(R.id.followers_list);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {

                JSONObject json = new JSONObject(jsonString);
                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);
                String list[] = new String[jar.length()];

                  for(int i=0; i < jar.length();++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String name = jobj.getString("name");
                    list[i] = name;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.follower_item,R.id.followers_id,list);
                followers.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            Intent intent = new Intent(getApplicationContext(),Welcome.class);
            startActivity(intent);
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
