package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.core.SongAddedLibraryEvent;

/**
 * Classe que representa uma playlist automatica para as musicas mais recentes
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist {

    private final int TOP_MAX = 5;

    /**
     * Construtor de uma recentlyAddedPlaylist
     */
    public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
        super("MostRecentlyAddedSongsPlaylist", library);
    }

    /**
     * Metodo que vai processar os eventos relevantes a este tipo de playlists
     */
    @Override
    public void processEvent(SongLibraryEvent e) {
        if (e instanceof SongAddedLibraryEvent) {
            if (super.size() < TOP_MAX) {
                addAutomatic(e.getSong());
            } else {
                removeAutomatic(0);
                addAutomatic(e.getSong());
            }
        } else if (e instanceof SongRemovedLibraryEvent) {
            if (super.size() < TOP_MAX) {
                removeAutomatic(0);
                boolean guard = true;
                for (int i = super.library.size() - 1; i >= 0 && guard; i--) {
                    ISong song = this.library.get(i);
                    for (int j = 0; j < size() && guard; j++) {
                        ISong pSong = this.songs.get(j);
                        if (!song.equals(pSong)) {
                            addAutomatic(song);
                            guard = false;
                        }
                    }
                }
            } else {
                super.processEvent(e);
            }
        }
    }
}
