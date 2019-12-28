package com.mixin.demo.ssm.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DavyNi on 2019/12/28.
 */
@Component
@Aspect
public class AopLoggerAspect {
    private final static Logger logger = LoggerFactory.getLogger(AopLoggerAspect.class);

    private final ObjectMapper mapper;

    @Autowired
    public AopLoggerAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    //@Pointcut("execution(public * com.mixin.demo.ssm.controller.*Controller.*(..)) || execution(public * com.mixin.demo.ssm.mvcfreemarker.*Controller.*(..)) ")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method Name : [" + methodName + "] ---> AOP before ");
        for (Object object : joinPoint.getArgs()) {
            if (
                    object instanceof MultipartFile
                            || object instanceof HttpServletRequest
                            || object instanceof HttpServletResponse
                    ) {
                continue;
            }
            try {
                if (true) {
                    logger.info(
                            joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
                                    + " : request parameter : " + mapper.writeValueAsString(object)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method Name : [" + methodName + "] ---> AOP after ");
    }

    @AfterReturning(pointcut = "pointCut()",returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method Name : [" + methodName + "] ---> AOP after return ,and result is : " + result.toString());
        if (result != null) {
            try {
                logger.info("response parameter : " + mapper.writeValueAsString(result));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method Name : [" + methodName + "] ---> AOP after throwing ,and throwable message is : " + throwable.getMessage());
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        try {
            logger.info("Method Name : [" + methodName + "] ---> AOP around start");
            long startTimeMillis = System.currentTimeMillis();
            //调用 proceed() 方法才会真正的执行实际被代理的方法
            Object result = joinPoint.proceed();
            long execTimeMillis = System.currentTimeMillis() - startTimeMillis;
            logger.info("Method Name : [" + methodName + "] ---> AOP method exec time millis : " + execTimeMillis);
            logger.info("Method Name : [" + methodName + "] ---> AOP around end , and result is : " + result.toString());
            return result;
        } catch (Throwable te) {
            logger.error(te.getMessage(),te);
            throw new RuntimeException(te.getMessage());
        }
    }

}