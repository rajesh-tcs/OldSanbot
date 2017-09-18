package com.qihancloud.librarydemo.utils;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

/**
 * Created by 1407053 on 8/24/2017.
 */

public class MyResultReceiver extends ResultReceiver {

    private Receiver mReceiver;

    public MyResultReceiver(Handler handler) {
        super(handler);
        // TODO Auto-generated constructor stub
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);

    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

}
