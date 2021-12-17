package Threed;

import tools.Request;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ReadThread implements Runnable {
    private Inquiry inquiry;


    ConcurrentLinkedQueue requestsWaitingProcessing = null;
    SelectionKey selectionKey = null;
    Stack openedSelectionKeys = null;

    public ReadThread(Inquiry inquiry, SelectionKey selectionKey, Stack openedSelectionKeys) {
        this.selectionKey = selectionKey;
        this.openedSelectionKeys = openedSelectionKeys;
        this.inquiry = inquiry;
    }

    @Override
    public void run() {


        SocketChannel channel = inquiry.getSocketChannel();
        SelectionKey key = inquiry.getKey();

        synchronized (channel) {
            try {
                ByteBuffer readBuffer = ByteBuffer.allocate(102400);//выделяем буффер на 1Кб
                if (!channel.isOpen())
                    return;
                int num = channel.read(readBuffer);
                if (num > 0) {

                    // Processing incoming data...
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(readBuffer.array());
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    Request request = (Request) objectInputStream.readObject();//считываем объект\
                    if (!request.getNameCommand().equals("updatetable")) {
                        System.out.println("Запрос от клиентского приложения: " + request.getNameCommand() + " от пользователя " + request.getLogin());
                    }
                    inquiry.setRequest(request);
                    inquiry.setSocketChannel(channel);

                    InquiryThreed.inquiryQueue.add(inquiry);


                } else if (num == -1) {
                    // - 1 represents that the connection has been closed
                    channel.close();
                }
            } catch (ClosedChannelException e) {
                key.cancel();
                System.out.println("Потеряно соединение с клиентом");
            } catch (Exception e) {
                key.cancel();
            }

        }
        Iterator iterator = openedSelectionKeys.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(selectionKey)) {
                iterator.remove();
            }
        }

    }
}
