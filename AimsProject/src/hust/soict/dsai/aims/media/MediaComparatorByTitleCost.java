
package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media>{
    @Override
    public int compare(Media m1, Media m2) {
        // Compare by title alphabetically
        int titleComparison = m1.getTitle().compareTo(m2.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }
        // If titles are the same, compare by cost (descending)
        return Double.compare(m2.getCost(), m1.getCost());
    }
}