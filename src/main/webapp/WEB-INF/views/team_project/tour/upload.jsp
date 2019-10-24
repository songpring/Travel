<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
// http://localhost:8080/myapp/imageUpload
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String imageUploadPost(MultipartFile file, HttpServletRequest request, HttpSession session,Model model)
			throws Exception {
		System.out.println("/boardController POST imageUpload() ");

		// 세션값 없으면 뒤로 돌려보냄~
		String id = (String) session.getAttribute("id");

		if (id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "team_project/tour/member/msg";
		}
		
		System.out.println("BoardController /board/fwrite post  fwritePost()");
		System.out.println("originalName: " + file.getOriginalFilename());
		System.out.println("size: " + file.getSize());
		System.out.println("contentType: " + file.getContentType());


		// 파일이름 중복인 경우 뒤에 숫자 붙이기
//			UUID uid = UUID.randomUUID();
//			String saveName = uid.toString()+"_"+file.getOriginalFilename();
		String filename1 = "";
		String filename = file.getOriginalFilename();
		System.out.println("filename ==> " + filename);
		int result = service.filenameCheck(filename);
			System.out.println("filenameCheck result ==> " + result);
			
		    if (result > 0) {          
		    	// 랜덤 난수.. 하지만 희박한 확률로 같은 이름이 생성될 수 있따.. 그땐.. UUID로 다시 바꾸지뭐 후후..
		    	int r = (int)(Math.random()*1000000);
		    	String name[] = filename.split("\\.");
		    	name[0] += r;
		    	filename1 = name[0].concat("."+name[1]);
			} else {
		filename1 = filename;
			}
		    System.out.println("filename1 : " + filename1);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		String dateDir ="";
//		if(dateDir.equals("")) {
//			dateDir = year + "/" + month + "/" + date;
//			uploadPath += "\\" + dateDir + "\\";
//		}
		dateDir ="\\" + year + "/" + month + "/" + date+ "\\";
		//uploadPath += "\\" + dateDir + "\\";
		
		
			System.out.println("targetDir 찍어본다1 : "+ uploadPath);
//		File targetDir = new File(uploadPath + dateDir);
		File targetDir = new File(uploadPath + dateDir.replace('\\', '/'));
			System.out.println("targetDir 찍어본다 : " + targetDir);
		if (!targetDir.exists()) {
			System.out.println("targetDir 만들기 전..");
			targetDir.mkdirs();
			System.out.println("targetDir 만들었다..");
		}
			System.out.println("targetDir 찍어본다2 : "+uploadPath);

		// 실제 파일은 file.getBytes()
		// 파일을 upload 폴더에 넣기 => 파일 카피
		File target = new File(uploadPath + dateDir, filename1);
		System.out.println("target: 이름: " +  target);
		
		FileCopyUtils.copy(file.getBytes(), target);
		
		// 이 밑으로는 파일업로드랑 관계X
		
		
		// 자바빈 저장
		BoardBean bb = new BoardBean();
			System.out.println(request.getParameter("hashtags"));

		bb.setBoard_id("gallery");


		// 해시태그 붙여서 etc1에 저장하기
		// 해시태그 중복 없애기
		String hashtag[] = request.getParameter("hashtags").split(",");
		Set<String> set = new HashSet<String>();
		for (String i : hashtag) {
			set.add(i);

		}
		// 해시태그 중복 없앤 값 # 붙여서 str에 담기
		String str = "";
		String hashtag2[] = set.toArray(new String[set.size()]);
		for(String i : hashtag2) {
			i = "#" + i;
			str += i;
			System.out.println("str출력 ㅡ> " + str);
		}

		bb.setEtc1(str);
		bb.setSubject(request.getParameter("hashtags"));
		bb.setNotice_use(request.getParameter("notice_use"));
		bb.setSecret_use(request.getParameter("secret_use"));
		bb.setRegi_id(id);
		bb.setContent(request.getParameter("content"));
		bb.setFile_use("1");
		// regi_ip_addr, modifier_ip_addr 세팅
		bb.setRegi_ip_addr(utilService.getIp(request));
		bb.setModifier_ip_addr(utilService.getIp(request));
		service.write(bb);
		
		System.out.println("여기여기" + dateDir);
		dateDir = dateDir.replace("\\", "");
		System.out.println("여기여기2" + dateDir);
		
		FileSettingBean fs = new FileSettingBean();
		fs.setFile_name(filename1);
		fs.setPath(dateDir);
		fs.setSize(file.getSize());
		fs.setRegi_id(id);
		fs.setExt(file.getContentType());
		fs.setBoard_seq(bb.getSeq());
		// regi_ip_addr, modifier_ip_addr 세팅
		fs.setRegi_ip_addr(utilService.getIp(request));
 
		service.fileUpload(fs);

		return "redirect:gallery";
	}