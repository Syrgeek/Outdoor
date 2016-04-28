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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class PlacePage extends ActionBarActivity {
    private ProgressDialog pDialog;
    private TextView tvGlobalRate;
    private TextView tvVoters;
    private TextView tvName;
    private RatingBar ratingBar;
    private EditText etComment;
    private Button btnPost;
    JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_page);
        Bundle bundle = getIntent().getExtras(); //modify later

        tvGlobalRate = (TextView)findViewById(R.id.globalRate);
        tvVoters = (TextView)findViewById(R.id.voters);
        ratingBar = (RatingBar)findViewById(R.id.rating);
        etComment = (EditText)findViewById(R.id.placeComment);
        btnPost = (Button)findViewById(R.id.postToPlace);
        if(bundle != null){
            try {
                String jsonString = bundle.getString("jsonObject");
                json = new JSONObject(jsonString);
                String name = json.getString("name");
                double rate = json.getDouble("rate");
                int numOfUsers = json.getInt("numberOfUsers");
                double myRate = json.getDouble("myRate");

                tvName = (TextView)findViewById(R.id.pageName);
                tvName.setText(name);

                String modified = (rate + "");
                if(modified.length() > 5){
                    modified = modified.substring(0, 4);
                }
                tvGlobalRate.setText(modified);
                tvVoters.setText(numOfUsers+"");
                Toast.makeText(PlacePage.this,myRate+"",Toast.LENGTH_SHORT).show();
                if(myRate > 0){
                    ratingBar.setIsIndicator(true);
                    ratingBar.setRating((float)myRate);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setIsIndicator(true);
                new RateTask().execute(tvName.getText().toString(), "" + ratingBar.getRating());
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommentToPlace().execute(etComment.getText().toString(), tvName.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_page, menu);
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

    class RateTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PlacePage.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {
            JSONObject json1 = new System().ratePlace(strings);

            // Building Parameters
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            try {
                double rate = json.getDouble("rate");
                int numOfUsers = json.getInt("numberOfUsers");
                String newRate = (((float) (rate * numOfUsers + ratingBar.getRating()) / (numOfUsers + 1))+"");
                if(newRate.length() > 5)
                    newRate = newRate.substring(0, 4);

                tvGlobalRate.setText(newRate);
                tvVoters.setText((numOfUsers+1)+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            pDialog.dismiss();
            // Intent back = new Intent(getApplicationContext(),MainActivity.class);
            // startActivity(back);
        }

    }



    class CommentToPlace extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PlacePage.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {
            JSONObject json1 = new System().commentToPlace(strings);

            // Building Parameters
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            etComment.setText("");
            pDialog.dismiss();
            // Intent back = new Intent(getApplicationContext(),MainActivity.class);
            // startActivity(back);
        }

    }


}
