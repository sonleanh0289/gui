package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public CompactDisc(String title) {
        super(title);
    }

    public CompactDisc(String title, String category) {
        super(title, category);
    }

    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
    }

    public void addTrack(Track t) {
        if (tracks.contains(t)) {
            System.out.println("Track has already been added.");
        } else {
            tracks.add(t);
            System.out.println("Track added successfully.");
        }
    }

    public void removeTrack(Track t) {
        if (tracks.remove(t)) {
            System.out.println("Track removed successfully.");
        } else {
            System.out.println("Track not found.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("Error: CD length is non-positive!");
        }

        System.out.println("Compact Disc: " + this.getTitle());
        System.out.println("Artist: " + this.getArtist());
        System.out.println("Total length: " + this.getLength());

        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        return getId() + " - " + getTitle() + " - " + getCategory() + " - " + this.artist + " - " + getLength() + " min: " + getCost() + " $";
    }

    public String getArtist() {
        return artist;
    }
}
