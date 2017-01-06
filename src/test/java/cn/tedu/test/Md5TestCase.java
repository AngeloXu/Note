package cn.tedu.test;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

public class Md5TestCase {
	
	@Test
	public void testMd5(){
		String str="123";
		String md5=DigestUtils.md5Hex(str);
		System.out.println(md5);
	}
	
	@Test
	public void testSaltPwd() throws UnsupportedEncodingException{
		String pwd="123";
		String salt="你吃了吗？";
		String s=DigestUtils.md5Hex(pwd+salt);
		System.out.println(s);
	}
	
	@Test
	public void testUUID(){
		String id=UUID.randomUUID().toString();
		System.out.println(id);
	}
	
	
}
