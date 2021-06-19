package edu.school21.models;

import java.util.Objects;

public class Statistic {
    private final long id;
    private final String player1;
    private final String player2;
    private final int shots;
    private final int misses;
    private final int hits;

    public Statistic(long id, String player1, String player2, int shots, int misses, int hits) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.shots = shots;
        this.misses = misses;
        this.hits = hits;
    }

    public long getId() {
        return id;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public int getShots() {
        return shots;
    }

    public int getMisses() {
        return misses;
    }

    public int getHits() {
        return hits;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass())
            return false;
        Statistic stat = (Statistic) object;
        return id == stat.id && player1.equals(stat.player1) && player2.equals(stat.player2) &&
                shots == stat.shots && misses == stat.misses && hits == stat.hits;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player1, player2, shots, misses, hits);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", player1: " + player1 + ", player2: " + player2 + ", shots: " + shots +
                ", misses: " + misses + ", hits: " + hits;
    }
}
