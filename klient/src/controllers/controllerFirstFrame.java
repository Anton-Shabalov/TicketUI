package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class controllerFirstFrame {


    ////        ResourceBundle brusCZ=ResourceBundle.getBundle("resurs", new Locale("cs","CZ"));
////        System.out.println((brus.getString("ss")));
//         System.out.println(brus.getString("PP"));
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonRegistr;


    @FXML
    private Button sv;

    @FXML
    private Button buttonES;

    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;

    @FXML
    void buttonCS(MouseEvent event) {

    }

    @FXML
    void buttonES(MouseEvent event) {

    }

    @FXML
    void buttonRU(MouseEvent event) {

    }

    @FXML
    void buttonRegistr(ActionEvent event) {

    }

    @FXML
    void buttonSV(MouseEvent event) {

    }

    @FXML
    void clickButtonLogIn(ActionEvent event) {

    }


    @FXML
    void initialize() {

        buttonRegistr.setText(workingWithTranslation.mainL.getString(buttonRegistr.getText()));
        buttonLogIn.setText(workingWithTranslation.mainL.getString(buttonLogIn.getText()));
        serchLenguage();


//       buttonLogIn.setOnAction(event -> {
//           buttonLogIn.getScene().getWindow().hide();
//           FXMLLoader loader=new FXMLLoader();
//           loader.setLocation(getClass().getResource("/screenImage/LogIn.fxml"));
//
//           try {
//               loader.load();
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
//           Parent root=loader.getRoot();
//           Stage stage=new Stage();
//           stage.setScene(new Scene(root));
//           stage.setMinWidth(799.0);
//           stage.setMinHeight(464.0);
//           stage.setWidth(799.0);
//           stage.setHeight(464.0);
//           stage.setMaxWidth(799.0);
//           stage.setMaxHeight(464.0);
//           stage.showAndWait();
//       });
//        buttonRegistr.setOnAction(event -> {
//            buttonRegistr.getScene().getWindow().hide();
//            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(getClass().getResource("/screenImage/Registration.fxml"));
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
    }

    public void clickButtonLogIn() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/LogIn.fxml"));
        Stage window = (Stage) buttonLogIn.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));

    }

    public void buttonRegistr() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/Registration.fxml"));
        Stage window = (Stage) buttonLogIn.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));

    }

    private void rewrite(ResourceBundle newResourceBundle) {
        buttonRegistr.setText(newResourceBundle.getString(workingWithTranslation.findKey(buttonRegistr.getText())));
        buttonLogIn.setText(newResourceBundle.getString(workingWithTranslation.findKey(buttonLogIn.getText())));
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
