package application.model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * @author Zachary Ellis(ebl533)
 * Sprite - Generic sprite class for all game objects on screen
 */
public class Sprite {
	/**
	 * Sprite Variables
	 */
	private Image image;
	private double xPosition;
	private double yPosition;
	private double xVelocity;
	private double yVelocity;
	private int width;
	private int height;
	
	/**
	 * Constructor
	 * Generic Sprite
	 */
	public Sprite() {
		xPosition = 0.0;
		yPosition = 0.0;
		xVelocity = 0.0;
		yVelocity = 0.0;
		width = 0;
		height = 0;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the xPosition
	 */
	public double getXPosition() {
		return xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public double getYPosition() {
		return yPosition;
	}

	/**
	 * @param x X Position
	 * @param y Y Position
	 */
	public void setXYPosition(double x, double y) {
		xPosition = x;
		yPosition = y;
	}
	
	/**
	 * @param fps - simulates frames per second, higher number = less fps
	 */
	public void refresh(double fps) {
		setXYPosition(xPosition += (fps * xVelocity), yPosition += (fps * yVelocity));
	}
	
	/**
	 * @return the xVelocity
	 */
	public double getXVelocity() {
		return xVelocity;
	}

	/**
	 * @return the yVelocity
	 */
	public double getYVelocity() {
		return yVelocity;
	}

	/**
	 * @param x X initial rate
	 * @param y Y initial rate
	 */
	public void setXYVelocity(double x, double y) {
		xVelocity = x;
		yVelocity = y;
	}
	
	/**
	 * @param x X rate
	 * @param y Y rate
	 */
	public void addXYVelocity(double x, double y) {
		xVelocity += x;
		yVelocity += y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @param width Image width
	 * @param height Image height
	 */
	public void setWidthHeight(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	* @return a rectangle (area) bound of the sprite
	*/
	public Rectangle getBounds() {
		return new Rectangle(xPosition, yPosition, width, height);
	}
}
