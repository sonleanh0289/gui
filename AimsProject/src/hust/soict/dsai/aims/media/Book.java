
package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {	

    private List<String> authors = new ArrayList<String>(); 

    public void addAuthor(String authorName){
        for(String s : authors){
            if(s.equals(authorName)){
                System.out.println("The author has been added already");
                return;
            }
        }
        authors.add(authorName);   
        System.out.println("Author added successfully");
    }

    public void removeAuthor(String authorName){
        for(String s : authors){
            if(s.equals(authorName)){
                authors.remove(authors.indexOf(s));
                return;
            }
        }
        System.out.println("The author is not found");
    }


    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Book(String title) {
        super(title);
    }

    public Book(String title, String category) {
        super(title, category);
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public String toString() {
        return getId() + " - " + getTitle() + " - " + getCategory()  + ": " + getCost() + " $";
    }

}
