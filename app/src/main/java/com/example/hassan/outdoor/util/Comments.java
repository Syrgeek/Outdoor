package com.example.hassan.outdoor.util;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.example.hassan.outdoor.Comment;
import com.example.hassan.outdoor.CommentAdapter;
import com.example.hassan.outdoor.InboxAdapter;
import com.example.hassan.outdoor.Message;
import com.example.hassan.outdoor.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Comments extends ActionBarActivity {
    ListView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_comments);

        comments = (ListView) findViewById(R.id.comments_list);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String jsonString = bundle.getString("jsonObject");
            try {
                List<Comment> list = new ArrayList<Comment>();

                JSONObject json = new JSONObject(jsonString);
                String arr = json.getString("array");
                JSONArray jar = new JSONArray(arr);

                for(int i=0; i<jar.length();++i) {
                    JSONObject jobj = jar.getJSONObject(i);
                    String user = jobj.getString("username");
                    String text = jobj.getString("text");
                    String date = jobj.getString("date");

                    list.add(new Comment(user,text,date));
                }

                CommentAdapter adapter = new CommentAdapter(this,list);
                comments.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
