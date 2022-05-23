package com.example.web;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class CustomSessionListner implements HttpSessionListener {

	private static final Logger LOG = LoggerFactory.getLogger(CustomSessionListner.class);

	private final AtomicInteger counter = new AtomicInteger();

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		Iterator<String> ite = se.getSession().getAttributeNames().asIterator();

	    while (ite.hasNext()) {
	        String s = ite.next();
	        System.out.println(s);
	      }
		System.out.println("getAttribute"+se.getSession().getAttribute("aaaaaaa"));

		System.out.println(ToStringBuilder.reflectionToString(se.getSession().getServletContext(),
				ToStringStyle.MULTI_LINE_STYLE));
		LOG.info("sessionCreated");
		counter.incrementAndGet(); // incrementing the counter
		updateSessionCounter(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOG.info("sessionDestroyed");
		counter.decrementAndGet(); // decrementing counter
		updateSessionCounter(se);
	}

	private void updateSessionCounter(HttpSessionEvent httpSessionEvent) {
		// Let's set in the context
		httpSessionEvent.getSession().getServletContext().setAttribute("activeSession", counter.get());
		LOG.info("Total active session are {} ", counter.get());
	}

}
