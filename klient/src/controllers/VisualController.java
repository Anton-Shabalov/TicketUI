package controllers;

import collection.*;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.ServerConnection;
import tools.CommandRead;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

public class VisualController {
    @FXML
    private Text statusBar;

    public Button backButton;

    public ImageView imgView;


    public Canvas canvas;
    public AnchorPane anchorPane;
    public Button changeColorButton;
    public ImageView animationPic;


    //    private List<Ticket> tickets;
//    private ControllerContext context;
//    private ControlManager controlManager;
//    private ResourceBundle bundle;
//    private CommandReader reader;
    private final double MAX_X = 600;
    private final double MAX_Y = 300;
    private double xCoefficient;
    private double yCoefficient;
    private double ticketMinX;
    private double ticketMinY;
    private final double TICKET_H = 10;
    private final double TICKET_W = 10;
    private final double H_GAP = 5;
    private final double W_GAP = 8;
    private final double H_CONST = 80;
    private final double W_CONST = 80;
    @FXML
    private ImageView es;

    @FXML
    private ImageView sw;
    @FXML
    private ImageView ru;

    @FXML
    private ImageView cz;

    //    private final Controller controller = this;
//    private Map<String, java.awt.Color> colorMap;
    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();


    public void initialize() {
        backButton.setText(workingWithTranslation.mainL.getString(backButton.getText()));
        ServerConnection.statusBar = statusBar;
        serchLenguage();

        start();

    }

