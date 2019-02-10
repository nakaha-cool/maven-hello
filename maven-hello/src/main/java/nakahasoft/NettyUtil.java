package nakahasoft;

import io.netty.buffer.ByteBuf;

public class NettyUtil {
	public static void printByteBuf(ByteBuf in) {
		System.out.print("<");
		while (in.isReadable()) {
			byte b = in.readByte();
			if (b == 0x00 || b == 0x0d) continue;
			//System.out.printf("%c(%x)", (char)b, (int)b);
			System.out.printf("%c", (char)b);
		}
		System.out.print(">");
		System.out.println();
		
		in.resetReaderIndex();
	}
}
