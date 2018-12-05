package com.microduino.mDesigner;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;
/*
 * ：Created by z on 2018/11/14
 */

public class xunfeiListenSpeaking extends CordovaPlugin {

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if ("startListen".equals(action)) {
            // 获取activity和context --> cordova.getActivity()和cordova.getContext()
            return true;
        }
        return false;
    }

}
