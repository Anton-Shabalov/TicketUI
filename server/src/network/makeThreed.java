//package network;
//
//import tools.ConnectingСollection;
//import tools.Request;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.nio.channels.SocketChannel;
//import java.util.Optional;
//import java.util.Scanner;
//
//public class makeThreed implements Runnable {
//    SocketChannel socketChannel;
//    public makeThreed(SocketChannel socketChannel){
//        this.socketChannel=socketChannel;
//        run();
//    }
//    @Override
//    public void run() {
//        RequestReader requestReader=new RequestReader();
//        ResponseSender responseSender=new ResponseSender();
//        while (true){
//
//        try {
//            Optional<Request> command = requestReader.readRequest(socketChannel);
//            System.out.println(command);
//            if (command.isPresent()) {
//                Request s= ConnectingСollection.commandRead.reader(command.get(),socketChannel);
//                responseSender.sendAnswer(s, socketChannel);}
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//
//
//
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//
//        }}
////                            if (command.isPresent()) {
////                              String s= ConnectingСollection.commandRead.reader(command.get(),client);
////                                responseSender.sendAnswer(s, socketChannel);
////                            }
//
//
//    }
//}
