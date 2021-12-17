package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.connectDB;
import tools.CommandRead;


public class controllerStart {
    private static boolean on = true;
    private static boolean flagConnect = true;
    private static String host = "localhost";
    private static int port = 1932;
    private static Boolean statusWork = true;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text satrtText;

    @FXML
    private Text status;

    @FXML
    private Text statusTwo;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        status.setText("Подключиться не удалось");
        statusTwo.setText("Попробуйте запустить программу позже");
        exitButton.setText(workingWithTranslation.mainL.getString(exitButton.getText()));
        buttonRU();


    }

    private void rewrite(ResourceBundle newResourceBundle) {


        status.setText(newResourceBundle.getString(workingWithTranslation.findKey(status.getText())));
        statusTwo.setText(newResourceBundle.getString(workingWithTranslation.findKey(statusTwo.getText())));
        exitButton.setText(newResourceBundle.getString(workingWithTranslation.findKey(exitButton.getText())));

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

    public void buttonExit() {
        System.exit(0);

    }


}

