/**
 * 
 */
package com.itag.water.platform.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @author Soul
 * @date May 21, 2014
 */
public class MsgHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	private Logger logger = LogManager.getLogger(MsgHandler.class);

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

		ByteBuf bb = msg.content();

		byte[] bytes = new byte[bb.readableBytes()];
		int readerIndex = bb.readerIndex();
		bb.getBytes(readerIndex, bytes);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]).append(",");
		}
		logger.info(sb);
		logger.info(msg);
	}

}
