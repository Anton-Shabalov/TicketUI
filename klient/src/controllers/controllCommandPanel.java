package controllers;

import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import collection.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.ServerConnection;
import org.w3c.dom.ls.LSOutput;
import tools.CommandRead;
import tools.newTiket;
import user.authorization;


public class controllCommandPanel {
    public static CommandRead commandRead;
    private static String nameAdmin;


    @FXML
    private Text statusBar;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text nameUser;

    @FXML
    private Button ButtonExit;

    @FXML
    private Button ButtonBack;

    @FXML
    private volatile TextArea commanList;

    @FXML
    private Button ButtonExecute;

    @FXML
    private TextField commandLine;

    @FXML
    void clickButtonExitc(ActionEvent event) {

    }

    @FXML
    private Text hi;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;
    @FXML
    private Text adminName;


    @FXML
    void initialize() {
        hi.setText(workingWithTranslation.mainL.getString(hi.getText()));
        ButtonExecute.setText(workingWithTranslation.mainL.getString(ButtonExecute.getText()));
        ButtonBack.setText(workingWithTranslation.mainL.getString(ButtonBack.getText()));
        ButtonExit.setText(workingWithTranslation.mainL.getString(ButtonExit.getText()));
        commandLine.setPromptText(workingWithTranslation.mainL.getString(commandLine.getPromptText()));
        nameAdmin = authorization.login;
        adminName.setText(nameAdmin);
        serchLenguage();
        ServerConnection.statusBar = statusBar;
        commanList.appendText(workingWithTranslation.mainL.getString("Введите команду, для простотра всех комманд введите <help>"));
        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1500);
                    ArrayList<Ticket> newArrayTicket = CommandRead.makeObject("updatetable");


                } catch (InterruptedException | NullPointerException ignored) {
                }
            }
        });
        updateThread.start();


    }

    public void writeAnwer(String value) {

        if (value.trim().length() != 0) {
            commanList.appendText("\n" + value + "\n");
        }
    }

    public void buttonBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/userMainPanel.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));

    }

    public void buttonExecute() throws SocketException {
        statusBar.setText("");
        String command = commandLine.getText();
        if (command.trim().length() != 0) {
            commandLine.setText("");
            commandRead.reader(command);
        } else {
            commanList.appendText("\n " + workingWithTranslation.mainL.getString("Вы ввели пустую строку"));
        }

    }

    public void ButtonExit() throws Exception {
        CommandRead.blockingCommandBoolean = false;
        newTiket.ff = false;
        authorization.logins = false;
        authorization.login = null;
        authorization.password = null;
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/firstFrame.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
    }

    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.out.println("15");
        }
    };

    private void rewrite(ResourceBundle newResourceBundle) {

        hi.setText(newResourceBundle.getString(workingWithTranslation.findKey(hi.getText())));
        ButtonExecute.setText(newResourceBundle.getString(workingWithTranslation.findKey(ButtonExecute.getText())));
        ButtonBack.setText(newResourceBundle.getString(workingWithTranslation.findKey(ButtonBack.getText())));
        ButtonExit.setText(newResourceBundle.getString(workingWithTranslation.findKey(ButtonExit.getText())));
        commandLine.setPromptText(newResourceBundle.getString(workingWithTranslation.findKey(commandLine.getPromptText())));

        workingWithTranslation.mainL = newResourceBundle;
    }

    private void oppacity() {
        ru.setOpacity(0.25);
        es.setOpacity(0.25);
        sw.setOpacity(0.25);
        cz.setOpacity(0.25);

    }


    public void buttonRU() {

        oppacity();
        rewrite(workingWithTranslation.lRU);
        ru.setOpacity(1);

    }

    public void buttonSV() {
        oppacity();
        rewrite(workingWithTranslation.lSV);
        sw.setOpacity(1);

    }

    public void buttonES() {
        oppacity();
        rewrite(workingWithTranslation.lES);
        es.setOpacity(1);

    }

    public void buttonCS() {
        oppacity();
        rewrite(workingWithTranslation.lCZ);
        cz.setOpacity(1);

    }

    private void serchLenguage() {


        if (workingWithTranslation.mainL.equals(workingWithTranslation.lRU)) {
            buttonRU();

        }
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lCZ)) {
            buttonCS();
        }
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lES)) {
            buttonES();
        }
        if (workingWithTranslation.mainL.equals(workingWithTranslation.lSV)) {
            buttonSV();
        }

    }


}
