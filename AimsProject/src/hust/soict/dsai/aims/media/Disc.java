
package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private int length;
    private String director;

    public Disc(String title) {
		super();
		setTitle(title); 
		setId(nbId);
		nbId ++;
	}

    public Disc(String title, String category) {
		super();
		setTitle(title); 
        setCategory(category);
		// setId(nbId);
		// nbId ++;
	}

	public Disc(String title, String category, float cost) {
		super(title, category, cost);
		// setId(nbId);
		// nbId ++;
	}
	public Disc(String title, String category, String director, float cost) {
		super();
		this.director = director;
		setTitle(title);
		setCost(cost);
		setCategory(category);
		// setId(nbId);
		// nbId ++;
	}
	public Disc(String title, String category, String director, int length, float cost) {
		super();
		this.director = director;
		this.length = length;
		setTitle(title);
		setCost(cost);
		setCategory(category);
		// setId(nbId);	
		// nbId ++;
	}

    public int getLength() {
        return length;
    }
    public String getDirector() {
        return director;
    }

}

