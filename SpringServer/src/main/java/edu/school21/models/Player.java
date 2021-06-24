package edu.school21.models;

import edu.school21.constants.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

@Component
@Scope("prototype")
public class Player {
 //   private long id;
    private Socket socket;
//    private String login;
//    private String password;

    public Player() {

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        System.out.println(Colors.ANSI_RED + "Connected to the game" + socket + Colors.ANSI_RESET);
        this.socket = socket;
    }
}
