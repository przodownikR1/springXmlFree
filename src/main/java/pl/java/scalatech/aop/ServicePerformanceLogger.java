package pl.java.scalatech.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ServicePerformanceLogger {


    @Around("PointcutActionDef.webLog()")
	public Object O(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(pjp.getSignature().getName());
		Object returnValue = pjp.proceed();
		sw.stop();
        log.info("#########  method : " + pjp.getSignature().getName() + "  time:  " + sw.prettyPrint());
		return returnValue;
	}

}