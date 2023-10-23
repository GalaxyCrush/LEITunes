package domain.playlists;

import domain.core.MusicLibrary;

/**
 * Classe que representa uma playlist manual
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class ManualPlaylist extends AbsPlaylist {

    /**
     * Construtor de uma playlist manual
     * 
     * @param name Nome da playlist
     * @param library A library onde estao as musicas
     */
    public ManualPlaylist(String name, MusicLibrary library) {
        super(library, name);
    }
}
