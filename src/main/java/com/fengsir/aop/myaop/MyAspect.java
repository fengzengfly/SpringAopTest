package com.fengsir.aop.myaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Aspect
@Component
public class MyAspect {

  /**
   * 定义切入点：对要拦截的方法进行定义与限制，如包、类
   *
   * 1、execution(public * *(..)) 任意的公共方法
   * 2、execution（* set*（..）） 以set开头的所有的方法
   * 3、execution（* com.fengsir.aop.annotation.LoggerApply.*（..））com.fengsir.aop.annotation.LoggerApply这个类里的所有的方法
   * 4、execution（* com.fengsir.aop.annotation.*.*（..））com.fengsir.aop.annotation包下的所有的类的所有的方法
   * 5、execution（* com.fengsir.aop.annotation..*.*（..））com.fengsir.aop.annotation包及子包下所有的类的所有的方法
   * 6、execution(* com.fengsir.aop.annotation..*.*(String,?,Long)) com.fengsir.aop.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
   * 7、execution(@annotation(com.fengsir.aop.annotation.MyAnnotation))
   */
  @Pointcut("@annotation(com.fengsir.aop.myaop.MyAnnotation)")
  public void addAdvice() {}

  /**
   * 前置通知：在目标方法执行前调用
   */
  @Before("addAdvice()")
  public void begin(JoinPoint joinPoint) {

    // 获取目标方法名称
    String methodName = joinPoint.getSignature().getName();
    // 获取目标方法的参数
    Object[] args = joinPoint.getArgs();
    System.out.println("==@Before== user valid  : begin => " + methodName);
    for (int i = 0; i < args.length; i++) {
      System.out.println("参数 " + i + " : " + args[i]);
    }
  }

  /**
   * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
   */
  @AfterReturning("addAdvice()")
  public void afterReturning() {
    System.out.println("==@AfterReturning== user valid : valid returning");
  }

  /**
   * 后置/最终通知：无论目标方法在执行过程中出现一场都会在它之后调用
   */
  @After("addAdvice()")
  public void after() {
    System.out.println("==@After== user valid : finally returning");
  }

  /**
   * 异常通知：目标方法抛出异常时执行
   */
  @AfterThrowing("addAdvice()")
  public void afterThrowing() {
    System.out.println("==@AfterThrowing== user valid : valid exception");
  }

  @Around("addAdvice()")
  public Object Interceptor(ProceedingJoinPoint joinPoint) {

    // 获取目标方法的名称
    String methodName = joinPoint.getSignature().getName();
    System.out.println("==@AfterThrowing== 正在执行方法: " + methodName);

    Object result = null;

    // 获取方法传入参数
    Object[] args = joinPoint.getArgs();

    if (args != null && args.length > 0) {
      String userId = (String) args[0];
      if (!"03".equals(userId)) {
        return "no anthorization";
      }
    }
    try {
      // joinPoint.proceed() 执行源方法，返回值位源方法的返回结果
      result = joinPoint.proceed();
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return result;
  }
}
