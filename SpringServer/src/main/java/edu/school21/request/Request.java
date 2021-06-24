package edu.school21.request;

import edu.school21.action.Action;
import edu.school21.constants.Colors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Request {
    public static Action getAction(BufferedReader in) {
        try {
            String userAction = in.readLine();
            for (Action action: Action.values()) {
                if (action.toString().equals(userAction)) {
                    return action;
                }
            }
        } catch (IOException e) {
            System.err.println(Colors.ANSI_RED + "Can't get user action");
        }
        return Action.OFFLINE;
    }
}
