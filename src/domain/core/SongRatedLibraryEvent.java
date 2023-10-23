package domain.core;

/**
 * Classe que representa um evento especifico, no caso se uma musica foi avaliada
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class SongRatedLibraryEvent extends SongLibraryEvent {

    /**
     * Construtor de um SongRateLibraryEvent
     * 
     * @param song A musica em que ocorreu o evento
     * @param lib A library em que ocorreu o evento
     */
    public SongRatedLibraryEvent(Song song, MusicLibrary lib) {
        super(song, lib);
    }

}
