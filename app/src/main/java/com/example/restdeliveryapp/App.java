package com.example.restdeliveryapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.db.memory.InMemoryDatabase;

/**
 * Created by angel on 17/06/16.
 */
public class App extends Application implements MeteorCallback {

    private static Meteor mMeteor;

    @Override
    public void onCreate() {
        super.onCreate();
        mMeteor = new Meteor(this, "ws://0.0.0.0:3000/websocket",new InMemoryDatabase());

        // register the callback that will handle events and receive messages
        mMeteor.addCallback(this);

        // establish the connection
        mMeteor.connect();

    }

    public static Meteor getInstance(){
        return mMeteor;
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValuesJson) {

    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {

    }

    @Override
    public void onConnect(boolean signedInAutomatically) {
        Toast.makeText(this,""+signedInAutomatically,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onDisconnect() {

    }

}
