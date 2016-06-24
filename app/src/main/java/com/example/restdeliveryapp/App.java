package com.example.restdeliveryapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.db.memory.InMemoryDatabase;

/**
 * Created by angel on 17/06/16.
 */
public class App extends Application implements MeteorCallback{

//    private static Meteor mMeteor;

    @Override
    public void onCreate() {
        super.onCreate();

        MeteorSingleton.createInstance(this, "ws://172.20.20.31:3000/websocket");
        MeteorSingleton.setLoggingEnabled(true);
        MeteorSingleton.getInstance().addCallback(this);
        MeteorSingleton.getInstance().connect();


//        mMeteor = MeteorSingleton.createInstance(this, "ws://0.0.0.0:3000/websocket");
//
//
////        mMeteor = new Meteor(this, "ws://0.0.0.0:3000/websocket",new InMemoryDatabase());
//
////         register the callback that will handle events and receive messages
//        mMeteor.addCallback(this);
//
////         establish the connection
//        mMeteor.connect();

    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {

    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValuesJson) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(Exception e) {
        Log.e("Exception",e.toString());
    }

    @Override
    public void onConnect(boolean signedInAutomatically) {
        Log.i("Conectado",""+signedInAutomatically);
    }

}
