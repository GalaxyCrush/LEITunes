package domain.core;

/**
 * Classe abstrata que simboliza um evento que representa as remocoes de musica a biblioteca
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class SongRemovedLibraryEvent extends SongLibraryEvent {

    /**
     * Construtor de um SongRemovedLibraryEvent
     * 
     * @param song A musica em que ocorreu o evento
     * @param library A library em que ocorreu o evento
     */
    public SongRemovedLibraryEvent(Song song, MusicLibrary library) {
        super(song, library);
    }

}
