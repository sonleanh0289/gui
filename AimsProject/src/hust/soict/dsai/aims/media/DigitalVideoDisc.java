
package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
       super(title, category, director, cost);
     }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength() + " seconds");
	}

	@Override
    public String toString() {
        return getId() + " - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        return getTitle().equals(title);
    }

    @Override
    public int compareTo(Media other) {
        if (!(other instanceof DigitalVideoDisc)) {
            return super.compareTo(other);
        }

        DigitalVideoDisc otherDVD = (DigitalVideoDisc) other;
        int titleComparison = this.getTitle().compareTo(otherDVD.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }
        int lengthComparison = Integer.compare(otherDVD.getLength(), this.getLength());
        if (lengthComparison != 0) {
            return lengthComparison;
        }
        return Double.compare(this.getCost(), otherDVD.getCost());
    }
}