    private void start() {
        ticketList = CommandRead.makeObject("updatetable");
//        getCollection();
        visualize();
//        localize();
        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    ArrayList<Ticket> newArrayTicket = CommandRead.makeObject("updatetable");
                    if (!newArrayTicket.equals(ticketList)) {
                        ticketList = newArrayTicket;
                        update();
                    }

                } catch (InterruptedException | NullPointerException ignored) {
                }
            }
        });
        updateThread.start();

    }


    private void update() {
        Runnable task = () -> {
//            drawAll();
            visualize();
        };
        Platform.runLater(task);
    }


    ArrayList<Group> gg = new ArrayList<>();

    private void visualize() {
        ServerConnection.statusBar = statusBar;
        drawAll();
        for (Group p : gg) {
            RotateTransition rt = new RotateTransition(Duration.millis(2000), p);
            rt.setByAngle(360);
            rt.setCycleCount(3);
            rt.setAutoReverse(true);
            rt.setInterpolator(Interpolator.LINEAR);
            rt.play();
            rt.setOnFinished((x) -> {
            });
        }


    }

    private void drawAll() {
        double ticketMaxX = ticketList.stream().map(Ticket::getX).max((x, y) -> (int) ((x) - (y))).get();
        ticketMinX = ticketList.stream().map(Ticket::getX).max((x, y) -> (int) ((y) - (x))).get();
        xCoefficient = MAX_X / (ticketMaxX - ticketMinX + TICKET_W);
        ticketMinY = ticketList.stream().map(Ticket::getY).max((x, y) -> (int) ((y) - (x))).get();
        double ticketMaxY = ticketList.stream().map(Ticket::getY).max(Comparator.comparingDouble(x -> (x))).get();
        yCoefficient = MAX_Y / (ticketMaxY - ticketMinY + TICKET_H);
        anchorPane.getChildren().clear();
        for (Ticket tok : ticketList) {
            Group group = new Group(draw(tok));
            gg.add(group);
            anchorPane.getChildren().add(group);
        }
    }

    private Group draw(Ticket ticket) {


        String owner = ticket.getAdmin();
        Color color = convertColor(owner);

        double x = (ticket.getX() - ticketMinX) * xCoefficient;
        double y = (ticket.getY() - ticketMinY) * yCoefficient;
        Rectangle rectangle = getRectangle(color);

        Group drawing = new Group(rectangle);
        drawing.setOnMouseClicked(event -> {
            counter(ticket);
        });
        drawing.getRotate();
        drawing.setLayoutX(x);
        drawing.setLayoutY(y);
        return drawing;
    }

    public static ArrayList<Ticket> countArray = new ArrayList<>();

    private void counter(Ticket ticket) {
        int count = 0;
        double x = (ticket.getX() - ticketMinX) * xCoefficient;
        double y = (ticket.getY() - ticketMinY) * yCoefficient;
        countArray.clear();
        for (Ticket ticket1 : ticketList) {

            double x1 = (ticket1.getX() - ticketMinX) * xCoefficient;
            double y1 = (ticket1.getY() - ticketMinY) * yCoefficient;
            double k = Math.abs(Math.abs(x1) - Math.abs(x)) - 9;
            double u = Math.abs(Math.abs(y) - Math.abs(y1)) - 9;

            if ((x1 * x >= 0 && y * y1 >= 0) && u < 0 && k < 0) {
                count = count + 1;
                countArray.add(ticket1);
            }

        }
        if (count == 1) {
            controllTablePanel.updateTicket = ticket;
            controllerUserMainPanel.halpBack = false;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/screenImage/chsngeTicket.fxml"));
                Stage window = (Stage) backButton.getScene().getWindow();
                window.setScene(new Scene(root, 799.0, 464.0));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            controllerUserMainPanel.halpBack = true;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/screenImage/tableForVisual.fxml"));
                Stage window = (Stage) backButton.getScene().getWindow();
                window.setScene(new Scene(root, 799.0, 464.0));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private Rectangle getRectangle(Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(color);
        rectangle.setHeight(TICKET_H);
        rectangle.setWidth(TICKET_W);
        return rectangle;
    }

    public void clickButtonBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screenImage/userMainPanel.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root, 799.0, 464.0));

    }


    private Color convertColor(String owner) {

        double k = sha256(owner).hashCode();
        if (k < 0) {
            k = k * (-1);
        }


        return Color.color((k * 13 / 12 * 32) % 255 / 255, (k * 23 / 124 * 223) % 255 / 255, (k * 255 / 12345 * 1234) % 255 / 255);
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private void rewrite(ResourceBundle newResourceBundle) {

//        id.setText(newResourceBundle.getString(workingWithTranslation.findKey(id.getText())));
//        name.setText(newResourceBundle.getString(workingWithTranslation.findKey(name.getText())));
//
//        mesto.setText(newResourceBundle.getString(workingWithTranslation.findKey(mesto.getText())));
//        ryad.setText(newResourceBundle.getString(workingWithTranslation.findKey(ryad.getText())));
//        price.setText(newResourceBundle.getString(workingWithTranslation.findKey(price.getText())));
//        dicount.setText(newResourceBundle.getString(workingWithTranslation.findKey(dicount.getText())));
//        TicketType.setText(newResourceBundle.getString(workingWithTranslation.findKey(TicketType.getText())));
//        nameVenue.setText(newResourceBundle.getString(workingWithTranslation.findKey(nameVenue.getText())));
//        capacity.setText(newResourceBundle.getString(workingWithTranslation.findKey(capacity.getText())));
//        vvenueType.setText(newResourceBundle.getString(workingWithTranslation.findKey(vvenueType.getText())));
//        Zipcode.setText(newResourceBundle.getString(workingWithTranslation.findKey(Zipcode.getText())));
//        Adres_x.setText(newResourceBundle.getString(workingWithTranslation.findKey(Adres_x.getText())));
//        Adres_y.setText(newResourceBundle.getString(workingWithTranslation.findKey(Adres_y.getText())));
//        Adres_z.setText(newResourceBundle.getString(workingWithTranslation.findKey(Adres_z.getText())));
//        data.setText(newResourceBundle.getString(workingWithTranslation.findKey(data.getText())));
//        adminName.setText(newResourceBundle.getString(workingWithTranslation.findKey(adminName.getText())));
        backButton.setText(newResourceBundle.getString(workingWithTranslation.findKey(backButton.getText())));


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
