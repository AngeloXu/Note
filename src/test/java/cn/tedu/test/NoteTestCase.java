package cn.tedu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;

public class NoteTestCase {
	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-web.xml","spring-mybatis.xml","spring-service.xml");
	}
	@Test
	public void testFindNotesByNotebookId(){
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		String nbId="d0b0727f-a233-4a1f-8600-f49fc1f25bc9";
		List<Map<String,Object>> list=dao.findNotesByNotebookId(nbId);
		for(Map<String,Object>m:list){
			System.out.println(m);
		}		
	}
	
	@Test
	public void testListNotes(){
		String id="d0b0727f-a233-4a1f-8600-f49fc1f25bc9";
		NoteService service=ctx.getBean("noteService",NoteService.class);
		List<Map<String,Object>> list=service.listNotes(id);
		for(Map<String,Object>m:list){
			System.out.println(m);
		}
	}
	@Test
	public void testFindNoteById(){
		String id="7851d547-d8a3-40cc-9948-c701cd905d41";
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		Note note=dao.findNoteById(id);
		System.out.println(note);

	}
	
	@Test
	public void testLoadNote(){
		String id="7851d547-d8a3-40cc-9948-c701cd905d41";
		NoteService service=ctx.getBean("noteService",NoteService.class);
		Note note=service.loadNote(id);
		System.out.println(note);
	}
	
	@Test
	public void  testUpdateNote(){
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		String id="7851d547-d8a3-40cc-9948-c701cd905d41";
		Map<String,Object> note=new HashMap<String,Object>();
		//加入比选ID
		note.put("id",id);
		note.put("lastModifyTime",System.currentTimeMillis());
		//加入可选参数
		note.put("title", "YOU KKKK");
		note.put("body", "hello!");
		int n=dao.updateNote(note);
		System.out.println(n);
		//select * from cn_note where cn_note_id=?
	}
	@Test
	public void testCountNotesById(){
		String id="7851d547-d8a3-40cc-9948-c701cd905d41";
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		int n=dao.countNotesById(id);
		System.out.println(n);
	}
	@Test
	public void testSaveNote(){
		String id="7851d547-d8a3-40cc-9948-c701cd905d41";
		NoteService service=ctx.getBean("noteService",NoteService.class);
		boolean b=service.saveNote(id,"javaa阿娇第三方考虑", "Java WEB阿斯顿发三大");
		System.out.println(b);
		Note n=service.loadNote(id);
		System.out.println(n);
	}
	@Test
	public void testAddNote(){
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		String id="1223213";
		String notebookId="0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		String statusId="0";
		String typeId="0";
		String title="hello";
		String body="Java";
		long now=System.currentTimeMillis();
		Note note=new Note(id,notebookId,userId,statusId,typeId,title,body,now,now);
		int n=dao.addNote(note);
		System.out.println(n);
	}
	@Test
	public void testAddNoteService(){
		NoteService service=ctx.getBean("noteService",NoteService.class);
		String notebookId="0037215c-09fe-4eaa-aeb5-25a340c6b39b";
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		String title="helloJavavavava";
		long now=System.currentTimeMillis();
		Note note=service.addNote(userId,notebookId,title);
		System.out.println(note);
		
	}
	@Test
	public void testFindNoteByParams(){
		Map<String,Object> params=new HashMap<String,Object>();
		//params.put("userId","");
		//params.put("notebookId","");
		params.put("key","Java");
		params.put("start",0);
		params.put("rows", 10);
		NoteDao dao=ctx.getBean("noteDao",NoteDao.class);
		List<Map<String,Object>> list=dao.findNoteBysParams(params);
		for(Map<String,Object> map:list){
			System.out.println(map);
		}	
	}
	
	
}
