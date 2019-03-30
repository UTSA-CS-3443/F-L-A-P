package application.model;

import javafx.scene.image.Image;

public class Pipe {
	private Image pipeSprite;
	private double locationX;
	private double locationY;
	private double height;
	private double width;
	
	Pipe(boolean isFacingUp, double height){
		if(isFacingUp) {
			this.pipeSprite = new Image("data/images/pipe_up.png");
		}else {
			this.pipeSprite = new Image("data/images/pipe_down.png");
		}
		this.width = 70;
		this.height = height;
		//TODO figure out locationX and locationY
		
	}
	
	public Image getPipeSprite() {
		return pipeSprite;
	}
	public void setPipeSprite(Image pipeSprite) {
		this.pipeSprite = pipeSprite;
	}
	public double getLocationX() {
		return locationX;
	}
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	public double getLocationY() {
		return locationY;
	}
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	
}
