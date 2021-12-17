package Threed;

import tools.*;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InquiryThreed implements Runnable {
    Inquiry inquiry;

    public InquiryThreed(Inquiry inquiry) {
        this.inquiry = inquiry;
    }


    public static ConcurrentLinkedQueue<Inquiry> inquiryQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        ResourceBundle rs = Transfer.checkLenguage(inquiry.getRequest().getLenguage());
        inquiry.setSendRequest(ConnectingСollection.commandRead.reader(inquiry.getRequest(), inquiry.getSocketChannel(), rs));
        SendThreed.sendQueue.add(inquiry);
    }
}
