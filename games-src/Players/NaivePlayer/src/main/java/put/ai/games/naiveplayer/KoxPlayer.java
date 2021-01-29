/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.naiveplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

import static java.lang.StrictMath.*;

public class KoxPlayer extends Player {

    private class dwaInt{
        public int a, b;
        public dwaInt(int x, int y){
            a = x;
            b = y;
        }
    }

    private int distToCentr(int x1, int y1){
        return 5-(int)sqrt(pow(3.5-x1,2)+pow(3.5-y1,2));
    }

    private int eval(Board b, Move move, Color color) {
        int eval = 0;
        int size = b.getSize();
        int ile = 0;
        List<dwaInt> values = new ArrayList<dwaInt>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(b.getState(i,j) == getColor()){
                    ile = 0;
                    eval += distToCentr(i, j);
                    for (int p = -1; p < 2; p++) {
                        for (int o = -1; o < 2; o++) {
                            if (p != 0 && o != 0 && i + p > 0 && j + o > 0) {
                                if (b.getState(i+p, j + o)==getColor()) {
                                    ile++;
                                }
                            }
                        }
                    }
                    eval += ile;
                }
                else if(b.getState(i,j) == getOpponent(getColor())){
                    eval -= distToCentr(i, j);
                }
            }
        }
        return eval;
    }

    @Override
    public String getName() {
        return "Jerzy Weber 140801 Lubomir Basiński 141185";
    }


    @Override
    public Move nextMove(Board b) {
        List<Move> moves = b.getMovesFor(getColor());
        List<Integer> values = new ArrayList<Integer>();
        for(Move m : moves){
            b.doMove(m);
            int d = eval(b, m, getColor());
            values.add(d);
            System.out.println("Move: " + m + " = " + d);
            b.undoMove(m);
        }
        /*
        try{
            TimeUnit.SECONDS.sleep(1);
        }
        catch (java.lang.InterruptedException e){

        }*/
        return moves.get(values.indexOf(Collections.max(values)));
    }
}
