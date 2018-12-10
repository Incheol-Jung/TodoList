package com.homework.todolist.utility;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.todolist.model.pojo.JsonResponse;


@Aspect
@Component
public class SetterMonitor implements ThrowsAdvice {
	protected final Logger logger = LogManager.getLogger();
	LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
	
	@AfterThrowing(pointcut = "execution(* com.homework.todolist.service..*(..))" , throwing="e")
	public void callMethodException(JoinPoint joinPoint, Throwable e) throws Throwable {

		Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    StringBuilder keyBuilder = new StringBuilder();
	    keyBuilder.append(methodName + ":");
	    ObjectMapper oMapper = new ObjectMapper();
	    String exceptionMsg = e.getMessage();
	    
	    for (Object obj : joinPoint.getArgs()) {
			try {
				Map<String, Object> map = oMapper.convertValue(obj, Map.class);
				keyBuilder.append("{" + map.toString() + "}");
			}catch(Exception ex) {
				if(obj != null) keyBuilder.append(obj.toString());
			}
	    }
	    
	    logger.error(stuff + "\n method with arguments " + keyBuilder + " exception is: " + e.getMessage());
	    HttpResponse(exceptionMsg);
	}
	
	public void HttpResponse(String exceptionMsg) throws Throwable {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
	    
		JsonResponse<String> res = new JsonResponse<String>();
		res.setSuccess(false);
		res.setMessage(exceptionMsg);
		res.setData(null);
		
	    ObjectMapper om = new ObjectMapper();
		String returnStr = om.writeValueAsString(res);
		OutputStream ostr = response.getOutputStream();
		ostr.write(returnStr.getBytes());
		ostr.flush();
		ostr.close();
	}
}