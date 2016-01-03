package com.example.hassan.outdoor;

import org.json.JSONException;
import org.json.JSONObject;

public class System {
    public DB db = new DB();
    public static String myEmail = "";

    public void sendnotification(Message not){

    }

    public void showSortedPlaces(List l){

    }

    public void	showMessage(Message msg){

    }

    public boolean isNormalUser(NormalUser user){
        return false;
    }

    public JSONObject like(String... strings) {
        JSONObject json = db.like(strings[0]);
        return json;
    }

    public JSONObject addFriend(String... strings) {
        String userEmail = strings[0];
        JSONObject json = db.addFriend(userEmail);
        return json;
    }

    public JSONObject getQuestion(String...strings) {
        JSONObject json = db.getQuestion(strings[0]);
        return json;
    }

    public JSONObject register(String... strings){
        NormalUser u = new NormalUser();
        u.setEmail(strings[0]);
        u.setUsername(strings[1]);
        u.setPassword(strings[2]);
        u.setQuestion(strings[3]);
        u.setAnswer(strings[4]);
        u.setEmail2(strings[5]);
        JSONObject json =  db.addUser(u);
        return json;
    }

    public JSONObject checkIn(String... strings){
        String status = strings[0];
        String location = strings[1];

        JSONObject json =  db.checkIn(status,location);
        return json;
    }

    public JSONObject login(String... strings){
        String email = strings[0];
        String password = strings[1];

        JSONObject json =  db.login(email,password);

        return json;
    }

    public JSONObject getProfile(String... strings) {

        JSONObject json = db.getProfile(strings[0]);

        return json;
    }

    public JSONObject getMyProfile() {

        JSONObject json = db.getMyProfile();

        return json;
    }

    public  void sendMessage(String... strings) {
        String receiver = strings[0];
        String message = strings[1];
        db.sendMessage(receiver,message);
    }

    public JSONObject getHomeList(String... strings) {

        JSONObject json = db.getHomeList();

        return json;
    }

    public JSONObject answer(String... strings){
        String email = strings[0];
        String ans = strings[1];

        JSONObject json =  db.answer(email,ans);
        return json;
    }

    public void addFollowerToBrand(NormalUser user, Brand b){

    }

    public List getPlacesList(){
        return null;
    }

    public void sortPlaces(List list, String sorting){

    }

    public void addTipToPlace(Comment comm){

    }

    public void addBrand(Brand brand){

    }

    public boolean validateLocation(Location loc){
        return false;
    }

    public void updateUserProfile(NormalUser user){

    }

    public Location getCurrentLocation(){
        return null;
    }

    public String getDirection(Place source, Place destination){
        return "";
    }

    public boolean checkIfVisited(NormalUser user, Place place){
        return false;
    }

    public JSONObject getInbox() {
        JSONObject json = db.getInbox();
        return json;
    }
}

