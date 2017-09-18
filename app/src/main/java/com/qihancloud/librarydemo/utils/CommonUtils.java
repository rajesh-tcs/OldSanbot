package com.qihancloud.librarydemo.utils;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.qihancloud.librarydemo.HomeActivity;
import com.qihancloud.librarydemo.R;
import com.qihancloud.librarydemo.robotservice.RobotLocationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CommonUtils {

    private CommonUtils() {
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        boolean flag = netInfo != null && netInfo.isConnected();
        if(!flag) {
            Toast.makeText(context, "Check network", Toast.LENGTH_SHORT).show();
        }
        return flag;

    }

    public static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    public static String getRobotLocation(String api_url){
        String server_response = null;
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(api_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = CommonUtils.readStream(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server_response; //"{\"x\":34,\"y\":34}";//server_response;
    }

    public static void startAlaram(Context context, long timeFrequency) {
        MyResultReceiver mReceiver;
        mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver((MyResultReceiver.Receiver) context);
        AlarmManager mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RobotLocationService.class);
        intent.putExtra("receiverTag", mReceiver);
        PendingIntent pendingIntent = PendingIntent.getService(context, 111, intent, 0 );
        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + timeFrequency, timeFrequency,
                pendingIntent);
    }

    public static void cancelAlaram(Context context) {
        AlarmManager mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RobotLocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 111, intent, 0 );
        mAlarmManager.cancel(pendingIntent);
    }

    public static double checkDistant(double x2, double x1, double y2, double y1){
        Log.d("TAG_Robot","x2  ::: " + x2);
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }



}
