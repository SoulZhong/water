/**
 * 
 */
package com.itag.water.platform;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.itag.water.platform.service.MsgHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author Soul
 * @date May 21, 2014
 */
public class Service implements Runnable {

	private Logger logger = LogManager.getLogger(Service.class);
	private ServiceConfiguration serviceConfig;

	public Service() throws IOException {
		serviceConfig = new ServiceConfiguration();
	}

	public void run() {

		Configuration config;
		try {
			config = buildConfiguration();
			SessionFactory sessionFactory = config.configure()
					.buildSessionFactory();

			EventLoopGroup group = new NioEventLoopGroup();

			try {
				Bootstrap b = new Bootstrap();
				b.group(group).channel(NioDatagramChannel.class)
						.option(ChannelOption.SO_BROADCAST, true)
						.handler(new MsgHandler(sessionFactory));

				b.bind(serviceConfig.getPort()).sync().channel().closeFuture()
						.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				group.shutdownGracefully();
				sessionFactory.close();
			}
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	private Configuration buildConfiguration() throws IOException,
			FileNotFoundException {
		Configuration config = new Configuration();
		Properties jdbcProperties = new Properties();

		jdbcProperties.load(new FileReader(serviceConfig.getJdbcFile()));
		config.addProperties(jdbcProperties);
		return config;
	}
}
