var exec = require('cordova/exec');

var xunfeiListenSpeaking = {
	startListen:function (success,error,isShowDialog,isShowPunc,isCh){
		exec(success,error,"XunfeiListenSpeaking","startListen",[isShowDialog,isShowPunc,isCh?'zh_cn':'en_us']);
	},
	stopListen:function(){
		exec(null,null,"XunfeiListenSpeaking","stopListen",[]);
	},
	startSpeak:function(success,error,speakMessage){
		exec(success,error,"XunfeiListenSpeaking","startSpeak",[speakMessage]);
	},
	stopSpeak:function(){
		exec(null,null,"XunfeiListenSpeaking","stopSpeak",[]);
	},
	pauseSpeaking: function() {
        exec(null, null, 'XunfeiListenSpeaking', 'pauseSpeaking', []);
    },

    resumeSpeaking: function() {
        exec(null, null, 'XunfeiListenSpeaking', 'resumeSpeaking', []);
    }

};

module.exports = xunfeiListenSpeaking;
