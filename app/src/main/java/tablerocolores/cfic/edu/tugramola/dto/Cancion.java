package tablerocolores.cfic.edu.tugramola.dto;

public class Cancion {

    private String artistName;
    private String trackName;
    private String collectionName;

    public Cancion() {
    }

    public Cancion(String artistName, String trackName, String collectionName) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.collectionName = collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
