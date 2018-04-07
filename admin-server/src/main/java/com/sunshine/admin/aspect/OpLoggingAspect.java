package com.sunshine.admin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class OpLoggingAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(OpLoggingAspect.class);

	@Around(value = "execution(* com.sunshine.admin.service.impl.PolicyServiceImpl.saveOrUpdate*(..))")
	public Object updateOp(ProceedingJoinPoint joinPoint) {
		Object rt = false;
		try {
			rt = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("error occurs when check the condition:", e);
		}
		String className = joinPoint.getTarget().toString();
		Object[] args = joinPoint.getArgs();
		logger.info("classname:{}  args:{}", className, args);
		return rt;
	}

	@Around(value = "execution(* com.sunshine.admin.service.impl.PolicyServiceImpl.del*(..))")
	public Object delOp(ProceedingJoinPoint joinPoint) {
		Object rt = false;
		try {
			rt = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("error occurs when check the condition:", e);
		}
		String className = joinPoint.getTarget().toString();
		Object[] args = joinPoint.getArgs();
		logger.info("classname:{}  args:{}", className, args);
		return rt;
	}

}
