package cn.tedu.note.service;

import cn.tedu.note.entity.User;

public interface UserService {
	
	User login(String name,String password) throws UserNameException,PasswordException;
	/**
	 * 用户注册功能
	 * @throws UserNameException 用户名空，或已经注册
	 * @throws PasswordException 密码不一致
	 */
	
	public User regist(String name,String nick,String password,String confirm)throws UserNameException,PasswordException;
	boolean checkToken(String userId, String token);
	
	
	
	
	
}
