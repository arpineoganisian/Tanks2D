package edu.school21.application;

import edu.school21.config.ServerConfiguration;
import edu.school21.constants.Constants;
import edu.school21.models.Context;
import edu.school21.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

    private static int getPort(String line) {
        String command = line.strip().split("=")[0];
        if (!command.equals("--port")) {
            throw new RuntimeException("Illegal command");
        }
        int port = Integer.parseInt(line.strip().split("=")[1]);
        if (port < 0 || port > 65535) {
            throw new RuntimeException("Illegal port");
        }
        return port;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 1) {
            throw new RuntimeException("Invalid sever Configuration");
        }

        ApplicationContext context = Context.getContext();
        Server server = context.getBean("server", Server.class);
        server.setPort(getPort(args[0]));
        server.start();
    }
}
