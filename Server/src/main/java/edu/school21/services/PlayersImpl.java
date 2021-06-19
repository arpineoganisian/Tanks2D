package edu.school21.services;

import java.net.Socket;
import edu.school21.session.room.SessionRoom;

public class PlayersImpl implements Players{

    @Override
    public void signIn(String login, String password) {
    }

    @Override
    public void signUp(String login, String password) {
    }

    @Override
    public void connectToSession(Socket player1, Socket player2) {
        SessionRoom.setPlayerSocket(player1, SessionRoom.Player.FIRST);
        SessionRoom.setPlayerSocket(player2, SessionRoom.Player.SECOND);
    }
}
