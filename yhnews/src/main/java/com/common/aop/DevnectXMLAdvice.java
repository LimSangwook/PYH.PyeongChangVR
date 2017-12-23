package com.common.aop;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.StopWatch;

public class DevnectXMLAdvice {
	Log logger = LogFactory.getLog(getClass());	
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	public void afterThrowingExceptionExecuteMethod(JoinPoint joinPoint, Exception exception) throws Exception {
		
		StringBuilder logBuffer = new StringBuilder(512);
		
		exception.printStackTrace();
	    
	    logBuffer.append("\n\n");
	    logBuffer.append("ERROR_EXCEPTION       		: " + exception.getClass() + "\n");
	    logBuffer.append("ERROR_TARGET CLASS    		: " + joinPoint.getTarget().getClass() + "\n");
	    logBuffer.append("ERROR_TARGET METHOD   		: " + joinPoint.getSignature().getName() + "\n");
	    logBuffer.append("ERROR_MESSAGE         		: " + exception.getMessage() + "\n");
	    logBuffer.append("\n");
	}
	
	public Object aroundExecuteMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
	    String targetClassName = joinPoint.getTarget().getClass().getName();
	
	    boolean isController = targetClassName.endsWith("Controller");
	    boolean isService = targetClassName.endsWith("ServiceImpl");
	    boolean isDAO = targetClassName.endsWith("Dao");
	
	    StringBuffer logBuffer = new StringBuffer();
	    if (isController){
	    	logBuffer.append("[Controller]");	    	
	    }
	    else if (isService){
	    	logBuffer.append("[Service]");
	    }
	    else if (isDAO) {
	      logBuffer.append("[DAO]");
	    }
	    
	    StopWatch stopWatch = new StopWatch();
	    Object retVal = null;
	    
	    try {
	    	if ((isController) || (isService) || (isDAO)) {
		    	
		    	stopWatch.start();
		    	this.logger.info(logBuffer.toString() + "[" + joinPoint.getTarget().getClass() + "." + signature.getName() + "] ============================= START =============================");
		    }	
		    retVal = joinPoint.proceed();		    
		} catch (Exception e) {			
			this.logger.info(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			stopWatch.stop();
			if ((isController) || (isService) || (isDAO)) {		    		    	
		    	this.logger.info(logBuffer.toString() + "[" + joinPoint.getTarget().getClass() + "." + signature.getName() + "] =============== ProcessTime ["+stopWatch.getTotalTimeSeconds()+"]초 END ===============================");
		    }
		}	
		return retVal;
	}
}
