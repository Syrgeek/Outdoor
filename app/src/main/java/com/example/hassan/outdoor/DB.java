package com.example.hassan.outdoor;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama on 12/25/2015.
 */
public class DB {

    private static final String url = "http://outdoor.netne.net/";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();

    public JSONObject getQuestion(String email) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loginEmail",email));
        String file = "Recover1.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;

    }

    public JSONObject answer(String email,String ans) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loginEmail",email));
        params.add(new BasicNameValuePair("securityAnswer", ans));

        String file = "Recover2.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject login(String email,String password) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loginEmail",email));
        params.add(new BasicNameValuePair("loginPassword", password));

        String file = "Login.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getProfile(String email) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",email));

        String file = "GetUserCheckins.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public void sendMessage(String receiver,String message) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",receiver));
        params.add(new BasicNameValuePair("message", message));

        String file = "AddMessage.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
    }

    public JSONObject getMyProfile() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        String file = "GetMyCheckins.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getHomeList() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        String file = "GetFriendsCheckin.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject checkIn(String status,String location) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("status",status));
        params.add(new BasicNameValuePair("placeName", location));

        String file = "AddCheckin.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject like(String checkin_id) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("checkin_id",checkin_id));

        String file = "AddLike.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject addFriend(String userEmail) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("addedEmail",userEmail));

        String file = "AddFriend.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject  addUser(NormalUser u){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",u.getEmail()));
        params.add(new BasicNameValuePair("username", u.getUsername()));
        params.add(new BasicNameValuePair("password", u.getPassword()));
        params.add(new BasicNameValuePair("securityQuestion",u.getQuestion()));
        params.add(new BasicNameValuePair("securityAnswer", u.getAnswer()));
        params.add(new BasicNameValuePair("altEmail", u.getEmail2()));

        String file = "Signup.php";
        // getting JSON Object
        // Note that create product url accepts POST method

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        //if(json == null)
          //  return false;

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check json success tag
        // check for success tag


        return json ;
    }

    public JSONObject getInbox() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        String file = "GetUserMessages.php";

        JSONObject json = jsonParser.makeHttpRequest(url + file,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }
}
