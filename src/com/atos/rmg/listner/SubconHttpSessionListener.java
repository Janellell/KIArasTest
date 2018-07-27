package com.atos.rmg.listner;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atos.rmg.beans.UserBean;
import com.atos.rmg.services.RmgOperationServices;
/**
 * 
 * @author A180562
 *
 */
public class SubconHttpSessionListener implements HttpSessionListener {
	
	final static Logger LOG = Logger.getLogger(RmgOperationServices.class.getName()); 
	  @Override
	  public void sessionCreated(HttpSessionEvent event) {
	   // event.getSession().setMaxInactiveInterval(15 * 60); 
		  LOG.info("sessionCreated");
	  }

	  @Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// session destroyed
		LOG.info("sessionDestroyed");
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(event.getSession().getServletContext());
		UserBean user = (UserBean) event.getSession().getServletContext().getAttribute("user");
		RmgOperationServices rmgOperationServices = context.getBean(RmgOperationServices.class);
		if (user != null) {
			rmgOperationServices.updateActFlaginlogout(user.getUserID());
		}
	}
}
