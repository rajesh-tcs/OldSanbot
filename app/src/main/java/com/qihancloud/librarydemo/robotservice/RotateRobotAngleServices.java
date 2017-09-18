package com.qihancloud.librarydemo.robotservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.qihancloud.librarydemo.HomeActivity;
import com.qihancloud.librarydemo.MainService;
import com.qihancloud.librarydemo.utils.CommonUtils;
import com.qihancloud.librarydemo.utils.SharepreferenceKeystore;
import com.qihancloud.opensdk.base.BindBaseService;
import com.qihancloud.opensdk.beans.FuncConstant;
import com.qihancloud.opensdk.function.beans.wheelmotion.DistanceWheelMotion;
import com.qihancloud.opensdk.function.beans.wheelmotion.RelativeAngleWheelMotion;
import com.qihancloud.opensdk.function.unit.WheelMotionManager;

import java.net.URISyntaxException;

import io.socket.client.IO;

/**
 * Created by 1407053 on 8/17/2017.
 */

public class RotateRobotAngleServices extends BindBaseService {
    public double currentX1, currentY1, currentX2, currentY2, destX, destY;
    public SharepreferenceKeystore sharepreferenceKeystore;

    private WheelMotionManager wheelMotionManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        register(RotateRobotAngleServices.class);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: onCreate");

        sharepreferenceKeystore = SharepreferenceKeystore.getInstance(this);
        wheelMotionManager = (WheelMotionManager) getUnitManager(FuncConstant.WHEELMOTION_MANAGER);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: onStartCommand");
        //findDestinationAndMove();
        return START_STICKY;
    }



    @Override
    protected void onMainServiceConnected() {
        //findDestinationAndMove();
    }


    public class MyBinder extends Binder {

        public RotateRobotAngleServices getService(){
            return RotateRobotAngleServices.this;
        }
    }

    private RotateRobotAngleServices.MyBinder myBinder = new RotateRobotAngleServices.MyBinder();
}
