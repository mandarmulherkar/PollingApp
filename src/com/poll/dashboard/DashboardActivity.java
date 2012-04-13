package com.poll.dashboard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;
import com.poll.fbpoll.R;
import com.poll.utils.FBObjects;

//TODO 
//This will be turned into a dashboard activity and the methods here sent to their respective activities

public class DashboardActivity extends Activity{
   
   protected static final String TAG = DashboardActivity.class.getSimpleName();
   private TextView textview; 
   private AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(FBObjects.mFacebook);
   private Handler mHandler;
   
   @Override
   public void onCreate(Bundle savedInstanceState){
      
      super.onCreate(savedInstanceState);
      setContentView(R.layout.dashboard);
      textview = (TextView) findViewById(R.id.welcomeMessage);
      textview.setText("Welcome to the fb poll app!");
      
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
   }

   @Override
   protected void onResume() {
      super.onResume();
      
      mHandler = new Handler();
      getInfo();
   }

   
   private void getInfo() {

      textview.setText("Fetching your info");
      Bundle params = new Bundle();
      
      params.putString("fields", "name, picture, ");
      mAsyncRunner.request("me", params, new RequestListener(){

         @Override
         public void onComplete(String response, Object state) {
            Log.v(TAG, ""+response);
            //{"name":"Mandar Mulherkar","id":"100003548002777","picture":"http:\/\/profile.ak.fbcdn.net\/static-ak\/rsrc.php\/v1\/yo\/r\/UlIqmHJn-SK.gif"}
            JSONObject jsonObject;
            try {
               jsonObject = new JSONObject(response);
               final String name = jsonObject.getString("name");
               if(name != null)
               {
                  mHandler.post(new Runnable(){

                     @Override
                     public void run() {
                        textview.setText("Welcome, "+name);
                        
                     }});
                  
               }
            } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
         }

         @Override
         public void onIOException(IOException e, Object state) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void onFileNotFoundException(FileNotFoundException e, Object state) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void onMalformedURLException(MalformedURLException e, Object state) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void onFacebookError(FacebookError e, Object state) {
            // TODO Auto-generated method stub
            
         }});
      
   }
   
}
