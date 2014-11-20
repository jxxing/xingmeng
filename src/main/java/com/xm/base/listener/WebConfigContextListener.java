package com.xm.base.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebConfigContextListener implements ServletContextListener,HttpSessionAttributeListener{
	private static Logger logger=Logger.getLogger(WebConfigContextListener.class);
	private Object lock=new Object();
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context=event.getServletContext();
		ApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
}
