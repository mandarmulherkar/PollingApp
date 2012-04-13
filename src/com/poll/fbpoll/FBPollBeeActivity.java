package com.poll.fbpoll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.poll.dashboard.DashboardActivity;
import com.poll.utils.FBObjects;

public class FBPollBeeActivity extends Activity {

   int duration = Toast.LENGTH_SHORT;

   

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);

       FBObjects.mFacebook.authorize(this, new DialogListener() {

         @Override
         public void onFacebookError(FacebookError e) {

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
            
            Intent dashIntent = new Intent(FBPollBeeActivity.this, DashboardActivity.class);
            startActivity(dashIntent);
            
            finish();
                        
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

       FBObjects.mFacebook.authorizeCallback(requestCode, resultCode, data);
   }
   
}