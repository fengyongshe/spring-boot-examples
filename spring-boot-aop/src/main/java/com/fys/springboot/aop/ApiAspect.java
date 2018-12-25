package com.fys.springboot.aop;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class ApiAspect {

  ThreadLocal<Long> startTime = new ThreadLocal<Long>();

  @Pointcut("execution(public * com.fys.springboot.aop.*Controller.*(..))")
  public void log() {
  }

  @Before("log()")
  public void beforeLog(JoinPoint joinPoint) {
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    log.info("Request URL: {}",request.getRequestURL());
    log.info("Request IP: {}", request.getRemoteAddr());
    log.info("Request Class: {}, Method:{}", joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName() );
    Map parameterMap = request.getParameterMap();
    log.info("Request Parameter: {}", JsonMapper.obj2Str(parameterMap));
  }

  @Around("log()")
  public Object arroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Object result = proceedingJoinPoint.proceed();
    log.info("return result: {}", JsonMapper.obj2Str(result));
    return result;
  }

  @AfterReturning("log()")
  public void afterReturning(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // Long start = (Long) request.getAttribute(START_TIME);
    Long end = System.currentTimeMillis();
    log.info("【请求耗时】：{}毫秒", end );
    String header = request.getHeader("User-Agent");
    UserAgent userAgent = UserAgent.parseUserAgentString(header);
    log.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}",
        userAgent.getBrowser().toString(),
        userAgent.getOperatingSystem().toString(), header);
  }

}
