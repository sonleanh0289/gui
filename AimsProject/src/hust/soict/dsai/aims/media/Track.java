
package hust.soict.dsai.aims.media;

public class Track implements Playable {
    private int length;
    private String title;

    public Track(String title, int length){
        this.title = title;
        this.length = length;
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength() + " seconds");
    }

    @Override
    public boolean equals(Object obj){
        Track track = (Track) obj;
        if(track.getLength() == length){
            return title.equals(track.getTitle());
        }else{
            return false;
        }
    }

    public int getLength() {
        return length;
    }
    public String getTitle() {
        return title;
    }


}