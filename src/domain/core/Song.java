package domain.core;

import java.util.List;

import domain.facade.ISong;
import util.adts.RegExpMatchable;

/**
 * Classe que representa uma musica e todas as suas funcionalidades
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class Song implements ISong, RegExpMatchable {

    private Rate songRate;
    private SongMetaInfo songInfo;
    private String songFileName;
    private int timesPlayed;

    /**
     * Construtor da classe que vai criar uma song a
     * partir dos metadados e do nome do seu ficheiro
     *
     * @param info     Os metadados de uma Song
     * @param fileName O nome do ficheiro da musica
     */
    public Song(SongMetaInfo info, String fileName) {
        this.songInfo = info;
        this.songFileName = fileName;
        this.timesPlayed = 0;
        this.songRate = Rate.NO_RATE;
    }

    /**
     * Increments the number of times the song was played
     */
    @Override
    public void incTimesPlayed() {
        this.timesPlayed++;
    }

    /**
     * Returns the number of times the song was played
     * 
     * @return number of times the song was played
     */
    @Override
    public int getTimesPlayed() {
        return this.timesPlayed;
    }

    /**
     * Returns the rating of the song
     * 
     * @return the song's rating
     * @ensures \result != null
     */
    @Override
    public Rate getRating() {
        return this.songRate;
    }

    /**
     * Increments the song's rating
     * 
     * @ensures getRating().equals(\old(getRating().inc())
     */
    @Override
    public void incRating() {
        this.songRate = Rate.incRateByRate(songRate);
    }

    /**
     * Decrements the song's rating
     * 
     * @ensures getRating().equals(\old(getRating().dec())
     */
    @Override
    public void decRating() {
        this.songRate = Rate.decRateByRate(songRate);
    }

    /**
     * Returns the title of the song
     * 
     * @return the song's title
     * @ensures \result != null
     */
    @Override
    public String getSongTitle() {
        return this.songInfo.title();
    }

    /**
     * Returns the genre of the song
     * 
     * @return the song's genre
     * @ensures \result != null
     */
    @Override
    public String getGenre() {
        return this.songInfo.genre();
    }

    /**
     * Returns the artist list of the song
     * 
     * @return the song's artists list
     * @ensures \result != null
     */
    @Override
    public List<String> getArtists() {
        return this.songInfo.artists();
    }

    /**
     * Returns the album name of the song
     * 
     * @return the song's album name
     * @ensures \result != null
     */
    @Override
    public String getAlbum() {
        return this.songInfo.album();
    }

    /**
     * Return the filename of the song
     * 
     * @return the song's filename
     * @ensures \result != null
     */
    @Override
    public String getFilename() {
        return this.songFileName;
    }

    /**
     * Checks if any song data matches the given regular expression
     * 
     * @param regexp the regular expression to be used
     * @requires regexp != null
     * @return whether some data of the song matches with the given regexp
     */
    @Override
    public boolean matches(String regexp) {
        return this.songInfo.matches(regexp);
    }

    /**
     * Metodo que verifica se duas Songs sao iguais
     * 
     * @return se duas Songs sao iguais
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Song musica = (Song) other;
        return musica.getFilename().equals(this.getFilename()) && musica.getTimesPlayed() == this.getTimesPlayed()
                && musica.getRating().equals(this.getRating()) && musica.songInfo.equals(this.songInfo);
    }

    /**
     *  Representacao textual de uma Song
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.songInfo.toString());
        sb.append(" --- " + Rate.getRateValue(getRating()) + " -- " + getTimesPlayed());
        return sb.toString();
    }
}
