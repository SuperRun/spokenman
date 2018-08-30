/**
 * 
 */

var errorInfo = new Vue({
    el: '#data',
    data: {
        error:{
        	name:"",
        	signupStartTime:"",
        	signupEndTime:"",
        	startTime:"",
        	endTime:""
        },
    },
    methods:{
    	editTraining:function(){
    		editTraining();
    	}
    }
});

function editTraining(){
	var name=$("#name").val();
	var startTime=$("#startTm").val();
	var endTime=$("#endTm").val();
	var signupStartTime=$("#signupStartTm").val();
	var signupEndTime=$("#signupEndTm").val();
	errorInfo.error.name="";
	errorInfo.error.starTime="";
	errorInfo.error.endTime="";
	errorInfo.error.signupStartTime="";
	errorInfo.error.signupEndTime="";
	
	var flag=1;
	if(name==null||name==""){
		errorInfo.error.name="请填写考试名称";
		flag=0;
	}
	if(startTime==null||startTime==""){
		errorInfo.error.startTime="请填写培训开始时间";
		flag=0;
	}
	if(endTime==null||endTime==""){
		errorInfo.error.endTime="请填写培训结束时间";
		flag=0;
	}
	if(signupStartTime==null||signupStartTime==""){
		errorInfo.error.signupStartTime="请填写报名开始时间";
		flag=0;
	}
	if(signupEndTime==null||signupEndTime==""){
		errorInfo.error.signupEndTime="请填写报名结束时间";
		flag=0;
	}
	
	if(flag==0){
		return false;
	}
	
	$("#data").submit();
	
}