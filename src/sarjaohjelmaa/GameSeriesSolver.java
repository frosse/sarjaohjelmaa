package sarjaohjelmaa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameSeriesSolver {

    //
    public static void solveSeries(GameSeries gs) {
        GameSeries gameseries = gs;
        boolean solved = false;
        int counter = 0;

        // Tällä arvolla määritellään koska algoritmi on jumissa.
        int coefficient = 1000;

        int limiter = gameseries.teams * gameseries.amount * coefficient;

        while(!solved) {

            counter++;
            // Otetaan pelin indeksit jossa on virhe talteen, jotta voidaan käsitellä sitä
            int[] gameToBeRemoved = findFirstFault(gameseries);
            int roundIndex = gameToBeRemoved[0];
            int gameIndex = gameToBeRemoved[1];

            // Otetaan peli pois listalta ja lisätään tilalle null. Null lisätään jotta listassa pysyy oikea määrä pelejä
            Game extraRoundGame = gameseries.series[roundIndex].get(gameIndex);
            gameseries.series[roundIndex].remove(gameIndex);
            gameseries.series[roundIndex].add(gameIndex, null);

            // ALoitetaan kierroksesta, joka tulee virheen sisältäneen kierroksen jälkeen
            for (int i = roundIndex + 1; i < gameseries.series.length; i++) {

                // Käydään kierroksen jokainen peli läpi
                for (int j = 0; j < gameseries.series[i].size(); j++) {

                    // Lisätään peli listalle, jotta voidaan katsoa aiheuttaako se virheitä
                    gameseries.series[roundIndex].remove(gameIndex);
                    gameseries.series[roundIndex].add(gameIndex, gameseries.series[i].get(j));


                    // Jos virheitä löytyy poistetaan testattava peli uudelta paikaltaan ja lisätään null tilalle
                    if (doesGameHaveErrors(gameseries.series[roundIndex].get(gameIndex), gameseries.series[roundIndex])) {

                        gameseries.series[roundIndex].remove(gameIndex);
                        gameseries.series[roundIndex].add(gameIndex, null);

                    } else {
                        // Jos virheitä ei löydy niin asetetaan testattavan pelin alkuperäiselle paikalle null
                        gameseries.series[i].remove(j);
                        gameseries.series[i].add(j, null);

                        //Valmistellaan indexit seuraavaa kierrosta varten ja poistutaan loopista
                        gameToBeRemoved = new int[]{i, j};
                        roundIndex = gameToBeRemoved[0];
                        gameIndex = gameToBeRemoved[1];
                        break;
                    }
                }
            }

            // Etsitään sarjaohjelmasta null ja lisätään sivussa oleva peli listaan,
            // jotta voidaan testata onko algoritmi valmis
            for (int i = 0; i < gameseries.series.length; i++) {
                for (int j = 0; j < gameseries.series[i].size(); j++) {

                    if (gameseries.series[i].get(j) == null) {

                        gameseries.series[i].remove(j);
                        gameseries.series[i].add(j, extraRoundGame);
                    }
                }
            }

            // Sekoitetaan kierrokset.
            // Tällä estetään paikallaan junnaaminen
            Collections.shuffle(Arrays.asList(gameseries.series));

            // Jos kaikki kierrokset ok, niin ei aloiteta uutta kierrosta
            solved = checkAllRounds(gameseries.series);


            // Jos ollaan kuitenkin jumissa niin tällä resetoidaan sarjaohjelma ja jatketaan nollista.
            if(counter > limiter) {
                System.out.println("I think I'm stuck here... Let me try to shuffle things...");
                counter = 0;
                GameSeriesMaker.makeSeries(gameseries);
            }
        }

        System.out.println("----------------------------------------------------------------------");

        gameseries.printSeries();

        System.out.println("Solved!");
    }

    // Palauttaa truen jos on virhe
    public static boolean doesGameHaveErrors(Game game, ArrayList<Game> gameList) {
        for (int i = 0; i < gameList.size(); i++) {
            //palauttaa truen jos ei ole virheitä
            if(game != gameList.get(i) && game != null && gameList.get(i) != null) {
                if (!compareGames(game, gameList.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Verrataan pelejä keskenään eli onko peleissä samoja joukkueita
    // Jos ei virheitä palauttaa true
    public static boolean compareGames(Game game1, Game game2) {
        if (game1.home == game2.home) return false;
        if (game1.home == game2.away) return false;
        if (game1.away == game2.home) return false;
        if (game1.away == game2.away) return false;
        return true;
    }

    // checkataan onko kierroksella jo joukkueen pelejä
    // jos virhe palautetaan false, muuten true
    public static boolean compareRound(ArrayList<Game> gamesForRound) {
        int fault[] = new int[gamesForRound.size()];
        for (int i = 0; i < gamesForRound.size(); i++) {
            for (int j = 0; j < gamesForRound.size(); j++) {
                if(gamesForRound.get(i) != gamesForRound.get(j)) {
                    if(!compareGames(gamesForRound.get(i), gamesForRound.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Käydään sarjaohjelmaa läpi kunnes löydetään ensimmäinen virhe, palautetaan indeksit
    public static int[] findFirstFault(GameSeries gameseries) {
        for (int i = 0; i < gameseries.series.length; i++) {
            for (int j = 0; j < gameseries.series[i].size(); j++) {
                for (int k = 0; k < gameseries.series[k].size(); k++) {
                    if (gameseries.series[i].get(j) != gameseries.series[i].get(k) && gameseries.series[i].get(k) != null && gameseries.series[i].get(j) != null) {
                        if (!compareGames(gameseries.series[i].get(j), gameseries.series[i].get(k))) {
                            return new int[]{i, j};
                        }
                    }
                }
            }
        }
        return new int[]{0,0};
    }

    // Käy läpi joka kierroksen ja kerää virheet talteen
    public static boolean checkAllRounds(ArrayList<Game>[] series) {
        for (int i = 0; i < series.length; i++) {
            if(!compareRound(series[i])) {
                return false;
            }
        }
        return true;
    }
}
