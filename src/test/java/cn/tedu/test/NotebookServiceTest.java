package cn.tedu.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.service.NotebookService;

public class NotebookServiceTest {
	
	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml");
	}
	@Test
	public void testListNotebook(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		NotebookService service=ctx.getBean("notebookService",NotebookService.class);
		List<Map<String,Object>> list=service.listNotebooks(userId);
		for(Map<String,Object> m:list){
			System.out.println(m);
		}
		
	}
	
	@Test
	public void testFindNotebooksByPage(){
		String userId="2273f742-61ec-4440-b88a-42cf48db19ff";
		NotebookDao dao=ctx.getBean("notebookDao",NotebookDao.class);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("start", 0);
		params.put("rows", 6);
		List<Map<String,Object>>list=dao.findNotebooksByPage(params);
		for(Map<String,Object>map:list){
			System.out.println(map);
		}
		
		dao.findNotebooksByPage(params);
		
		
		
	}

}
