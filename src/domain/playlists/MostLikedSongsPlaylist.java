package domain.playlists;

import java.util.ArrayList;
import java.util.List;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import util.adts.ArrayQListWithSelection;

/**
 * Classe que representa uma playlist automatica para as musicas com melhores rating
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class MostLikedSongsPlaylist extends SmartPlaylist {

    protected final int TOP_MAX = 5;
    protected List<ISong> songss;
    protected Rate minRate;

    /**
     * Construtor de uma recentlyAddedPlaylist
     */
    protected MostLikedSongsPlaylist(MusicLibrary library) {
        super("MostLikedSongsPlaylist", library);
        this.songss = (List<ISong>) library.getSongs();
        minRate = Rate.NO_RATE;
    }
    
    /**
     * Metodo que vai processar os eventos relevantes a este tipo de playlists
     */
    @Override
    public void processEvent(SongLibraryEvent e) {
        if (e instanceof SongRatedLibraryEvent) {
            ISong eSong = e.getSong();
            if (super.size() < TOP_MAX) {
                addAutomatic(eSong);
                realocateSongs();
            } else {
                if (e.getSong().getRating().compareTo(minRate) > 0) {
                    removeAutomatic(0);
                    addAutomatic(e.getSong());
                    realocateSongs();
                    minRate = this.songs.get(0).getRating();
                }

            }
        } else if (e instanceof SongRemovedLibraryEvent) {
            if (super.size() - 1 < TOP_MAX) {
                Boolean removed = false;
                ISong eSong = e.getSong();
                for (int i = 0; i < this.songs.size(); i++) {
                    if (eSong.equals(this.songs.get(i))) {
                        removeAutomatic(i);
                        removed = true;
                    }
                }

                if (removed) {
                    Rate topRate = this.songs.get(this.songs.size() - 1).getRating();

                    for (int i = 0; i < super.library.size(); i++) {
                        ISong song = this.library.get(i);
                        Rate tempRate = song.getRating();
                        if (tempRate.compareTo(topRate) == 0 && notInTop(song)) {
                            addAutomatic(song);
                            realocateSongs();
                            break;
                        }
                    }
                }

            } else {
                super.processEvent(e);
            }
        }
    }

    /**
     *  Metodo que da sort as musicas da playlist apartir do Rating de cada uma
     */
    private void realocateSongs() {
        ArrayList<ISong> newList = new ArrayList<>();
        for (int i = 0; i < this.songs.size(); i++) {
            newList.add(this.songs.get(i));
        }

        for (int i = 0; i < newList.size(); i++) {
            for (int j = 0; j < newList.size(); j++) {
                ISong tempSong = null;
                if (songs.get(i).getRating().compareTo(songs.get(j).getRating()) > 0) {
                    tempSong = newList.get(i);
                    newList.set(i, newList.get(j));
                    newList.set(j, tempSong);
                }
            }
        }

        ArrayQListWithSelection<ISong> newQlist = new ArrayQListWithSelection<>();

        for (int i = 0; i < newList.size(); i++) {
            newQlist.add(newList.get(i));
        }

        this.songs = newQlist;
    }

    /**
     * Metodo que verifica se uma musica nao esta presente na playlist MostLiked
     * 
     * @return  se uma musica nao esta presente na playlist MostLiked
     */
    private Boolean notInTop(ISong song) {
        for (int i = 0; i < size(); i++) {
            ISong pSong = this.songs.get(i);
            if (song.equals(pSong)) {
                return false;
            }
        }
        return true;
    }
}