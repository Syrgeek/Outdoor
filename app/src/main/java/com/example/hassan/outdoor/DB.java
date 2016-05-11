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

    private static final String url = "http://outdoorbackend-outdoor2.rhcloud.com/Outdoor-Backend/rest/";
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

        String service = "login";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getProfile(String email) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("my_email",System.myEmail));
        params.add(new BasicNameValuePair("his_email",email));

        String service = "getProfile";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getPlaceComments(String place) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("placeName",place));
        String service = "getPlaceComments";
        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);
        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject ratePlace(String placeName, String rate) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("placeName",placeName));
        params.add(new BasicNameValuePair("rate",rate));

        String service = "ratePlace";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getPlace(String name) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("placeName",name));


        String service = "getPlace";
        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject commentToPlace(String text, String name) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("text", text));
        params.add(new BasicNameValuePair("email", System.myEmail));
        params.add(new BasicNameValuePair("placeName", name));


        String service = "commentToPlace";
        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getNearestLocation(String lat, String lon) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("lat", lat));
        params.add(new BasicNameValuePair("long", lon));

        String service = "getNearestLocation";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public void sendMessage(String receiver,String message) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user_email",receiver));
        params.add(new BasicNameValuePair("sender_user_email",System.myEmail));
        params.add(new BasicNameValuePair("text", message));

        String service = "addMessage";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
    }

    public JSONObject getHomeList() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));

        String service = "getFriendsCheckins";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Home", json.toString());
        return json;
    }

    public JSONObject checkIn(String status,String location) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("placeName", location));
        params.add(new BasicNameValuePair("status",status));

        String service = "checkin";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject addPlace(String name,String lat,String lon) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("placeName", name));
        params.add(new BasicNameValuePair("lon",lon));
        params.add(new BasicNameValuePair("lat",lat));

        String service = "addPlace";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject like(String checkin_id) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("checkin_id",checkin_id));

        String service = "like";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject getCheckinComments(String checkin_id) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("checkin_id",checkin_id));

        String service = "getCheckinComments";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject comment(String checkin_id,String text) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("checkin_id",checkin_id));
        params.add(new BasicNameValuePair("text",text));

        String service = "commentToCheckin";
        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject likePlaceComment(String checkin_id) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("checkin_id",checkin_id));

        String service = "like";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject Follow(String userEmail) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("friendEmail",userEmail));

        String service = "follow";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());

        return json;
    }

    public JSONObject Unfollow(String userEmail) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        params.add(new BasicNameValuePair("friendEmail",userEmail));

        String service = "unfollow";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

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

        String service = "signup";
        // getting JSON Object
        // Note that create product url accepts POST method

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

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
        params.add(new BasicNameValuePair("email",System.myEmail));

        String service = "getAllMessages";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }

    public JSONObject getFollowers() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email",System.myEmail));
        String service = "getFollowers";

        JSONObject json = jsonParser.makeHttpRequest(url + service,"POST", params);

        Log.d("Create Response", json.toString());
        return json;
    }
}
