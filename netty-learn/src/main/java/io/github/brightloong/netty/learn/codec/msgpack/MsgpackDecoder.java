package io.github.brightloong.netty.learn.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @author BrightLoong
 * @date 2018/11/20 09:24
 * @description
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    /**
     * Decode from one message to an other. This method will be called for each written message that can be handled
     * by this encoder.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link MessageToMessageDecoder} belongs to
     * @param msg the message to decode to an other one
     * @param out the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error accour
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.getBytes(msg.readerIndex(), bytes,0, bytes.length);
        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(bytes));
    }
}
