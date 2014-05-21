/**
 * 
 */
package com.itag.water.platform;

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

	public void run() {

		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, true)
					.handler(new MsgHandler());

			b.bind(PORT).sync().channel().closeFuture().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}
}
