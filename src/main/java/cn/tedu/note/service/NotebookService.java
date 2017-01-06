package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

public interface NotebookService {
	
	List<Map<String,Object>> listNotebooks(String userId) throws UserNotFoundException;
	void deleteNotebook(String id);
	List<Map<String,Object>> listNotebooks(String userId,int pageNum,int pageSize)throws UserNotFoundException;
	
}
