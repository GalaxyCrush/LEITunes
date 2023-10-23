package domain.core;

/**
 * Classe abstrata que simboliza um evento que representa as adicoes de musica a biblioteca
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {

    /**
     * Construtor de um SongAddedLibraryEvent
     * 
     * @param song A musica em que ocorreu o evento
     * @param library A library em que ocorreu o evento
     */
    protected SongAddedLibraryEvent(Song song, MusicLibrary library) {
        super(song, library);
    }

}
