package nakahasoft;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {
    private int port;

    public HelloServer(int port) {
        this.port = port;
    }
    private void info(String msg) { System.out.println(msg); }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                	 // NOTE: The channel orders are very important.
                	 //       If you change the orders, the raised events differs.
                	 //       It seems OK that the Outbound handler should be added first.
                	 ch.pipeline().addLast(new HelloServerOutboundHandler());
                     ch.pipeline().addLast(new HelloServerInboundHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)
             .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            info("b.bind() port=" + port);
            ChannelFuture f = b.bind(port);
            f.sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (Throwable t) {
        	t.printStackTrace();
        	System.out.println("Throwable catched in run(): " + t);
        	if (t instanceof java.net.BindException) {
        		java.net.BindException e = (java.net.BindException)t;
        		System.out.println("bind() failed:" + e.getMessage());
        	}
        	return;
        } finally {
            workerGroup.shutdownGracefully();
            info("workerGroup.shutdownGracefully()");
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 1111;
        }
        try {
        	System.out.println("HelloServer has started. port=" + port);
        	new HelloServer(port).run();
        } catch (Throwable t) {
        	t.printStackTrace();
        	System.out.println("Throwable catched in main: " + t);
        	return;
        }
    }
}
