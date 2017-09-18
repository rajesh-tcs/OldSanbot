package com.qihancloud.librarydemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.qihancloud.librarydemo.Sppeech.MainSpeechActivity;
import com.qihancloud.librarydemo.appconstant.AppConstant;
import com.qihancloud.librarydemo.asyntaskws.GetCurrentPositionWS;
import com.qihancloud.librarydemo.utils.CommonUtils;
import com.qihancloud.librarydemo.utils.MyResultReceiver;
import com.qihancloud.librarydemo.utils.SharepreferenceKeystore;
import com.qihancloud.opensdk.base.TopBaseActivity;
import com.qihancloud.opensdk.beans.FuncConstant;
import com.qihancloud.opensdk.beans.OperationResult;
import com.qihancloud.opensdk.function.beans.wheelmotion.DistanceWheelMotion;
import com.qihancloud.opensdk.function.beans.wheelmotion.RelativeAngleWheelMotion;
import com.qihancloud.opensdk.function.unit.WheelMotionManager;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * className: com.qihancloud.librarydemo.HomeActivity
 * function: 功能菜单
 * <p/>
 * create at 2017/5/22 10:31
 *
 * @author gangpeng
 */

public class HomeActivity extends TopBaseActivity implements MyResultReceiver.Receiver {

    /**
     * 标题
     */
    @Bind(R.id.tv_title)
    TextView tvTitle;
    /**
     * 语音控制
     */
    @Bind(R.id.tv_speech_control)
    TextView tvSpeechControl;
    /**
     * 硬件控制
     */
    @Bind(R.id.tv_hardware_control)
    TextView tvHardwareControl;
    /**
     * 头部控制
     */
    @Bind(R.id.tv_head_control)
    TextView tvHeadControl;
    /**
     * 手部控制
     */
    @Bind(R.id.tv_hand_control)
    TextView tvHandControl;
    /**
     * 轮子控制
     */
    @Bind(R.id.tv_wheel_control)
    TextView tvWheelControl;
    /**
     * 系统控制
     */
    @Bind(R.id.tv_system_control)
    TextView tvSystemControl;
    /**
     * 投影仪控制
     */
    @Bind(R.id.tv_projector_control)
    TextView tvProjectorControl;
    /**
     * 多媒体控制
     */
    @Bind(R.id.tv_media_control)
    TextView tvMediaControl;
    /**
     * 模块化运动控制
     */
    @Bind(R.id.tv_modularity_control)
    TextView tvModularityControl;
    /**
     * 预处理指令
     */
    @Bind(R.id.tv_preprocessor_control)
    TextView tvPreprocessorControl;


    @Bind(R.id.tv_move_to_destination)
    TextView tvMoveToDestination;


