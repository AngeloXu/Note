package cn.tedu.test;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

public class UserServiceTest {
	

	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml");
	}
	@Test
	public void testLogin(){
		UserService service=ctx.getBean("userService",UserService.class);
		String name="Tom";
		String password="123";
		User user=service.login(name, password);
		System.out.println(user);
	}
	@Test
	public void testRegistUser(){
		UserService service=ctx.getBean("userService",UserService.class);
		User user=service.regist("Jerry","Mouse","123","123" );
		System.out.println(user);
	}
	@Test
	public void testUpdateUser(){
		
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		Map<String,Object> user=new HashMap<String,Object>();
		user.put("id","48595f52-b22c-4485-9244-f4004255b972");
		user.put("token",UUID.randomUUID().toString());
		int n=dao.updateUser(user);
		System.out.println(n);
		
	}
	
}
