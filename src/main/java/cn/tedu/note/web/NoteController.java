package cn.tedu.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteNotFoundException;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.util.JsonResult;

@RequestMapping("/note")
@Controller
public class NoteController extends AbstractController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> list(String notebookId){
		List<Map<String, Object>> list=noteService.listNotes(notebookId);
		return new JsonResult<List<Map<String,Object>>>(list);
		
		
	}
	@ResponseBody
	@RequestMapping("/load.do")
	public JsonResult<Note> load(String id){
		Note note=noteService.loadNote(id);
		return new JsonResult<Note>(note);
	}
	
	/*@ExceptionHandler(NoteNotFoundException.class)
	@ResponseBody
	public JsonResult noteEx(NoteNotFoundException e){
		e.printStackTrace();
		return new JsonResul
	}
	*/
	
	@ResponseBody
	@RequestMapping("/save.do")
	public JsonResult<Boolean> save(String id,String title,String body){
		boolean b=noteService.saveNote(id, title, body);
		return new JsonResult<Boolean>(b);
		
	}
	@ResponseBody
	@RequestMapping("/add.do")
	public JsonResult<Note> add(String userId,String notebookId,String title){
		Note note=noteService.addNote(userId, notebookId, title);
		return new JsonResult<Note>(note);
	}
	
	
}
