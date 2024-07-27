package School.CS_102.Labs.Lab_04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Damages 1 life when player interacts with
 */
public class Bomb extends InteractableRectangular{

    public static final int BOMB_DAMAGE = 1;

    /**
     * Returns an {@code Bomb} object having the given shape
     * 
     * @param rectangle shape of the {@code Bomb} object
     */
    public Bomb(Rectangle rectangle){
        super(rectangle);
    }

    /**
     * Decrements the score of the {@code s} by 1
     * 
     * @param s {@code Ship} object that {@code this} interacted with
     */
    @Override
    public void interact(Ship s) {
        s.takeDamage(BOMB_DAMAGE);
    }

    /**
     * Draws the {@code Bomb} object using black color
     * 
     * @param g {@code Graphics} object to draw onto
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        super.draw(g);
    }
}
