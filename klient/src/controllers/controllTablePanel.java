package controllers;

import collection.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.ServerConnection;
import sun.jvm.hotspot.oops.DefaultOopVisitor;
import sun.util.resources.cldr.kea.TimeZoneNames_kea;
import tools.CommandRead;
import user.authorization;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class controllTablePanel implements Initializable {
    @FXML
    private javafx.scene.control.Button qq;

    @FXML
    private Button qqq;

    @FXML
    private Text statusBar;
    public static Ticket updateTicket = null;

    @FXML
    private TableView<Ticket> table;

    @FXML
    private TableColumn<Ticket, Integer> id;

    @FXML
    private TableColumn<Ticket, String> name;

    @FXML
    private TableColumn<Ticket, Integer> mesto;

    @FXML
    private TableColumn<Ticket, Double> ryad;

    @FXML
    private TableColumn<Ticket, Float> price;

    @FXML
    private TableColumn<Ticket, Long> dicount;

    @FXML
    private TableColumn<Ticket, TicketType> TicketType;

    @FXML
    private TableColumn<Ticket, String> nameVenue;

    @FXML
    private TableColumn<Ticket, Integer> capacity;

    @FXML
    private TableColumn<Ticket, VenueType> vvenueType;

    @FXML
    private TableColumn<Ticket, String> Zipcode;

    @FXML
    private TableColumn<Ticket, Integer> Adres_x;

    @FXML
    private TableColumn<Ticket, Double> Adres_y;

    @FXML
    private TableColumn<Ticket, Long> Adres_z;

    @FXML
    private TableColumn<Ticket, String> data;
    @FXML
    private TableColumn<Ticket, String> adminName;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;

    @FXML
    private TableColumn<?, ?> adres;
    @FXML
    private TableColumn<?, ?> mestoprov;

    @FXML
    private TableColumn<?, ?> raspolo;


    ObservableList<Ticket> list = FXCollections.observableArrayList();
    ArrayList<Ticket> arrayList = new ArrayList<Ticket>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setText(workingWithTranslation.mainL.getString(id.getText()));
        name.setText(workingWithTranslation.mainL.getString(name.getText()));

        mesto.setText(workingWithTranslation.mainL.getString(mesto.getText()));
        ryad.setText(workingWithTranslation.mainL.getString(ryad.getText()));
        price.setText(workingWithTranslation.mainL.getString(price.getText()));
        dicount.setText(workingWithTranslation.mainL.getString(dicount.getText()));
        TicketType.setText(workingWithTranslation.mainL.getString(TicketType.getText()));
        nameVenue.setText(workingWithTranslation.mainL.getString(nameVenue.getText()));
        capacity.setText(workingWithTranslation.mainL.getString(capacity.getText()));
        vvenueType.setText(workingWithTranslation.mainL.getString(vvenueType.getText()));
        Zipcode.setText(workingWithTranslation.mainL.getString(Zipcode.getText()));
        Adres_x.setText(workingWithTranslation.mainL.getString(Adres_x.getText()));
        Adres_y.setText(workingWithTranslation.mainL.getString(Adres_y.getText()));
        Adres_z.setText(workingWithTranslation.mainL.getString(Adres_z.getText()));
        data.setText(workingWithTranslation.mainL.getString(data.getText()));
        adminName.setText(workingWithTranslation.mainL.getString(adminName.getText()));
        qq.setText(workingWithTranslation.mainL.getString(qq.getText()));
        qqq.setText(workingWithTranslation.mainL.getString(qqq.getText()));
        raspolo.setText(workingWithTranslation.mainL.getString(raspolo.getText()));
        mestoprov.setText(workingWithTranslation.mainL.getString(mestoprov.getText()));
        adres.setText(workingWithTranslation.mainL.getString(adres.getText()));
        statusBar.setText(workingWithTranslation.mainL.getString(statusBar.getText()));


        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Thread updateTable = new Thread(() -> {
            while (true) {
                try {

                    ArrayList<Ticket> newArrayTicket = CommandRead.makeObject("updatetable");
                    if (!newArrayTicket.equals(arrayList)) {
                        arrayList = newArrayTicket;
                        arrayToObservableList();
                        setTable();
                    }
                    Thread.sleep(1000);

                } catch (InterruptedException | NullPointerException ignored) {
                }
            }
        });
        updateTable.start();


        id.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Ticket, String>("names"));
        mesto.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("x"));
        ryad.setCellValueFactory(new PropertyValueFactory<Ticket, Double>("y"));
        price.setCellValueFactory(new PropertyValueFactory<Ticket, Float>("Price"));
        dicount.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("Discount"));
        TicketType.setCellValueFactory(new PropertyValueFactory<Ticket, TicketType>("Type"));
        nameVenue.setCellValueFactory(new PropertyValueFactory<Ticket, String>("VenName"));
        capacity.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("Capacity"));
        vvenueType.setCellValueFactory(new PropertyValueFactory<Ticket, VenueType>("VenueType"));
        Zipcode.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Zipcode"));
        Adres_x.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("LocationX"));
        Adres_y.setCellValueFactory(new PropertyValueFactory<Ticket, Double>("LocationY"));
        Adres_z.setCellValueFactory(new PropertyValueFactory<Ticket, Long>("LocationZ"));
        data.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Date"));
        adminName.setCellValueFactory(new PropertyValueFactory<Ticket, String>("Admin"));

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        table.setRowFactory(x -> {
            TableRow<Ticket> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Ticket ticket = row.getItem();
                    updateTicket = ticket;
                    try {
                        startUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });
            return row;
        });
        ServerConnection.statusBar = statusBar;

        serchLenguage();

    }


    private void arrayToObservableList() {
        list.removeAll();
        table.getItems().clear();
        for (Ticket t : arrayList) {
            list.add(t);
        }

    }


    public void setTable() {

        table.setItems(list);

    }

    private void startUpdate() throws Exception {
        controllerUserMainPanel.halpBack = true;
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/chsngeTicket.fxml"));
        Stage window = (Stage) table.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
    }

    public void ButtonExit() throws Exception {
        authorization.logins = false;
        authorization.login = null;
        authorization.password = null;
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/firstFrame.fxml"));
        Stage window = (Stage) table.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
        window.setMinWidth(799.0);
        window.setMinHeight(464.0);
        window.setWidth(799.0);
        window.setHeight(464.0);
        window.setMaxWidth(799.0);
        window.setMaxHeight(464.0);
        window.show();
    }

    public void setButtonback() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/userMainPanel.fxml"));
        Stage window = (Stage) table.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
        window.setMinWidth(799.0);
        window.setMinHeight(464.0);
        window.setWidth(799.0);
        window.setHeight(464.0);
        window.setMaxWidth(799.0);
        window.setMaxHeight(464.0);
        window.show();


    }

    private void rewrite(ResourceBundle newResourceBundle) {

        id.setText(newResourceBundle.getString(workingWithTranslation.findKey(id.getText())));
        name.setText(newResourceBundle.getString(workingWithTranslation.findKey(name.getText())));

        mesto.setText(newResourceBundle.getString(workingWithTranslation.findKey(mesto.getText())));
        ryad.setText(newResourceBundle.getString(workingWithTranslation.findKey(ryad.getText())));
        price.setText(newResourceBundle.getString(workingWithTranslation.findKey(price.getText())));
        dicount.setText(newResourceBundle.getString(workingWithTranslation.findKey(dicount.getText())));
        TicketType.setText(newResourceBundle.getString(workingWithTranslation.findKey(TicketType.getText())));
        nameVenue.setText(newResourceBundle.getString(workingWithTranslation.findKey(nameVenue.getText())));
        capacity.setText(newResourceBundle.getString(workingWithTranslation.findKey(capacity.getText())));
        vvenueType.setText(newResourceBundle.getString(workingWithTranslation.findKey(vvenueType.getText())));
        Zipcode.setText(newResourceBundle.getString(workingWithTranslation.findKey(Zipcode.getText())));
        Adres_x.setText(newResourceBundle.getString(workingWithTranslation.findKey(Adres_x.getText())));
        Adres_y.setText(newResourceBundle.getString(workingWithTranslation.findKey(Adres_y.getText())));
        Adres_z.setText(newResourceBundle.getString(workingWithTranslation.findKey(Adres_z.getText())));
        data.setText(newResourceBundle.getString(workingWithTranslation.findKey(data.getText())));
        adminName.setText(newResourceBundle.getString(workingWithTranslation.findKey(adminName.getText())));
        qq.setText(newResourceBundle.getString(workingWithTranslation.findKey(qq.getText())));
        qqq.setText(newResourceBundle.getString(workingWithTranslation.findKey(qqq.getText())));
        adres.setText(newResourceBundle.getString(workingWithTranslation.findKey(adres.getText())));
        mestoprov.setText(newResourceBundle.getString(workingWithTranslation.findKey(mestoprov.getText())));
        raspolo.setText(newResourceBundle.getString(workingWithTranslation.findKey(raspolo.getText())));
        statusBar.setText(newResourceBundle.getString(workingWithTranslation.findKey(statusBar.getText())));


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
