package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tools.CommandRead;
import user.authorization;

public class controllerUserMainPanel {
    public static boolean halpBack;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button commandLine;

    @FXML
    private Button TeblePage;

    @FXML
    private Button Visual;

    @FXML
    private Text nameUser;
    @FXML
    private Text hi;

    @FXML
    private Button ButtonExit;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;

    @FXML
    void initialize() {
        hi.setText(workingWithTranslation.mainL.getString(hi.getText()));
        commandLine.setText(workingWithTranslation.mainL.getString(commandLine.getText()));
        TeblePage.setText(workingWithTranslation.mainL.getString(TeblePage.getText()));
        Visual.setText(workingWithTranslation.mainL.getString(Visual.getText()));
        ButtonExit.setText(workingWithTranslation.mainL.getString(ButtonExit.getText()));
        nameUser.setText(authorization.login);
        serchLenguage();


    }

    public void clickButtonExit() throws Exception {
        authorization.logins = false;
        authorization.login = null;
        authorization.password = null;
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/firstFrame.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));

    }

    public void clickButtonCommandPanel() throws Exception {
//        buttonBack.getScene().getWindow().hide();
//            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(getClass().getResource("/screenImage/firstFrame.fxmla"));
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screenImage/commandPanel.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        CommandRead.setControllCommandPanel(fxmlLoader.getController());
        Stage window = (Stage) commandLine.getScene().getWindow();
        Scene scene = new Scene(root, 799.0, 464.0);

        window.setScene(scene);
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });


    }

    public void clickButtonColumn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/tablePanel.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
        window.setMinWidth(799.0);
        window.setMinHeight(464.0);
        window.setWidth(799.0);
        window.setHeight(464.0);
        window.setMaxWidth(2880);
        window.setMaxHeight(1800);
        window.show();

    }

    public void clickButtonVisual() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/Visual.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
    }

    private void rewrite(ResourceBundle newResourceBundle) {

        hi.setText(newResourceBundle.getString(workingWithTranslation.findKey(hi.getText())));
        commandLine.setText(newResourceBundle.getString(workingWithTranslation.findKey(commandLine.getText())));
        TeblePage.setText(newResourceBundle.getString(workingWithTranslation.findKey(TeblePage.getText())));
        Visual.setText(newResourceBundle.getString(workingWithTranslation.findKey(Visual.getText())));
        ButtonExit.setText(newResourceBundle.getString(workingWithTranslation.findKey(ButtonExit.getText())));


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
