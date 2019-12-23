package com.pani.tictactoe.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.pani.tictactoe.StandardActivity;


public class MyLifecycleCallbackAdapter implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(activity instanceof StandardActivity){
            activityCreated((StandardActivity)activity,savedInstanceState);
        }
    }

    protected void activityCreated(StandardActivity activity, Bundle savedInstanceState){

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if(activity instanceof StandardActivity){
            activityStarted((StandardActivity)activity);
        }
    }

    protected void activityStarted(StandardActivity activity){

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if(activity instanceof StandardActivity){
            activityResumed((StandardActivity)activity);
        }
    }

    protected void activityResumed(StandardActivity activity){

    }

    @Override
    public void onActivityPaused(Activity activity) {
         if(activity instanceof StandardActivity){
             activityPaused((StandardActivity)activity);
         }
    }

    protected void activityPaused(StandardActivity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(activity instanceof StandardActivity){
            activityStopped((StandardActivity) activity);
        }
    }

    protected void activityStopped(StandardActivity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(activity instanceof StandardActivity){
            activitySaveInstanceState((StandardActivity) activity,outState);
        }
    }

    protected void activitySaveInstanceState(StandardActivity activity, Bundle outState) {

    }


    @Override
    public void onActivityDestroyed(Activity activity) {
        if(activity instanceof StandardActivity){
            activityDestroyed((StandardActivity) activity);
        }
    }

    protected void activityDestroyed(StandardActivity activity) {

    }
}
