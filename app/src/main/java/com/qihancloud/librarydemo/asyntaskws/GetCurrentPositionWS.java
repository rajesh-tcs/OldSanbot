package com.qihancloud.librarydemo.asyntaskws;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qihancloud.librarydemo.HomeActivity;
import com.qihancloud.librarydemo.bean.MeasurmentBean;
import com.qihancloud.librarydemo.bean.MeasurmentDataBean;
import com.qihancloud.librarydemo.utils.CommonUtils;
import com.qihancloud.opensdk.function.beans.wheelmotion.DistanceWheelMotion;
import com.qihancloud.opensdk.function.beans.wheelmotion.RelativeAngleWheelMotion;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 1407053 on 8/17/2017.
 */

public class GetCurrentPositionWS extends AsyncTask<String , Void ,String> {

    String server_response;
    boolean callType;

    double oldRobotX;
    double oldRobotY;
    MyAsyncTaskCallback myAsyncTaskCallback;


    public GetCurrentPositionWS(boolean callType, Context context) {
        this.callType = callType;
        //this.myAsyncTaskCallback = (MyAsyncTaskCallback) context;
    }

    public void setMyAsyncTaskListener(MyAsyncTaskCallback myAsyncTaskCallback) {
        this.myAsyncTaskCallback = myAsyncTaskCallback;
    }


    @Override
    protected String doInBackground(String... strings) {


        Log.e("TAG_Robot", "strings[0]  ::: " + strings[0]);
        String server_response
                = CommonUtils.getRobotLocation(strings[0]);
        Log.e("TAG_Robot", "server_response  ::: " + server_response);
        return server_response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //s="{\"x\":100,\"y\":100}";
        Log.e("TAG_Robot", "server_response sss  ::: " + s);
        Gson gson = new Gson();
        MeasurmentDataBean loginResponse = gson.fromJson(s, MeasurmentDataBean.class);
        Log.e("TAG_Robot", "loginResponse.getMeasurmentBean().getX() ::: " + loginResponse.getMeasurmentBean().getX());
        Log.e("TAG_Robot", "loginResponse.getMeasurmentBean().getY() sss  ::: " + loginResponse.getMeasurmentBean().getY());
        if(loginResponse.getMeasurmentBean().getX()!= null) {
            myAsyncTaskCallback.onRobotCurrentStatus(loginResponse.getMeasurmentBean().getX(),
                    loginResponse.getMeasurmentBean().getY());
        }
    }

    public interface MyAsyncTaskCallback{
        public void onRobotCurrentStatus(double xValue, double yValue);
    }

}
