package tools;
/**
 * Класс для работы с файлами.
 */

import collection.Ticket;
import com.google.gson.Gson;
//import network.SendingResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class FileWorker {
    private static String fileName;
    private static final Gson json = new Gson();
    private static ConcurrentHashMap<Integer, Ticket> collection;
    static Date datePreservation;
    static Date dateDownloads;


    /**
     * Конструткор для задания имени файла и колекции
     *
     * @param fileName   название файла
     * @param collection коллекция
     */
    public FileWorker(String fileName, ConcurrentHashMap<Integer, Ticket> collection) {
        this.collection = collection;
        this.fileName = fileName;

    }

    Scanner scanner = new Scanner(System.in);


    /**
     * Считывает файл и записывает данные в коллекцию
     */
    public boolean filleReader() {
        return true;
//        File file=new File(fileName);
//        if(fileCheckAccessReader(file)) {
//            try {
//                dateDownloads=new Date();
//                Scanner scanner = new Scanner(file);
//                while (scanner.hasNextLine()) {
//                    Ticket ticket1 = json.fromJson(scanner.nextLine(), Ticket.class);
//                    int id = ticket1.getId();
//                    collection.put(id, ticket1);
//                }
//                scanner.close();
//                return true;
//            } catch (FileNotFoundException e) {
//                System.err.println("Файла по данному пути не существует" );
//
//            }
//        }else {
//            fixNameFileRead();
//
//        if (fileName.equals("!exit")){
//            return false;
//
//        }else { filleReader();}
//        }
//   FileWorker.dateDownloads=new Date();
//return true;

    }

    /**
     * Записывает коллекцию в файл
     */
    public void filleWrite() {
//        datePreservation=new Date();
//        File check=new File(fileName);
//        if (fileCheckAccessWrite(check)) {
//            try {
//                datePreservation=new Date();
//                String text = "";
//                FileOutputStream clear = new FileOutputStream(fileName);
//                clear.write(text.getBytes());
//                FileOutputStream fos = new FileOutputStream(fileName, true);
//                for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
//                    Ticket ticket = collection.get(element.getKey());
//                    String colectJson = json.toJson(ticket) + "\n";
//                    fos.write(colectJson.getBytes());
//
//                }
//
//            } catch (FileNotFoundException e) {
//                System.err.println("К сожалению, файла по указанному адресу не существует.");
//            } catch (IOException e) {
//                send("Невозможно записать данные в файл");
//            }
//            send("Коллекция успешно сохранена");
//            datePreservation = new Date();
//        }else {fixNameFileWrite();
//            if (fileName.equals("!exit")){
//
//            }else { filleWrite();}
//
//        }
    }

    /**
     * Проверяет файл на возможность чтения файла
     *
     * @param file файл с которого считываются данные
     */
    public boolean fileCheckAccessReader(File file) {
        if (fileName.length() != 0) {
            if (file.exists()) {
                if (file.canRead()) {
                    return true;
                } else {
                    send("К сожалению у вас нет прав на чтение этого файла  ");
                    return false;
                }
            } else {
                send("Файла с указанным именем не существует");
                return false;
            }
        } else {
            send("Вы не ввели название файла. Пожалкйста введите его ");
            return false;
        }
    }

    /**
     * Просит ввести откоректированное название файла для чтения
     */
    private void fixNameFileRead() {
        send("Пожалуйста, введите другое название файла.Для выхода из программы напишите !exit");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();


    }

    /**
     * Просит ввести откоректированное название файла для запист
     */
    private void fixNameFileWrite() {
        Scanner scanner = new Scanner(System.in);
        send("Если хотите изменить файл для записи введите его название, если хотите закрыть режим сохранения введите !exit");
        fileName = scanner.nextLine();

    }

    /**
     * Проверяет файл на возможность записи
     *
     * @param file файл с которого считываются данные
     */
    private boolean fileCheckAccessWrite(File file) {
        if (file.exists()) {
            if (file.canWrite()) {
                return true;

            } else {
                send("К сожалению, у вас нет прав на запись в этот файл");
                return false;
            }

        } else {
            send("К сожалению файла с указанныи именем не существет ");
            return false;
        }

    }

    public void setCollection(ConcurrentHashMap<Integer, Ticket> collection) {
        FileWorker.collection = collection;

    }

    private void send(String string) {
        System.out.println(string);
    }

}
