package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookService;

public class TxTestCase {
	
	ApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-aop.xml","spring-service.xml","spring-web.xml");
	}
	
	 /*3febebb3-a1b7-45ac-83ba-50cdb41e5fc1 |
	 | 9187ffd3-4c1e-4768-9f2f-c600e835b823 |
	 | ebd65da6-3f90-45f9-b045-782928a5e2c0 |
	 | fed920a0-573c-46c8-ae4e-368397846efd*/
	/*| c347f832-e2b2-4cb7-af6f-6710241bcdf6 |
	| 07305c91-d9fa-420d-af09-c3ff209608ff |
	| 5565bda4-ddee-4f87-844e-2ba83aa4925f |
	| 1ec185d6-554a-481b-b322-b562485bb8e8*/
	@Test
	public void testDeleteAll(){
		String id1="c347f832-e2b2-4cb7-af6f-6710241bcdf6";
		String id2="07305c91-d9fa-420d-af09-c3ff209608ff";
		String id3="5565bda4-ddee-4f87-844e-2ba83aa4925f";
		String id4="1ec185d6-554a-481b-b322-b562485bb8e8";
		NoteService service=ctx.getBean("noteService",NoteService.class);
		int n=service.deleteAll(id1,id2,id3,id4);
		System.out.println(n);
		
		
		
	}
	@Test
	public void testDeleteNotebook(){
		//测试事务的传播特性
		String id="d0e7ce0d-4893-4705-a51a-9a73d259bc70";
		NotebookService service=ctx.getBean("notebookService",NotebookService.class);
		service.deleteNotebook(id);
	
	}
	
	
}