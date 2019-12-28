package com.mixin.demo.ssm.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping)")
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
                            "request parameter : " +joinPoint.getTarget().getClass().getName()
                                    + "," + joinPoint.getSignature().getName()
                                    //+ mapper.w
                                    +  mapper.writeValueAsString(object)
                    );
                    logger.warn("request: "+ getFieldsName(joinPoint));
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



    private Map<String, Object> getFieldsName(JoinPoint joinPoint) throws Exception {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 参数值
        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            // 对于接受参数中含有MultipartFile，ServletRequest，ServletResponse类型的特殊处理，我这里是直接返回了null。（如果不对这三种类型判断，会报异常）
            if (args[k] instanceof MultipartFile || args[k] instanceof ServletRequest || args[k] instanceof ServletResponse) {
                return null;
            }
            if (!args[k].getClass().isPrimitive()) {
                // 当方法参数是基础类型，但是获取到的是封装类型的就需要转化成基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);

                // 当方法参数是封装类型
                //Class s = args[k].getClass();

                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        // 获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        Method method = Class.forName(classType).getMethod(methodName, classes);
        // 参数名
        String[] parameterNames = pnd.getParameterNames(method);
        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };


}