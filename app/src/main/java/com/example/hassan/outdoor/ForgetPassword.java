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

/**
 * activity to allow user to get to his account if he lost his password
 *
 * needs modifying to work with the new services
 */
public class ForgetPassword extends ActionBarActivity {

    private ProgressDialog pDialog;

    EditText inputAnswer;
    TextView errorMessage;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password);

        inputAnswer = (EditText) findViewById(R.id.security_answer);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            TextView tv = (TextView) findViewById(R.id.security_question);
            tv.setText(bundle.getString("question"));
        }

        if(bundle != null) {
            email = bundle.getString("email");
        }

        if(bundle != null) {
            TextView tv = (TextView) findViewById(R.id.error_message);
            tv.setText(bundle.getString("error"));
        }

        Button btnSubmit = (Button) findViewById(R.id.submit_button);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String ans = inputAnswer.getText().toString();


                new LoginTask().execute(email, ans);

            }
        });
    }

    class LoginTask extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ForgetPassword.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json = new System().answer(strings);
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

                Intent i = new Intent(getApplicationContext(),ForgetPassword.class);
                try {
                    i.putExtra("error",json.getString("message"));
                    i.putExtra("email",email);
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
        getMenuInflater().inflate(R.menu.menu_forget_password, menu);
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
