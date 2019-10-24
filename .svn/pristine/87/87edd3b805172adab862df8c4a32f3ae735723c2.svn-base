package com.itwill.controller;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.domain.MemberBean;
import com.itwill.service.MemberService;

@Controller
public class MailController {

	@Autowired
	private JavaMailSender mailSender;
	@Inject
	private MemberService memberService;

	// mailSending 코드
	@RequestMapping(value = "/mailSend")
	public String mailSending(HttpServletRequest request, Model model) {
		System.out.println("이메일보내기 MailController 도착~!");

		String setfrom = "";
		String tomail = "";
		String title = "";
		String content = "";
		String url = request.getParameter("url");
		String msg = "";
		System.out.println("url이 뭐냐면 : " + url);

		if (url.equals("tourcontact")) {
			try {
				System.out.println("이메일보내기 MailController 도착~! 123123");
				setfrom = request.getParameter("name");
				tomail = request.getParameter("tomail"); // 받는 사람 이메일
				title = request.getParameter("title"); // 제목
				content = request.getParameter("content"); // 내용
				String body = "보낸이: " + setfrom + "\n\r이메일주소: " + tomail + 
						"\n\n\r <내용> \n\r" + content;

				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom("sender"); // 보내는사람 생략하면 정상작동을 안함
				messageHelper.setTo("qodqlr@gmail.com"); // 고객센터 이메일 qodqlr@gmail.com
				messageHelper.setSubject("[고객센터문의] "+title); // 메일제목은 생략이 가능하다
				messageHelper.setText(body); // 메일 내용
				
				mailSender.send(message);
				msg = "성공적으로 발송되었습니다.";
			} catch (Exception e) {
				System.out.println(e);
				msg = "이메일 발송에 실패했습니다. 고객센터로 전화주세요.";
			}

		}

		if (url.equals("tourindex") || url.equals("tourreview") || url.equals("honey")) {
			System.out.println("tourindex 안에 도착 ㅋㅋ");
			
			tomail = request.getParameter("tomail"); // 받는 사람 이메일
			System.out.println("tomail 주소는 뭐냐면 : " + tomail);
			if(tomail.equals("")) {
				model.addAttribute("msg", "이메일주소를 입력해주세요.");
				return "/team_project/tour/member/msg";
			}
			
			int result = memberService.subscriberCheck(tomail);
			System.out.println("exist값: " + result);
			
			if (result > 0) {
				msg = "이미 등록된 이메일입니다.";	
			} else {
				try {
							int sub = memberService.subsrcibe(tomail);
							if(sub == 1) {
								System.out.println(tomail+" 구독자 리스트에 추가 완료~!");
							} else {
								System.out.println("구독자 리스트에 추가 실패 ~!");
							}
							System.out.println("구독자 추가 MailController 도착~! 123123");
							setfrom = "WeTravel";
							title = "[WeTravel] 즐거운 여행 소식을 받아보세요!"; // 제목
//							content = tomail + " 님~! \n\r 구독해주셔서 감사합니다^^" + "\n\n\r "
//									 + "<a href='http://localhost:8080/myapp/tourindex'>구독취소</a>"; // 내용
			
							content = "<h3>안녕하세요 위트래블 입니다!</h3><br>" 
									+ "<h3>" + tomail + "님</h3>" + "<h3>구독해주셔서 감사합니다. 유용한 여행 소식 기대해주세요~!</h3>" + "<p>메일받기를 윈치 않으시면 구독취소 버튼을 눌러 차단하실 수 있습니다 : " 
									+ "<a href='http://localhost:8080/myapp/subscribe?email="+tomail+"'>구독취소</a></p>"
									+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";

							
//							new StringBuffer().append(tomail +"님~! \\n\\r 구독해주셔서 감사합니다^^ \\n\\n\\r")
//							.append("<a href='http://localhost:8080/myapp/tourindex'>구독취소</a>")
//							.toString();
							
							MimeMessage message = mailSender.createMimeMessage();
							MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
							messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
							messageHelper.setTo(tomail); // 구독자 이메일
							messageHelper.setSubject(title); // 메일제목
							messageHelper.setText(content, true); // 메일 내용
			
							mailSender.send(message);
							msg = "구독해주셔서 감사합니다!";
				} catch (Exception e) {
					System.out.println(e);
					msg = "이메일 발송에 실패했습니다. 고객센터로 전화주세요.";
				}
			}
//			System.out.println("이메일보내기 MailController 도착~! 22222");
//			tomail = request.getParameter("tomail"); // 받는 사람 이메일
//
//			MemberBean mb = memberService.subscriberCheck(tomail);
//			System.out.println("exist값: " + mb.getId());
//
//			if (mb != null) {
//				msg = "이미 등록된 이메일입니다.";	
//				} else {
//				
//				try {
//					System.out.println("이메일보내기 MailController 도착~! 33333");
//					int result = memberService.subsrcibe(tomail);
//					System.out.println("구독자 추가 result값: " + result);
//					
//					MimeMessage message = mailSender.createMimeMessage();
//					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//
//					messageHelper.setFrom("sender"); // 보내는사람 생략하면 정상작동을 안함
//					messageHelper.setTo(tomail); // 구독자 이메일
//					messageHelper.setSubject("[WeTravel] 즐거운 여행 소식을 받아보세요!"); // 메일제목
//					messageHelper.setText(tomail + " 님~! \n\r 구독해주셔서 감사합니다^^"); // 메일 내용
//
//					mailSender.send(message);
//
//					msg = "구독해주셔서 감사합니다! 즐거운 소식 기대해주세요 :D";
//				} catch (Exception e) {
//					System.out.println(e);
//					msg = "이메일 구독 점검중";
//				}
//
//			}
		}	

		model.addAttribute("url", url);
		model.addAttribute("msg", msg);

		return "/team_project/tour/common/msg";
	}
}