package servicos;

import java.util.Arrays;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import domain.core.Song;
import domain.core.SongMetaInfo;

/**
 * Classe que cria songs
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class SongMaker {

    /**
     *  Construtor
     */
    public SongMaker() {
    }

    /**
     * Funcao que adiciona uma musica ah bibliotecaia uma musica a partir de um ficheiro
     * 
     * @param filename O nome do ficheiro da musica
     * @requires filename eh um ficheiro mp3
     */
    public Song createSong(String filename) {
        try {
            Mp3File pseudoSong = new Mp3File(filename);
            if (pseudoSong.hasId3v1Tag()) {
                ID3v1 id3v1Tag = pseudoSong.getId3v1Tag();
                String songName = id3v1Tag.getTitle() == null ? "unknown" : id3v1Tag.getTitle();
                String album = id3v1Tag.getAlbum() == null ? "unknown" : id3v1Tag.getAlbum();
                String genre = id3v1Tag.getGenreDescription() == null ? "unknown" : id3v1Tag.getGenreDescription();
                List<String> artists = id3v1Tag.getArtist() == null ? Arrays.asList("unknown")
                        : Arrays.asList(id3v1Tag.getArtist().split(";"));
                SongMetaInfo info = new SongMetaInfo(songName, genre, artists, album);
                return new Song(info, filename);
            } else if (pseudoSong.hasId3v2Tag()) {
                ID3v2 id3v2Tag = pseudoSong.getId3v2Tag();
                String songName = id3v2Tag.getTitle() == null ? "unknown" : id3v2Tag.getTitle();
                String album = id3v2Tag.getAlbum() == null ? "unknown" : id3v2Tag.getAlbum();
                String genre = id3v2Tag.getGenreDescription() == null ? "unknown" : id3v2Tag.getGenreDescription();
                List<String> artists = id3v2Tag.getArtist() == null ? Arrays.asList("unknown")
                        : Arrays.asList(id3v2Tag.getArtist().split(";"));
                SongMetaInfo info = new SongMetaInfo(songName, genre, artists, album);
                return new Song(info, filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}