package cn.tedu.test;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

public class MyBatisTestCase {
	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml");
	}
	@Test
	public void test1(){
		DataSource ds=ctx.getBean("dataSource",DataSource.class);
		System.out.println(ds);
	}
	@Test
	public void test2(){
		SqlSessionFactory ssf=ctx.getBean("sqlSessionFactory",SqlSessionFactory.class);
		System.out.println(ssf);
	}
	@Test
	public void test3(){
		MapperScannerConfigurer scanner=ctx.getBean("mapperScanner",MapperScannerConfigurer.class);
		System.out.println(scanner);
	}
	@Test
	public void test4(){
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=new User("78","Tommy","123","","Cat");
		dao.saveUser(user);
		//select * from cn_user where cn_user_id='123'
	}
	@Test
	public void testFindUserById(){
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		String id="123";
		User user=dao.findUserById(id);
		System.out.println(user);
	}
	@Test
	public void testFindUesrByName(){
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		String name="zhoujia";
		User user=dao.findUserByName(name);
		System.out.println(user);
	}
	/*@Test
	public void testUpdateUser(){
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User u=dao.findUserById("78");
		u.setNick("Buby");
		dao.updateUser(u);
	}*/
	@Test
	public void testFindNotebookByUserId(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		NotebookDao dao=ctx.getBean("notebookDao",NotebookDao.class);
		List<Map<String,Object>> list=dao.findNotebooksByUserId(userId);
		for(Map<String,Object>m:list){
			System.out.println(m);
		}
		
	}
	
	
}
