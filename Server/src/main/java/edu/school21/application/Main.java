package edu.school21.application;

import edu.school21.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        if (!args[0].equals("--port=" + port)) {
            throw new IllegalArgumentException("Invalid argument");
        }
        Server server = new Server(port);
        server.start();
    }
}
