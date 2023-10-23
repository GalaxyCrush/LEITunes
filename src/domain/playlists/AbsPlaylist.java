package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.ArrayQListWithSelection;

/**
 * Classe abstrata que representa uma playlist generica
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public abstract class AbsPlaylist implements Playlist {

    protected MusicLibrary library;
    protected String playlistName;
    protected ArrayQListWithSelection<ISong> songs;
    private boolean nowPlaying;
    protected Player p = PlayerFactory.INSTANCE.getPlayer();
    private ISong currentSong = null;

    /**
     * Construtor generico de uma playlist
     * 
     * @param MusicLibrary uma biblioteca de musica
     * @param String       O nome da playlist
     */
    protected AbsPlaylist(MusicLibrary lib, String name) {
        this.library = lib;
        this.playlistName = name;
        this.songs = new ArrayQListWithSelection<>();
        this.nowPlaying = false;
        this.p.addListener(this);
    }

    /**
     * Returns the number of songs in the playlist
     * 
     * @return the number of songs in the playlist
     * @ensures \return >= 0
     */
    @Override
    public int size() {
        return this.songs.size();
    }

    /**
     * Returns the selected song
     * 
     * @return the selected song
     * @requires someSelected()
     * @ensures \return != null
     */
    @Override
    public ISong getSelected() {
        return this.songs.getSelected();
    }

    /**
     * Returns true if some element is selected
     * 
     * @return true if some element is selected, false otherwise
     */
    @Override
    public boolean someSelected() {
        return this.songs.someSelected();
    }

    /**
     * Adds a song to the end of the playlist, if it
     * does not exist yet and selects it,
     * if addition is possible
     *
     * @param song the element to be added
     * @requires song != null
     * @return true if the song was added to the playlist, false otherwise
     * @ensures \result ==> size() == \old(size()) + 1 &&
     *          someSelected() &&
     *          getIndexSelected() == size() - 1
     */
    @Override
    public boolean add(ISong song) {
        for (ISong isong : this.songs) {
            if (isong.equals(song)) {
                return false;
            }
        }
        this.songs.add(song);
        return true;
    }

    @Override
    public Iterator<ISong> iterator() {
        return this.songs.iterator();
    }

    /**
     * Removes the selected element from the playlist, if possible
     *
     * @return true if the song was removed, false otherwise
     * @ensures \return && \old someSelected() ==>
     *          !someSelected() && size() == \old(size()) - 1
     * @ensures !\return ==> \old someSelected() == someSelected()
     *          && size() == \old(size())
     */
    @Override
    public boolean remove() {
        if (someSelected()) {
            this.songs.remove();
            return true;
        }
        return false;
    }

    /**
     * Selects song at position i
     * 
     * @param i the position denoting the element to be selected
     * @requires 0 <= i < size()
     * @ensures someSelected() && getIndexSelected() == i &&
     *          size() == \old(size())
     */
    @Override
    public void select(int i) {
        this.songs.select(i);
    }

    /**
     * Moves the current selected song up to position i,
     * shifting down all elements in the playlist from
     * positions i+1 to \old getIndexSelected()-1,
     * if movement in the playlist is possible
     * 
     * @param i the index where this element is going to be moved
     * @requires someSelected() && 0 <= i < getIndexSelected()
     * @ensures \return ==> someSelected() &&
     *          getIndexSelected() == i &&
     *          size() == \old(size())
     */
    @Override
    public boolean moveUpSelected(int i) {
        ArrayQListWithSelection<ISong> newList = new ArrayQListWithSelection<>();

        for (int j = 0; j < this.songs.size(); j++) {
            if (!getSelected().equals(this.songs.get(j))) {
                if (i == j) {
                    newList.add(this.songs.get(j));
                    newList.add(getSelected());
                } else {
                    newList.add(this.songs.get(j));
                }
            }
        }
        this.songs = newList;
        select(i);
        return true;
    }

    /**
     * Returns the index of the selected element, if any
     * 
     * @return the index of the selected element, if any
     * @requires someSelected()
     * @ensures 0 <= \return < size()
     */
    @Override
    public int getIndexSelected() {
        return this.songs.getIndexSelected();
    }

    /**
     * Selects the next element, if any. Otherwise, no element is selected.
     *
     * @requires someSelected()
     * @ensures if \old getIndexSelected() < size() - 1
     *          then getIndexSelected() = \old getIndexSelected() + 1
     *          else !someSelected()
     * @ensures size() == \old(size())
     */
    @Override
    public void next() {
        this.songs.next();
    }

    /**
     * Selects the previous element, if any. Otherwise, no element is selected.
     *
     * @requires someSelected()
     * @ensures if \old getIndexSelected() > 0
     *          then getIndexSelected() = \old getIndexSelected() - 1
     *          else !someSelected()
     * @ensures size() == \old(size())
     */
    @Override
    public void previous() {
        this.songs.previous();
    }

    /**
     * Returns the name of the playlist
     * 
     * @return the name of the playlist
     * @ensures \result != null
     */
    @Override
    public String getName() {
        return this.playlistName;
    }

    /**
     * Returns if a song is playing and the play action has been performed via the
     * playlist
     * 
     * @return true if a song is playing and the play action was done through the
     *         playlist,
     *         false otherwise
     */
    @Override
    public boolean isPlaying() {
        return this.nowPlaying;
    }

    /**
     * Plays the selected song
     * 
     * @requires someSelected()
     * @ensures isPlaying()
     */
    @Override
    public void play() {
        if (isPlaying()) {
            stop();
        }
        this.p.load(this.songs.getSelected().getFilename());
        this.p.play();
        this.currentSong = this.songs.getSelected();
        this.nowPlaying = true;
    }

    /**
     * Stops the playing song
     * 
     * @requires isPlaying()
     */
    @Override
    public void stop() {
        this.p.stop();
        this.nowPlaying = false;
        this.currentSong = null;
    }

    /**
     * Reaction to property change events, namely those emitted by the player
     * (can affect the selected song and song being played)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue().equals(Player.PlayingState.ENDED) && isPlaying()) {
            this.currentSong.incTimesPlayed();
            if (someSelected()) {
                next();
            }
            if (someSelected()) {
                play();
            } else {
                this.currentSong = null;
            }
        } else if (evt.getNewValue().equals(Player.PlayingState.STOPED) && isPlaying()) {
            this.nowPlaying = false;
            this.currentSong = null;
        }
    }

    /**
     * Reaction to events, namely those emitted by the music library that
     * backs up this playlist (can affect the content of the playlist)
     */
    @Override
    public void processEvent(SongLibraryEvent e) {
        if (e instanceof SongRemovedLibraryEvent) {
            ISong selected = this.songs.getSelected();
            for (int i = 0; i < this.songs.size(); i++) {
                ISong actual = this.songs.get(i);
                if (actual.equals(e.getSong())) {
                    this.songs.select(i);
                    this.songs.remove();
                    break;
                }
            }
            for (int i = 0; i < this.songs.size(); i++) {
                ISong actual = this.songs.get(i);
                if (actual.equals(selected)) {
                    this.songs.select(i);
                    break;
                }
            }
        }
    }

    /**
     * Metodo que retorna as musicas da AbsPlaylists
     * 
     * @return as musicas da AbsPlaylists
     */
    protected ArrayQListWithSelection<ISong> getSongs() {
        return this.songs;
    }

    /**
     * Metodo que retorna a library da AbsPlaylists
     * 
     * @return a library da AbsPlaylists
     */
    protected MusicLibrary getLibrary() {
        return this.library;
    }

    /**
     * Metodo que verifica se duas AbsPlaylists sao iguais
     * 
     * @return se duas AbsPlaylists sao iguais
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        AbsPlaylist pl = (AbsPlaylist) other;
        return this.library.equals(pl.getLibrary()) && playlistName.equals(pl.getName()) && this.songs.equals(pl.getSongs());
    }

    /**
     *  Representacao textual de uma AbsPlaylist
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + "*-- " + playlistName + "--*");
        sb.append(this.songs.toString());
        return sb.toString();
    }
    
}
