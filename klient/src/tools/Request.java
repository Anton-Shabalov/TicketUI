package tools;

import collection.Ticket;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Request extends InputStream implements Serializable {
    private String lenguage = "";
    private ArrayList<Ticket> list;
    private String login;
    private String password;
    private boolean authorized;
    private String nameCommand;
    private String arguments;
    private String value;
    private Ticket ticket;
    private String[] a;
    private ArrayList<String> arrayList;

    public Request(boolean authorized) {
        this.authorized = authorized;
    }

    public Request(String nameCommand, String login, String password) {
        this.nameCommand = nameCommand;
        this.login = login;
        this.password = password;
    }

    public Request(String nameCommand, String arguments) {
        this.nameCommand = nameCommand;
        this.arguments = arguments;
    }

    public Request(String nameCommand, String arguments, Ticket ticket) {
        this.nameCommand = nameCommand;
        this.arguments = arguments;
        this.ticket = ticket;
    }

    public Request(String nameCommand, String arguments, String[] a) {
        this.nameCommand = nameCommand;
        this.arguments = arguments;
        this.a = a;

    }

    public Request(String nameCommand, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.nameCommand = nameCommand;
        this.arguments = "12";
    }

    public Request(String value) {
        this.value = value;

    }

    public Request(String nameCommand, Integer in) {
        this.nameCommand = nameCommand;

    }

    @Override
    public String toString() {
        return "Request{" +
                "list=" + list +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authorized=" + authorized +
                ", nameCommand='" + nameCommand + '\'' +
                ", arguments='" + arguments + '\'' +
                ", value='" + value + '\'' +
                ", ticket=" + ticket +
                ", a=" + Arrays.toString(a) +
                ", arrayList=" + arrayList +
                '}';
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public String getArguments() {
        return arguments;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public String getValue() {
        return value;
    }

    public String[] getA() {
        return a;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Ticket> getList() {
        return list;
    }

    public void setList(ArrayList<Ticket> list) {
        this.list = list;
    }

    public String getLenguage() {
        return lenguage;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }


}

