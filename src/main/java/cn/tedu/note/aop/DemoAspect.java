package cn.tedu.note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect 声明 切面儿 （横截面）组件
//@Aspect
@Component
public class DemoAspect {
	
	//@Before("bean(userService)")
	public void Hello(){
		System.out.println("Hello World!");
		String s=null;
		s.length();
		
	}
	
	/*@Before("bean(userService)")
	public void before(){
		System.out.println("Before");
	}
	
	@AfterReturning("bean(userService)")
	public void afterReturning(){
		System.out.println("AfterReturning");
	}
	@AfterThrowing(pointcut="bean(userService)",throwing="ex")
	public void afterThrowing(Exception ex) throws Exception{
		System.out.println("afterThrowing");
		throw ex;
	}
	
	@After("bean(userService)")
	public void after(){
		System.out.println("After");
	}*/
	
	//环绕
	@Around("within(cn.tedu.note.service.*)")
	public Object around(ProceedingJoinPoint jp)throws Throwable{
		System.out.println("开始");
		Signature m=jp.getSignature();
		System.out.println(m);//方法签名
		Object value=jp.proceed();//调用业务方法
		System.out.println("结束");
		return value;//业务方法的返回值
	}

	
/*	//@Before("bean(userService)")
	public void before(){
		System.out.println("Before");
	}
	
	//@AfterReturning("bean(userService)")
	public void afterReturning(){
		System.out.println("AfterReturning");
	}
	//@AfterThrowing(pointcut="bean(userService)"
	//		,throwing="ex")
	public void afterThrowing(Exception ex)
		throws Exception{
		System.out.println("AfterThrowing");
		throw ex;
	}
	//@After("bean(userService)")
	public void after(){
		System.out.println("After");
	}
	
	//环绕
	//@Around("bean(*Service)")
	//@Around("within(cn.tedu.note.service.*)")
	@Around("execution(* cn.tedu.note.service.*Service.*(..))")
	public Object around(
			ProceedingJoinPoint joinPoint)	
			throws Throwable{
		System.out.println("开始");
		//调用业务方法
		Signature m=joinPoint.getSignature();
		System.out.println(m);//输出方法签名
		Object value=joinPoint.proceed();
		System.out.println("结束");
		return value;//业务方法的返回值
	}*/
	
}









