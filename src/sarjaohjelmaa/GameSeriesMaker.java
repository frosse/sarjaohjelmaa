package sarjaohjelmaa;

import java.util.ArrayList;
import java.util.Random;

/*
    Sarjaohjelman tekijä-luokka
 */

public class GameSeriesMaker {
     static final Random rand = new Random();

    // Tehdään pelilista eli kaikki pelattavat pelit, siten että jokainen
    // joukkue pelaa toisiaan vastaan GameSeries-oliossa määritetyn määrän
    // Joka toisella kohtaamisella joukkueet käännetään toisinpäin (ensin kotona, sitten vieraissa, sitten kotona...)

    public void makeGameList(GameSeries gs) {

        for (int h = 0; h < gs.amount; h++) {

            for (int i = 1; i < gs.teams+1; i++) {

                for (int j = i+1; j < gs.teams+1; j++) {

                    gs.getList().add( h%2== 0 ?new Game(i, j) : new Game(j,i));
                }
            }
        }
    }

    // Tehdään sarjataulukko. Arvotaan jokainen peli pelilistalta, jollekkin kierrokselle
    // Jos kierroksella on jo riittävä määrä pelejä eli joukkueet/2, niin generoidaan uusia indexejä niin kauan,
    // että peli mahtuu kierrokselle.
    public void makeSeries(GameSeries gs) {

        ArrayList<Game>[] series = gs.getSeries();

        for (int i = 0; i < series.length; i++) {
            series[i] = new ArrayList<>();
        }

        for (Game g : gs.getList()) {

            while (true) {

                int rnd = rand.nextInt(gs.rounds);

                if (series[rnd].size() < gs.gamesPerRound) {

                    series[rnd].add(g);
                    break;
                }
            }
        }
    }
}
