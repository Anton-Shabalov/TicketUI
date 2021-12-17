//package network;
//
//
//
//import tools.CommandRead;
//import tools.ConnectingСollection;
//import tools.Request;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.nio.ByteBuffer;
//import java.nio.channels.ClosedChannelException;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.SocketChannel;
//import java.util.Optional;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.RecursiveAction;
//import java.util.logging.Logger;
//
//public class RequestReader {
//    ExecutorService pool = Executors.newFixedThreadPool(100);
//
//
//    public RequestReader() {}
//
//    public void clientService(SocketChannel channel, SelectionKey key) {
//        pool.submit(()-> {
//            //пример официант - переложить ответственность
//
//                synchronized (channel) {
//                    try {
//                        ByteBuffer readBuffer = ByteBuffer.allocate(102400);//выделяем буффер на 1Кб
//                        if (!channel.isOpen())
//                            return;
//                        int num = channel.read(readBuffer);
//                        if (num > 0) {
//                            // Processing incoming data...
//                            ByteArrayInputStream inputStream = new ByteArrayInputStream(readBuffer.array());//массив байтов
//                            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//                            Request request = (Request) objectInputStream.readObject();//считываем объект
//                            System.out.println("Запрос от клиентского приложения: "+request.getNameCommand()+" от пользователя "+request.getLogin());
////                           ConnectingСollection.commandRead.reader(request,channel);
//                        } else if (num == -1) {
//                            // - 1 represents that the connection has been closed
//                            channel.close();
//                        }
//                    } catch (ClosedChannelException e) {
//                        key.cancel();
//                        System.out.println("Потеряно соединение с клиентом");
//                    } catch (Exception e) {
//                        key.cancel();
//                    }
//
//                }
//
//        });
//    }}
