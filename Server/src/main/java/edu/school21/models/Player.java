package edu.school21.models;

import java.util.Objects;

public class Player {
    private final long id;
    private final String login;
    private final String password;

    public Player(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", login: " + login;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return id == player.id && login.equals(player.login) && password.equals(player.password);
    }
}
