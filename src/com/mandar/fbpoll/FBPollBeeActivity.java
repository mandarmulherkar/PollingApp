package com.mandar.fbpoll;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FBPollBeeActivity extends Activity {

   int duration = Toast.LENGTH_SHORT;

   Facebook facebook = new Facebook("");

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);

       facebook.authorize(this, new DialogListener() {

         @Override
         public void onFacebookError(FacebookError e) {
            // TODO Auto-generated method stub

            Toast toast = Toast.makeText(getBaseContext(), "Error"+e.getErrorCode(), duration);
            toast.show();
         }

         @Override
         public void onError(DialogError e) {

            Toast toast = Toast.makeText(getBaseContext(), "Error"+e.getMessage(), duration);
            toast.show();            
         }

         @Override
         public void onComplete(Bundle values) {

            Toast toast = Toast.makeText(getBaseContext(), "complete", duration);
            toast.show();            
         }

         @Override
         public void onCancel() {
            // TODO Auto-generated method stub
            
         }
       });
   }

   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       facebook.authorizeCallback(requestCode, resultCode, data);
   }
   
}