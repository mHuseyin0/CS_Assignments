package School.CS_102.Labs.Lab_04;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * {@code InteractableDrawing} objects that has a shape of {@code Rectangle} object.
 */
public abstract class InteractableRectangular implements InteractableDrawing{

    Rectangle rectangle;

    /**
     * Assigns the {@code Rectangle} object to {@code this}
     * 
     * @param rectangle
     */
    public InteractableRectangular(Rectangle rectangle){
        this.rectangle = rectangle;
    }


    /**
     * Checks if {@code this} intersects with the given {@code Ship} object
     * 
     * @param s {@code Ship} object to check whether {@code this} intersects with
     * @return {@code true} if they have at least one pixel overlapping, {@code false} otherwise
     */
    @Override
    public boolean intersects(Ship s) {
        return s.getRectangle().intersects(rectangle);
    }

    @Override
    public abstract void interact(Ship s);

    /**
     * Move left by the given speed.
     * 
     * @param speed Number of pixels to move left
     */
    @Override
    public boolean moveLeft(int speed) {
        rectangle.setLocation((int) rectangle.getX() - speed, (int) rectangle.getY());

        if (rectangle.getX() + rectangle.getWidth() <= 0) {
            return true;
        }

        return false;

    }

    /**
     * Draws the rounded rectangle of {@code this}
     * 
     * @param g {@code Graphics} object to draw onto
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.fillRoundRect(
            (int) rectangle.getX(),
            (int) rectangle.getY(), 
            (int) rectangle.getWidth(), 
            (int) rectangle.getHeight(), 
            (int) rectangle.getWidth() / 5, 
            (int) rectangle.getWidth() / 5
        );
    }    

    public Rectangle getRectangle() {
        return rectangle;
    }
}
