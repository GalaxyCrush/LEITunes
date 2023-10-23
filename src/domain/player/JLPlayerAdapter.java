package domain.player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import servicos.JLPlayer;

/**
 * @author fmartins
 * @author malopes 
 * Adapter for the external mp3 player. This is a singleton class.
 * 
 */
public enum JLPlayerAdapter implements Player {

	/**
	 * The singleton player
	 */
	 INSTANCE;
	
	/**
	 * The reference for the external mp3Player
	 */
	private JLPlayer mp3Player;
	
	/**
	 * The support for the listeners
	 */	
	private PropertyChangeSupport listenerManager = new PropertyChangeSupport(this); 

	
	@Override
	public boolean load(String filename) {
    	try {
    		stop();
			mp3Player = new JLPlayer(new BufferedInputStream(new FileInputStream(filename)), this);
		} catch (Exception e) {
			System.out.println("Somethig went wrong. Did not load song in" + filename);
			return false;
		} 
		return true;
	}

	/**
	 * Plays the loaded music
	 */
	@Override
	public void play() {
		if (mp3Player != null)
			mp3Player.play();
	}
	
	/**
     * Pauses the music that is currently playing
     */
	@Override
	public void still() {
		if (mp3Player != null)
			mp3Player.still();
	}

	/**
     * Stopes the music that is currently playing
     */
	@Override
	public void stop() {
		if (mp3Player != null)
			mp3Player.stop();
	}

	/**
     * Notifies listeners that playing song was ended  
     */
	@Override
	public void hasEndedSong() {
		PropertyChangeEvent event = new PropertyChangeEvent(this, "playingState", Player.PlayingState.ON, Player.PlayingState.ENDED);
		listenerManager.firePropertyChange(event);
	}

	/**
     * Notifies listeners that playing song stopped 
     */
	@Override
	public void hasStopedSong() {
		PropertyChangeEvent event = new PropertyChangeEvent(this, "playingState", Player.PlayingState.ON, Player.PlayingState.STOPED);
		listenerManager.firePropertyChange(event);
	}

	/**
	 * Adiciona um listener
	 * 
	 * @param listener O listener a ser adicionado dado
	 */
	@Override
	public void addListener(PropertyChangeListener listener) {
		listenerManager.addPropertyChangeListener(listener); 
	}
	
	/**
	 * Remove um listener
	 * 
	 * @param listener O listener a ser removido dado
	 */
	@Override
	public void removeListener(PropertyChangeListener listener) {
		listenerManager.removePropertyChangeListener(listener); 
	}

}
