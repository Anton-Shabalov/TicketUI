package DB;

import collection.*;
import tools.ConnectingСollection;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorkDB {
    private static Connection connection = DataBase.getConnection();
    public static ConcurrentHashMap<Integer, Ticket> collection = ConnectingСollection.collection;
    public static java.util.Date save;
    public static java.util.Date dow;


    public static void readDB() {
        dow = new Date();
        collection.clear();
        String sql = "SELECT * FROM ticket";
        Statement statement = null;
        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Location location = new Location((Integer) resultSet.getInt("location_x"), (double) resultSet.getDouble("location_y"), (Long) Long.parseLong(String.valueOf(resultSet.getInt("location_z"))));
                Address address = new Address(resultSet.getString("zipcode"), location);
                Coordinates coordinates = new Coordinates(resultSet.getInt("coordinates_X"), resultSet.getDouble("coordinates_Y"));
                TicketType ticketType;
                switch (resultSet.getString("tickettype")) {
                    case "VIP":
                        ticketType = TicketType.VIP;
                        break;
                    case "USUAL":
                        ticketType = TicketType.USUAL;
                        break;
                    case "BUDGETARY":
                        ticketType = TicketType.BUDGETARY;
                        break;
                    case "CHEAP":
                        ticketType = TicketType.CHEAP;
                        break;
                    default:
                        ticketType = TicketType.VIP;
                }
                VenueType venueType;
                switch (resultSet.getString("venuetype")) {
                    case "PUB":
                        venueType = VenueType.PUB;
                        break;
                    case "OPEN_AREA":
                        venueType = VenueType.OPEN_AREA;
                        break;
                    case "MALL":
                        venueType = VenueType.MALL;
                        break;
                    default:
                        venueType = VenueType.PUB;
                }
                Venue venue = new Venue(id, resultSet.getString("venue_name"), resultSet.getInt("capacity"), venueType, address);
                Ticket ticket = new Ticket(id, resultSet.getString("name"), coordinates, resultSet.getString("dates"), resultSet.getInt("price"), resultSet.getInt("discount"), ticketType, venue, resultSet.getString("admin_name"));
                collection.put(id, ticket);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void pushall() {
        for (Map.Entry<Integer, Ticket> element : collection.entrySet()) {
            Ticket ticket = collection.get(element.getKey());
            pushTicket(ticket);
        }
    }

    public static void pushTicket(Ticket ticket) {

        String send = "INSERT INTO ticket (name, coordinates_x,coordinates_y,venue_name,capacity,venuetype,zipcode,location_x,location_y,Location_z,tickettype,admin_name,dates,price,discount) values('" + ticket.getName() + "', " + ticket.getCoordinates().getX() + "," + ticket.getCoordinates().getY() + ",'" + ticket.getVenue().getName() + "'," + ticket.getCapacity() + ",'" + ticket.getVenue().getType().toString() + "','" + ticket.getVenue().getAddress().getZipCode() + "'," + ticket.getVenue().getAddress().getTown().getX() + "," + ticket.getVenue().getAddress().getTown().getY() + "," + ticket.getVenue().getAddress().getTown().getZ() + ",'" + ticket.getType().toString() + "','" + ticket.getAdmin() + "','" + ticket.getCreationDate() + "'," + ticket.getPrice() + "," + ticket.getDiscount() + ")";
        send(send);

    }

    public static void removeitem(int key) {
        String send = "DELETE FROM ticket WHERE id =" + key + ";";
        send(send);

    }

    public static void replase(int key) {
        Ticket ticket = collection.get(key);
        String send = "UPDATE ticket SET name='" + ticket.getName() + "', coordinates_x=" + ticket.getCoordinates().getX() + ",coordinates_y=" + ticket.getCoordinates().getY() + ",venue_name='" + ticket.getVenue().getName() + "',capacity=" + ticket.getVenue().getCapacity() + ",venuetype='" + ticket.getVenue().getType() + "',zipcode='" + ticket.getVenue().getAddress().getZipCode() + "',location_x=" + ticket.getVenue().getAddress().getTown().getX() + ",location_y=" + ticket.getVenue().getAddress().getTown().getY() + ",Location_z=" + ticket.getVenue().getAddress().getTown().getZ() + ",tickettype='" + ticket.getType() + "',admin_name='" + ticket.getAdmin() + "',dates='" + ticket.getCreationDate() + "',price=" + ticket.getPrice() + ",discount=" + ticket.getDiscount() + " WHERE id=" + key;
        send(send);
        readDB();
    }

    public static void newUpdaye(Ticket ticket) {
        String send = "UPDATE ticket SET name='" + ticket.getName() + "', coordinates_x=" + ticket.getCoordinates().getX() + ",coordinates_y=" + ticket.getCoordinates().getY() + ",venue_name='" + ticket.getVenue().getName() + "',capacity=" + ticket.getVenue().getCapacity() + ",venuetype='" + ticket.getVenue().getType() + "',zipcode='" + ticket.getVenue().getAddress().getZipCode() + "',location_x=" + ticket.getVenue().getAddress().getTown().getX() + ",location_y=" + ticket.getVenue().getAddress().getTown().getY() + ",Location_z=" + ticket.getVenue().getAddress().getTown().getZ() + ",tickettype='" + ticket.getType() + "',admin_name='" + ticket.getAdmin() + "',dates='" + ticket.getCreationDate() + "',price=" + ticket.getPrice() + ",discount=" + ticket.getDiscount() + " WHERE id=" + ticket.getId();
        send(send);
        readDB();
    }

    private static void send(String send) {
        save = new Date();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ;
            statement.execute(send);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
