package com.example.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter;

@EnableRedisHttpSession 
@Configuration
public class Config {
	@Bean
    public SessionEventHttpSessionListenerAdapter sessionEventHttpSessionListenerAdapter() {
        List<HttpSessionListener> demoSessionListeners = new ArrayList<HttpSessionListener>();
        demoSessionListeners.add(new CustomSessionListner());
        return new SessionEventHttpSessionListenerAdapter(demoSessionListeners);
    }
}
