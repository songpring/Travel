<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.fileDrop {
    width: 100%;
    height: 200px;
    border: 1px dotted blue;
}
small {
    margin-left:3px;
    font-weight: bold;
    color: gray;
}
</style>
<script src="./resources/jquery-3.4.1.js"></script>
<script src="./resources/assets/tour/js/common.js"></script>
<script type="text/javascript">

$(document).ready(function() {

// 	var params = {};
// 	params["control_type"] ="select";
// 	params["first_item_type"]="select";
// 	//params["code"]="CO";
// 	params["code_div"]="CO";
// 	params["control_id"]="ddlid";
// 	params["type"]="code";
// 	LoadCommonCode($("#ssss"), params,false)
	
	
	
// 	var params = {};
// 	params["control_type"] ="radio";
// 	params["first_item_type"]="select";
// 	//params["code"]="CO";
// 	params["code_div"]="CO";
// 	params["control_id"]="radid";
// 	params["type"]="code";
// 	LoadCommonCode($("#spra"), params,false)
	
	
// 	var params = {};
// 	params["control_type"] ="check";
// 	params["first_item_type"]="select";
// 	//params["code"]="CO";
// 	params["code_div"]="CO";
// 	params["control_id"]="chid";
// 	params["type"]="code";
// 	LoadCommonCode($("#spch"), params,false)
	
	
 	
 	
 	var Gender = function() {
 		var params = {};
 	 	params["control_type"] ="radio";  //radio ,select ,select2, check
 	 	params["first_item_type"]="select"; // select:선택 , all:전체
 	 	params["code_div"]="01"; // 남여구분
 	 	params["control_id"]="radGender";
 	 	params["type"]="code";
 	 	LoadCommonCode($("#spGender"), params,false)	
	};
	var MobileCarrier = function() {
 		var params = {};
 	 	params["control_type"] ="select"; //radio ,select ,select2, check
 	 	params["first_item_type"]="select"; // select:선택 , all:전체
 	 	params["code_div"]="03"; // 통신사
 	 	params["control_id"]="sel​MobileCarrier";
 	 	params["type"]="code";
 	 	params["class"]="form-control3";
 	 	LoadCommonCode($("#sp​MobileCarrier"), params,false)	
	};
	var Mobile1 = function() {
 		var params = {};
 	 	params["control_type"] ="select"; //radio ,select ,select2, check
 	 	params["first_item_type"]="select"; // select:선택 , all:전체
 	 	params["code_div"]="02"; // 011
 	 	params["control_id"]="sel​Mobile1";
 	 	params["type"]="code";
 	 	LoadCommonCode($("#sp​Mobile1"), params,false)	
	};
	
	

	var City = function () {
        //var params = JSON.parse("{}");
        var params = {};
        params["control_type"] ="select2"; //radio ,select ,select2, check
     	params["first_item_type"]="select"; // select:선택 , all:전체
     	params["code_div"]="CT";
     	params["type"]="code";
     	params["use_method1"]=$("select[id$=selCountry]").val();
        LoadCommonCode(null, params, false, function (pResult) {
        	//console.log(pResult);
        	var data = jQuery.parseJSON(pResult);
             //셀렉트 박스 리셋 필요하 따라 리셋을 않해도 됩
             $("select[id$=selCity] option").remove();
              console.log(data);
             //데이터 바인딩
             $("select[id$=selCity]").append("<option value=''>선택</option>");
             for (var index in data.Table) {
                 $("select[id$=selCity]").append("<option value='" + data.Table[index]["VALUE"] + "'>" + data.Table[index]["TEXT"] + "</option>");
             }
        }, false);
    };

	//국가 변경
    $("select[id$=selCountry]").change(function () {
    	if($("select[id$=selCountry]").val()!=""){
    		//도시 호출
        	City();	
    	}
    	else{
    		$("select[id$=selCity]").val("");
    	}
    });
	//남여 구분 세팅
    Gender();
	//통신사 세팅
    MobileCarrier();
	// 
    Mobile1();
	
	
	// 아작스로 데이터 가져 오기
    var params = {};
 	params["type"] ="Scd";
 	params["call_type"]="AllDate";
 	params["code"] ="01";
 	params["code_div"] ="01";
 	Ajax(params, function(pResult) {
 		 console.log(pResult);
	},false);
 	
 	
 	
 	// 
 	//이벤트 설정시에는 jquery의 .on()을 사용한다.
    //드래그 기본 효과를 막음
    $(".fileDrop").on("dragenter dragover", function(event){
        //drop영역에 들어가고, 드롭영역에 드래그 되고있을때의 기본 효과를 막음
        event.preventDefault();
    });
    $(".fileDrop").on("drop",function(event){
        //drop이 될 때 기본 효과를 막음
        //기본 효과를 막지 않으면 드래그시에 브라우저에서 이미지파일이 열려버림
        event.preventDefault();
        //첨부파일 배열
        var files=event.originalEvent.dataTransfer.files;
        var formData=new FormData();
        //console.log(files);
        for(var i=0; i<files.length; i++){
        	formData.append("file"+i,files[i]); //폼에 file 변수 추가	
        }
        //AJAX로 (이미지를 넘길때)폼 전송을 가능케해주는 FormData 객체
        //return false;
     	AjaxFileUpload(formData, function(pResult) {
     		//console.log(pResult);
     		//console.log(pResult.data);
     		var fileInfos = pResult.data.split('|');
     		var str="";
			for(var i=0;i<fileInfos.length;i++){
				var fileInfo = fileInfos[i].split('?');
				str+="<div><a href='${path}/upload/displayFile?fileName="
	     	         +fileInfo[1]+"'>"+fileInfo[0]+"</a>";
				str+="<span data-src="+fileInfo[0]+">[삭제]</span></div>";
     		}
             $(".uploadedList").append(str);
    	},false);
    });
    
    //첨부파일 삭제 함수
    $(".uploadedList").on("click","span",function(event){
//         //현재 클릭한 태그
//         var that=$(this);
// 		//data: "fileName="+$(this).attr("data-src"),        
//         $.ajax({
//             url: "${path}/upload/deleteFile",
//             type: "post",
//             data: {
//                 fileName: $(this).attr("data-src")
//             },
//             dataType: "text",
//             success: function(result){
//                 if(result=="deleted"){
//                     that.parent("div").remove();
//                 }
//             }
//         });
    });
 	
});
</script>
</head>
<body>
(컨트롤단에서 세팅 이후  로드)<br>
국가 : ${selCountry}
도시 :(국가 코드가 바뀌면 리로드) ${selCity}<br>
성별 구분 : ${radGender1}
  
<br>
<br>

(제이쿼리 사용시)<br>
성별 구분
<span id="spGender" name="spGender"></span>
<br>
통신사
<span id="sp​MobileCarrier" name="sp​MobileCarrier"></span>
<span id="sp​Mobile1" name="sp​Mobile1"></span>

<br>
<!-- 파일을 업로드할 영역 / div태그는 레이아웃 설정 역할-->
<div class="fileDrop">Drag & Drop Files Here</div>
<!-- 업로드된 파일 목록을 출력할 영역 -->
<div class="uploadedList"></div>
</body>
</html>