////  CommonCode - Start  
var LoadCommonCode = function (pTargetControl, pParams, pAsync, pCallBackFunction) {
//	console.log("LoadCommonCode");
//	console.log(pTargetControl);
//	console.log(pParams);
//	console.log(JSON.stringify(pParams));
	
    if (pTargetControl != null) {
        ClearCommonCode(pTargetControl);
    }
    if (pAsync == null) {
        pAsync = true;
    }
    $.ajax({
        method: 'POST'
        //, url: "/Ajax/AjaxCommonCode.aspx"
    	, url: "ajaxCodeProc"
        , data: JSON.stringify(pParams)
        , contentType: 'application/json; charset=utf-8'
        , dataType: 'json'
    	//, dataType: 'text'
        , async: pAsync
        , success: function (res) {
            ////if (console) //console.log("RESULT - " + JSON.stringify(res));
        	//console.log($(pTargetControl));
        	//console.log(res);
        	//console.log("res : " + res.data);
        	
            if (pTargetControl != null) {
                $(pTargetControl).empty();
                $(pTargetControl).append(res.data);
            } else {

            }
            if (pCallBackFunction) {
                pCallBackFunction(res.data);
            	//pCallBackFunction(res);
            } else {

            }
        }
        , error: function (request, status, error) {
        }
    });

}

var ClearCommonCode = function (pTargetControl) {
    $(pTargetControl).empty();
}




var Ajax = function (pParams, pCallBackFunction, pAsync) {
	//if (console) console.log("Ajax ACT - " + JSON.stringify(pParams));
	//console.log("bbbbb");

	if (pParams == null) return;
	if (pAsync) {            
	    pAsync = pAsync;
	} else {
	    if (pAsync == false) {
	        pAsync = false;
	    } else {
	        pAsync = true;
	    }
	}
	//pParams["DB_TYPE"] = 0;
	$.ajax({
	    type: 'POST'
	        , url: "ajaxProc"
	        , data: JSON.stringify(pParams)
	        , contentType: 'application/json; charset=utf-8'
	        , dataType: 'json'
        	//, dataType: 'text'
	        , async: pAsync
	        , success: function (res) {
//	        	console.log("res" + res);
	            if (pCallBackFunction != null) {
	                if (res) {
	                    pCallBackFunction(res);
	                }                        
	            }
	            //HideLoading();
	        }
	        , error: function (request, status, error) {
	        	console.log("error" + error);
            }
	    });
}

var AjaxList = function (pParams, pCallBackFunction, pAsync) {
	//if (console) console.log("Ajax ACT - " + JSON.stringify(pParams));
	//console.log("bbbbb");

	if (pParams == null) return;
	if (pAsync) {            
	    pAsync = pAsync;
	} else {
	    if (pAsync == false) {
	        pAsync = false;
	    } else {
	        pAsync = true;
	    }
	}
	//pParams["DB_TYPE"] = 0;
	$.ajax({
	    type: 'POST'
	        , url: "ajaxList"
	        , data: JSON.stringify(pParams)
	        , contentType: 'application/json; charset=utf-8'
	        , dataType: 'json'
	        , async: pAsync
	        , success: function (res) {
	        	//console.log(res);
	            if (pCallBackFunction != null) {
	                if (res) {
	                    pCallBackFunction(res);
	                }                        
	            }
	            //HideLoading();
	        }
	        , error: function (request, status, error) {
	        	console.log("error" + error);
            }
	    });
}

var AjaxFileUpload = function (pParams, pCallBackFunction, pAsync) {
	//if (console) console.log("Ajax ACT - " + JSON.stringify(pParams));
	//console.log("bbbbb");

	if (pParams == null) return;
	if (pAsync) {            
	    pAsync = pAsync;
	} else {
	    if (pAsync == false) {
	        pAsync = false;
	    } else {
	        pAsync = true;
	    }
	}
	//pParams["DB_TYPE"] = 0;
	$.ajax({
	    type: 'POST'
	        , url: "ajaxFileUpload"
	        , data: pParams
	        , processData: false
            , contentType: false
            , dataType: 'json'
	        , async: pAsync
	        , success: function (data) {
	        	//console.log(res);
	            if (pCallBackFunction != null) {
	                if (data) {
	                    pCallBackFunction(data);
	                }                        
	            }
	            //HideLoading();
	        }
	        , error: function (data) {
	        	console.log("error" + error);
            }
	    });
}

var Toastr = function(pType,pData){
	/*
	 * https://codeseven.github.io/toastr/demo.html 참고 하여 제작 하면 됨
	 * 타입은(pType) : 4종
	 * 기본 : info
	 * 성공 : success
	 * 경고 : warning
	 * 에러 : error
	 * 
	 */
	toastr.options = {
			 "closeButton": false,
			 "debug": false,
			 "newestOnTop": false,
			 "progressBar": false,
			 "positionClass": "toast-bottom-center",
			 "preventDuplicates": false,
			 "onclick": null,
			 "showDuration": "300",
			 "hideDuration": "1000",
			 "timeOut": "5000",
			 "extendedTimeOut": "1000",
			 "showEasing": "swing",
			 "hideEasing": "linear",
			 "showMethod": "fadeIn",
			 "hideMethod": "fadeOut"
		};
	toastr[pType](pData);
}

// type 에 따라서 날짜를 변경 해줌
var getFormatDate = function(data,type) {
	var date = new Date(data);
	var returnDate = ""
	if(data!=undefined && date!="Invalid Date"){
		var year = date.getFullYear(); //yyyy 
		var month = (1 + date.getMonth()); //M 
		month = month >= 10 ? month : '0' + month; //month 두자리로 저장 
		var day = date.getDate(); //d
		day = day >= 10 ? day : '0' + day; //day 두자리로 저장
		if(type=="-"){
			returnDate = year + "-" + month + "-" + day;
		}
	}
	return returnDate
};