package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteService {
	List<Map<String,Object>> listNotes(String notebookId) throws NotebookNotFoundException;
	Note loadNote(String id)throws NoteNotFoundException;
	//更新功能
	boolean saveNote(String id,String title,String body)throws NoteNotFoundException;
	public Note addNote(String userId,String notebookId,String title) throws UserNotFoundException,NotebookNotFoundException;
	
	//NoteService中增加批量删除方法
		int deleteAll(String ... noteId);
		//String...称为变长参数，本质与String[]完全一样，变长参数调用：deleteAll("1")
		//变长参数调用：deleteAll("1")
		//deleteAll("1","2","3")
		//deleteAll("1","2","3","4")
		//deleteAll()
		//按照数组处理即可
		//变长参数只能用在最后一个参数，只能使用一次。
	
}
