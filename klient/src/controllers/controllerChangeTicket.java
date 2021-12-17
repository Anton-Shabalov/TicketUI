package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import collection.*;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.ServerConnection;
import tools.CommandRead;
import tools.Validation;
import tools.newTiket;
import user.authorization;

public class controllerChangeTicket {
    private Ticket ticket;
    ObservableList<TicketType> TicketType = FXCollections.observableArrayList(collection.TicketType.VIP, collection.TicketType.USUAL, collection.TicketType.CHEAP, collection.TicketType.BUDGETARY);
    ObservableList<VenueType> VenueType = FXCollections.observableArrayList(collection.VenueType.PUB, collection.VenueType.OPEN_AREA, collection.VenueType.MALL);

    @FXML
    private ResourceBundle resources;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;

    @FXML
    private URL location;
//
//    @FXML
//    private Text nameUser;

    @FXML
    private Button ButtonExit;

    @FXML
    private TextField nameTicket;

    @FXML
    private TextField mesto;

    @FXML
    private Text q;

    @FXML
    private TextField ryad;

    @FXML
    private TextField price;

    @FXML
    private TextField discount;

    @FXML
    private ChoiceBox<TicketType> typeTicket;

    @FXML
    private TextField nameVenue;

    @FXML
    private TextField capacity;

    @FXML
    private ChoiceBox<VenueType> typeVenue;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField xVenue;

    @FXML
    private TextField yVenue;

    @FXML
    private TextField zVenue;

    @FXML
    private Text nameCreatorTXT;
    @FXML
    private Text nameCreator;

    @FXML
    private Text status;

    @FXML
    private Button update;

    @FXML
    private Button back;


    @FXML
    private Text hi;


    @FXML
    private Text nameTicket_text;

    @FXML
    private Text mesto_Text;


    @FXML
    private Text ryad_text;


    @FXML
    private Text priceText;


    @FXML
    private Text discountText;


    @FXML
    private Text typeTicketText;


    @FXML
    private Text nameVenueText;
    @FXML
    private Text nameUser;


    @FXML
    private Text capacityText;

    @FXML
    private Text TypeVenueText;


    @FXML
    private Text zipCOdeText;


    @FXML
    void clickButtonExit(ActionEvent event) {

    }

    private boolean permission = false;

    @FXML
    void initialize() {

        nameTicket_text.setText(workingWithTranslation.mainL.getString(nameTicket_text.getText()));
        mesto_Text.setText(workingWithTranslation.mainL.getString(mesto_Text.getText()));
        ryad_text.setText(workingWithTranslation.mainL.getString(ryad_text.getText()));
        priceText.setText(workingWithTranslation.mainL.getString(priceText.getText()));
        discountText.setText(workingWithTranslation.mainL.getString(discountText.getText()));
        typeTicketText.setText(workingWithTranslation.mainL.getString(typeTicketText.getText()));
        TypeVenueText.setText(workingWithTranslation.mainL.getString(TypeVenueText.getText()));
        nameVenueText.setText(workingWithTranslation.mainL.getString(nameVenueText.getText()));
        capacityText.setText(workingWithTranslation.mainL.getString(capacityText.getText()));
        zipCOdeText.setText(workingWithTranslation.mainL.getString(zipCOdeText.getText()));
        back.setText(workingWithTranslation.mainL.getString(back.getText()));
        ButtonExit.setText(workingWithTranslation.mainL.getString(ButtonExit.getText()));
        update.setText(workingWithTranslation.mainL.getString(update.getText()));
        status.setText(workingWithTranslation.mainL.getString(status.getText()));
        nameCreatorTXT.setText(workingWithTranslation.mainL.getString(nameCreatorTXT.getText()));
        hi.setText(workingWithTranslation.mainL.getString(hi.getText()));


        nameUser.setText(authorization.login);
        typeTicket.setItems(TicketType);
        typeVenue.setItems(VenueType);
//        nameUser.setText(authorization.login);
        setTicket();
        serchLenguage();
        ServerConnection.statusBar = status;

    }


