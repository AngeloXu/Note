package cn.tedu.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.tedu.note.service.UserService;

public class ACLFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response=(HttpServletResponse)res;
		HttpServletRequest request=(HttpServletRequest)req;
		String path=request.getRequestURI();
		System.out.println(path);
		path=path.substring(path.indexOf("/",1));
		System.out.println("trim path:"+path);
		if(path.matches(".*/edit\\.html$")){//检查登录：没登录必须登录
			checkLogin(request,response,chain);
			return;
		}
		if(path.matches(".(note).*\\.do$")){
			checkDotDo(request,response,chain);
			return;
		}
		chain.doFilter(request, response);//其他请求资源路径放过
	}

	private void checkDotDo(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("checkDotDo");
		String token= getCookie(request, "token");
		if(token==null){//没有登录的时候，返回json错误信息
			String json="{\"state\":1,\"message\":\"必须登录!\"}";
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(json);
		}
		//检查token的有效性:自身cookie和token是否一致
		ServletContext sc=request.getServletContext();
		ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(sc);//获取spring容器,必须配置
		UserService service=ctx.getBean("userService",UserService.class);//从容器中获取UserService对象
		String userId=getCookie(request,"userId");
		if(service.checkToken(userId,token)){
			chain.doFilter(request,response);
			return;
		}
		String json="{\"state\":1,\"message\":\"必须登录!\"}";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(json);
			
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("checkLogin");
		//检查是否有token cookie
		//如果没有，就重定向到login.html
		String token = getCookie(request, "token");
		if(token==null){
			//path=/note/log_in.html
			String path=request.getContextPath()+"/log_in.html";//动态拼接项目名
			response.sendRedirect(path);
			return;
		}
		//检查token的有效性:
		ServletContext sc=request.getServletContext();
		ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(sc);//获取spring容器,必须配置ContextLoaderListener
		UserService service=ctx.getBean("userService",UserService.class);//从容器中获取UserService对象
		String userId=getCookie(request,"userId");
		if(service.checkToken(userId,token)){
			chain.doFilter(request,response);
			return;
		}
		String path=request.getContextPath()+"/log_in.html";
		response.sendRedirect(path);
		
	}

	private String getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies=request.getCookies();
		//避免没有cookie时候发生的空指针异常
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookieName.equals(cookie.getName())){
					cookieName=cookie.getValue();
					break;
				}
			}
		}
		return cookieName;
	}

	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
