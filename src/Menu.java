import java.util.Scanner;

public class Menu {
	
	private static enum SUBMENU {
		ZERO,
		ADD_NEW_BOOK,
		ADD_NEW_ONLINE_ARTICLE,
		CREATE_MEMBER_ACCOUNT,
		CHECKOUT_BOOK,
		GIVE_ACCESS_TO_ONLINE_ARTICLE,
		EXIT;
		
		private static SUBMENU[] values = SUBMENU.values();
	}
	
	static Scanner input = new Scanner(System.in);
	
	public static void start() {
		int choice;
		boolean terminate = false;
		while (true) {
			printMenu();
			choice = input.nextInt();
			if (!(0 < choice && choice <= SUBMENU.values.length - 1)) {
				System.out.println("ERR: You've entered invalid choice, try again. (1-6)");
				continue;
			}
			SUBMENU selection = SUBMENU.values[choice];
			switch (selection) {
			case ADD_NEW_BOOK: 
				addNewBook(); 
				break;
			case ADD_NEW_ONLINE_ARTICLE: 
				addNewArticle();
				break;
			case CREATE_MEMBER_ACCOUNT: 
				createAccount();
				break;
			case CHECKOUT_BOOK: 
				checkOut();
				break;
			case GIVE_ACCESS_TO_ONLINE_ARTICLE:
				giveAccess();
				break;
			case EXIT:
				System.out.println("Goodbye!");
				terminate = true;
				break;
			}
			if (terminate) break;
		}
	}
	
	private static boolean isISBNValid(String isbnInput) {
		String part = isbnInput.substring(0, 4);
		if (part.equals("978-")) {
			return true;
		}
		return false;
	}
	
	private static void printMenu() {
		System.out.println(" ===================================================");
		System.out.println("|                 WELCOME TO LIBMAN                 |");
		System.out.println(" ===================================================");
		System.out.println("1. Add a new book");
		System.out.println("2. Add a new online article");
		System.out.println("3. Create a member account");
		System.out.println("4. Check out a book");
		System.out.println("5. Give access to an online article");
		System.out.println("6. Exit");
		System.out.print(">");
	}
	
	private static void addNewBook() {
		if (Book.bookCount == 10) {
			System.out.println("Library is full! Cannot add more than 10 books.");
			return;
		}
		System.out.print("Enter book name:");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter ISBN number:");
		String isbn = input.nextLine();
		if (!isISBNValid(isbn)) {
			System.out.println("ERR: Invalid ISBN number.");
			return;
		}
		Book theBook = new Book(name, isbn);
		addToBookArray(theBook);
	}
	
	private static void addToBookArray(Book theBook) {
		Book.bookArray[Book.bookCount] = theBook;
		Book.bookCount++;
		System.out.println("Book added successfully! (" + Book.bookCount + "/10 books)");
	}
	
	private static void addNewArticle() {
		if (OnlineArticle.articleCount == 10) {
			System.out.println("Library is full! Cannot add more than 10 articles.");
			return;
		}
		System.out.print("Enter article name: ");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter DOI number: ");
		String doi = input.nextLine();
		
		OnlineArticle theArticle = new OnlineArticle(name, doi);
		addToArticleArray(theArticle);
	}
	
	private static void addToArticleArray(OnlineArticle theArticle) {
		OnlineArticle.articleArray[OnlineArticle.articleCount] = theArticle;
		OnlineArticle.articleCount++;
		System.out.println("Online Article added successfully! (" + OnlineArticle.articleCount + "/10 articles)");
	}
	
	private static void createAccount() {
		System.out.print("Enter account name: ");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter account id: ");
		long id = input.nextLong();
		
		RegularMember theReader = new RegularMember(name, id);
		
		RegularMember.readerArray[RegularMember.readerCount] = theReader;
		RegularMember.readerCount++;
		System.out.println("New user successfully created.");
	}
	
	private static void checkOut() {
		if (Book.bookCount == 0) {
			System.out.println("WARN: There is no books in the library, add some and try again.");
			waitForKey();
			return;
		}
		System.out.print("Enter your id: ");
		long id = input.nextLong();
		for (RegularMember reader : RegularMember.readerArray) {
			if (reader == null) continue;
			if (id == reader.getId()) {
				System.out.print("Welcome " + reader.getReaderName() + ", enter the ISBN of of the book you would like to check out: ");
				input.nextLine(); // bugfix
				String isbn = input.nextLine();
				System.out.println("traversing books...");
				for (Book book : Book.bookArray) {
					if (book == null) continue;
					if (book.getBookISBN().equals(isbn)) {
						System.out.println("Book found with name: " + book.getBookName());
						int day, month, year;
						System.out.print("Enter due year (YYYY): ");
						year = input.nextInt();
						System.out.print("Enter due month (MM): ");
						month = input.nextInt();
						System.out.print("Enter due day (DD): ");
						day = input.nextInt();
						if (Date.isDateValid(day, month, year)) {
							Date dueDate = new Date(day, month, year);
							book = new Book(book.getBookName(), book.getBookISBN(), dueDate);
							reader.setCheckedOutBook(book);
							System.out.println("The book with name '" + book.getBookName() + "' (ISBN#:" + book.getBookISBN() + ") is checked out by user " + reader.getReaderName() + ".");
							return;
						}
						System.out.println("ERR: Invalid date.");
						return;
					}
				}
				System.out.println("ERR: There is no book with the ISBN#:" + isbn);
				return;
			}
		}
		System.out.println("ERR: There is no user with the ID:" + id);
		return;
	}
	
	private static void giveAccess() {
		if (OnlineArticle.articleCount == 0) {
			System.out.println("WARN: There is no articles in the library, add some and try again.");
			waitForKey();
			return;
		}
		System.out.print("Enter your id: ");
		long id = input.nextLong();
		for (RegularMember reader : RegularMember.readerArray) {
			if (reader == null) continue;
			if (id == reader.getId()) {
				System.out.print("Welcome " + reader.getReaderName() + ", enter the DOI of of the article you would like to access: ");
				input.nextLine(); // bugfix
				String doi = input.nextLine();
				System.out.println("traversing articles...");
				for (OnlineArticle article : OnlineArticle.articleArray) {
					if (article == null) continue;
					if (article.getArticleDOI().equals(doi)) {
						System.out.println("Article found with name: " + article.getArticleName());
						reader.setAccessedArticle(article);
						System.out.println("The article with name '" + article.getArticleName() + "' (DOI#:" + article.getArticleDOI() + ") is accessed by user " + reader.getReaderName() + ".");
						return;
					}
				}
				System.out.println("ERR: There is no article with the DOI#:" + doi);
				return;
			}
		}
		System.out.println("ERR: There is no user with the ID:" + id);
		return;
	}
	
	private static void waitForKey() {
		System.out.println("Hit enter to go back to main menu.");
		input.nextLine(); 
        input.nextLine();
	}
}