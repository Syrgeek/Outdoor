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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Home extends ActionBarActivity {

    private ProgressDialog pDialog;

    EditText inputStatus;
    EditText inputLocation;
    EditText inputEmail;
    TextView errorMessage;
    ListView checkInsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        inputStatus = (EditText) findViewById(R.id.status);
        inputLocation = (EditText) findViewById(R.id.location);
        inputEmail = (EditText) findViewById(R.id.userEmail);
        errorMessage = (TextView) findViewById(R.id.error_message);

        ImageView btnProfile = (ImageView) findViewById(R.id.profile_button);
        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               new MyProfileTask().execute(System.myEmail);
            }
        });

        checkInsList = (ListView) findViewById(R.id.checkins_list);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            errorMessage.setText(bundle.getString("error"));
        }

        // Create button
        Button btnCheckIn = (Button) findViewById(R.id.checkin_button);
        // button click event
        btnCheckIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String status = inputStatus.getText().toString();
                String location = inputLocation.getText().toString();

                new CheckInTask().execute(status, location);

            }
        });

        Button btnSearch = (Button) findViewById(R.id.search_button);
        // button click event
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userEmail = inputEmail.getText().toString();
                new ProfileTask().execute(userEmail);

            }
        });

        if(bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                List<Checkin> list = new ArrayList<Checkin>();
                //list.add(new Checkin("Hassan","Cairo","Feeling good","27/12/2015",12,1));

                JSONObject json = new JSONObject(jsonString);
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

    class CheckInTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Checking in..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().checkIn(strings);
            int success = 0;
            try {
                success = json.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Building Parameters
            if(success == 1){
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);

                // closing this screen
                //finish();

            }else{

                Intent i = new Intent(getApplicationContext(),Home.class);
                try {
                    i.putExtra("error",json.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);
                finish();
                // failed to create product
            }
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
            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getProfile(strings);

            // Building Parameters
                    if(json != null) {
                        //Log.d("Create Home", json.toString());
                        Intent i = new Intent(getApplicationContext(),Profile.class);
                        i.putExtra("jsonObject",json.toString());
                        startActivity(i);
                    } else
                        return null;

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
    class ProfileTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getProfile(strings);

            // Building Parameters
            if(json != null) {
                Intent i = new Intent(getApplicationContext(),Profile_Other.class);
                i.putExtra("jsonObject",json.toString());
                i.putExtra("userEmail",strings[0]);
                startActivity(i);
            } else
                return null;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
