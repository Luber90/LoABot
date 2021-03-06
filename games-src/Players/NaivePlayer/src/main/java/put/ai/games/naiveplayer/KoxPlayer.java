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

    private boolean contains(List<dwaInt> l, int x, int y){
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).a == x&&l.get(i).b==y){
                return true;
            }
        }
        return false;
    }

    private int distToCentr(int x1, int y1, double cx, double cy){
        return (int) (5.8-sqrt(pow(cx-x1,2)+pow(cy-y1,2)));
    }

    private int eval(Board b, Color color, int stareprzec) {
        int pionki = 0, przeciwne = 0;
        if(b.getWinner(color) == getColor()) return 50000;
        else if(b.getWinner(color) == getOpponent(getColor())) return -50000;
        int eval = 0;
        int size = b.getSize();
        int ile = 0;
        List<dwaInt> visited = new ArrayList<dwaInt>();
        List<dwaInt> tovisit = new ArrayList<dwaInt>();
        List<dwaInt> counted = new ArrayList<dwaInt>();
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(b.getState(i,j) == getColor()){
                    pionki++;
                }
                else if(b.getState(i,j) == getOpponent(getColor())){
                    przeciwne++;
                }
            }
        }
        eval -= pow(2, 4+stareprzec - przeciwne);
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(b.getState(i,j) == getColor()){
                    ile = 0;
                    eval += pow(2, distToCentr(i, j, (size-1)/2, (size-1)/2)+4);
                    if(!contains(visited, i, j)){
                        tovisit.add(new dwaInt(i, j));
                    }
                    dwaInt tmp;
                    while(tovisit.size()!=0) {
                        tmp = new dwaInt(tovisit.get(0).a, tovisit.get(0).b);
                        tovisit.remove(0);
                        visited.add(new dwaInt(tmp.a, tmp.b));
                        for (int p = -1; p < 2; p++) {
                            for (int o = -1; o < 2; o++) {
                                if ((p != 0 || o != 0) && tmp.a + p >= 0 && tmp.b + o >= 0) {
                                    if (b.getState(tmp.a + p, tmp.b + o) == getColor()&&(!contains(visited, tmp.a + p, tmp.b + o))) {
                                        //System.out.println("moj ziomo sie dodaje");
                                        if(!contains(counted, tmp.a + p, tmp.b + o)){
                                            ile++;
                                            counted.add(new dwaInt(tmp.a + p, tmp.b + o));
                                        }
                                        tovisit.add(new dwaInt(tmp.a + p, tmp.b + o));
                                    }
                                }
                            }
                        }
                    }
                    eval += (int)pow(2, (12-pionki)+ile);
                }
                else if(b.getState(i,j) == getOpponent(getColor())){
                    eval -= pow(2, distToCentr(i, j, (size-1)/2, (size-1)/2)+3);
                    ile = 0;
                    if(!contains(visited, i, j)){
                        tovisit.add(new dwaInt(i, j));
                    }
                    dwaInt tmp;
                    while(tovisit.size()!=0) {
                        tmp = new dwaInt(tovisit.get(0).a, tovisit.get(0).b);
                        tovisit.remove(0);
                        visited.add(new dwaInt(tmp.a, tmp.b));
                        for (int p = -1; p < 2; p++) {
                            for (int o = -1; o < 2; o++) {
                                if ((p != 0 || o != 0) && tmp.a + p > 0 && tmp.b + o > 0) {
                                    if (b.getState(tmp.a+p, tmp.b + o)==getOpponent(getColor())&&(!contains(visited, tmp.a + p, tmp.b + o))) {
                                        if(!contains(counted, tmp.a + p, tmp.b + o)){
                                            ile++;
                                            counted.add(new dwaInt(tmp.a + p, tmp.b + o));
                                        }
                                        tovisit.add(new dwaInt(tmp.a + p, tmp.b + o));
                                    }
                                }
                            }
                        }
                    }

                    eval -= (int)pow(2, (12-przeciwne)+ile+1);
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
        List<Move> moves2, moves3;
        List<Integer> values = new ArrayList<Integer>();
        List<Integer> values2 = new ArrayList<Integer>();
        List<Integer> values3 = new ArrayList<Integer>();
        for(Move m : moves){
            int p1 = 0;
            int size = b.getSize();
            for(int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(b.getState(i,j) == getOpponent(getColor())){
                        p1++;
                    }
                }
            }
            b.doMove(m);
            //System.out.println(m);
            moves2 = b.getMovesFor(getOpponent(getColor()));
            for(Move m2 : moves2){
                int p2 = 0;
                for(int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if(b.getState(i,j) == getOpponent(getColor())){
                            p2++;
                        }
                    }
                }
                b.doMove(m2);
                //System.out.println("    "+m2);
                moves3 = b.getMovesFor(getColor());
                for(Move m3 : moves3){
                    int p3 = 0;
                    for(int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if(b.getState(i,j) == getOpponent(getColor())){
                                p3++;
                            }
                        }
                    }
                    b.doMove(m3);
                    int d3 = eval(b, getColor(), p3);
                    //values4.clear();
                    values3.add(d3);
                    //System.out.println("    "+d2);
                    b.undoMove(m3);
                }
                int d2 = eval(b, getColor(), p2)+values3.get(values3.indexOf(Collections.max(values3)));
                values3.clear();
                values2.add(d2);
                //System.out.println("    "+d2);
                b.undoMove(m2);
            }
            int d = eval(b, getColor(), p1)+values2.get(values2.indexOf(Collections.min(values2)));
            values2.clear();
            values.add(d);
            //System.out.println(d);
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
