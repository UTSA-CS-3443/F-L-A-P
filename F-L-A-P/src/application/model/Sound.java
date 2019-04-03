package application.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	/*
	 * Class Variables
	 */
    private Media soundEffect;

    /**
     * Constructor for Sound object
     * @param filePath
     */
    Sound(String filePath) {
        this.soundEffect = new Media(getClass().getResource(filePath).toExternalForm());
    }

    /**
     * playClip method.  plays sound
     */
    public void playClip() {
    	MediaPlayer mediaPlayer = new MediaPlayer(soundEffect);
        mediaPlayer.play();
    }
}