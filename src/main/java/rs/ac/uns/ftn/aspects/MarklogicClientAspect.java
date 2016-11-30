package rs.ac.uns.ftn.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by SBratic on 11/30/2016.
 */
@Aspect
public class MarklogicClientAspect {


  @Pointcut("within(@(rs.ac.uns.ftn.annotations.MarklogicClient *) *)")
  public void injectionPointcut() {
  }


  @Around("injectionPointcut()")
  public void injectAround(ProceedingJoinPoint joinPoint) {

  }

  ;

}
