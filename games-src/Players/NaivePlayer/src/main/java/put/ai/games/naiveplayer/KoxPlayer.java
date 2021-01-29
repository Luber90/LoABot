/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.naiveplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

import static java.lang.StrictMath.*;

public class KoxPlayer extends Player {

    private Random random = new Random(0xdeadbeef);

    private int distToCentr(int x1, int y1){
        return (int)sqrt(pow(3.5-x1,2)+pow(3.5-y1,2));
    }

    private int eval(Board b, Move move, Color color) {
        int eval = 0;
        int size = b.getSize();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(b.getState(i,j) == getColor()) eval += distToCentr(i, j);
                else if(b.getState(i,j) == getOpponent(getColor())) eval -= distToCentr(i, j);
            }
        }
        return -eval;
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
        }return moves.get(values.indexOf(Collections.max(values)));
    }
}
