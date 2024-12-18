

package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore; 

    public Store() {
        itemsInStore = new ArrayList<>();
    }
    public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}

    public void addMedia(Media media) {
        for (Media m : itemsInStore){
            if (media.equals(m)) {
                System.out.println("The media has been added already");
                return;
            } 
        }
        itemsInStore.add(media);
        System.out.println("Media added successfully");
    }

    public void removeMedia(int id) {
        boolean found = false;
        for (Media media : itemsInStore) {
            if (media.getId() == id) { 
                found = true;
                itemsInStore.remove(media);
                System.out.println("Removed Media with ID: " + id);
                break;
            }
        }
        if (!found) {
            System.out.println("Media with ID " + id + " not found in the store.");
        }
    }

    public void displayStore() {
        System.out.println("Media currently in the store:");
            for (Media media : itemsInStore) {
                System.out.println(media.toString());
            }
    }

    public Media searchById(int id) {
		boolean found = false;
		for (int i = 0; i < itemsInStore.size(); i++) {
			if (itemsInStore.get(i).getId() == id) {
				System.out.println("Media found: " + itemsInStore.get(i).toString());
				found = true;
				return itemsInStore.get(i);
			}
		}

		if (!found) {
			System.out.println("No Media found with ID: " + id);
		}
        return null;
	}

	public Media searchByTitle(String title) {
		boolean found = false;
		for (int i = 0; i < itemsInStore.size(); i++) {
			if (itemsInStore.get(i).getTitle().equals(title)) {
				System.out.println("Media found: " + itemsInStore.get(i).toString());
				found = true;
				return itemsInStore.get(i);
			}
		}

		if (!found) {
			System.out.println("No Media found with title: " + title);
		}
        return null;
	}
}