package cn.tedu.note.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.util.Utils;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	public UserServiceImpl() {
	}

	public User login(String name, String password) throws UserNameException, PasswordException {
		System.out.println("login");
		//检验输入参数的合理性
		if(name==null||name.trim().isEmpty()){
			throw new UserNameException("名不能空");
		}
		String reg="^\\w{3,10}$";
		if(!name.matches(reg)){
			throw new UserNameException("用户名不合3-8个规范");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(!password.matches(reg)){
			throw new PasswordException("密码不合规");
		}
		//查询用户数据
		User user=userDao.findUserByName(name);
		if(user==null){
			throw new UserNameException("用户名不存在");
		}
		//比较摘要
		/*String salt="你吃了吗？";
		String md5=DigestUtils.md5Hex(password+salt);*/
		String md5=Utils.crypt(password);
		if(user.getPassword().equals(md5)){
			//业务处理
			//登录成功，返回用户信息
			String token=UUID.randomUUID().toString();
			user.setToken(token);
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("id",user.getId());
			data.put("token", token);
			userDao.updateUser(data);
			/*String s=null;
			s.length();*/
			return user;
		}
		throw new PasswordException("密码错误");
		
	}
	
	
	@Transactional
	public User regist(String name, String nick, String password, String confirm)
			throws UserNameException, PasswordException {
		//检查用户名
		if(name==null||name.trim().isEmpty()){
			throw new UserNameException("用户名不能为空");
		}
		String reg="^\\w{3,10}$";
		if(!name.matches(reg)){
			throw new UserNameException("用户名不合规范");
		}
		if(nick==null||nick.trim().isEmpty()){
			nick=name;
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		password=password.trim();
		if(!password.equals(confirm)){
			throw new PasswordException("确认密码不一致");
		}
		if(!password.matches(reg)){
			throw new PasswordException("密码不贵范");
		}
		name=name.trim();
		//检验用户名是否重复？
		User one=userDao.findUserByName(name);
		if(one!=null){
			throw new UserNameException("已经注册");
		}
		
		//name,nick,password
		//UUID用于生成永远不重复的id
		String id=UUID.randomUUID().toString();
		System.out.println(id);
		String token="";
		String pwd=Utils.crypt(password);
		User user=new User(id,name,pwd,token,nick);
		userDao.saveUser(user);
		return user;
	}

	public boolean checkToken(String userId, String token) {
		if(userId==null||userId.trim().isEmpty()){
			return false;
		}
		if(token==null||token.trim().isEmpty()){
			return false;
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			return false;
		}
		return token.equals(user.getToken());
	}

	
	
	
	
	
}
