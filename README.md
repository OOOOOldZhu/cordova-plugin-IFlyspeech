#
科大讯飞的语音听说读写的cordova插件 
### Supported Platforms

- iOS
- android
简述：http://www.cnblogs.com/dinglinjie/p/7458548.html


## Android安装方法:

1,新建一个Cordova工程
2，cordova platform add android
3,cordova platform remove android
  cordova platform add android@6.4.0
4,/Users/z/WebstormProjects/cordova-plugin-xunfeiListenSpeaking/plugin.xml
   android:name="IFLYTEK_APPKEY"修改appid

   /Users/z/WebstormProjects/cordova-plugin-xunfeiListenSpeaking/src/android/res/values/strings.xml
   <string name="app_id">修改appid
5,添加插件
    //如果是加载git上的插件
    cordova plugin add https://github.com/Edc-zhang/cordova-plugin-xunfeiListenSpeaking
    //如果是加载本地插件，
    cordova plugin add /Users/z/WebstormProjects/cordova-plugin-xunfeiListenSpeaking
6,
    //在适当的位置添加以下代码，开始语音识别
    document.addEventListener("deviceready", onDeviceReady, false);
                function onDeviceReady() {
                    //参数1: 成功回调方法
                    //参数2:失败回调方法（ios没有失败回调方法）
                    //参数3: 是否显示对话框,默认true
                    //参数4:是否生成标点符号 ,默认true
                    xunfeiListenSpeaking.startListen(function (data) {
                        console.log('data=', data);
                        //resolve(data);
                    }, function (error) {
                        console.log('error=', error);
                        //reject(error);
                    }, true, false);

                }
6,cordova build android
7,cordova run android


遇到的问题:

如果build的时候发现找不到config文件，就降低Android版本。
cordova platform remove android
cordova platform add android@6.4.0

