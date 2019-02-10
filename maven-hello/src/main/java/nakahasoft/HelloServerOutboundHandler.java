package nakahasoft;

import java.net.SocketAddress;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class HelloServerOutboundHandler extends ChannelOutboundHandlerAdapter {
	private void info (String msg) {
		System.out.println(msg);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		info("[out] handlerAdded");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		info("[out] handlerRemoved");
		super.handlerRemoved(ctx);
	}

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		info("[out] bind");
		super.bind(ctx, localAddress, promise);
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		info("[out] connect");
		super.connect(ctx, remoteAddress, localAddress, promise);
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		info("[out] disconnect");
		super.disconnect(ctx, promise);
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		info("[out] close");
		super.close(ctx, promise);
	}

	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		info("[out] deregister");
		super.deregister(ctx, promise);
	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		info("[out] read");
		super.read(ctx);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		info("[out] write msg=" + msg);
		super.write(ctx, msg, promise);
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		info("[out] flush");
		super.flush(ctx);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		info("[out] exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
