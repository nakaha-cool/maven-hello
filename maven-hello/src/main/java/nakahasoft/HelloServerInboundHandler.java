package nakahasoft;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */
public class HelloServerInboundHandler extends ChannelInboundHandlerAdapter {
	private void info (String msg) {
		System.out.println(msg);
	}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	info("[in] channelRead");
    	
        // print the msg
        ByteBuf in = (ByteBuf) msg;
        NettyUtil.printByteBuf(in);
        // Note: no need to release msg because ctx.write() will release the msg.

        // reply the response message.
        //in.writeBytes(new byte[] { 'A', 'B', 'C' }); // OK
    	ctx.writeAndFlush(in);
    	
    	//super.channelRead(ctx, msg);
    }

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    	info("[in] channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    	info("[in] channelUnregistered");
		super.channelUnregistered(ctx);
	}

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
   		info("[in] channelActive");
   		super.channelActive(ctx);
    }

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	info("[in] channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	info("[in] channelReadComplete");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    	info("[in] userEventTriggered");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    	info("[in] channelWritabilityChanged");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		info("[in] handlerAdded");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		info("[in] handlerRemoved");
		super.handlerRemoved(ctx);
	}
    
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		info("[in] exceptionCaught");
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
