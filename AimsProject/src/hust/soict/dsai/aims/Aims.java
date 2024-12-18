
package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;
import java.util.Scanner;

import javax.naming.LimitExceededException;

public class Aims {
	private static Store store = new Store();
	private static Cart cart = new Cart();

	public static void main(String[] args) throws LimitExceededException, PlayerException {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			showMenu();
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					viewStore(scanner);
					break;
				case 2:
					updateStore(scanner, store);
					break;
				case 3:
					seeCurrentCart(scanner);
					break;
				case 0:
					System.out.println("Exiting AIMS. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice! Please choose a number between 0-3.");
			}
		} while (choice != 0);
		scanner.close();
	}

	public static void showMenu() {
		System.out.println("");
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
	}

	public static void viewStore(Scanner scanner) throws LimitExceededException, PlayerException {
		System.out.println("Store Items:");
		store.displayStore();

		int choice;
		do {
			storeMenu();
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					seeMediaDetails(scanner);
					break;
				case 2:
					addMediaToCart(scanner);
					break;
				case 3:
					playMedia(scanner);
					break;
				case 4:
					seeCurrentCart(scanner);
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid choice! Please choose a number between 0-4.");
			}
		} while (choice != 0);
	}

	public static void storeMenu() {
		System.out.println("");
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. See a media’s details");
		System.out.println("2. Add a media to cart");
		System.out.println("3. Play a media");
		System.out.println("4. See current cart");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
	}

	public static void seeMediaDetails(Scanner scanner) throws LimitExceededException {
		System.out.print("Enter the title of the media: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);

		if (media != null) {
			System.out.println(media.toString());
			mediaDetailsMenu(scanner, media);
		} else {
			System.out.println("Media not found in the store.");
		}
	}

	public static void mediaDetailsMenu(Scanner scanner, Media media) throws LimitExceededException {
		int choice;
		do {
			System.out.println("");
			System.out.println("Options: ");
			System.out.println("--------------------------------");
			System.out.println("1. Add to cart");
			System.out.println("2. Play");
			System.out.println("0. Back");
			System.out.println("--------------------------------");
			System.out.println("Please choose a number: 0-1-2");

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				cart.addMedia(media);
				System.out.println("Media added to cart.");
				break;
			case 2:
				if (media instanceof Playable) {
                    if(media instanceof DigitalVideoDisc){
                        ((DigitalVideoDisc) media).play();
                    }
                    else if(media instanceof CompactDisc){
                        try {
							((CompactDisc)media).play();
						} catch (PlayerException e) {
							// TODO Auto-generated catch block
							e.getMessage();
							e.toString();
							e.printStackTrace();
						}
                    }
				} else {
					System.out.println("This media cannot be played.");
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Invalid choice! Please choose a number between 0-2.");
		}
	} while (choice != 0);
}



	public static void addMediaToCart(Scanner scanner) throws LimitExceededException {
		System.out.print("Enter the title of the media to add to cart: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);

		if (media != null) {
			cart.addMedia(media);
			System.out.println("Media added to cart.");
		} else {
			System.out.println("Media not found in the store.");
		}
	}

	public static void playMedia(Scanner scanner) throws PlayerException {
		System.out.print("Enter the title of the media to play: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);

		if (media != null) {
			if (media instanceof Playable) {
				((Playable) media).play();
			} else {
				System.out.println("This media cannot be played.");
			}
		} else {
			System.out.println("Media not found in the store.");
		}
	}

	public static void updateStore(Scanner scanner, Store store) {
		System.out.println("");
		System.out.println("Update Store Options: ");
		System.out.println("1. Add a media");
		System.out.println("2. Remove a media");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
			case 1:
				addMediaStore(scanner, store);
				break;
			case 2:
				removeMediaStore(scanner, store);
				break;
			default:
				System.out.println("Invalid option!");
		}
	}

	private static void addMediaStore(Scanner scanner, Store store) {
		System.out.println("");
		System.out.println("Choose the type of media to add: ");
		System.out.println("1. Book");
		System.out.println("2. Digital Video Disc");
		System.out.println("3. Compact Disc");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		Media media = null;

		switch (choice) {
			case 1:
				// Adding a Book
				System.out.print("Enter the title of the book: ");
				String bookTitle = scanner.nextLine();
				System.out.print("Enter the category of the book: ");
				String bookCategory = scanner.nextLine();
				System.out.print("Enter the cost of the book: ");
				float bookCost = scanner.nextFloat();
				scanner.nextLine();
				Book book = new Book(bookTitle, bookCategory, bookCost);
				while (true) {
					System.out.print("Do you want to add a author of the book? (yes/no): ");
					String addTrack = scanner.nextLine();
					if (addTrack.equalsIgnoreCase("yes")) {
						System.out.print("Enter author name: ");
						String authorName = scanner.nextLine();
						book.addAuthor(authorName);
					} else {
						break;
					}
				}
				media = book;
				break;
			case 2:
				// Adding a Digital Video Disc
				System.out.print("Enter the title of the DVD: ");
				String dvdTitle = scanner.nextLine();
				System.out.print("Enter the category of the DVD: ");
				String dvdCategory = scanner.nextLine();
				System.out.print("Enter the cost of the DVD: ");
				float dvdCost = scanner.nextFloat();
				scanner.nextLine();
				System.out.print("Enter the director of the DVD: ");
				String director = scanner.nextLine();
				System.out.print("Enter the length of the DVD (in minutes): ");
				int dvdLength = scanner.nextInt();
				scanner.nextLine();
				media = new DigitalVideoDisc(dvdTitle, dvdCategory, director, dvdLength, dvdCost);
				break;
			case 3:
				// Adding a Compact Disc
				System.out.print("Enter the title of the CD: ");
				String cdTitle = scanner.nextLine();
				System.out.print("Enter the category of the CD: ");
				String cdCategory = scanner.nextLine();
				System.out.print("Enter the cost of the CD: ");
				float cdCost = scanner.nextFloat();
				System.out.print("Enter the artist of the CD: ");
				String artist = scanner.nextLine();
				CompactDisc cd = new CompactDisc(cdTitle, cdCategory, artist, cdCost);
				while (true) {
					System.out.print("Do you want to add a track? (yes/no): ");
					String addTrack = scanner.nextLine();
					if (addTrack.equalsIgnoreCase("yes")) {
						System.out.print("Enter track title: ");
						String trackTitle = scanner.nextLine();
						System.out.print("Enter track length: ");
						int trackLength = scanner.nextInt();
						scanner.nextLine();
						cd.addTrack(new Track(trackTitle, trackLength));
					} else {
						break;
					}
				}
				media = cd;
				break;
			default:
				System.out.println("Invalid choice.");
				return; // Exit the method
		}

		if (media != null) {
			store.addMedia(media);
			System.out.println("Media added to the store.");
		}
	}

	private static void removeMediaStore(Scanner scanner, Store store) {
		System.out.println("");
		System.out.print("Enter the title of the media to remove: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);
		if (media != null) {
			store.removeMedia(media.getId());
			System.out.println("Media removed from the store.");
		} else {
			System.out.println("Media not found in the store.");
		}
	}

	public static void seeCurrentCart(Scanner scanner) throws PlayerException {
		cart.printOrders();

		int choice;
		do {
			cartMenu();
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					filterMediasInCart(scanner);
					break;
				case 2:
					sortMediasInCart(scanner);
					break;
				case 3:
					removeMediaFromCart(scanner);
					break;
				case 4:
					playMedia(scanner);
					break;
				case 5:
					placeOrder();
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid choice! Please choose a number between 0-5.");
			}
		} while (choice != 0);
	}

	public static void cartMenu() {
		System.out.println("");
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter medias in cart");
		System.out.println("2. Sort medias in cart");
		System.out.println("3. Remove media from cart");
		System.out.println("4. Play a media");
		System.out.println("5. Place order");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4-5");
	}

	public static void filterMediasInCart(Scanner scanner) {
		System.out.println("");
		System.out.println("Filter by: ");
		System.out.println("1. ID");
		System.out.println("2. Title");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		if (choice == 1) {
			System.out.print("Enter ID to filter: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			cart.searchById(id);
		} else if (choice == 2) {
			System.out.print("Enter title to filter: ");
			String title = scanner.nextLine();
			cart.searchByTitle(title);
		} else {
			System.out.println("Invalid option!");
		}
	}

	public static void sortMediasInCart(Scanner scanner) {
		System.out.println("");
		System.out.println("Sort by: ");
		System.out.println("1. Title");
		System.out.println("2. Cost");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		if (choice == 1) {
			cart.sortByTitleCost();
		} else if (choice == 2) {
			cart.sortByCostTitle();
		} else {
			System.out.println("Invalid option!");
		}
	}

	public static void removeMediaFromCart(Scanner scanner) {
		System.out.print("Enter the title of the media to remove: ");
		String title = scanner.nextLine();
		Media mediaToRemove = cart.takeByTitle(title);
		cart.removeMedia(mediaToRemove);
	}

	public static void placeOrder() {
		System.out.println("Order placed successfully!");
		cart.clear();
	}
}