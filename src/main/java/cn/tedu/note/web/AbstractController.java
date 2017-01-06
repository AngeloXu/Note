package cn.tedu.note.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.util.JsonResult;

public abstract class AbstractController {
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult expHandle(Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}
	
	
}
