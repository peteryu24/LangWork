package gmx.fwd.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WriteLog {

    private static final Logger logger = LoggerFactory.getLogger(WriteLog.class);

    @Pointcut("execution(* gmx.fwd.service.content.ContentService.*(..))")
    private void forContentService() {
    }

    @AfterThrowing(pointcut = "forContentService()", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
        logger.error("예외 발생(" + joinPoint.getSignature().getName() + ") : " + e.getMessage(), e);
    }
}
