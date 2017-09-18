package com.qihancloud.librarydemo.robotservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class RobotSocketService extends Service {
    private Socket mSocket;
    public static final String TAG = RobotSocketService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "on created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "start command", Toast.LENGTH_SHORT).show();
        try {
            mSocket = IO.socket("UUURRRLLL");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mSocket.on("newMessageReceived", onNewMessage);
        mSocket.connect();
        return START_STICKY;
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String message = args[0].toString();
            Log.d(TAG, "call: new message ");
        }
    };


}