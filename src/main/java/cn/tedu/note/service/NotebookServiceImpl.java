package cn.tedu.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;
@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {
	@Resource
	private NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	@Resource
	private NoteService noteService;
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listNotebooks(String userId) throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNameException("用户id为空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("查无此人");
		}
		System.out.println(userId);
		return notebookDao.findNotebooksByUserId(userId);
	}
	
	//事务的传播特性
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteNotebook(String id) {
		List<Map<String,Object>> list=noteService.listNotes(id);
		for(Map<String,Object>map:list){
			String noteId=(String)map.get("id");
			noteService.deleteAll(id);
		}
	/*	String s=null;
		s.length();*/
		notebookDao.deleteNotebook(id);		
	}

	public List<Map<String, Object>> listNotebooks(String userId, int pageNum, int pageSize)
			throws UserNotFoundException {
		//计算起始行号
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户Id不能为空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		int start=pageNum*pageSize;
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("start", start);
		params.put("rows", pageSize);
		return notebookDao.findNotebooksByPage(params);
	}
	
	public Notebook createNotebook(String userId,String notebookName,String desc ){
	    if(userId==null||userId.trim().isEmpty()){
	        throw new UserNotFoundException("用户id不能为空！");
	    }
	    User user=userDao.findUserById(userId);
	    if(user==null){
	        throw new UserNotFoundException("没有这个用户id");
	    }
	    if(notebookName==null||notebookName.trim().isEmpty()){
	        notebookName="新建笔记本";
	    }
	    String id=UUID.randomUUID().toString();
	    String typeId="0";
	    Timestamp time =new Timestamp(System.currentTimeMillis());
	    Notebook notebook=new Notebook(id,userId,typeId,notebookName,desc,time);
	    int n=notebookDao.createNotebook(notebook);
	    if(n!=1){
	        throw new UserNotFoundException("笔记本增加失败！");
	    }
	    return notebook;
	}
	
	
}
