package Threed;

import tools.Request;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLOutput;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SendThreed implements Runnable {
    Inquiry inquiry;

    public SendThreed(Inquiry inquiry) {
        this.inquiry = inquiry;

    }

    public static ConcurrentLinkedQueue<Inquiry> sendQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        try {


            Request s = inquiry.getSendRequest();

            SocketChannel channel = inquiry.getSocketChannel();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(s);
            oos.flush();
            byte[] data = bos.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(data);
            channel.write(buffer);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
