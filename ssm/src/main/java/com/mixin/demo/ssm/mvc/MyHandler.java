package com.mixin.demo.ssm.mvc;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyHandler implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(MyHandler.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, IOException {
        logger.info("请求之前处理");
        String token = request.getParameter("token");
        logger.info("token=" + token);
        if (StringUtils.isEmpty(token)) {
//            response.getWriter().write("token is null");
//            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("postHandle方法之后处理");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("afterCompletion方法之后处理");
    }
}

