package sarjaohjelmaa;

import java.util.*;

public class GameSeriesSolver {

    Random random = new Random(100);
    public void solveSeries(GameSeries gs, int maxRounds) {
        boolean lopetus = false;
        while (!lopetus) {
            int[] virheet = new int[gs.series.length];
            for (int i = 0; i < virheet.length; i++) {
                virheet[i] = 0;
            }

            for (int i = 0; i < gs.series.length; i++) {
                for (int j = 0; j < gs.series[i].size(); j++) {
                    for (int k = j; k < gs.series[i].size(); k++) {
                        if(j != k) {
                            if (gs.series[i].get(j).home == gs.series[i].get(k).home) {
                                virheet[i]++;
                            } else if (gs.series[i].get(j).home == gs.series[i].get(k).away) {
                                virheet[i]++;
                            } else if (gs.series[i].get(j).away == gs.series[i].get(k).home) {
                                virheet[i]++;
                            } else if (gs.series[i].get(j).away == gs.series[i].get(k).away) {
                                virheet[i]++;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < virheet.length; i++) {
                //System.out.println(virheet[i]);
            }
            ArrayList<Game>[] newArray = new ArrayList[gs.series.length];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = new ArrayList<>();
            }

            int limit = gs.gamesPerRound;
            Queue<Game> jono = new LinkedList<>();
            Queue<Game> tempJono = new LinkedList<>();

            for (int i = gs.series.length; i > 0 ; i--) {
                for (int j = gs.series[i-1].size(); j > 0; j--) {
                    jono.add(gs.series[i-1].get(j-1));
                }
            }

            for (int i = 0; i < newArray.length; i++) {
                for (int j = 0; j < limit; j++) {
                    if (jono.size() != 0) {
                        Game temp = jono.remove();
                        if (compareGameToList(temp, newArray[i])) {
                            newArray[i].add(temp);
                            int counter = jono.size();
                            for (int k = 0; k < counter; k++) {
                                tempJono.add(jono.remove());
                            }
                            jono = new LinkedList<>(tempJono);
                            tempJono.clear();
                        } else {
                            tempJono.add(temp);
                            j--;
                        }
                    } else {
                        for (int h = newArray.length; h > 0; h--) {
                            int counter = limit - newArray[h-1].size();
                            for (int k = 0; k < counter; k++) {
                                if (tempJono.size() == 0) break;
                                newArray[h-1].add(tempJono.remove());
                            }
                        }
                    }
                }
            }

            gs.series = newArray.clone();

            int sum = 0;
            for (int i = 0; i < virheet.length; i++) {
                sum += virheet[i];
            }
            if(sum == 0) {
                lopetus = true;
            }
        }
    }

    public boolean compareGameToList(Game error, ArrayList<Game> list) {
         for (int i = 0; i < list.size(); i++) {
                 if(error.home == list.get(i).home || error.away == list.get(i).home ||
                 error.home == list.get(i).away || error.away == list.get(i).away){
                     return false;
             }
         }
        return true;
    }
}
