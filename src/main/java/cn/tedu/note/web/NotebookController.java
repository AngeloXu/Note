package cn.tedu.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController extends AbstractController{
	@Resource
	private NotebookService notebookService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> list(String userId){
		//System.out.println("controller:"+userId);
		List<Map<String,Object>> list= notebookService.listNotebooks(userId);
		//System.out.println("list:"+list);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	/*// 通用异常提取到父类AbstractController
	@ExceptionHandler
	@ResponseBody
	public JsonResult exp(Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	} */
	
	@RequestMapping("/notebooks.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> notebooks(String userId,int page){
		int size=6;
		System.out.println(userId);
		List<Map<String,Object>> list=notebookService.listNotebooks(userId,page,size);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	@RequestMapping("/create.do")
	@ResponseBody
	public JsonResult<Notebook> createNotebook(String userId,String notebookName,String desc){
	    System.out.println("创建notebook!,userId:"+userId+",bookname:"+notebookName);
	    Notebook notebook=notebookService.createNotebook(userId, notebookName, desc);
	    return new JsonResult<Notebook>();
	}
	
	
}
