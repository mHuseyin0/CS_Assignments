package School.CS_102.Labs.Lab_04;

import java.awt.Graphics; 

/**
 * {@code Ship} objects can only interact with {@code InteractableDrawing} objects
 */
public interface InteractableDrawing { 
    boolean intersects(Ship s); 
    
    void interact(Ship s); 
    
    boolean moveLeft(int speed); 
    
    void draw(Graphics g); 
}