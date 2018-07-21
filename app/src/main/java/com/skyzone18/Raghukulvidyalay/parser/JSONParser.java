package com.skyzone18.Raghukulvidyalay.parser;

/**
 * Created by n9xCh on 23-Aug-16.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class JSONParser {
    /********
     * URLS
     *******/
    public static final String SaveData="http://gnandeepvidhyasankulkathodara.com/web_service/save_data";
    /**
     * TAGs Defined Here...
     */
    public static final String TAG = "TAG";
    /**
     * Response
     */
    private static Response response;


    public static JSONObject insertData(String DeviceID) {
        try {
            OkHttpClient client = new OkHttpClient();
          //  Request request = new Request.Builder().url(Std+ID).build();

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM) //this is what I say in my POSTman (Chrome plugin)
                    .addFormDataPart("serverkey", "AAAATxmGUjM:APA91bEZkpM4jACgfJQ1GRVJIJebqxmmsuOMZtT9c8ccC6ALGPD84JO_PnIhfZP-_WoHiAiCMPCRfXAlwmC88XFKWmRgOC2Hgk4-oytIWTUISJQhcZizf8XNow2sfHlkqKhifYKz0Brg")
                    .addFormDataPart("senderid", "339730649651")
                    .addFormDataPart("devicetoken",DeviceID)
                    .build();

            Request request = new Request.Builder()
                    .url(SaveData)
                    .post(requestBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

}