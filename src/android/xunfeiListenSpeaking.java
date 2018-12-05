package com.microduino.mDesigner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
/*
 * ：Created by z on 2018/11/14
 */

public class xunfeiListenSpeaking extends CordovaPlugin {
    private static String TAG = "zhu"; //xunfeiListenSpeaking.class.getSimpleName();
    private Context context;
    private CallbackContext callbackContext;
    private Toast mToast;
    private Handler mHandler = new Handler();

    private SpeechSynthesizer mTts;

    // 语音听写对象
    private SpeechRecognizer mIat;

    private SharedPreferences mSharedPreferences;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        context = cordova.getActivity();
        // 5b95e51f
        String key = context.getString(getId("app_id", "string"));
        String s = SpeechConstant.APPID + "=" + key;
        SpeechUtility.createUtility(context, s);
    }

    private int getId(String idName, String type) {
        return context.getResources().getIdentifier(idName, type, context.getPackageName());
    }

    private static final int DIALOG_ACTIVIT_CODE = 0;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        this.callbackContext = callbackContext;
        Log.d(TAG, "action: - - - - - -- - - - -- - - - -> " + action);

        //开始听写
        if (action.equals("startListen")) {
            boolean isShowDialog;
            try {
                isShowDialog = args.getBoolean(0);
            } catch (Exception e) {
                isShowDialog = true;
            }
            String punc;
            try {
                punc = args.getBoolean(1) ? "1" : "0";
            } catch (Exception e) {
                punc = "1";
            }
            String language;
            try {
                language = args.getString(2);
            } catch (Exception e) {
                language = "zh_cn";
            }

            if (isShowDialog) {
                RecognizerDialog mIatDialog = new RecognizerDialog(context, mInitListener);
                mIatDialog.setParameter(SpeechConstant.LANGUAGE,language);
                mIatDialog.setUILanguage(language.equalsIgnoreCase("zh_cn")?Locale.CHINA:Locale.ENGLISH);
                //开始识别并设置监听器
                mIatDialog.setListener(new RecognizerDialogListener() {
                    @Override
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        Log.i(TAG, "onResult: "+recognizerResult);
                    }

                    @Override
                    public void onError(SpeechError speechError) {
                        Log.i(TAG, "onError: "+speechError.getErrorDescription());
                    }
                });
                //显示听写对话框
                mIatDialog.show();
            } else {
               // startListenWidthNotDialog(punc);
            }

            //Toast.makeText(context, "aaaaaaaaaaa", Toast.LENGTH_SHORT).show();
            //callbackContext.success("success");
            return true;
        }
        return false;
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Log.i(TAG, "初始化失败，错误码： = " + code);
            }
        }
    };

}
