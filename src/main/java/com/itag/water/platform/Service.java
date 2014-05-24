/**
 * 
 */
package com.itag.water.platform;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
public class Service {

	private static final int PORT = 9999;
	private static final String JDBC_CONFIG = "/udpserver/jdbc.properties";

	public void run() throws FileNotFoundException, IOException {

		Configuration config = buildConfiguration();

		SessionFactory sessionFactory = config.configure()
				.buildSessionFactory();

		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioDatagramChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new MsgHandler(sessionFactory));

			b.bind(PORT).sync().channel().closeFuture().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
			sessionFactory.close();
		}

	}

	private Configuration buildConfiguration() throws IOException,
			FileNotFoundException {
		Configuration config = new Configuration();
		Properties jdbcProperties = new Properties();

		jdbcProperties.load(new FileReader(JDBC_CONFIG));
		config.addProperties(jdbcProperties);
		return config;
	}
}
