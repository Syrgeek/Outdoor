package com.example.hassan.outdoor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Hassan on 18/12/2015.
 */



public class Register extends ActionBarActivity {

    private ProgressDialog pDialog;

    EditText inputEmail;
    EditText inputName;
    EditText inputPassword;
    EditText inputQuestion;
    EditText inputAnswer;
    EditText inputEmail2;
    TextView errorMessage;



    //private static String url_create_account = "http://outdoor.site88.net/create_account2.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        // Edit Text
        inputEmail = (EditText) findViewById(R.id.email);
        inputName = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        inputQuestion = (EditText) findViewById(R.id.question);
        inputAnswer = (EditText) findViewById(R.id.answer);
        inputEmail2 = (EditText) findViewById(R.id.email2);
        errorMessage = (TextView) findViewById(R.id.error_message);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            errorMessage.setText(bundle.getString("error"));
        }

        // Create button
        Button btnCreateAccount = (Button) findViewById(R.id.register);

        // button click event
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String username = inputName.getText().toString();
                String password = inputPassword.getText().toString();
                String question = inputQuestion.getText().toString();
                String answer = inputAnswer.getText().toString();
                String email2 = inputEmail2.getText().toString();

                new CreateNewAccount().execute(email, username, password,question,answer,email2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    class CreateNewAccount extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating Account...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().register(strings);
            int success = 0;
            try {
                success = json.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Building Parameters
            if(success == 1){

                Intent i = new Intent(getApplicationContext(), Welcome.class);
                try {
                    i.putExtra("username",json.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);
                // closing this screen
                finish();

            }else{
                Intent i = new Intent(getApplicationContext(),Register.class);
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

