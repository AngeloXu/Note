package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Notebook;

public interface NotebookService {
	
	List<Map<String,Object>> listNotebooks(String userId) throws UserNotFoundException;
	void deleteNotebook(String id);
	List<Map<String,Object>> listNotebooks(String userId,int pageNum,int pageSize)throws UserNotFoundException;
	Notebook createNotebook(String userId,String notebookName,String desc);
	
}
