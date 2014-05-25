/**
 * 
 */
package com.itag.water.platform.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itag.water.platform.Service;

/**
 * @author Soul
 * @date 2014年5月25日
 */
public class ApplicationInitListener implements ServletContextListener {

	private Logger logger = LogManager.getLogger(ApplicationInitListener.class);
	private Service service;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			service = new Service();

			// service.run();
			new Thread(service).start();

		} catch (IOException e) {
			logger.error("Exception on init the udpservice.", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
