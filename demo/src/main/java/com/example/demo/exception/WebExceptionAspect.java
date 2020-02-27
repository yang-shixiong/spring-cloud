package com.example.demo.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Aspect
@Component
public class WebExceptionAspect {

    // 凡事注解了@RequestMapping的方法都会拦截
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPoint(){}

    @AfterThrowing(pointcut = "webPoint()", throwing = "e")
    public void handleThrowing(Exception e){
        e.printStackTrace();
        // 调用写内容到浏览器
        writeContent("服务异常");

    }

    // 将内容输出到浏览器中
    private void writeContent(String content){
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;
        try{
            writer = response.getWriter();
        }catch (IOException e){
            e.printStackTrace();
        }

        writer.print(content);
        writer.flush();
        writer.close();
    }
}
