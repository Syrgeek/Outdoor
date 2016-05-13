package com.example.hassan.outdoor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Display my profile
 */
public class Profile extends AppCompatActivity {

    ListView checkInsList;
    private ProgressDialog pDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        ImageView btnHome = (ImageView) findViewById(R.id.home_button);
        btnHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new HomeTask().execute();
            }
        });

        checkInsList = (ListView) findViewById(R.id.checkins_list);

        Bundle bundle = getIntent().getExtras();

        /*if(bundle != null) {
            TextView tv = (TextView) findViewById(R.id.username);
            tv.setText(bundle.getString("username"));
        }*/

        if (bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                List<Checkin> list = new ArrayList<Checkin>();

                JSONObject json = new JSONObject(jsonString);
                TextView tv = (TextView) findViewById(R.id.username);
                tv.setText(System.USERNAME);

                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);

                for (int i = 0; i < jar.length(); ++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String username = System.USERNAME;
                    String place = jobj.getString("checkin_place_name");
                    String status = jobj.getString("status");
                    String date = jobj.getString("date");
                    int likes = jobj.getInt("likes");
                    int id = jobj.getInt("checkin_id");
                    int like = jobj.getInt("if_liked");
                    int comments = jobj.getInt("numOfComments");

                    list.add(new Checkin(username, place, status, date, likes, id, like,comments));
                }
                CheckinAdapter checkinAdapter = new CheckinAdapter(list, this);
                checkInsList.setAdapter(checkinAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Profile Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.hassan.outdoor/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Profile Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.hassan.outdoor/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class HomeTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
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
         */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getHomeList(strings);

            Intent i = new Intent(getApplicationContext(), Home.class);
            if (json != null)
                i.putExtra("jsonObject", json.toString());
            else
                return null;

            startActivity(i);
            // closing this screen
            finish();

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
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
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... strings) {

            JSONObject json = new System().getInbox();

            Intent i = new Intent(getApplicationContext(), Inbox.class);
            if (json != null)
                i.putExtra("jsonObject", json.toString());
            else
                return null;

            startActivity(i);
            // closing this screen
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            // Intent back = new Intent(getApplicationContext(),MainActivity.class);
            // startActivity(back);
        }

    }
    class FollowersTask extends AsyncTask<String, String, String> {

        /**
         * `         * Before starting background thread Show Progress Dialog
         */
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
         */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getFollowers();

            Intent i = new Intent(getApplicationContext(), Followers.class);
            if (json != null)
                i.putExtra("jsonObject", json.toString());
            else
                return null;

            startActivity(i);
            // closing this screen
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            // Intent back = new Intent(getApplicationContext(),MainActivity.class);
            // startActivity(back);
        }

    }
    class NotificationsTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... strings) {

            JSONObject json = new System().getAllNotifications();

            Intent i = new Intent(getApplicationContext(), Notifications.class);
            if (json != null)
                i.putExtra("jsonObject", json.toString());
            else
                return null;

            startActivity(i);
            // closing this screen
            return null;
        }

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_inbox:
                new InboxTask().execute();
                return true;

            case R.id.action_logout:
                intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.action_addPlace:
                intent = new Intent(getApplicationContext(),AddPlace.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.action_followers:
                new FollowersTask().execute();
                return true;

            case R.id.action_notifications:
                new NotificationsTask().execute();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