    private SharepreferenceKeystore sharepreferenceKeystore;
    private WheelMotionManager wheelMotionManager;
    private double destX;
    private double destY;
    private double currentX1;
    private double currentY1;
    private double currentX2;
    private double currentY2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_home);
        ButterKnife.bind(this);

        //设置顶部状态栏颜色及标题
        GradientDrawable topDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT
                , new int[]{Color.parseColor("#2B6DF8"), Color.parseColor("#00A2ED")});
        setHeadBackground(topDrawable);
        tvTitle.setText(R.string.title);

        sharepreferenceKeystore = SharepreferenceKeystore.getInstance(this);
        wheelMotionManager = (WheelMotionManager) getUnitManager(FuncConstant.WHEELMOTION_MANAGER);
    }

    @Override
    protected void onMainServiceConnected() {

    }

    @OnClick({R.id.tv_speech_control, R.id.tv_hardware_control, R.id.tv_head_control, R.id.tv_hand_control,
            R.id.tv_wheel_control, R.id.tv_system_control, R.id.tv_projector_control, R.id.tv_media_control,
            R.id.tv_modularity_control, R.id.tv_preprocessor_control, R.id.tv_move_to_destination,
            R.id.tv_google_speech, R.id.tv_webview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_speech_control:
                Intent intent = new Intent(HomeActivity.this, SpeechControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_hardware_control:
                intent = new Intent(HomeActivity.this, HardwareControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_head_control:
                intent = new Intent(HomeActivity.this, HeadControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_hand_control:
                intent = new Intent(HomeActivity.this, HandControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_wheel_control:
                intent = new Intent(HomeActivity.this, WheelControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_system_control:
                intent = new Intent(HomeActivity.this, SystemControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_projector_control:
                intent = new Intent(HomeActivity.this, ProjectorControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_media_control:
                intent = new Intent(HomeActivity.this, MediaControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_modularity_control:
                intent = new Intent(HomeActivity.this, ModularityControlActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_preprocessor_control:
                intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_move_to_destination:
                final GetCurrentPositionWS getCurrentPositionWS = new GetCurrentPositionWS(false,getApplicationContext());
                getCurrentLocation(getCurrentPositionWS);

                Log.d("TAG_ROBOT","URL :: " + AppConstant.BASE_URL+AppConstant.GET_LOCATION_COORDINATE+ Calendar.getInstance().getTimeInMillis());
                getCurrentPositionWS.execute(AppConstant.BASE_URL+AppConstant.GET_LOCATION_COORDINATE+ Calendar.getInstance().getTimeInMillis());
                break;

            case R.id.tv_google_speech:
                intent = new Intent(HomeActivity.this, MainSpeechActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_webview:
                intent = new Intent(HomeActivity.this, WebviewActivity.class);
                startActivity(intent);
                break;

        }
    }


    void getCurrentLocation(GetCurrentPositionWS getCurrentPositionWS){
        getCurrentPositionWS.setMyAsyncTaskListener(new GetCurrentPositionWS.MyAsyncTaskCallback()
        {
            @Override
            public void onRobotCurrentStatus(double xValue, double yValue) {
                if(xValue < 1000 || yValue < 1000) {
                    sharepreferenceKeystore.updateKey("prevCurrentRobotX",""+xValue);
                    sharepreferenceKeystore.updateKey("prevCurrentRobotY",""+yValue);
                    Log.d("TAG_Robot", "GetCurrentPositionWS :::: output");
                    Log.d("TAG_Robot", "GetCurrentPositionWS :::: xValue ::: " + xValue);
                    Log.d("TAG_Robot", "GetCurrentPositionWS :::: yValue ::: " + yValue);
                    sharepreferenceKeystore.updateKey("currentX1", "" + xValue);
                    sharepreferenceKeystore.updateKey("currentY1", "" + yValue);
                    DistanceWheelMotion distanceWheelMotion = new DistanceWheelMotion(DistanceWheelMotion.ACTION_FORWARD_RUN, 20, 200);
                    wheelMotionManager.doDistanceMotion(distanceWheelMotion);
                    CommonUtils.startAlaram(HomeActivity.this, 5000);

                }else{
                    Log.d("TAG_Robot", "GetCurrentPositionWS :::: 111111 ");
                    final GetCurrentPositionWS getCurrentPositionWS = new GetCurrentPositionWS(false,getApplicationContext());
                    getCurrentLocation(getCurrentPositionWS);
                    //getCurrentPositionWS.execute("https://loungeassistant-existing.herokuapp.com/readcoord");
                    getCurrentPositionWS.execute(AppConstant.BASE_URL+AppConstant.GET_LOCATION_COORDINATE);
                    Log.d("TAG_Robot", "GetCurrentPositionWS :::: 2222222 ");
                }
            }
        });

    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        findDestinationAndMove();

    }


    private void findDestinationAndMove() {
        currentX1 = Double.parseDouble(sharepreferenceKeystore.getKey("currentX1"));
        currentY1 = Double.parseDouble(sharepreferenceKeystore.getKey("currentY1"));
        currentX2 = Double.parseDouble(sharepreferenceKeystore.getKey("currentX2"));
        currentY2 = Double.parseDouble(sharepreferenceKeystore.getKey("currentY2"));
        destX = Double.parseDouble(sharepreferenceKeystore.getKey("destX"));
        destY = Double.parseDouble(sharepreferenceKeystore.getKey("destY"));
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: currentX1  :: " + currentX1);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: currentY1  :: " + currentY1);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: currentX2  :: " + currentX2);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: currentY2  :: " + currentY2);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: destX  :: " + destX);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: destY  :: " + destY);
        double angleOne = getNewAngle(currentX1, currentY1, currentX2, currentY2);
        double angleTwo = getNewAngle(currentX2, currentY2, destX, destY);
        double newAngle = angleTwo - angleOne;
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: angleOne  :: " + angleOne);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: angleTwo  :: " + angleTwo);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: newAngle  :: " + newAngle);
        RelativeAngleWheelMotion relativeAngleWheelMotion;
        if (newAngle > 0) {
            Log.d("TAG_Robot", "RotateRobotAngleServices :::: TURN_LEFT  :: ");
            relativeAngleWheelMotion = new RelativeAngleWheelMotion(RelativeAngleWheelMotion.TURN_LEFT, 10, (int) Math.abs(newAngle));
        } else {
            Log.d("TAG_Robot", "RotateRobotAngleServices :::: TURN_RIGHT  :: ");
            relativeAngleWheelMotion = new RelativeAngleWheelMotion(RelativeAngleWheelMotion.TURN_RIGHT, 10, (int) Math.abs(newAngle));
        }
        wheelMotionManager.doRelativeAngleMotion(relativeAngleWheelMotion);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //The code here will run after 5 sec, do what you want
                double finalDistance = Math.sqrt(Math.pow((destX - currentX2), 2) + Math.pow((destY - currentY2), 2)) * 100;
                Log.d("TAG_Robot", "RotateRobotAngleServices :::: finalDistance  :: " + finalDistance);
                DistanceWheelMotion distanceWheelMotion = new DistanceWheelMotion(DistanceWheelMotion.ACTION_FORWARD_RUN, 20, (int) finalDistance);
                wheelMotionManager.doDistanceMotion(distanceWheelMotion);
                Log.d("TAG_Robot", "RotateRobotAngleServices :::: finalDistance  :: " + finalDistance);

            }
        }, 5*1000);

        /*
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finalDistance = Math.sqrt(Math.pow((destX - currentX2), 2) + Math.pow((destY - currentY2), 2)) * 100;
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: finalDistance  :: " + finalDistance);
        DistanceWheelMotion distanceWheelMotion = new DistanceWheelMotion(DistanceWheelMotion.ACTION_FORWARD_RUN, 20, (int) finalDistance);
        wheelMotionManager.doDistanceMotion(distanceWheelMotion);
        Log.d("TAG_Robot", "RotateRobotAngleServices :::: finalDistance  :: " + finalDistance);
        //CommonUtils.startAlaram(RotateRobotAngleServices.this, 5000);

        */
    }


    private double getNewAngle(double x1, double y1, double x2, double y2) {
        return Math.toDegrees(Math.atan2((y2 - y1), (x2 - x1)));
    }





}
