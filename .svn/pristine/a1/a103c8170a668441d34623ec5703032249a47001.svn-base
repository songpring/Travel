package com.itwill.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.dao.ScdCodeDAO;
import com.itwill.dao.UtilDAO;
import com.itwill.domain.JsonDataBean;
import com.itwill.domain.ScdCodeBean;


@Service
public class UtilServiceImpl implements UtilService {
	
	@Inject
	private ScdCodeDAO scdDao;
	
	@Inject
	private ScdCodeService scdService;
	@Inject
	private AirlineService airService;
	@Inject
	private MemberService memberService;
	@Inject
	private BoardService boardService;
	
	@Inject
	private TableColumnMappingService TableColumnMappingService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	

	private ObjectMapper mapper = new ObjectMapper();
	
	private String control_type;  // 컨트롤 타입
	private String first_item_type;// 셀렉트 박스 일때 전체 혹은 선택 표시 뜨도록
	private String control_id; // 만들어지는 아이디값
	private String class_id; // 적용할 class
	private String bean_type; // 사용할 빈 타입
	private String selectItem; // 공통 코드 사용시 값 선택 할수 있도록 
	private String id_use; // 아이디 할당 여부
	
	// ip 주소 가져 오기
	@Override
	public String getIp(HttpServletRequest request) {
		if(request==null) {
			return "";
		}
		
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	// 현재 날짜를 기준으로 하여 문자열 만들기
	@Override
	public String getDateData(String Type) {
		String returnValue ="";
		Date today = new Date();
	    SimpleDateFormat date = new SimpleDateFormat("yyyy"+Type+"MM"+Type+"dd");
	    returnValue = date.format(today);
		return returnValue;
	}
	
	@Override
	public String getTimeData(String Type) {
		String returnValue ="";
		Date today = new Date();
	    SimpleDateFormat date = new SimpleDateFormat("HH"+Type+"mm"+Type+"ss"+Type+"SSS");
	    returnValue = date.format(today);
		return returnValue;
	}
	

	

	// 파일 업로드를 하고 결과를 리턴
	@Override
	public String setFileUpload(String dateDir, MultipartFile mpf) {
		String reName = "";
		// 시스템상 경로는 \\ 로 되어 있으나 자바에서는 / 으로 경로를 구분 해야 해서 변환을 함
		String uploadFullPath = uploadPath.replace('\\', '/')+"/"+dateDir+"/";

		// 업로드 해야할 경로를 만들어서 폴더가 있는지 확인 후 없으면 생성한다
		File targetDir = new File(uploadFullPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		reName = getDateData("")+"_" + getTimeData("")+"_"+ mpf.getOriginalFilename();

		// 실제 파일은 file.getBytes()
		// 파일을 upload 폴더에 넣기 => 파일 카피
		File target = new File(uploadFullPath, reName);

		try {
			FileCopyUtils.copy(mpf.getBytes(), target);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return reName;
	}

	//// util 서비스를 이용하여 DataTable에 사용할 데이터가공후 (map) 객체로 리턴
	@Override
	public Map<String, String> getDataTableMpa(MultiValueMap<String, String> formData) {
		Map<String, String> map = new HashMap<String, String>();
	    // 소트관련
	    int orderNo = Integer.parseInt(formData.get("order[0][column]").get(0));
	    String orderType = formData.get("order[0][dir]").get(0);
	    String order = formData.get("columns["+orderNo+"][data]").get(0);
//		    System.out.println("orderType : " + orderType);
//		    System.out.println("order : " + order);
	    // 검색조건
	    // 데이터 가공 각 컬럼에 [search][value]에 값이 있으면 값을 세팅 
	    String name="";
	    for (String key : formData.keySet() ) {
	    	if(key.indexOf("[data]")>-1) {
	    		name = formData.get(key).get(0).toString();
	    	}
	    	if(key.indexOf("[search][value]")>-1) {
	    		String value = formData.get(key).get(0).toString();
	    		if(!value.equals("")) {
	    			map.put(name, value);
	    		}
	    	}
	    }
	    
	    map.put("startRow", formData.get("start").get(0));  // 스타트 페이지
	    map.put("endRow", formData.get("length").get(0)); // 가져올 데이터양
	    map.put("orderData", order); // 소트할 데이터
	    map.put("orderType", orderType); // 소트할 타입(ASC,DESC)
	    map.put("draw", formData.get("draw").get(0));
		
		
		return map;
	}




	// json 방식의 데이터를 받아서 타입에 따라서 각각의 서비스 호출 하여 List<Object> 방식으로 데이터 리턴
	/*
	 * 입력폼 예 : {"type":"Scd","call_type":"AllDate","code_step":"1","code_name":""}
	 * 출력폼 예 : [{order_seq=0, code=11, code_sml_name=, regi_ip_addr=0:0:0:0:0:0:0:1, use_yn=Y, remark=, code_step=1, code_name=2222, eng_sml_name=, modifier_ip_addr=0:0:0:0:0:0:0:1, regi_date=2019-09-16 10:49:07.0, order_seq1=0, code_div=00, eng_name=, modifier_date=2019-09-16 11:39:35.0, use_method3=, use_method2=, use_method1=}, {order_seq=0, code=22, code_sml_name=, regi_ip_addr=0:0:0:0:0:0:0:1, use_yn=Y, remark=, code_step=1, code_name=22, eng_sml_name=, modifier_ip_addr=0:0:0:0:0:0:0:1, regi_date=2019-09-16 11:00:29.0, order_seq1=0, code_div=00, eng_name=, use_method3=, use_method2=, use_method1=}, {order_seq=1, code=CO, code_sml_name=, regi_ip_addr=2019.08.29, use_yn=Y, remark=, code_step=1, code_name=국가코드, eng_sml_name=, modifier_ip_addr=2019.08.29, regi_date=2019-02-16 08:00:01.0, order_seq1=0, code_div=00, modifier_id=admin, eng_name=, regi_id=admin, modifier_date=2019-02-16 08:00:01.0, use_method3=, use_method2=, use_method1=}, {order_seq=2, code=CT, code_sml_name=, regi_ip_addr=2019.08.29, use_yn=Y, remark=, code_step=1, code_name=도시, eng_sml_name=, modifier_ip_addr=2019.08.29, regi_date=2019-02-16 08:00:01.0, order_seq1=0, code_div=00, modifier_id=admin, eng_name=, regi_id=admin, modifier_date=2019-02-16 08:00:01.0, use_method3=, use_method2=, use_method1=}, {order_seq=3, code=01, code_div=00, use_yn=Y, regi_id=admin, code_step=1, code_name=남여구분}, {order_seq=3, code=02, code_div=00, use_yn=Y, regi_id=admin, code_step=1, code_name=휴대폰국번}]
	 */
	@Override
	public List<Object> getListData(String paramData) {
		System.out.println("UtilServiceImpl getListData!!!");
		List<Object> list = null;
		// paramData 데이터를 Map 형식으로 리턴
		Map<String, Object> map = getMap(paramData);
		System.out.println("map : " + map);
		
		if(map.get("type")!=null) {
			if(map.get("type").equals("Scd")) {
				list = scdService.getCodeObject(map);
			}
			else if(map.get("type").equals("Airline")) {
				list = scdService.getCodeObject(map);
			}
			else if(map.get("type").equals("Board")) {
				list = scdService.getCodeObject(map);
			}
			else if(map.get("type").equals("Member")) {
				list = scdService.getCodeObject(map);
			}
			else if(map.get("type").equals("Util")) {
				list = scdService.getCodeObject(map);
			}
		}
		//System.out.println("list" + list);
		return list;
	}



	// json 방식의 데이터를 받아서 타입에 따라서 각각의 서비스 호출 하여 json 방식으로 데이터 리턴
	/*
	 * 입력폼 예 : {"type":"Scd","call_type":"AllDate","code":"01","code_div":"01","regi_ip_addr":"0:0:0:0:0:0:0:1","modifier_ip_addr":"0:0:0:0:0:0:0:1","regi_id":null,"modifier_id":null}
	 * 출력폼 예 : {"name":null,"data":"{\"Table\":[{\"order_seq\":1,\"code\":\"01\",\"code_div\":\"01\",\"use_yn\":\"Y\",\"regi_id\":\"admin\",\"code_step\":2,\"code_name\":\"남자\"}]}","result":"1","message":"처리 완료 되었습니다."}
	 */
	
	@Override
	public String getData(String paramData) throws JsonProcessingException {
		System.out.println("UtilServiceImpl getData!!!");
		String jsonStr="";
		
		// paramData 데이터를 Map 형식으로 리턴
		Map<String, Object> map = getMap(paramData);
		
		if(map.get("type")!=null) {

			if(map.get("type").equals("Scd")) {
				jsonStr = scdService.getAjaxData(map);
			}
			else if(map.get("type").equals("Airline")) {
				jsonStr = airService.getAjaxData(map);
			}
			else if(map.get("type").equals("Board")) {
				jsonStr = boardService.getAjaxData(map);
			}
			else if(map.get("type").equals("Member")) {
				jsonStr = memberService.getAjaxData(map);
			}
			else if(map.get("type").equals("Util")) {
				jsonStr = scdService.getAjaxData(map);
			}
			else if(map.get("type").equals("tableColumn")) {
				jsonStr = TableColumnMappingService.getAjaxData(map);
			}
		}
//		System.out.println("jsonStr" + jsonStr);
		return jsonStr;
	}
	
	
	/*
	 * json 받은 데이터를 기점으로 하여 리턴 받은 데이터를 가지고 
	 * 자동으로 select, radio, check 만들기
	 * 입력예 : {control_id=selCountry, code_div=CO, first_item_type=select, type=code, control_type=select}
	 * 출력예 : <select id='selCountry' name='selCountry'><option value=''>선택</option><option value='KOR'>대한민국</option><option value='ESA'>동남아</option><option value='JAP'>일본</option><option value='CHN'>중국</option><option value='OCN'>대양주</option><option value='AMR'>미주</option><option value='EUR'>유럽</option><option value='AFR'>중동/아프리카</option><option value='WSA'>서남아</option></select>
	 * 
	 */
	
	@Override
	public String getCodeData(Map<String, Object> map) {
		//System.out.println("getCodeData!! : " + map);
		String returnValue="";
		// map 형식을 String 형식으로 변환
		String json = getJson(map);
		try {
			// json 형식으로 코드를 가져옴 
			returnValue = getCode(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 실제 data 만 추출 하기 위해 map로 변경후 데이터 뽑아서 리턴
		map.clear();
		// 가져온 코드를 map 형으로 변경
		map = getMap(returnValue);
		// 실제 데이터는 data 에 담겨 있어서 data 값만 추출
		returnValue = map.get("data").toString();
		//System.out.println("returnValue : " +  returnValue);
		
		return returnValue;
	}


	// 코드를 가져와서 상황에 맞게 데이터를 가공 하여 리턴
	@Override
	public String getCode(String paramData) throws JsonProcessingException{
		System.out.println("UtilServiceImpl getCode!!!");
		JsonDataBean json = new JsonDataBean();
		
		// 공퉁 부분이라서 param 값을 가지고 사용항 Bean 객체를 항당 하여  List<Object> 형태로 리턴 받음 
		List<Object> list = setCodeData(paramData);
		
		// 데이터를 가공함
		String jsonStr = getCodeDataSetting(list);
		//System.out.println("jsonStr 가공전 : " + jsonStr);
		
		// 가공된 데이터를 JsonDataBean 객체에 할당
		json.setData(jsonStr);
		
		// JsonBean 객체의 내용을 json 형식으로 변경
		jsonStr = mapper.writeValueAsString(json);
		//System.out.println("jsonStr : " + jsonStr);
		return jsonStr;
	}
	
	// 컨트롤러 초기화
	public void controllerInit() {
		control_type="";
		first_item_type="";
		control_id="";
		bean_type="";
		class_id="";
		selectItem="";
		id_use ="";
	}
	// 컨트롤러 세팅
	public void controllerSet(Map<String, Object> map) {
		// map 에 항목이 있으면 할당 시키고 없으면 제외
		if(map.get("control_type")!=null) {
			control_type = map.get("control_type").toString();
		}
		if(map.get("first_item_type")!=null) {
			first_item_type = map.get("first_item_type").toString();
		}
		if(map.get("control_id")!=null) {
			control_id = map.get("control_id").toString();
		}
		if(map.get("type")!=null){
			bean_type = map.get("type").toString();
		}
		if(map.get("class")!=null){
			class_id = map.get("class").toString();
		}
		if(map.get("selectItem")!=null){
			selectItem = map.get("selectItem").toString();
		}
		if(map.get("id_use")!=null){
			id_use = map.get("id_use").toString();
		}
		
	}
	
	// param를 받아서 변수 활당 및 실제 데이터를 List<Map<String, Object>> 형식으로 리턴
	public List<Object> setCodeData(String paramData) {
		//System.out.println("Object setData paramData : " + paramData);
		
		// 컨트롤러 초기화
		controllerInit();

		// paramData 데이터를 Map 형식으로 리턴
		Map<String, Object> map = getMap(paramData);
		
		// 세팅 
		controllerSet(map);
		
		// bean_type 가 code 일경우는 ScdCode 데이터를 가져 오는것으로 판단 데이터 가져 와서 리턴
		if(bean_type.equals("code")) {
			ScdCodeBean code = new ScdCodeBean();
			if(map.get("code")!=null) {
				code.setCode(map.get("code").toString());
			}
			if(map.get("code_div")!=null) {
				code.setCode_div(map.get("code_div").toString());
			}
			if(map.get("use_method1")!=null) {
				code.setUse_method1(map.get("use_method1").toString());
			}
			if(map.get("code_step")!=null) {
				code.setCode_step(Integer.parseInt(map.get("code_step").toString()));
			}
			else {
				code.setCode_step(2);
			}
			
			code.setUse_yn("Y");

			List<Object> list = scdDao.getCodeObject(code);
			
			return list;
		}
		else {
			return null;
		}
	}
	
	// json 형식 데이터를 map 형식으로 가공 후 리턴
	public Map<String, Object> getMap(String strData) {
		
		Map<String, Object> map = null;
		mapper = new ObjectMapper();
		try {
			map = mapper.readValue(strData, new TypeReference<Map<String, Object>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("strData " + strData);
//		System.out.println("map " + map);
		return map;
	}
	
	
	// mpa 혁식을 json 형식으로 가공 후 리턴
 	public String getJson(Map<String, Object> map) {
		String returnValue ="";
		
		try {
			returnValue = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
 	
 	// List<map<String, Object>> 형식을 json 형식으로 가공 후 리턴
 	public String getListMapJson(List<Map<String, Object>> list) {
		String returnValue="{\"Table\":";
		
		try {
			returnValue += mapper.writeValueAsString(list) + "}";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
 	
 	
 	
 	
 	// List<Object> list 형식을 JsonBean 에 넣기
 	@Override
	public JsonDataBean setJsonListData(List<Object> list) {
 		JsonDataBean json = new JsonDataBean();
 		
 		if(list==null) {
 			json.setResult("100");
 			json.setMessage("오류가 발생 하였습니다.");
		}
		else {
			if(list.size()<=0) {
				json.setResult("-1");
				json.setMessage("데이터가 없습니다.");
			}
			else {
				json.setResult("1");
				json.setData(getListJson(list));
				json.setMessage("처리 완료 되었습니다.");
			}
		}
		return json;
	}
 	

 	// 추가 저장 삭제 리턴값을 형식을 json 형식으로 가공 후 리턴
	@Override
	public JsonDataBean setJsonIntData(int result) {
		JsonDataBean json = new JsonDataBean();
		if(result>0) {
			json.setResult("1");
			json.setData("success");
			json.setMessage("처리 완료 되었습니다.");
		}
		else {
			json.setResult("-1");
			json.setData("error");
			json.setMessage("실패 하였습니다.");
		}
		return json;
	}
	
	



	@Override
	public JsonDataBean setJsonStringData(String data) {
//		String returnValue="";
// 		try {
// 			returnValue += mapper.writeValueAsString(data);
// 		} catch (JsonProcessingException e) {
// 			e.printStackTrace();
// 		}
//		return returnValue;
		
		JsonDataBean json = new JsonDataBean();
 		
 		if(data==null) {
 			json.setResult("100");
 			json.setMessage("오류가 발생 하였습니다.");
		}
		else {
			
			json.setResult("1");
			json.setData(getStringJson(data));
			json.setMessage("처리 완료 되었습니다.");
		}
		return json;
	}
	
	
	public String getStringJson(String data) {
 		//String returnValue="{\"data\":";
 		String returnValue="";
 		try {
 			returnValue += mapper.writeValueAsString(data);
 		} catch (JsonProcessingException e) {
 			e.printStackTrace();
 		}

 		return returnValue;
 	}


	// List<Object>> 형식을 json 형식으로 가공 후 리턴
 	@Override
  	public String getListJson(List<Object> list) {
 		//String returnValue="{\"data\":";
 		String returnValue="{\"Table\":";
 		try {
 			returnValue += mapper.writeValueAsString(list) + "}";
 		} catch (JsonProcessingException e) {
 			e.printStackTrace();
 		}

 		return returnValue;
 	}

  	// 가져온 데이터를 상황에 맞도록 제 해석 하여 리턴
	public String getCodeDataSetting(List<Object> list) {
		//System.out.println("getData(List<Object> list)");
		//System.out.println("getListJson : " + getListJson(list));
		
		if(list==null) {
			return "";
		}
		
		String returnValue = "";
		int iControlCount=0;
		switch (control_type) {
		case "select":
			returnValue += "<select class='"+class_id+"' id='"+control_id+"' name='"+control_id+"'>";
            if (first_item_type.equals("select"))
            {
                returnValue += "<option value=''>선택</option>";
            }
            else if (first_item_type.equals("all"))
            {
                returnValue += "<option value=''>전체</option>";
            }
            //실제 데이터 바인딩
        	for(Object obj : list) {
        		if(bean_type.equals("code"))
                {
        			ScdCodeBean scd = (ScdCodeBean) obj;
        			if(selectItem.equals(scd.getCode())) 
        			{
        				returnValue += "<option value='"+scd.getCode()+"' selected='selected'>"+scd.getCode_name()+"</option>";
        			}
        			else if(selectItem.equals(scd.getCode_name())) 
        			{
        				returnValue += "<option value='"+scd.getCode()+"' selected='selected'>"+scd.getCode_name()+"</option>";
        			}
        			else
        			{
        				returnValue += "<option value='"+scd.getCode()+"'>"+scd.getCode_name()+"</option>";
        			}
					
                }
			}
            returnValue += "</select>";
			break;
		case "radio":
            //실제 데이터 바인딩
			for(Object obj : list) {
				if(bean_type.equals("code"))
	            {
					ScdCodeBean scd = (ScdCodeBean) obj;
					if(selectItem.equals(scd.getCode())){
						returnValue += "<input type='radio' class='"+class_id+" margin-right-0 ' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked'/>"
								+ "<label for='"+control_id+iControlCount+"' class='margin-right-5'>"+scd.getCode_name()+"</label>";
        			}
					else if(selectItem.equals(scd.getCode_name())){
						returnValue += "<input type='radio' class='"+class_id+" margin-right-0 ' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked'/>"
								+ "<label for='"+control_id+iControlCount+"' class='margin-right-5'>"+scd.getCode_name()+"</label>";
        			}
					else {
						returnValue += "<input type='radio' class='"+class_id+" margin-right-0 ' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' class='margin-right-0' />"
								+ "<label for='"+control_id+iControlCount+"' class='margin-right-5'>"+scd.getCode_name()+"</label>";
					}
					
	            }
                iControlCount++;
			}
			break;
		 case "check":
			//실제 데이터 바인딩
			 for(Object obj : list) {
				 if(bean_type.equals("code"))
				 {
					ScdCodeBean scd = (ScdCodeBean) obj;
					if(selectItem.equals(scd.getCode())){
						returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
					}
					else if(selectItem.equals(scd.getCode_name())){
						returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
					}
					else {
						returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
					}
					
				 }
                 iControlCount++;
			 }
            break;
		 case"select2":
			 //System.out.println("여기까지 오냐?");
			 Map<String, Object> map = new HashMap<String, Object>();
			 List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
			 //System.out.println("여기까지 오냐?1" + list);
			 for(Object obj : list) {
				 //System.out.println("여기까지 오냐?");
				 if(bean_type.equals("code"))
				 {
					 ScdCodeBean scd = (ScdCodeBean) obj;
					 map = new HashMap<String, Object>();
					 map.put("VALUE", scd.getCode());
					 map.put("TEXT", scd.getCode_name());
					 list3.add(map);
				 }
			 }
			 //System.out.println("여기까지 오냐?3");
			 returnValue = getListMapJson(list3);
			 break;
		default:
			returnValue = getListJson(list);
			break;
		}
		return returnValue;
	}
	
	
	@Override
	public String getCodeDataSetting2(List<Object> columnTypeList) {
		String returnValue = "";
		for(Object obj : columnTypeList) {
			try {
				ScdCodeBean scd = (ScdCodeBean) obj;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		return returnValue;
	}



	@Override
	// 가져온 데이터와 파라미터를 받아서 상황에 맞도록 제 해석 하여 리턴 
	public String getCodeDataSetting(List<Object> list, Map<String, Object> map) {
		//System.out.println("getData(List<Object> list, Map<String, Object> map)!!!!");
		//System.out.println("getListJson : " + getListJson(list));
		// 컨트롤러 초기화
		controllerInit();
		// 컨트롤러 할당
		controllerSet(map);
		
		if(list==null) {
			return "";
		}
		String returnValue = "";
		int iControlCount=0;
		switch (control_type) {
		case "select":
			if(id_use.equals("y")) {
				returnValue += "<select class='"+class_id+"' id='"+control_id+"' name='"+control_id+"'>";
			}
			else {
				returnValue += "<select class='"+class_id+"' name='"+control_id+"'>";
			}
			
            if (first_item_type.equals("select"))
            {
                returnValue += "<option value=''>선택</option>";
            }
            else if (first_item_type.equals("all"))
            {
                returnValue += "<option value=''>전체</option>";
            }
            //실제 데이터 바인딩
            System.out.println("bean_type : "+ bean_type);
        	for(Object obj : list) {
        		if(bean_type.equals("code"))
                {
        			//System.out.println("여기 오냐?");
        			ScdCodeBean scd = (ScdCodeBean) obj;
        			if(selectItem.equals(scd.getCode())) 
        			{
        				returnValue += "<option value='"+scd.getCode()+"' selected='selected'>"+scd.getCode_name()+"</option>";
        			}
        			else if(selectItem.equals(scd.getCode_name())) 
        			{
        				returnValue += "<option value='"+scd.getCode()+"' selected='selected'>"+scd.getCode_name()+"</option>";
        			}
        			else
        			{
        				returnValue += "<option value='"+scd.getCode()+"'>"+scd.getCode_name()+"</option>";
        			}
					
                }
			}
            returnValue += "</select>";
			break;
		case "radio":
            //실제 데이터 바인딩
			for(Object obj : list) {
				if(map.get("bean_type").toString().equals("code"))
	            {
					ScdCodeBean scd = (ScdCodeBean) obj;
					
					if(selectItem.equals(scd.getCode())){
						returnValue += "<input type='radio' class='"+class_id+" margin-right-0 ' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked'/>"
								+ "<label for='"+control_id+iControlCount+"' class='margin-right-5'>"+scd.getCode_name()+"</label>";
        			}
					else if(selectItem.equals(scd.getCode_name())){
						returnValue += "<input type='radio' class='"+class_id+" margin-right-0 ' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked'/>"
								+ "<label for='"+control_id+iControlCount+"' class='margin-right-5'>"+scd.getCode_name()+"</label>";
        			}
					else {
						returnValue += "<input type='radio' class='"+class_id+" margin-right-0 ' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' class='margin-right-0' />"
								+ "<label for='"+control_id+iControlCount+"' class='margin-right-5'>"+scd.getCode_name()+"</label>";
					}
	            }
                iControlCount++;
			}
			break;
		 case "check":
			//실제 데이터 바인딩
			 for(Object obj : list) {
				 if(bean_type.equals("code"))
				 {
					ScdCodeBean scd = (ScdCodeBean) obj;
					if(selectItem.equals(scd.getCode())){
						returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
					}
					else if(selectItem.equals(scd.getCode_name())){
						returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' checked='checked' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
					}
					else {
						returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
					}
					//returnValue += "<input type='checkbox' class='"+class_id+"' id='"+control_id+iControlCount+"' name='"+control_id+"' value='"+scd.getCode()+"' />&nbsp;<label for='"+control_id+iControlCount+"'>"+scd.getCode_name()+"</label>&nbsp;&nbsp;";
				 }
                 iControlCount++;
			 }
            break;
		 case"select2":
			 //System.out.println("여기까지 오냐?");
			 map = new HashMap<String, Object>();
			 List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
			 //System.out.println("여기까지 오냐?1" + list);
			 for(Object obj : list) {
				 //System.out.println("여기까지 오냐?");
				 if(bean_type.equals("code"))
				 {
					 ScdCodeBean scd = (ScdCodeBean) obj;
					 map = new HashMap<String, Object>();
					 map.put("VALUE", scd.getCode());
					 map.put("TEXT", scd.getCode_name());
					 list3.add(map);
				 }
			 }
			 //System.out.println("여기까지 오냐?3");
			 returnValue = getListMapJson(list3);
			 break;
		default:
			returnValue = getListJson(list);
			break;
		}
		//System.out.println("returnValue : " + returnValue);
		return returnValue;
	}
}
