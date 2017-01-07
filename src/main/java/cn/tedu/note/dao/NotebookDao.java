package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Notebook;

public interface NotebookDao {
	List<Map<String,Object>> findNotebooksByUserId(String userId);
	Notebook findNotebookById(String notebookId);
	void deleteNotebook(String id);
	/**
	 * 分页查询，必须有参数：
	 * userId:用户的id
	 * start：查询起始行号
	 * rows：一次查询的最多行数
	 * @param params 封装参数的Map
	 * @return List<Map>
	 */
	List<Map<String,Object>> findNotebooksByPage(Map<String,Object> params);
	int createNotebook(Notebook notebook);
	
	
}
