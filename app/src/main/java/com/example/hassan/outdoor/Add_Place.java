package com.example.hassan.outdoor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Add_Place extends ActionBarActivity {

    private ProgressDialog pDialog;

    EditText inputName;
    EditText inputLong;
    EditText inputLat;
    TextView message;

    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add__place);

        inputName = (EditText) findViewById(R.id.place_name);
        inputLat = (EditText) findViewById(R.id.latitude);
        inputLong = (EditText) findViewById(R.id.longitude);
        message = (TextView) findViewById(R.id.message);

        Button btnAddPlace = (Button) findViewById(R.id.add_place);

        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String lat = inputLat.getText().toString();
                String lon = inputLong.getText().toString();
                new CreateNewPlace().execute(name,lat,lon);
                return;
            }
        });

    }


    class CreateNewPlace extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Add_Place.this);
            pDialog.setMessage("Creating place...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().addPlace(strings);
            int success = 0;
            try {
                success = json.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Building Parameters
            if(success == 1){
                Intent i = new Intent(getApplicationContext(), Add_Place.class);
                startActivity(i);
                // closing this screen
                finish();

            }else{
                Intent i = new Intent(getApplicationContext(),Welcome.class);
                try {
                    i.putExtra("error",json.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);
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

}
