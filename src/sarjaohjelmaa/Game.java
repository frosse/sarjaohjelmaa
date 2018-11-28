package sarjaohjelmaa;/*
    Erillinen luokka toteuttamaan yksittäistä peliä.
 */

public class Game {
    int home;
    int away;

    public Game(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }

    @Override
    public String toString() {
        return home + " vs " + away;
    }
}
