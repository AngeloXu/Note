package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteDao {
	/**
	 * 根据笔记本ID查询全部的笔记信息
	 * @param notebookId
	 * @return
	 */
	List<Map<String,Object>> findNotesByNotebookId(String notebookId);
	Note findNoteById(String id);
	/**
	 * 更新note信息，参数是map封装的note信息数据，
	 * 必须有参数：
	 * id,lastModifyTime
	 * 可选参数：
	 * notebooId/userId/statusId/typeId/title/body
	 * 使用：
	 * Map<String,Object> note=new HashMap<String,Object>();
	 * note.put("id","123123");
	 * note.put("lastModifyTime",System.currentTimeMilles());
	 * note.put("title","问候");
	 *  //...
	 *  dao.updateNote(note);
	 * @param note
	 */
	int updateNote(Map<String,Object> note);//返回更新的记录数
	int countNotesById(String id);//校验Note存在
	int addNote(Note note);
	void deleteNote(String id);
	List<Map<String,Object>> findNoteBysParams(Map<String,Object> params);
	
}
