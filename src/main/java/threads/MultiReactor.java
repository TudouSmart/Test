package threads;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiReactor implements Runnable {

    private final int port = 8080;

    private final Selector selector;

    private final ServerSocketChannel serverSocketChannel;

    MultiReactor() throws Exception{

        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        sk.attach(new Acceptor(10, serverSocketChannel));

        InetSocketAddress socketAddress = new InetSocketAddress(port);

        serverSocketChannel.socket().bind(socketAddress);
    }

    @Override
    public void run() {
        try {
            while (! Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                keys.clear();
            }
        } catch (IOException e) {

        }
    }

    private void dispatch(SelectionKey sk) {
        Runnable rb = (Runnable) sk.attachment();
        if (rb != null) {
            rb.run();
        }
    }

    private static final class Acceptor implements Runnable {

        private final ServerSocketChannel serverSocketChannel;

        private final Selector[] selectors;

        private final SubReactor[] subReactors;

        private int index = 0;

        private final Thread[] threads;

        public Acceptor(int count, ServerSocketChannel serverSocketChannel) throws Exception {
            this.serverSocketChannel = serverSocketChannel;

            selectors = new Selector[count];
            subReactors = new SubReactor[count];
            threads = new Thread[count];
            for (int i = 0; i < count; i++) {
                selectors[i] = Selector.open();
                subReactors[i] = new SubReactor(serverSocketChannel, selectors[i], i);
                threads[i] = new Thread(subReactors[i]);
                threads[i].start();
            }
        }

        @Override
        public void run() {
            try {
                while (! Thread.interrupted()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if (socketChannel != null) {
                        Selector selector = selectors[index];
                        socketChannel.configureBlocking(false);
                        SelectionKey sk = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        sk.attach(new Handler(socketChannel));
                        index = (index+1) % selectors.length;
                    }
                }
            } catch (IOException e) {

            }
        }
    }

    private static final class SubReactor implements Runnable {
        private final ServerSocketChannel serverSocketChannel;

        private final Selector selector;

        public SubReactor(ServerSocketChannel serverSocketChannel, Selector selector, int index) {
            this.serverSocketChannel = serverSocketChannel;
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (! Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    for (SelectionKey key : keys) {
                        Runnable runnable = (Runnable) key.attachment();
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                    keys.clear();
                }
            } catch (IOException e) {

            }
        }
    }

    private static final class Handler implements Runnable {

        private final SocketChannel socketChannel;

        public Handler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            try {
                // TODO
                socketChannel.read(ByteBuffer.allocate(1024));
            } catch (Exception e) {

            }
        }
    }
}
