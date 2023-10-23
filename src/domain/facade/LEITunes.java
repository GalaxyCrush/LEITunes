package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

/**
 * Classe que representa o programa LEITunes
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class LEITunes {

    private MusicLibrary lib;
    private PlaylistList list;
    private PlaylistListController listCont;
    private MusicLibraryController mlCont;

    /**
     * Contrutor de um LEITunes
     */
    public LEITunes() {
        this.lib = new MusicLibrary();
        this.list = new PlaylistList(this.lib);
        this.listCont = new PlaylistListController(this.list, this.lib);
        this.mlCont = new MusicLibraryController(this.lib);
    }

    /**
     * Funcao que retorna a musicLibrary
     * @return a musicLibrary
     */
    public MusicLibrary getLib() {
        return this.lib;
    }

    /**
     * Funcao que retorna a lista de Playlists
     * @return A lista de Playlists
     */
    public PlaylistList getList() {
        return this.list;
    }

    /**
     * Funcao que retorna o controler das Playlists
     * @return O controler de Playlists
     */
    public PlaylistListController getPlaylistController() {
        return this.listCont;
    }

    /**
     * Funcao que retorna o controler da music Library
     * @return O controler de Library
     */
    public MusicLibraryController getMusicLibraryController() {
        return this.mlCont;
    }
}
