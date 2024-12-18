
package hust.soict.dsai.aims.media;

import java.util.Collections;
import java.util.Comparator;

abstract public class Media implements Comparable<Media> {
    private String title;
	private String category;
	private float cost;
    private int id;
    public static int nbId = 0;

    public Media(String title) {
        this.title = title;
        this.id = nbId;
        nbId++;
    }

    public Media(String title, String category) {
        this.title = title;
        this.category = category;
        this.id = nbId;
        nbId++;
    }

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = nbId;
        nbId++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return getId() + " - " + getTitle() + " - " + getCategory()  + ": " + getCost() + " $";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Media)) {
            return false;
        }
        // Compare the current object with the other Media object
        Media other = (Media) obj;
        // Implement comparison logic here
        return this.getTitle().equals(other.getTitle()) && this.getId() == other.getId();
    }

    @Override
    public int compareTo(Media other) {
        int titleComparison = this.title.compareTo(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        // compare for higher cost if they have the same title
        return Double.compare(other.cost, this.cost);
    }
    
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media(){
        // auto by myself
    }

}