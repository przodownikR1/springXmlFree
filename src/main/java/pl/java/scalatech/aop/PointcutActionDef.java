package pl.java.scalatech.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutActionDef {
    @Pointcut("execution(public * pl.java.scalatech.web..*.*(..))")
    public void webLog() {
	}
	
	

}