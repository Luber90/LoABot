/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.linesofaction;

import put.ai.games.game.Player.Color;
import put.ai.games.game.moves.MoveMove;

import java.util.Objects;

public class LinesOfActionMove implements MoveMove {

    private Color color;
    private int srcX, srcY, dstX, dstY;
    private Color previous = Color.EMPTY;


    public LinesOfActionMove(Color color, int srcX, int srcY, int dstX, int dstY) {
        this.color = color;
        this.srcX = srcX;
        this.srcY = srcY;
        this.dstX = dstX;
        this.dstY = dstY;
        if (!(srcX - dstX == 0 || srcY - dstY == 0 || Math.abs(srcX - dstX) == Math.abs(srcY - dstY))) {
            throw new IllegalArgumentException("Move can be only horizontal, vertical or diagonal");
        }
    }


    @Override
    public int getDstX() {
        return dstX;
    }


    @Override
    public int getDstY() {
        return dstY;
    }


    @Override
    public int getSrcX() {
        return srcX;
    }


    @Override
    public int getSrcY() {
        return srcY;
    }


    @Override
    public Color getColor() {
        return color;
    }


    /*package*/Color getPrevious() {
        return previous;
    }


    /*package*/void setPrevious(Color previous) {
        this.previous = previous;
    }


    /*package*/void resetPrevious() {
        this.previous = Color.EMPTY;
    }


    /*package*/int length() {
        if (srcX == dstX) {
            return Math.abs(srcY - dstY);
        } else {
            return Math.abs(srcX - dstX);
        }
    }


    @Override
    public String toString() {
        return String.format("(%d,%d)->(%d,%d)", srcX, srcY, dstX, dstY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinesOfActionMove that = (LinesOfActionMove) o;
        return srcX == that.srcX &&
                srcY == that.srcY &&
                dstX == that.dstX &&
                dstY == that.dstY &&
                color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, srcX, srcY, dstX, dstY);
    }
}
