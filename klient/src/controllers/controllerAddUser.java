package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import collection.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.ServerConnection;
import tools.CommandRead;
import user.authorization;

public class controllerAddUser {
    @FXML
    private Button buttonBack;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Text statusBar;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button buttonIn;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;
    @FXML
    private Text red_text;
    @FXML
    private Text login_text;
    @FXML
    private Text password_text;

    @FXML
    void initialize() {
        ServerConnection.statusBar = statusBar;
        login_text.setText(workingWithTranslation.mainL.getString(login_text.getText()));
        password_text.setText(workingWithTranslation.mainL.getString(password_text.getText()));
        red_text.setText(workingWithTranslation.mainL.getString(red_text.getText()));
        statusBar.setText(workingWithTranslation.mainL.getString(statusBar.getText()));
        buttonBack.setText(workingWithTranslation.mainL.getString(buttonBack.getText()));
        buttonIn.setText(workingWithTranslation.mainL.getString(buttonIn.getText()));
        login.setPromptText(workingWithTranslation.mainL.getString(login.getPromptText()));
        password.setPromptText(workingWithTranslation.mainL.getString(password.getPromptText()));


//        buttonBack.setOnAction(event -> {
//            buttonBack.getScene().getWindow().hide();
//            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(getClass().getResource("/screenImage/firstFrame.fxml"));
//
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent root=loader.getRoot();
//            Stage stage=new Stage();
//            stage.setScene(new Scene(root));
//
//            stage.setMinWidth(799.0);
//            stage.setMinHeight(464.0);
//            stage.setWidth(799.0);
//            stage.setHeight(464.0);
//            stage.setMaxWidth(799.0);
//            stage.setMaxHeight(464.0);
//            stage.showAndWait();
//        });
//        buttonIn.setOnAction(event -> {
//            String enterLogin=login.getText();
//            String enterPassword=password.getText();
//            if(enterLogin.trim().length()!=0){
//                if (enterPassword.trim().length()!=0){
////                    CommandRead.login(authorization.logIn(enterLogin,enterPassword));
////                    if(authorization.logins){
////                        authorization.login=enterLogin;
////                        authorization.password=enterPassword;
////                        authorization.logins=true;
////                        buttonIn.getScene().getWindow().hide();
////                        FXMLLoader loader=new FXMLLoader();
////                        loader.setLocation(getClass().getResource("/screenImage/userMainPanel.fxml"));
////
////                        try {
////                            loader.load();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                        Parent root=loader.getRoot();
////                        Stage stage=new Stage();
////                        stage.setScene(new Scene(root));
////                        stage.showAndWait();
//
//
////                    }
////                else {
////                        statusBar.setText("Данные, которые вы ввели не действительны");
////                    }
//
//
//                }else{
//                    statusBar.setText("Поле Password не может быть пустым");
//                }
//
//            }else {
//                statusBar.setText("Поле Login не может быть пустым");
//
//            }
//        });
        ServerConnection.statusBar = statusBar;
        serchLenguage();
    }

    public void clickButtonLogIn() throws Exception {
        String enterLogin = login.getText();
        String enterPassword = password.getText();
        if (enterLogin.trim().length() != 0) {
            if (enterPassword.trim().length() != 0) {
                ArrayList<Ticket> newArrayTicket = CommandRead.makeObject("updatetable");
                if (ServerConnection.serverOn) {
                    CommandRead.login(authorization.logIn(enterLogin, enterPassword));
                    if (net.addUser.addUsers(enterLogin, enterPassword)) {
                        authorization.login = enterLogin;
                        authorization.password = enterPassword;
                        authorization.logins = true;
                        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/userMainPanel.fxml"));
                        Stage window = (Stage) buttonIn.getScene().getWindow();
                        window.setScene(new Scene(root, 799.0, 464.0));
                    } else {
                        statusBar.setText(workingWithTranslation.mainL.getString("Пользователь с такими именем уже существует"));
                    }
                }


            } else {
                statusBar.setText(workingWithTranslation.mainL.getString("Поле пароль не может быть пустым"));
            }

        } else {
            statusBar.setText(workingWithTranslation.mainL.getString("Поле логин не может быть пустым"));

        }

    }

    public void clickButtonback() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/firstFrame.fxml"));
        Stage window = (Stage) buttonIn.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));

    }

    private void rewrite(ResourceBundle newResourceBundle) {

        login_text.setText(newResourceBundle.getString(workingWithTranslation.findKey(login_text.getText())));
        password_text.setText(newResourceBundle.getString(workingWithTranslation.findKey(password_text.getText())));
        red_text.setText(newResourceBundle.getString(workingWithTranslation.findKey(red_text.getText())));
        statusBar.setText(newResourceBundle.getString(workingWithTranslation.findKey(statusBar.getText())));
        buttonBack.setText(newResourceBundle.getString(workingWithTranslation.findKey(buttonBack.getText())));
        buttonIn.setText(newResourceBundle.getString(workingWithTranslation.findKey(buttonIn.getText())));
        login.setPromptText(newResourceBundle.getString(workingWithTranslation.findKey(login.getPromptText())));
        password.setPromptText(newResourceBundle.getString(workingWithTranslation.findKey(password.getPromptText())));


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
