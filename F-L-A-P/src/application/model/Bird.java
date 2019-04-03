package application.model;

import javafx.scene.image.ImageView;

public class Bird {
	/*
	 * Class Variables
	 */
	ImageView birdImage;
	boolean isJumping;
	boolean isDead;
	int width, height;
	double xPos, yPos;
	
	/**
	 * Constructor for bird
	 * @param imageFilePath, image for bird
	 * @param width
	 * @param height
	 */
	public Bird(){
		this.birdImage = new ImageView();
		this.width = 50;
		this.height = 50;
		this.birdImage.setFitWidth(width);
		this.birdImage.setFitHeight(height);
		this.xPos = 50;
		this.yPos = 400;
	}
	public void jump() {
		this.isJumping = true;
		//TODO translate bird up
	}
	public ImageView getBirdImage() {
		return birdImage;
	}

	public void setBirdImage(ImageView birdImage) {
		this.birdImage = birdImage;
	}
	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
