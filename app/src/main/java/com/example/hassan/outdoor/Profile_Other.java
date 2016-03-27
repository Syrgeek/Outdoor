package com.example.hassan.outdoor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;


public class Profile_Other extends ActionBarActivity {

    ListView checkInsList;
    TextView username;
    String userEmail = "";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile__other);

        ImageView btnHome = (ImageView) findViewById(R.id.Home_button);
        btnHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new HomeTask().execute();
            }
        });

        ImageView btnProfile = (ImageView) findViewById(R.id.profile_button);
        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new MyProfileTask().execute();
            }
        });

        checkInsList = (ListView) findViewById(R.id.checkins_list);
        final Button addFriend = (Button) findViewById(R.id.add_friend);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            userEmail = bundle.getString("userEmail");
        }

        if(bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                java.util.List<Checkin> list = new ArrayList<Checkin>();
                JSONObject json = new JSONObject(jsonString);
                TextView tv = (TextView) findViewById(R.id.username);
                tv.setText(json.getString("username"));
                int friends = json.getInt("is_friend");
                if(friends == 1){
                    addFriend.setText("Ufollow");
                }else {
                    addFriend.setText("Follow");
                }
                final JSONArray jar = json.getJSONArray("array");

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

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addFriend.getText().equals("Follow")) {
                    new FollowTask().execute(userEmail);
                    addFriend.setText("Ufollow");
                }
                else {
                    new UnfollowTask().execute(userEmail);
                    addFriend.setText("Follow");
                }
                addFriend.refreshDrawableState();
            }
        });

        Button message = (Button) findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SendMessage.class);
                i.putExtra("userEmail",userEmail);
                startActivity(i);
            }
        });

    }

    class HomeTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile_Other.this);
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
    class MyProfileTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile_Other.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getMyProfile();

            // Building Parameters

            Intent i = new Intent(getApplicationContext(), Profile.class);

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
    class FollowTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile_Other.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().Follow(strings);
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

    class UnfollowTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile_Other.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().Unfollow(strings);
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
        getMenuInflater().inflate(R.menu.menu_profile__other, menu);
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
