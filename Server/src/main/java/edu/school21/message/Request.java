package edu.school21.message;

import edu.school21.constants.Action;

import java.io.BufferedReader;
import java.io.IOException;

public class Request {

    private static final long TIME_TO_REQUEST = 10000l;

    public static Action getAction(BufferedReader in) {
        try {
//            long begin = System.currentTimeMillis();
//            while (System.currentTimeMillis() - begin < TIME_TO_REQUEST) {
                //TODO закомменченный in.ready здесь
//                if (in.ready()) {
                    String userAction = in.readLine();
                    //TODO сюда заходит только когда отваливается один или оба клиента))))))))
                    System.out.println("Player action:" + userAction);
                    for (Action action: Action.values()) {
                        if (action.toString().equals(userAction)) {
//                            System.out.println("Action from user" + action);
                            return action;
                        }
                    }
//                }
//            }
        } catch (IOException e) {
            System.err.println("Impossible to read request from a player");
            e.printStackTrace();
        }
        return Action.OFFLINE;
    }
}
