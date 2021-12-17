package tools;

import DB.DataBase;
import collection.Ticket;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectingСollection {
    public static ConcurrentHashMap<Integer, Ticket> collection;
    public static FileWorker fileWorker;
    public static HandlerCommand handlerCommand;
    public static CommandRead commandRead;
    public static String filleName;

    public static boolean connect() {

        collection = new ConcurrentHashMap<Integer, Ticket>();
        fileWorker = new FileWorker(filleName, collection);
        handlerCommand = new HandlerCommand();
        commandRead = new CommandRead();
        fileWorker.filleReader();
        if (DataBase.connectBase()) {
            return true;
        } else {
            return false;
        }


    }

}
