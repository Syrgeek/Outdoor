package com.example.hassan.outdoor;

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
                String[] list = new String[1000];

                JSONObject json = new JSONObject(jsonString);
                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);


                for(int i=0; i<jar.length();++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String name = jobj.getString("name");
                    list[i] = name;
                }


                ArrayAdapter adapter = new ArrayAdapter(this,R.layout.follower_item,list);
                followers.setAdapter(adapter);

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
