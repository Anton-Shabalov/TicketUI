package tools;
/**
 * Класс для работы с файлами.
 */

import collection.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class FileWorker {
    private static String fileName;
    private static Map<Integer, Ticket> collection;
    static Date datePreservation;
    static Date dateDownloads;
    Scanner scanner = new Scanner(System.in);

    /**
     * Считывает файл и записывает данные в коллекцию
     */

    /**
     * Записывает коллекцию в файл
     */

    /**
     * Проверяет файл на возможность чтения файла
     *
     * @param file файл с которого считываются данные
     */
    public static boolean fileCheckAccessReader(File file) {
        if (fileName.length() != 0) {
            if (file.exists()) {
                if (file.canRead()) {
                    return true;
                } else {
                    System.out.println("К сожалению у вас нет прав на чтение этого файла  ");
                    return false;
                }
            } else {
                System.out.println("Файла с указанным именем не существует");
                return false;
            }
        } else {
            System.out.println("Вы не ввели название файла. Пожалкйста введите его ");
            return false;
        }
    }

    public static boolean newfileCheckAccessReader(File file) {

        if (file.exists()) {
            if (file.canRead()) {
                return true;
            } else {
                Validation.tranlite("К сожалению у вас нет прав на чтение этого файла");
                return false;
            }
        } else {
            Validation.tranlite("Файла с указанным именем не существует");
            return false;
        }

    }

    /**
     * Просит ввести откоректированное название файла для чтения
     */
    private static void fixNameFileRead() {
        Validation.tranlite("Пожалуйста, введите другое название файла.Для выхода из программы напишите !exit");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();


    }

    /**
     * Просит ввести откоректированное название файла для запист
     */
    private static void fixNameFileWrite() {
        Scanner scanner = new Scanner(System.in);
        Validation.tranlite("Если хотите изменить файл для записи введите его название, если хотите закрыть режим сохранения введите !exit");
        fileName = scanner.nextLine();

    }

    /**
     * Проверяет файл на возможность записи
     *
     * @param file файл с которого считываются данные
     */
    private static boolean fileCheckAccessWrite(File file) {
        if (file.exists()) {
            if (file.canWrite()) {
                return true;

            } else {
                Validation.tranlite("К сожалению, у вас нет прав на запись в этот файл");
                return false;
            }

        } else {
            Validation.tranlite("К сожалению файла с указанныи именем не существет ");
            return false;
        }
    }

    public static void setCollection(Map<Integer, Ticket> collection) {
        FileWorker.collection = collection;
    }
}
