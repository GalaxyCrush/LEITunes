package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

/**
 * Classe abstrata de uma smartPlaylist
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public abstract class SmartPlaylist extends AbsPlaylist {

    /**
     * Construtor de uma smartPlaylist
     * @param name Nome da smartPlaylist
     * @param library A biblioteca onde se encontram as musicas
     */
    protected SmartPlaylist(String name, MusicLibrary library) {
        super(library, name);
    }

    /**
     * Funcao que nao deixa adicionar musicas a uma smartPlaylist
     *
     * @return False
     */
    @Override
    public boolean add(ISong song) {
        return false;
    }

    /**
     * Funcao que nao deixa remover musicas a uma smartPlaylist
     *
     * @return False
     */
    @Override
    public boolean remove() {
        return false;
    }
    

    /**
     * Funcao que nao deixa fazer moveUp de musicas numa smartPlaylist
     *
     * @param i Um indice
     * @return False
     */
    @Override
    public boolean moveUpSelected(int i) {
        return false;
    }

    /**
     * Metodo que adiciona automaticamente a uma smartPlaylist
     * 
     * @param song Song a ser adicionada
     */
    protected void addAutomatic(ISong song) {
        super.add(song);
    }

    /**
     * Metodo que remove automaticamente de uma smartPlaylist
     * 
     * @param index Indice da musica a ser removida
     */
    protected void removeAutomatic(int index) {
        ISong selected = super.getSelected();
        super.select(index);
        super.remove();
        for (int i = 0; i < super.size(); i++) {
            ISong actual = super.songs.get(i);
            if (actual.equals(selected)) {
                super.select(i);
                break;
            }
        }
    }
}
