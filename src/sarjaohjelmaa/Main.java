package sarjaohjelmaa;/*
Olkoon meillä 6 joukkuetta, jotka pelaavat keskenään kaksinkertaisen sarjan.
Kaksinkertainen sarja tarkoittaa sitä, että jokainen joukkue kohtaa jokaisen muun joukkueen kahdesti
 – kerran kotikentällä ja kerran vieraskentällä.
Koodaa algoritmi, joka muodostaa kaikki pelattavat pelit.

Muodosta sarjaohjelman tekijästä luokka, joka pystyy hoitamaan minkä tahansa joukkuemäärän
ja minkä tahansa kierrosmäärän.
Joukkueita voi siis olla kuinka monta kappaletta tahansa ja
joukkueet voivat kohdata toisensa, kuinka monta kertaa tahansa.
Ota huomioon, että joukkueita voi olla pariton määrä ja että keskinäisiä kohtaamisia voi olla pariton määrä.
Toteuta ratkaisusi sekä lambda kalkyyliä käyttäen että ”perinteisellä” tavalla.

Suunnittele ja toteuta luokka, joka mallintaa sarjaohjelmaa.
Sarjaohjelmassa on kierroksia yhteensä (n-1)*m kappaletta,
missä on n = joukkueiden määrä ja m = keskinäisten kohtaamisten määrä.
Esimerkiksi, jos meillä on 6 joukkuetta ja kaksinkertainen sarja, niin kierroksia on (6-1)*2 = 10 kpl.
Ota huomioon, että jos joukkueita on pariton määrä, niin n pyöristetään ylöspäin lähimpään kokonaislukuun.
Esim. 15 joukkuetta nelinkertaisella sarjalla tekisi (16-1)*4 = 60 kierrosta.

Arvo kaikki pelit satunnaisille kierroksille.
 */


public class Main {

    public static void main(String[] args) {
        GameSeries gs = new GameSeries(8,2);
        GameSeriesMaker maker = new GameSeriesMaker();
        GameSeriesSolver solver = new GameSeriesSolver();
        GameSeriesSolverBetter gs2 = new GameSeriesSolverBetter();
        maker.makeGameList(gs);
        gs.printGameList();
        maker.makeSeries(gs);
        gs.printSeries();
        gs2.compareRound(gs.series[1]);
        //solver.solveSeries(gs, 10);
        //gs.printSeries();
    }
}
