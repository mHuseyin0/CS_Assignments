package School.CS_102.Labs.Lab_04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main class for the game where each element comes together.
 * This should not be directly initialized. {@code GameFrame}
 * will create this properly.
 */
public class GamePanel extends JPanel implements MouseMotionListener, ActionListener{

    /**
     * Update period of the game in miliseconds
     */
    public static final int TICK_MS = 10;

    public static final int BOMB_SIZE = 10;
    public static final int APPLE_SIZE = 10;


    ArrayList<InteractableDrawing> objects;
    Ship player;
    GameFrame parentComponent;
    Timer timer;

    /**
     * Creates the sea and ship for the game
     * 
     * @param s {@code Ship} object to be controlled by player
     * @param parentComponent {@code GameFrame} containing {@code this}
     */
    public GamePanel(Ship s, GameFrame parentComponent){
        this.parentComponent = parentComponent;
        player = s;

        // Set the title
        updateTitle();
        objects = new ArrayList<InteractableDrawing>();
        setBackground(new Color(137, 207, 240));

        // Mouse listener to control the ship
        addMouseMotionListener(this);

        // Action listener and timer to simulate {@code Apple} and {@code Bomb} objects
        this.timer = new Timer(TICK_MS, this);
        timer.start();
    }

    @Override
    public void mouseDragged(MouseEvent event) {}

    /**
     * Draws the {@code Ship} where the mouse is located
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        player.getRectangle().setLocation(
            event.getX() - (int) player.getRectangle().getWidth() / 2,
            event.getY() - (int) player.getRectangle().getHeight() / 2
        );

        for (int i = objects.size() - 1; i >= 0; i--) {
            handleCollisionsWithPlayer(objects.get(i));
        }

        repaint();
    }
    
    /**
     * Updates the all entities on the board
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (InteractableDrawing object : objects) {
            object.draw(g);
        }

        player.draw(g);
    }

    /**
     * Creates the {@code Bomb} and {@code Apple} objects randomly
     * 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        InteractableDrawing object;
        for (int i = objects.size() - 1; i >= 0; i--) {
            object = objects.get(i);
            if (object.moveLeft(parentComponent.getGameSpeed())) {
                objects.remove(object);
            }
            
            handleCollisionsWithPlayer(object);
        }

        // Determine max number of the rectangles to crate per tick according to the height of the frame
        int maxRectangleToCreate = parentComponent.getHeight() / 200;
        InteractableDrawing newObject;

        for (int i = 0; i < maxRectangleToCreate; i++) {
            if (Math.random() < .1) {

                if (Math.random() < .5) {
                    newObject = new Bomb(createNewRandomRectangle(BOMB_SIZE, BOMB_SIZE));
                }
                else{
                    newObject = new Apple(createNewRandomRectangle(APPLE_SIZE, APPLE_SIZE));
                }

                objects.add(newObject);

                handleCollisionsWithPlayer(newObject);
            }
        }

        repaint();

        // Handle if the player has no remaining life
        if (player.getHealth() == 0){
            timer.stop();
            this.removeMouseMotionListener(this);
            int choice = JOptionPane.showConfirmDialog(
                this, "YOU DIED. Your score: " + player.getScore() + " Do you want to play again?",
                "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            switch (choice) {
                case 0:
                    MenuFrame menuFrame = new MenuFrame();
                    parentComponent.dispose();
                    break;
                case 1:
                    parentComponent.dispose();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Handles if the object overlaps with the player. Do nothing
     * if they do not overlap
     * 
     * @param object Object to check whether intersects with the player
     */
    private void handleCollisionsWithPlayer(InteractableDrawing object){
        if (object.intersects(player)) {
            object.interact(player);

            updateTitle();

            objects.remove(object);
        }
    }

    /**
     * Creates a rectangle with the given parameters at the right of the screen.
     * y coordinate of the rectangle is random
     * 
     * @param height height of the new rectangle
     * @param width width of the new rectangle
     * @return {@code Rectangle} object created
     */
    private Rectangle createNewRandomRectangle(int height, int width){
        int frameWidth = parentComponent.getWidth();
        int frameHeight = parentComponent.getHeight();
        
        return new Rectangle(
            frameWidth - width,
            pickRandomIntBetween(0, frameHeight - height),
            width,
            height
        );
    }

    /**
     * Updates the title to display health and score
     * 
     */
    private void updateTitle(){
        parentComponent.setTitle("Life: " + player.getHealth() + " - Score: " + player.getScore()); 
    }

    /**
     * Helper method to pick a random integer between the given ranges
     * 
     * @param lowerBound minimum value of the random integer
     * @param upperBound maximum value of the random integer
     * @return a random integer between the ranges
     */
    public static int pickRandomIntBetween(int lowerBound, int upperBound){
        return (int) (Math.random() * (upperBound - lowerBound) + lowerBound);
    }
}
