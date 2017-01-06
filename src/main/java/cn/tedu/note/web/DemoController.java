package cn.tedu.note.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public Object hello(HttpServletResponse res){
		Cookie cookie=new Cookie("name","Tommy");
		cookie.setMaxAge(60*60*24*30);
		cookie.setPath("/");
		res.addCookie(cookie);
		return new String[]{"hello","World!"};
	}
	
	@RequestMapping("/hi.do")
	@ResponseBody
	public Object hi(HttpServletRequest req){
		Cookie[] cookies=req.getCookies();
		for(Cookie cookie:cookies){
			System.out.println(cookie);
		}
		return new String[]{"HI"};
	}
	
	@RequestMapping("/test.do")
	@ResponseBody
	public Object test() throws InterruptedException{
		Thread t=Thread.currentThread();
		String name=t.getName();
		long id=t.getId();
		Thread.sleep(10000);
		System.out.println("线程："+t);
		return new String[]{name+":"+id,"OK!"};
	}
	
	
	
	
	
}
