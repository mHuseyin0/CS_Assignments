package School.CS_102.Labs.Lab_04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Gives 1 point when player interacts with
 */
public class Apple extends InteractableRectangular{

    public static final int SCORE_GAINED = 1;

    /**
     * Returns an {@code Apple} object having the given shape
     * 
     * @param rectangle shape of the {@code Apple} object
     */
    public Apple(Rectangle rectangle){
        super(rectangle);
    }

    /**
     * Increments the score of the {@code s} by 1
     * 
     * @param s {@code Ship} object that {@code this} interacted with
     */
    @Override
    public void interact(Ship s) {
        s.gainScore(SCORE_GAINED);
    }

    /**
     * Draws the {@code Apple} object using red color
     * 
     * @param g {@code Graphics} object to draw onto
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        super.draw(g);
    }
}
