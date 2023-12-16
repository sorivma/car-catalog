package com.example.carcatalog.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {
    @Before("execution(* com.example.carcatalog.controller..*.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        logControllerMethod(joinPoint, "calling", "with arguments", joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.example.carcatalog.controller..*.*(..))", returning = "result")
    public void logAfterControllerMethod(JoinPoint joinPoint, Object result) {
        logControllerMethod(joinPoint, "has called", "with result", result);
    }

    private void logControllerMethod(JoinPoint joinPoint, String action, String additionalInfo, Object additionalData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : "Anonymous";
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        logger.info("User with nickname [{}] {} method {} in class {} {} {}", username,
                action, methodName, className, additionalInfo, additionalData);
    }
}
