package cn.tedu.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;

@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private UserDao userDao;
	@Resource
	private NotebookDao notebookDao;

	public List<Map<String, Object>> listNotes(String notebookId) throws NotebookNotFoundException {
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("id不嫩为空");
		}
		Notebook notebook=notebookDao.findNotebookById(notebookId);
		if(notebook==null){
			throw new NotebookNotFoundException("不存在该笔记本");
		}
		return noteDao.findNotesByNotebookId(notebookId);
	}

	public Note loadNote(String id) throws NoteNotFoundException {
		if(id==null||id.trim().isEmpty()){
			throw new NoteNotFoundException("id不能为kong");
		}
		
		Note note=noteDao.findNoteById(id);
		if(note==null){
			throw new NotebookNotFoundException("该id笔记不存在");
		}
		return note;
	}

	public boolean saveNote(String id, String title, String body) throws NoteNotFoundException {
		if(id==null||id.trim().isEmpty()){
			throw new NoteNotFoundException("id不能为空哦");
		}
		//Note note=noteDao.findNoteById(id);//各种属性，body较大时，性能不好
		int num=noteDao.countNotesById(id);//校验时性能好
		if(num!=1){
			throw new NoteNotFoundException("没有笔记");
		}
		Map<String,Object> note=new HashMap<String,Object>();//收集sql更新的参数
		if(title!=null&&!title.trim().isEmpty()){
			//不改变原有的title
			note.put("title",title);
		}
		if(body==null){
			body="";
		}
		note.put("body", body);
		note.put("id", id);
		note.put("lastModifyTime",new Timestamp(System.currentTimeMillis()));
		int n=noteDao.updateNote(note);
		return n==1;
	}

	public Note addNote(String userId, String notebookId, String title)
			throws UserNotFoundException, NotebookNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户Id空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("没有这个用户id");
		}
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("notebook的id空了！");
		}
		Notebook notebook=notebookDao.findNotebookById(notebookId);
		if(notebook==null){
			throw new NotebookNotFoundException("没有该笔记本");
		}
		if(title==null||title.trim().isEmpty()){
			title="葵花宝典";
		}
		String id=UUID.randomUUID().toString();
		String statusId="0";
		String typeId="0";
		String body="";
		Timestamp time=new Timestamp(System.currentTimeMillis());
		Note note=new Note(id,notebookId,userId,statusId,typeId,title,body,time,time);
		int n=noteDao.addNote(note);
		if(n!=1){
			throw new NotebookNotFoundException("笔记增加失败");
		}
		return note;
	}
	
	@Transactional
	public int deleteAll(String... noteId) {
		int n=0;
		for(String id:noteId){
			int c=noteDao.countNotesById(id);
			if(c==0){
				throw new NotebookNotFoundException();
			}
			noteDao.deleteNote(id);
			n++;
		}
		
		return n;
	}
	
	
	
	
}
