package School.CS_102.Labs.Lab_04;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Main menu of the ship game
 */
public class MenuFrame extends JFrame{

    // Main components of the main menu
    private JPanel namePanel;
    private JPanel speedPanel;
    private JButton startButton;

    /**
     * Initializes and displays the main menu
     * 
     */
    public MenuFrame(){
        initialize();
        setVisible(true);
    }

    /**
     * Initializes the main menu by creating and adding components
     */
    private void initialize(){
        setTitle("Ship Game");
        setLayout(new GridLayout(3,1));

        namePanel = new JPanel(new GridLayout(1,2));
        speedPanel = new JPanel(new GridLayout(1,2));

        namePanel.add(new JLabel("Name:", JLabel.CENTER));
        speedPanel.add(new JLabel("Speed:", JLabel.CENTER));

        JTextArea nameField = new JTextArea();
        JTextArea speedField = new JTextArea();

        startButton = new JButton("Start");

        /**
         * Listener for the start button
         */
        class StartListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent event) {
                String name = nameField.getText().trim();
                String speed = speedField.getText().trim();

                boolean invalidSpeed = false;

                // Check if speed contains non-digit characters
                for (int i = 0; i < speed.length(); i++) {
                    if (!Character.isDigit(speed.charAt(i))) {
                        invalidSpeed = true;
                        break;
                    }
                }

                // Starts the game unless the inputs are invalid
                if (invalidSpeed) {
                    JOptionPane.showMessageDialog(MenuFrame.this,
                    "Speed can only contain digits. (Must be an integer)", 
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
                else if (name.isBlank()) {
                    JOptionPane.showMessageDialog(MenuFrame.this,
                    "Name cannot be blank.", 
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
                else if (speed.isBlank()) {
                    JOptionPane.showMessageDialog(MenuFrame.this,
                    "Speed cannot be blank.", 
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    MenuFrame.this.dispose();
                    GameFrame game = new GameFrame(name, Integer.parseInt(speed));
                }
            }
        }

        startButton.addActionListener(new StartListener());

        namePanel.add(nameField);
        speedPanel.add(speedField);

        add(namePanel);
        add(speedPanel);
        add(startButton);

        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true);

        nameField.setBorder(border);
        speedField.setBorder(border);
        startButton.setBorder(border);

        // Center the frame and set size
        setSize(300, 200);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
