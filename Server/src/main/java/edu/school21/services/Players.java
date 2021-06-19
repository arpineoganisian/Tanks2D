package edu.school21.services;

import java.net.Socket;

public interface Players {
    public void signIn(String login, String password);
    public void signUp(String login, String password);
    public void connectToSession(Socket player1, Socket player2);
}
