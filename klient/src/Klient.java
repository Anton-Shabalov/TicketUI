import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.Locale;
import java.util.NoSuchElementException;


import java.util.ResourceBundle;
import java.util.Scanner;


import controllers.controllCommandPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.CommandRead;
import net.*;
import tools.MakeTiket;

public class Klient extends Application {
    public static CommandRead commandRead;
    private static boolean on = true;
    private static boolean flagConnect = true;
    private static String host = "localhost";
    private static int port = 1932;

    public static void main(String[] args) throws UnsupportedEncodingException {
        connectDB.connectBase();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            commandRead = new CommandRead(reader, port, host);
            controllCommandPanel.commandRead = commandRead;

        } catch (IOException e) {
            on = false;
        }
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        if (connectDB.status && on) {
            root = FXMLLoader.load(getClass().getResource("screenImage/firstFrame.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("screenImage/start.fxml"));
        }
        primaryStage.setTitle("TiketP");
        primaryStage.setScene(new Scene(root, 799.0, 464.0));
        primaryStage.setMinWidth(799.0);
        primaryStage.setMinHeight(464.0);
        primaryStage.setWidth(799.0);
        primaryStage.setHeight(464.0);
        primaryStage.setMaxWidth(799.0);
        primaryStage.setMaxHeight(464.0);
        primaryStage.show();

    }
}
