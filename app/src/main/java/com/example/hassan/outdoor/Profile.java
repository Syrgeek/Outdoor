package com.example.hassan.outdoor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.Object;


public class Profile extends ActionBarActivity {

    ListView checkInsList;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        Button logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView btnHome = (ImageView) findViewById(R.id.Home_button);
        btnHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new HomeTask().execute();
            }
        });

        Button inbox = (Button) findViewById(R.id.inbox_button);
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InboxTask().execute();
            }
        });

        checkInsList = (ListView) findViewById(R.id.checkins_list);

        Bundle bundle = getIntent().getExtras();

        /*if(bundle != null) {
            TextView tv = (TextView) findViewById(R.id.username);
            tv.setText(bundle.getString("username"));
        }*/

        if(bundle != null) {
           String jsonString = bundle.getString("jsonObject");
            try {
                List<Checkin> list = new ArrayList<Checkin>();

                JSONObject json = new JSONObject(jsonString);
                TextView tv = (TextView) findViewById(R.id.username);
                tv.setText(json.getString("username"));
                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);

                for(int i=0; i<jar.length();++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String username = jobj.getString("username");
                    String place = jobj.getString("checkin_place_name");
                    String status = jobj.getString("status");
                    String date = jobj.getString("date");
                    int likes = jobj.getInt("likes");
                    int id = jobj.getInt("checkin_id");
                    int like = jobj.getInt("if_liked");

                    list.add(new Checkin(username,place,status,date,likes,id,like));
                }
                Adapter adapter = new Adapter(list,this);
                checkInsList.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class HomeTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getHomeList(strings);

                Intent i = new Intent(getApplicationContext(), Home.class);
            if(json != null)
                i.putExtra("jsonObject",json.toString());
            else
                return null;

            startActivity(i);
                // closing this screen
                finish();

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            // Intent back = new Intent(getApplicationContext(),MainActivity.class);
            // startActivity(back);
        }

    }
    class InboxTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getInbox();

            Intent i = new Intent(getApplicationContext(), Inbox.class);
            if(json != null)
                i.putExtra("jsonObject",json.toString());
            else
                return null;

            startActivity(i);
            // closing this screen
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            // Intent back = new Intent(getApplicationContext(),MainActivity.class);
            // startActivity(back);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
