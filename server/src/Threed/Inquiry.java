package Threed;

import tools.Request;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class Inquiry {
    private SocketChannel socketChannel;
    private SelectionKey key;
    private Request request;
    private Request sendRequest;
    private SocketChannel client;

    @Override
    public String toString() {
        return "Inquiry{" +
                "socketChannel=" + socketChannel +
                ", key=" + key +
                ", request=" + request +
                ", sendRequest=" + sendRequest +
                ", client=" + client +
                '}';
    }

    public Inquiry(SocketChannel socketChannel, SelectionKey key) {
        this.socketChannel = socketChannel;
        this.key = key;

    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public SelectionKey getKey() {
        return key;
    }

    public Request getRequest() {
        return request;
    }

    public Request getSendRequest() {
        return sendRequest;
    }

    public SocketChannel getClient() {
        return client;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public void setKey(SelectionKey key) {
        this.key = key;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setSendRequest(Request sendRequest) {
        this.sendRequest = sendRequest;
    }

    public void setClient(SocketChannel client) {
        this.client = client;
    }
}
