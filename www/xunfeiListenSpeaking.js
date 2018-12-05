var exec = require('cordova/exec');

var xunfeiListenSpeaking = {
	startListen:function (success,error,isShowDialog,isShowPunc,isCh){
	    console.log('isCh = = ', isCh);
        var lang = isCh?'zh_cn':'en_us';
		exec(success,error,"xunfeiListenSpeaking","startListen",[isShowDialog,isShowPunc,lang]);
	},
	stopListen:function(){
		exec(null,null,"xunfeiListenSpeaking","stopListen",[]);
	},
	startSpeak:function(success,error,speakMessage){
		exec(success,error,"xunfeiListenSpeaking","startSpeak",[speakMessage]);
	},
	stopSpeak:function(){
		exec(null,null,"xunfeiListenSpeaking","stopSpeak",[]);
	},
	pauseSpeaking: function() {
        exec(null, null, 'xunfeiListenSpeaking', 'pauseSpeaking', []);
    },

    resumeSpeaking: function() {
        exec(null, null, 'xunfeiListenSpeaking', 'resumeSpeaking', []);
    }

};

module.exports = xunfeiListenSpeaking;
