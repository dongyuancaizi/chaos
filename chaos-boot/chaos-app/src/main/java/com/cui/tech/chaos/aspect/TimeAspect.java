package com.cui.tech.chaos.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面for 调用跟踪
 */
//@Aspect
//@Component
@Slf4j
public class TimeAspect {
    @Pointcut("execution(public * com.cui.tech..*.*(..))")
    public void timeLog() {
    }

    @Around("timeLog()")
    public Object doBeforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();

        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();

        //代理的是哪一个方法
        String methodNmae = signature.getName();
        //AOP代理类的名字
        String className = signature.getDeclaringTypeName();

//        JSONArray ja = new JSONArray();
//        for (Object o : obj) {
//            ja.add(o);
//        }
//        String js = ja.toJSONString();

        //log.info(className + "." + methodNmae + ", request: " + js);

        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        // log.info(className + "." + methodNmae + ", response: " + js);

        long elapsedTime = endTime - beginTime;
        if (elapsedTime < 500) {
            log.info(className + "." + methodNmae + ", elapsed: " + elapsedTime + "ms.");
        } else {
            log.warn(className + "." + methodNmae + ", elapsed: " + elapsedTime + "ms.");
        }
        return result;
    }


}