    private void setTicket() {
        ticket = controllTablePanel.updateTicket;


        if (authorization.login.equals(ticket.getAdmin())) {
            update.setVisible(true);
            permission = true;
        } else {
            status.setText(workingWithTranslation.mainL.getString("У вас нет прав на изменение этого обьекта"));
            update.setVisible(false);
        }
        nameTicket.setText(ticket.getName());
        mesto.setText(String.valueOf(ticket.getX()));
        ryad.setText(String.valueOf(ticket.getY()));
        price.setText(String.valueOf(ticket.getPrice()));
        discount.setText(String.valueOf(ticket.getDiscount()));
        nameVenue.setText(String.valueOf(ticket.getVenName()));
        capacity.setText(String.valueOf(ticket.getCapacity()));
        zipcode.setText(String.valueOf(ticket.getZipcode()));
        xVenue.setText(String.valueOf(ticket.getLocationX()));
        yVenue.setText(String.valueOf(ticket.getLocationY()));
        zVenue.setText(String.valueOf(ticket.getLocationZ()));
        typeVenue.setValue(ticket.getVenueType());
        typeTicket.setValue(ticket.getType());
        nameCreator.setText(ticket.getAdmin());
    }

    public void Buttonupdate() throws Exception {
        checkLoyut();
    }


