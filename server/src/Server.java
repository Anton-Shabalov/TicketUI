
import DB.DataBase;
import DB.WorkDB;
import Threed.*;
import network.*;

import tools.ConnectingСollection;
import tools.ServerCommand;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main класс программы
 */
public class Server {
    private static int port = 1932;
    private static String hostname = "localhost";

    private final Selector selector;//доступ к кналом
    private final ServerSocketChannel server;//главый канал, генерит каналы для клиентов
    private SocketChannel client;
    private final ConnectionAccepter connectionAccepter;


    public Server(int port, String hostname) throws IOException {
        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(hostname, port));//указываем порт, на котором будет приниматься подключение
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        connectionAccepter = new ConnectionAccepter(selector);
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);


    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 1932;
        if (!ConnectingСollection.connect()) {
            System.out.println("Закрываем программу");
            System.exit(0);
        } else {

            Server serverObject = new Server(1932, "localhost");
            Helper helper = new Helper();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Завершение работы сервера...");
                ConnectingСollection.handlerCommand.commandsSave();
                System.out.println("Коллекция сохранена");
            }));

            SelectableChannel Channel = helper.getStdinChannel();
            Channel.register(serverObject.selector, SelectionKey.OP_READ);
            serverObject.startServer();
        }


    }

    private static boolean flag = true;

    public void startServer() throws IOException, ClassNotFoundException {
        System.out.println("Сервер начал свою работу");
        DataBase dataBase = new DataBase();
        dataBase.connectBase();
        WorkDB.readDB();
        new ServerCommand().start();
        ExecutorService read = Executors.newFixedThreadPool(15);
        ExecutorService inquri = Executors.newCachedThreadPool();
        ExecutorService send = Executors.newCachedThreadPool();
        Stack<SelectionKey> processingSelectionKeys = new Stack<>();

        while (true) {
            try {
//            int readyChannels = selector.select();
                if (selector.selectNow() == 0) {
                    Set<SelectionKey> readyKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = readyKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = server.accept();
                            connectionAccepter.acceptConnection(socketChannel);
                        } else if (key.isReadable()) {
                            Iterator iterator1 = processingSelectionKeys.iterator();
                            boolean keyIsAlreadyInwork = false;
//
                            while (iterator1.hasNext()) {
                                try {
                                    if (key.equals(iterator1.next())) {
                                        keyIsAlreadyInwork = true;
                                        break;
                                    }
                                } catch (Exception e) {
                                }

                            }
                            if (!keyIsAlreadyInwork) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                Inquiry inquiry = new Inquiry(socketChannel, key);

                                ReadThread readThread = new ReadThread(inquiry, key, processingSelectionKeys);
                                read.execute(readThread);
                                processingSelectionKeys.add(key);
                            }
                        }
                    }
                }
                while (!InquiryThreed.inquiryQueue.isEmpty()) {
                    Inquiry inquiry = InquiryThreed.inquiryQueue.poll();
                    inquri.execute(new InquiryThreed(inquiry));
                }

                while (!SendThreed.sendQueue.isEmpty()) {
                    Inquiry inquiry = SendThreed.sendQueue.poll();
                    send.execute(new SendThreed(inquiry));
                }

            } catch (CancelledKeyException e) {

            }
        }
    }
}


