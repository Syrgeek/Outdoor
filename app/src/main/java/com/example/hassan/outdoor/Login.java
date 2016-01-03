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


public class Login extends ActionBarActivity {

    private ProgressDialog pDialog;

    EditText inputEmail;
    EditText inputPassword;
    TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.login_email);
        inputPassword = (EditText) findViewById(R.id.login_password);
        errorMessage = (TextView) findViewById(R.id.error_message);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            errorMessage.setText(bundle.getString("error"));
        }

        Button btnLogin = (Button) findViewById(R.id.login_button);
        Button btnForget = (Button) findViewById(R.id.forget_button);

        btnForget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EnterEmail.class);
                startActivity(i);
            }
        });

        // button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                new LoginTask().execute(email, password);
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
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Logging in..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating account
         * */
        protected String doInBackground(String... strings) {

            JSONObject json1 = new System().login(strings);
            JSONObject json2 = new System().getProfile(strings);

            int success = 0;

            try {
                success = json1.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Building Parameters
            if(success == 1){
                Intent i = new Intent(getApplicationContext(), Profile.class);
                try {
                    System.myEmail = strings[0];
                    i.putExtra("username",json1.getString("message"));
                    if(json2 != null)
                        i.putExtra("jsonObject",json2.toString());
                    else {
                        return null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);
                // closing this screen
                finish();
            }else{
                Intent i = new Intent(getApplicationContext(),Login.class);
                try {
                    i.putExtra("error",json1.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);
                finish();
                // failed to create product
            }

            ///////////////////////////////////////////////////////////////////////

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
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
