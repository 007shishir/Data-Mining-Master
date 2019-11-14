package com.saiful.dataminingmaster;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class DataMiningMaster extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
