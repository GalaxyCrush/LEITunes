package domain.core;

import util.observer.Event;

/**
 * Classe abstrata que simboliza um evento generico relacionado as song e library
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public abstract class SongLibraryEvent implements Event {

    protected Song e;
    protected MusicLibrary ml;
    
    /**
     * Cria um objeto que eh um evento 
     *
     * @param song A musica que o evento ocorreu
     * @param library A library que o evento ocorreu
     */
    protected SongLibraryEvent(Song song, MusicLibrary library) {
        this.e = song;
        this.ml = library;
    }

    /**
     * Funcao que retorna a song em que o evento ocorreu
     * 
     * @return Uma song
     */
    public Song getSong() {
        return this.e;
    }

    /**
     * Funcao que retorna a library em que o evento ocorreu
     * 
     * @return Uma MusicLibrary
     */
    public MusicLibrary getMl() {
        return this.ml;
    }

}
