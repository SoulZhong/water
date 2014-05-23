/**
 * 
 */
package com.itag.water.platform.service;

import java.net.InetSocketAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itag.water.platform.domain.DataFrame;
import com.itag.water.platform.exception.IllegalDataFrameException;

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
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) {

		ByteBuf bb = msg.content();

		byte[] bytes = new byte[bb.readableBytes()];
		int readerIndex = bb.readerIndex();
		bb.getBytes(readerIndex, bytes);

		try {
			validate(bytes);
			DataFrame dataFrame = parseData(msg, bytes);
			logger.info(dataFrame);

			ctx.write("OK");// 回复信息
		} catch (IllegalDataFrameException e) {

			StringBuffer tmp = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				tmp.append(bytes[i] + ",");
			}
			logger.error(e.getMessage() + ",data recieved: " + tmp);
			ctx.write("ERROR");
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void validate(byte[] bytes) throws IllegalDataFrameException {
		if (bytes.length < 12) {
			throw new IllegalDataFrameException("data is uncompleted");
		} else if (bytes[0] != 64 || bytes[11] != 64) {
			throw new IllegalDataFrameException("illegall header or tail byte");
		}
	}

	/**
	 * @param msg
	 * @param bytes
	 * @return
	 */
	private DataFrame parseData(DatagramPacket msg, byte[] bytes) {
		DataFrame dataFrame = new DataFrame();

		InetSocketAddress sender = msg.sender();
		dataFrame.setIp(sender.getHostName());
		dataFrame.setPort(sender.getPort());

		dataFrame.setId(bytes[1]);
		dataFrame.setState(bytes[2]);

		double voltage = (bytes[3] << 8) + bytes[4];
		dataFrame.setVoltage(voltage);

		double electricity = bytes[5] << 8 + bytes[6];
		dataFrame.setElectricity(electricity);

		double waterGage = bytes[7] << 8 + bytes[8];
		dataFrame.setWaterGage(waterGage);

		double waterLevel = bytes[9] << 8 + bytes[10];
		dataFrame.setWaterLevel(waterLevel);
		return dataFrame;
	}
}
