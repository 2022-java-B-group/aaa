package com.example.demo;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Autowired
    HttpSession session;

    @After("execution(* *..*Controller.*(..))")
    public void log(JoinPoint jp)throws Exception {
        
        String name=(String)session.getAttribute("name");
        String loginName=name==null ? "" :name;

        StringBuilder sb=new StringBuilder();
        sb.append("メソッド開始 : "+jp.getSignature());
        sb.append("、ログインユーザー名: "+ loginName);

        System.out.println(sb.toString());
        
        
    }

}
