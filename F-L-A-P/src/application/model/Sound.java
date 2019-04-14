package application.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 */
public class Sound {
	/**
	 * Sound variables
	 */
    private Media soundEffect;

    /**
     * Constructor for Sound object
     * @param filePath path to sound file
     */
    Sound(String filePath) {
        this.soundEffect = new Media(getClass().getResource(filePath).toExternalForm());
    }

    /**
     * playSound - plays sound
     */
    public void playSound() {
    	(new MediaPlayer(soundEffect)).play();
    }
}