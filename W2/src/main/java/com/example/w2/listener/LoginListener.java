package com.example.w2.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Log4j2
public class LoginListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String name = event.getName();

        Object obj = event.getValue();

        if (name.equals("LoginInfo")) {
            log.info("A user Logined..................");
            log.info(obj);
        }
    }
}
