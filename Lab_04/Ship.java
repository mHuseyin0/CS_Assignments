package School.CS_102.Labs.Lab_04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * {@code Ship} object will be controlled by player during the game
 */
public class Ship extends InteractableRectangular{

    private String name;
    private int health;
    private int score;

    /**
     * Returns a {@code Ship} object with the given parameters
     * 
     * @param rectangle Shape of {@code this}
     * @param health Health of {@code this}
     * @param name Name to be displayed
     */
    public Ship(Rectangle rectangle, int health, String name){
        super(rectangle);
        this.health = health;
        this.score = 0;
        this.name = name;
    }

    @Override
    public void interact(Ship s) {}

    /**
     * Decrements its lives by the given amount
     * 
     * @param damage Number of lives to be decremented
     */
    public void takeDamage(int damage){
        health -= damage;
    }

    /**
     * Increments its score by the given amount
     * 
     * @param gainedScore Amount of score to be gained
     */
    public void gainScore(int gainedScore){
        score += gainedScore;
    }

    /**
     * Draws the {@code Ship} object using green color and displays the name on it
     * 
     * @param g {@code Graphics} object to draw onto
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        super.draw(g);

        g.setColor(Color.BLACK);
        g.drawString(name, (int) rectangle.getX(), (int) (rectangle.getY() + rectangle.getHeight()));
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }
}
