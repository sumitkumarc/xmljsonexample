package com.skyzone18.Raghukulvidyalay.Notification;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.skyzone18.Raghukulvidyalay.parser.JSONParser;

import org.json.JSONObject;


/**
 * Created by n9xCh on 24-Jan-17.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();

        new GetDataTask(token).execute();
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {
        String device_id;
        public GetDataTask(String cat)
        {
            this.device_id=cat;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("I am getting your data");
            dialog.show(); */
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = JSONParser.insertData(device_id);

            /**
             * Check Whether Its NULL???
             */
            if (jsonObject != null) {
                /**
                 * Check Length...
                 */
                if(jsonObject.length() > 0) {
                    /**
                     * Getting Array named "contacts" From MAIN Json Object
                     */
                    Log.d(TAG, "doInBackground: >>>>>>>>"+jsonObject);
                }
            } else {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // dialog.dismiss();
            /*
            if(list.size() > 0) {
                mAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(ImageByCat_Activity.this, "Data Not found", Toast.LENGTH_SHORT).show();
            }*/
            Log.d(TAG, "onPostExecute: DONE");
        }
    }
}
