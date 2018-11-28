package sarjaohjelmaa;

import java.util.ArrayList;

public class GameSeries {

    static final String[] teamNameArray = { "Ässät", "Lukko", "HIFK", "HPK", "Ilves", "Jukurit", "JYP", "Kalpa", "KooKoo",
            "Kärpät", "Pelicans", "Saipa", "Sport", "Tappara", "TPS" };

    int teams;
    int amount; //keskinäiset kohtaamiset
    int rounds;
    int gamesPerRound;
    ArrayList<Game> gameList = new ArrayList<>();
    ArrayList<Game>[] series;

    // Alustetaan muuttujat ja lasketaan pelattavat kierrokset
    // Tehdää arraysta ( series ), joka sisältää jokaisen kierroksen niin oikean mittainen
    public GameSeries(int teams, int amount) {

        this.teams = teams;
        this.amount = amount;

        if(teams % 2 == 0) {
            rounds = (teams -1) * amount;
        }else{
            rounds = teams * amount;
        }

        gamesPerRound = teams / 2;
        series = new ArrayList[rounds];
    }

    // Debuggaukseen
    // Tarvittaessa voidaan tulostaa kaikki olemassa olevat pelit genereroidusta listasta
    // Listan teko on ulkoistettu GameSeriesMaker-luokalle
    public void printGameList() {
        for (Game g : gameList) {
            if(teams < 16) {
                System.out.println(teamNameArray[g.home-1] + " - " + teamNameArray[g.away-1]);
            } else {
                System.out.println(g.home + " - " + g.away);
            }
        }
    }

    // Tulostaa sarjataulukon konsoliin.
    // Jos joukkueita on vähemmän kuin 16 niin käytetään Liiga-joukkueiden nimiä, muuten tulostetaan vain numeroita.
    // Tulostukseen käytettävä lista tehdään luokassa GameSeriesMaker
    public void printSeries() {
        for (int i = 0; i < series.length; i++) {
            System.out.printf("Round %d: ", i+1);
            for (int j = 0; j < series[i].size(); j++) {
                if(teams < 16) {
                    System.out.print(teamNameArray[series[i].get(j).home - 1] + " vs " + teamNameArray[series[i].get(j).away -1]);
                } else {
                    System.out.print(series[i].get(j) + "  ");
                }
                if(j+1 != series[i].size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public ArrayList<Game> getList() {
        return gameList;
    }

    public ArrayList<Game>[] getSeries() {
        return series;
    }
}
