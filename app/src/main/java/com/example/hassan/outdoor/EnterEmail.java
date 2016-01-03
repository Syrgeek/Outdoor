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
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class EnterEmail extends ActionBarActivity {

    private ProgressDialog pDialog;

    EditText inputEmail;
    TextView errorMessage;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_enter_email);

        inputEmail = (EditText) findViewById(R.id.enter_email);
        errorMessage = (TextView) findViewById(R.id.error_message);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            errorMessage.setText(bundle.getString("error"));
        }

        Button btnEnter = (Button) findViewById(R.id.enter_button);

        btnEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email = inputEmail.getText().toString();

                new EnterTask().execute(email);
            }
        });

    }

    class EnterTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EnterEmail.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().getQuestion(strings);
            int success = 0;
            try {
                success = json.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Building Parameters
            if(success == 1){

                Intent i = new Intent(getApplicationContext(), ForgetPassword.class);
                try {
                    String question = json.getString("message");
                    i.putExtra("question",question);
                    i.putExtra("email",email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(i);


                // closing this screen
                finish();

            }else{

                Intent i = new Intent(getApplicationContext(),EnterEmail.class);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_email, menu);
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