    public void ButtonExit() throws Exception {
        authorization.logins = false;
        authorization.login = null;
        authorization.password = null;
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/firstFrame.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));
    }

    public void setButtonback() throws Exception {
        if (controllerUserMainPanel.halpBack) {
            Parent root = FXMLLoader.load(getClass().getResource("/screenImage/tablePanel.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            window.setScene(new Scene(root, 799.0, 464.0));

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/screenImage/Visual.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            window.setScene(new Scene(root, 799.0, 464.0));

        }
    }

    private void checkLoyut() {
        if (nameTicket.getText().trim().length() != 0 && mesto.getText().trim().length() != 0 && ryad.getText().trim().length() != 0 && price.getText().trim().length() != 0 && discount.getText().trim().length() != 0 && nameVenue.getText().trim().length() != 0 && capacity.getText().trim().length() != 0 && zipcode.getText().trim().length() != 0 && xVenue.getText().trim().length() != 0 && yVenue.getText().trim().length() != 0 && zVenue.getText().trim().length() != 0) {


            String name = nameTicket.getText();
            if (name.trim().length() != 0) {
                try {
                    int TX = Integer.parseInt(mesto.getText());
                    if (TX > -951) {
                        try {
                            double TY = Double.parseDouble(ryad.getText());
                            try {
                                float Tprice = Float.parseFloat(price.getText());
                                try {
                                    long Tdis = Long.parseLong(discount.getText());
                                    if (Tdis >= 0 && Tdis <= 100) {
                                        String nameVen = nameVenue.getText();
                                        try {
                                            int Tcapa = Integer.parseInt(capacity.getText());
                                            if (Tcapa > 0) {
                                                String tZip = zipcode.getText();
                                                if (tZip.trim().length() < 20) {
                                                    try {
                                                        Integer x = Integer.parseInt(xVenue.getText());
                                                        try {
                                                            Double y = Double.parseDouble(yVenue.getText());

                                                            try {

                                                                Long z = Long.parseLong(zVenue.getText());
                                                                Ticket tti = new Ticket(ticket.getId(), name, new Coordinates(TX, TY), ticket.getCreationDate(), Tprice, Tdis, typeTicket.getValue(), new Venue(ticket.getId(), nameVen, Tcapa, typeVenue.getValue(), new Address(tZip, new Location(x, y, z))), ticket.getAdmin());
                                                                CommandRead.makeObject(tti);
                                                                status.setText(workingWithTranslation.mainL.getString("Элемент успещно обновлен"));


                                                            } catch (Exception e) {
                                                                status.setText(workingWithTranslation.mainL.getString("Z может быть только long"));
                                                            }


                                                        } catch (Exception e) {
                                                            status.setText(workingWithTranslation.mainL.getString("Y может быть только Double"));
                                                        }


                                                    } catch (Exception e) {
                                                        status.setText(workingWithTranslation.mainL.getString("X может быть только int"));
                                                    }


                                                } else {
                                                    status.setText(workingWithTranslation.mainL.getString("Индекс не может быть больше 20 символов"));
                                                }

                                            } else {
                                                status.setText(workingWithTranslation.mainL.getString("Вместимость может быть строго больше 0"));
                                            }
                                        } catch (Exception e) {
                                            status.setText(workingWithTranslation.mainL.getString("Вметсимость может быть только числом"));
                                        }

                                    } else {
                                        status.setText(workingWithTranslation.mainL.getString("Скидка может быть от 0 до 100"));
                                    }
                                } catch (Exception e) {
                                    status.setText(workingWithTranslation.mainL.getString("Поле скидка может быть только Long"));
                                }

                            } catch (Exception e) {
                                status.setText(workingWithTranslation.mainL.getString("цена может быть только float"));
                            }


                        } catch (Exception e) {
                            status.setText(workingWithTranslation.mainL.getString("Поле ряд может быть только Double"));
                        }


                    } else {
                        status.setText(workingWithTranslation.mainL.getString("Место может быть строго больше -951"));
                    }


                } catch (Exception e) {
                    status.setText(workingWithTranslation.mainL.getString("В поле место не int значение"));
                }

            } else {
                status.setText(workingWithTranslation.mainL.getString("Имя не может быть пустым"));
            }


        } else {
            status.setText(workingWithTranslation.mainL.getString("Не все поля заполнены"));
        }
    }

    private void rewrite(ResourceBundle newResourceBundle) {


//        id.setText(newResourceBundle.getString(workingWithTranslation.findKey(id.getText())));
//        name.setText(newResourceBundle.getString(workingWithTranslation.findKey(name.getText())));
        nameTicket_text.setText(newResourceBundle.getString(workingWithTranslation.findKey(nameTicket_text.getText())));
        mesto_Text.setText(newResourceBundle.getString(workingWithTranslation.findKey(mesto_Text.getText())));
        ryad_text.setText(newResourceBundle.getString(workingWithTranslation.findKey(ryad_text.getText())));
        priceText.setText(newResourceBundle.getString(workingWithTranslation.findKey(priceText.getText())));
        discountText.setText(newResourceBundle.getString(workingWithTranslation.findKey(discountText.getText())));
        typeTicketText.setText(newResourceBundle.getString(workingWithTranslation.findKey(typeTicketText.getText())));
        TypeVenueText.setText(newResourceBundle.getString(workingWithTranslation.findKey(TypeVenueText.getText())));
        nameVenueText.setText(newResourceBundle.getString(workingWithTranslation.findKey(nameVenueText.getText())));
        capacityText.setText(newResourceBundle.getString(workingWithTranslation.findKey(capacityText.getText())));
        zipCOdeText.setText(newResourceBundle.getString(workingWithTranslation.findKey(zipCOdeText.getText())));
        back.setText(newResourceBundle.getString(workingWithTranslation.findKey(back.getText())));
        ButtonExit.setText(newResourceBundle.getString(workingWithTranslation.findKey(ButtonExit.getText())));
        update.setText(newResourceBundle.getString(workingWithTranslation.findKey(update.getText())));
        status.setText(newResourceBundle.getString(workingWithTranslation.findKey(status.getText())));
        nameCreatorTXT.setText(newResourceBundle.getString(workingWithTranslation.findKey(nameCreatorTXT.getText())));
        hi.setText(newResourceBundle.getString(workingWithTranslation.findKey(hi.getText())));


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
