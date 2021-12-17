package net;


import collection.Ticket;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.scene.text.Text;
import tools.CommandRead;
import tools.Request;
import user.authorization;
import controllers.*;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class ServerConnection {
    public static Request lastRequest = null;
    public static Text statusBar;


    public static boolean serverOn = true;
    public final SocketChannel socketChannel;

    public ServerConnection(int port, String host) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(host, port));
    }

    public String sendCommand(Request command) throws IOException, SocketException {
        // Send requests

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(command);
        oos.flush();

        byte[] data = bos.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        try {

            socketChannel.write(buffer);

            // Read response
            ByteBuffer readBuffer = ByteBuffer.allocate(102400);
            int num;

            if ((num = socketChannel.read(readBuffer)) > 0) {
                ((Buffer) readBuffer).flip();

                byte[] re = new byte[num];
                readBuffer.get(re);

                ByteArrayInputStream b = new ByteArrayInputStream(re);
                ObjectInputStream o = new ObjectInputStream(b);

                try {

                    String result = " ";

                    Request request = (Request) o.readObject();
                    ServerConnection.lastRequest = request;
                    if (!request.isAuthorized()) {
                        authorization.logins = false;
                        result = workingWithTranslation.mainL.getString("Что-то произошло с вашим логином, авторизируйтесь еще раз");
                    } else {
                        result = request.getValue();
                        CommandRead.nowComman = null;
                    }

                    return result;
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        } catch (Exception e) {

            CommandRead.conect = true;


            try {
                serverOn = false;
                statusBar.setText(workingWithTranslation.mainL.getString("Сервер временно недоступен, попробуйте позже"));
                CommandRead commandRead = new CommandRead(1932, "localhost");
            } catch (Exception exception) {

            }


//            while (true) {
//
//                try {
////                    Thread.sleep(10*1000);
//                    CommandRead commandRead = new CommandRead(1932, "localhost");
//                    while (true) {
//                        commandRead.reader("1");
//                    }
//                } catch (Exception x) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
//                    }
//
//                }
//
//            }

        }
        return " ";
    }

//        while (true){
//            System.out.println("Соединение было разовано, через 2 минуты попробуем отправить запрос еще раз");
//            try {
//                Thread.sleep( 2*60 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Попытка создать новое соединение");
//            String host = "localhost";
//            int port = 1932;
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//
//                CommandRead commandRead = new CommandRead(reader, port, host);
//                while (true) {
//
//                    commandRead.reader();
//                }
//            } catch (Exception e){
//                System.out.println("Соединиться не получилось");
//            }
//
//        }


}
