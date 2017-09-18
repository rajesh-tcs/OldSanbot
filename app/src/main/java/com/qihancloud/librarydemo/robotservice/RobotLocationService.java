package com.qihancloud.librarydemo.robotservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import com.google.gson.Gson;
import com.qihancloud.librarydemo.appconstant.AppConstant;
import com.qihancloud.librarydemo.bean.MeasurmentBean;
import com.qihancloud.librarydemo.bean.MeasurmentDataBean;
import com.qihancloud.librarydemo.utils.CommonUtils;
import com.qihancloud.librarydemo.utils.SharepreferenceKeystore;
import com.qihancloud.opensdk.beans.FuncConstant;
import com.qihancloud.opensdk.function.beans.wheelmotion.DistanceWheelMotion;
import com.qihancloud.opensdk.function.unit.WheelMotionManager;

import java.util.Calendar;

/**
 * Created by 1407053 on 8/8/2017.
 */


public class RobotLocationService extends IntentService {


    public SharepreferenceKeystore sharepreferenceKeystore;

    public RobotLocationService() {
        super("RobotLocationService");
        sharepreferenceKeystore = SharepreferenceKeystore.getInstance(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.d("TAG_Robot","RobotLocationService :::: onHandleIntent");
            boolean flag = false;
            String robotLocationJSON = CommonUtils.getRobotLocation(AppConstant.BASE_URL+AppConstant.GET_LOCATION_COORDINATE+ Calendar.getInstance().getTimeInMillis());
            if (robotLocationJSON != null) {


                Log.d("TAG_Robot", "robotLocationJSON ::: " + robotLocationJSON);
                Gson gson = new Gson();
                MeasurmentDataBean loginResponse = gson.fromJson(robotLocationJSON, MeasurmentDataBean.class);
                double currentRobotX = loginResponse.getMeasurmentBean().getX();
                double currentRobotY = loginResponse.getMeasurmentBean().getY();

                Log.d("TAG_Robot", "currentRobotX ::: " + loginResponse.getMeasurmentBean().getX());
                Log.d("TAG_Robot", "currentRobotY  ::: " + loginResponse.getMeasurmentBean().getY());

                double lastRobotX = Double.parseDouble(sharepreferenceKeystore.getKey("prevCurrentRobotX"));
                double lastRobotY = Double.parseDouble(sharepreferenceKeystore.getKey("prevCurrentRobotY"));
                double dist_1 = Math.sqrt(Math.pow((currentRobotX-lastRobotX),2) + Math.pow((currentRobotY-lastRobotY),2));
                Log.d("TAG_Robot","RobotLocationService dist_1 :::: " +  dist_1*100);

                //if((int) Math.abs(dist_1*100) < 10){
                    if(Math.round(dist_1) == 0){ // 0.98 == 0
                    Log.d("TAG_Robot", "RobotLocationService :::: Both point are same");
                    sharepreferenceKeystore.updateKey("currentX2", "" + currentRobotX);
                    sharepreferenceKeystore.updateKey("currentY2", "" + currentRobotY);
                    flag = true;
                }

                sharepreferenceKeystore.updateKey("prevCurrentRobotX", "" + currentRobotX);
                sharepreferenceKeystore.updateKey("prevCurrentRobotY", "" + currentRobotY);

                if(flag){
                    sharepreferenceKeystore.updateKey("destX", AppConstant.DEST_X);
                    sharepreferenceKeystore.updateKey("destY", AppConstant.DEST_Y);
                    CommonUtils.cancelAlaram(this);
                    //startService(new Intent(this,RotateRobotAngleServices.class));
                    Log.d("TAG_Robot", "RobotLocationService :::: Going to stop RobotLocationService");


                    ResultReceiver rec = intent.getParcelableExtra("receiverTag");
                    Bundle b=new Bundle();
                    rec.send(0, b);

                    //serviceCallbacks.doSomething();
                    //flag = false;
                   // stopSelf();
                }

            }
        } catch (Exception e) {

        }


    }
}
