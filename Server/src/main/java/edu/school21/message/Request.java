package edu.school21.message;

import edu.school21.constants.Action;

import java.io.BufferedReader;
import java.io.IOException;

public class Request {

    public static Action getAction(BufferedReader in) {
        try {
            String userAction = in.readLine();
            System.out.println("Player action:" + userAction);
            for (Action action: Action.values()) {
                if (action.toString().equals(userAction)) {
                    return action;
                }
            }
        } catch (IOException e) {
            System.err.println("Impossible to read request from a player");
            e.printStackTrace();
        }
        return Action.OFFLINE;
    }
}
