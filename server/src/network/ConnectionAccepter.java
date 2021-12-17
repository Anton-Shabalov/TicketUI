package network;


import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;


public class ConnectionAccepter {
    private Selector selector;

    public ConnectionAccepter(Selector selector) {
        this.selector = selector;
    }

    public void acceptConnection(SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}

