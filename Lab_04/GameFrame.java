package School.CS_102.Labs.Lab_04;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * Container for the {@code GamePanel} which handles the game simulation.
 */
public class GameFrame extends JFrame{ 

    private String playerName;
    private int gameSpeed;

    /**
     * Initializes the game and displays {@code GameFrame} object
     * 
     * @param name Name of the player to be displayed
     * @param speed Speed of the game
     */
    public GameFrame(String name, int speed){
        this.playerName = name;
        this.gameSpeed = speed;

        initialize();
    }

    private void initialize(){    
        setMinimumSize(new Dimension(200, 200));
        add(new GamePanel(new Ship(new Rectangle(0, 0, 25, 15), 3, playerName), this));

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public String getPlayerName() {
        return playerName;
    }
}
