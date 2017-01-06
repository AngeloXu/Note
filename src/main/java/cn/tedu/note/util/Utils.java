package cn.tedu.note.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
	
	private static final  String salt="你吃了吗？";
	public static String crypt(String pwd){
		return DigestUtils.md5Hex(pwd+salt);
	}
	
	
}
